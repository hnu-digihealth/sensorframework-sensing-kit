package com.sensingkit.plugin.sensors;
import java.util.HashMap;

public class SensorNameResolverEntry extends HashMap<String, Object> {

    public static final String keySensorType = "sensor";
    public static final String keyListenerClass = "listener";

    public Integer getSensorType(){
        return (Integer) this.get(keySensorType);
    }

    public Class getListenerClass(){
        return (Class) this.get(keyListenerClass);
    }

}
