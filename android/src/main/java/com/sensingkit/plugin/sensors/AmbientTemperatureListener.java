package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class AmbientTemperatureListener extends AbstractEnvironmentSensorListener {


    public AmbientTemperatureListener(SensingKit kit){
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_AMBIENT_TEMPERATURE;
    }
}
