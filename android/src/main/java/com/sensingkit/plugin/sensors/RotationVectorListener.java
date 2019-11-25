package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class RotationVectorListener extends AbstractSensorListener {
    public RotationVectorListener(SensingKit kit) {
        super(kit);
    }

    @Override
    protected String getSensorType() {
        return SensorNameResolver.NAME_ROTATION_VECTOR;
    }

    @Override
    protected JSObject toJSON(float[] values) {

        JSObject ret = new JSObject();
        ret.put(keyX, values[0]);
        ret.put(keyY, values[1]);
        ret.put(keyZ, values[2]);

        if(values.length == 4){
            ret.put(keyW, values[3]);
        }

        return ret;
    }
}
