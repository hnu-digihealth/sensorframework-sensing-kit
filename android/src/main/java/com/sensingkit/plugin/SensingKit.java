package com.sensingkit.plugin;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.sensingkit.plugin.sensors.AbstractSensorListener;
import com.sensingkit.plugin.sensors.SensorNameResolver;

import java.lang.reflect.Constructor;
import java.util.HashMap;

@NativePlugin()
public class SensingKit extends Plugin {

    private static final String keySensorName = "name";
    private static final String keyIsActive = "isActive";
    private static final String keyDelay = "frequency";
    private static final String keyIsAvailable = "isAvailable";

    private static final String propertySensor = "sensor";
    private static final String propertyListener = "listener";
    private static final String propertyDelay = "delay";

    private HashMap<String, Object> runningSensors = new HashMap<String, Object>();
    private SensorNameResolver resolver = new SensorNameResolver();
    private SensorManager sensorManager;


    @PluginMethod()
    public void isSensorAvailable(PluginCall call){

        String sensorName = call.getString(keySensorName);

        if(sensorName == null){
            call.reject("Missing property 'name'");
            return;
        }

        Integer sensorType = resolver.getSensorType(sensorName);

        if(sensorType == null){
            JSObject ret = new JSObject();
            ret.put(keyIsAvailable, false);
            call.resolve(ret);
            return;
        }

        Sensor sensor = sensorManager.getDefaultSensor(sensorType);

        boolean isAvailable = sensor != null ? true : false;

        JSObject ret = new JSObject();
        ret.put(keyIsAvailable, isAvailable);

        call.resolve(ret);


    }

    @PluginMethod()
    public void isSensorActive(PluginCall call){

        String sensorName = call.getString(keySensorName);

        if(sensorName == null){
            call.reject("Missing property 'name'");
            return;
        }

        Boolean active = isActive(sensorName);

        JSObject ret = new JSObject();
        ret.put(keyIsActive, active);

        call.resolve(ret);

    }

    @PluginMethod()
    public void start(PluginCall call){

        String sensorName = call.getString(keySensorName);

        if(sensorName == null){
            call.reject("Missing property 'name'");
            return;
        }

        if(isActive(sensorName)){
            call.resolve();
            return;
        }

        Integer delayProp = call.getInt(keyDelay);

        if(delayProp != null && delayProp != 0){
            //Convert frequncy to microseconds
            delayProp = (int) Math.floor((1/delayProp)*1000);
        }

        final Integer delay = delayProp != null ? delayProp : SensorManager.SENSOR_DELAY_NORMAL;


        final AbstractSensorListener listener = createListener(sensorName);
        Integer sensorType = resolver.getSensorType(sensorName);

        if(listener != null && sensorType != null){
            final Sensor sensor = sensorManager.getDefaultSensor(sensorType);
            boolean registered = sensorManager.registerListener(listener, sensor, delay);

            if(registered){
                this.runningSensors.put(sensorName, new HashMap<String, Object>(){{
                    put(propertySensor, sensor);
                    put(propertyListener, listener);
                    put(propertyDelay, delay);
                }});
                call.resolve();
                return;
            }

            call.error("Unable to register Listener for " + sensorName + "Sensor");
            return;
        }



    }

    @PluginMethod()
    public void stop(PluginCall call){

        String sensorName = call.getString(keySensorName);

        if(!isActive(sensorName)){
            call.resolve();
            return;
        }

        HashMap<String, Object> sensorRef = (HashMap<String, Object>) runningSensors.get(sensorName);

        Sensor sensor = (Sensor) sensorRef.get(propertySensor);
        AbstractSensorListener listener = (AbstractSensorListener) sensorRef.get(propertyListener);

        sensorManager.unregisterListener(listener, sensor);
        runningSensors.remove(sensorName);

        call.resolve();

    }

    public void publish(String event, JSObject data){
        notifyListeners(event, data);
    }

    private boolean isActive(String sensorName){
        return runningSensors.get(sensorName) != null ? true : false;
    }

    private AbstractSensorListener createListener(String name){

        try {
            Class listenerClass = resolver.getListenerClass(name);
            Constructor<?> cons = listenerClass.getConstructor(SensingKit.class);
            AbstractSensorListener sensorListener = (AbstractSensorListener) cons.newInstance(this);
            return sensorListener;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void handleOnStart(){
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void handleOnResume(){

        for(Object value: runningSensors.values()){

            Sensor sensor = (Sensor) ((HashMap) value).get(propertySensor);
            AbstractSensorListener listener = (AbstractSensorListener) ((HashMap) value).get(propertyListener);
            int sensorDelay = (Integer) ((HashMap) value).get(propertyDelay);

            sensorManager.registerListener(listener, sensor, sensorDelay);
        }

    }

    @Override
    public void handleOnPause(){

        for(Object value: runningSensors.values()){

            Sensor sensor = (Sensor) ((HashMap) value).get(propertySensor);
            AbstractSensorListener listener = (AbstractSensorListener) ((HashMap) value).get(propertyListener);

            sensorManager.unregisterListener(listener, sensor);
        }

    }


}
