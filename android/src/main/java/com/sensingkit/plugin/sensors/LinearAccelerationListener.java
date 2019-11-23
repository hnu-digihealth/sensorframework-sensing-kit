package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class LinearAccelerationListener extends AbstractSensorListener {

    public LinearAccelerationListener(SensingKit kit) {
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_LINEAR_ACCELERATION;
    }

    @Override
    protected JSObject toJSON(float[] values) {

        JSObject ret = new JSObject();
        ret.put("x", values[0]);
        ret.put("y", values[1]);
        ret.put("z", values[2]);

        return ret;
    }
}
