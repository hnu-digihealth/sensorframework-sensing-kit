package com.sensingkit.plugin.sensors;

import com.sensingkit.plugin.SensingKit;

public class AmbientLightListener extends AbstractEnvironmentSensorListener {

    public AmbientLightListener(SensingKit kit){
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_AMBIENT_LIGHT;
    }
}
