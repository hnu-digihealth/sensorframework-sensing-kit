package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class GeomagneticRotationVectorListener extends AbstractSensorListener {
    public GeomagneticRotationVectorListener(SensingKit kit) {
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_GEOMAGNETIC_ROTATION_VECTOR;
    }

    @Override
    protected JSObject toJSON(final float[] values) {

        JSObject ret = new JSObject(){{
            put(keyX, values[0]);
            put(keyY, values[1]);
            put(keyZ, values[2]);
        }};

        return ret;
    }
}
