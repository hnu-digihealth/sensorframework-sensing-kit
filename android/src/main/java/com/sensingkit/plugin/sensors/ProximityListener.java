package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class ProximityListener extends AbstractSensorListener {
    public ProximityListener(SensingKit kit) {
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_PROXIMITY;
    }

    @Override
    protected JSObject toJSON(final float[] values) {

        JSObject ret = new JSObject(){{
            put("value", values[0]);
        }};

        return ret;
    }
}
