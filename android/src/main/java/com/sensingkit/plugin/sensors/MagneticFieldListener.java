package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class MagneticFieldListener extends AbstractSensorListener {
    public MagneticFieldListener(SensingKit kit) {
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_MAGNETIC_FIELD;
    }

    @Override
    protected JSObject toJSON(final float[] values) {

        JSObject ret = new JSObject(){{
           put("x", values[0]);
           put("y", values[1]);
           put("z", values[2]);
        }};

        return ret;
    }
}
