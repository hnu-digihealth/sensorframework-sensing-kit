package com.sensingkit.plugin.sensors;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public class GravityListener extends AbstractSensorListener {

    public GravityListener(SensingKit kit){super(kit);}

    @Override
    protected String getSensorType(){return SensorNameResolver.NAME_GRAVITY;}

    @Override
    protected JSObject toJSON(float[] values){

        JSObject ret = new JSObject();
        ret.put("x", values[0]);
        ret.put("y", values[1]);
        ret.put("z", values[2]);

        return ret;
    }

}
