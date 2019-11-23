package com.sensingkit.plugin.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public abstract class AbstractSensorListener implements SensorEventListener {

    protected SensingKit kit;

    private static final String eventAccuracyChanged = "AccuracyChanged";
    private static final String eventSensorChanged = "SensorChanged";
    private static final String keyAccuracy = "accuracy";

    public AbstractSensorListener(SensingKit kit){
        this.kit = kit;
    }

    protected abstract String getSensorType();

    protected abstract JSObject toJSON(float[] values);

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){
        JSObject data = new JSObject();
        data.put(keyAccuracy, accuracy);
        kit.publish(getSensorType().concat(eventAccuracyChanged), data);
    }

    @Override
    public final void onSensorChanged(SensorEvent event){
        JSObject data = toJSON(event.values);
        kit.publish(getSensorType().concat(eventSensorChanged), data);
    }

}
