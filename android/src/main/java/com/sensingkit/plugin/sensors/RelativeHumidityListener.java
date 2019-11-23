package com.sensingkit.plugin.sensors;

import com.sensingkit.plugin.SensingKit;

public class RelativeHumidityListener extends AbstractEnvironmentSensorListener {

    public RelativeHumidityListener(SensingKit kit){ super(kit);}

    @Override
    protected String getSensorType(){
        return SensorNameResolver.NAME_RELATIVE_HUMIDITY;
    }

}
