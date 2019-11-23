package com.sensingkit.plugin.sensors;

import android.hardware.Sensor;

import java.util.HashMap;

public class SensorNameResolver extends HashMap<String, SensorNameResolverEntry> {

    public static final String NAME_AMBIENT_LIGHT ="ambientLight";
    public static final String NAME_AMBIENT_TEMPERATURE ="ambientTemperature";
    public static final String NAME_AMBIENT_PRESSURE ="ambientPressure";
    public static final String NAME_RELATIVE_HUMIDITY ="relativeHumidity";
    public static final String NAME_ACCELEROMETER ="accelerometer";
    public static final String NAME_GRAVITY ="gravity";
    public static final String NAME_GYROSCOPE ="gyroscope";
    public static final String NAME_LINEAR_ACCELERATION ="linearAcceleration";
    public static final String NAME_ROTATION_VECTOR ="rotationVector";
    public static final String NAME_GAME_ROTATION_VECTOR ="gameRotationVector";
    public static final String NAME_GEOMAGNETIC_ROTATION_VECTOR ="geomagneticRotationVector";
    public static final String NAME_MAGNETIC_FIELD ="magneticField";
    public static final String NAME_PROXIMITY ="proximity";




    public SensorNameResolver(){
        super();
        put(NAME_AMBIENT_LIGHT, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_LIGHT);
            put(SensorNameResolverEntry.keyListenerClass, AmbientLightListener.class);
        }});
        put(NAME_AMBIENT_TEMPERATURE, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_AMBIENT_TEMPERATURE);
            put(SensorNameResolverEntry.keyListenerClass, AmbientTemperatureListener.class);
        }});
        put(NAME_AMBIENT_PRESSURE, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_PRESSURE);
            put(SensorNameResolverEntry.keyListenerClass, AmbientPressureListener.class);
        }});
        put(NAME_RELATIVE_HUMIDITY, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_RELATIVE_HUMIDITY);
            put(SensorNameResolverEntry.keyListenerClass, RelativeHumidityListener.class);
        }});
        put(NAME_ACCELEROMETER, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_ACCELEROMETER);
            put(SensorNameResolverEntry.keyListenerClass, AccelerometerListener.class);
        }});
        put(NAME_GRAVITY, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_GRAVITY);
            put(SensorNameResolverEntry.keyListenerClass, GravityListener.class);
        }});
        put(NAME_GYROSCOPE, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_GYROSCOPE);
            put(SensorNameResolverEntry.keyListenerClass, GyroscopeListener.class);
        }});
        put(NAME_LINEAR_ACCELERATION, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_LINEAR_ACCELERATION);
            put(SensorNameResolverEntry.keyListenerClass, LinearAccelerationListener.class);
        }});
        put(NAME_ROTATION_VECTOR, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_ROTATION_VECTOR);
            put(SensorNameResolverEntry.keyListenerClass, RotationVectorListener.class);
        }});
        put(NAME_GAME_ROTATION_VECTOR, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_GAME_ROTATION_VECTOR);
            put(SensorNameResolverEntry.keyListenerClass, GameRotationVectorListener.class);
        }});
        put(NAME_GEOMAGNETIC_ROTATION_VECTOR, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
            put(SensorNameResolverEntry.keyListenerClass, GeomagneticRotationVectorListener.class);
        }});
        put(NAME_MAGNETIC_FIELD, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_MAGNETIC_FIELD);
            put(SensorNameResolverEntry.keyListenerClass, MagneticFieldListener.class);
        }});
        put(NAME_PROXIMITY, new SensorNameResolverEntry(){{
            put(SensorNameResolverEntry.keySensorType, Sensor.TYPE_PROXIMITY);
            put(SensorNameResolverEntry.keyListenerClass, ProximityListener.class);
        }});
    }


    public Class getListenerClass(String name){

        SensorNameResolverEntry entry = this.get(name);

        if(entry != null){
            return entry.getListenerClass();
        }

        return null;
    }

    public Integer getSensorType(String name){

        SensorNameResolverEntry entry = this.get(name);

        if(entry != null){
            return entry.getSensorType();
        }

        return null;
    }


}
