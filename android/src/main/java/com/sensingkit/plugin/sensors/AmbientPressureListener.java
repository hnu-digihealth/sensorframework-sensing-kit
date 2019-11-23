package com.sensingkit.plugin.sensors;

import com.sensingkit.plugin.SensingKit;

public class AmbientPressureListener extends AbstractEnvironmentSensorListener {

    public AmbientPressureListener(SensingKit kit){
        super(kit);
    }

    @Override
    protected String getSensorType(){return SensorNameResolver.NAME_AMBIENT_PRESSURE;}

}
