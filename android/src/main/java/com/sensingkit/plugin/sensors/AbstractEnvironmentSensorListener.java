package com.sensingkit.plugin.sensors;
import android.hardware.SensorEventListener;

import com.getcapacitor.JSObject;
import com.sensingkit.plugin.SensingKit;

public abstract class AbstractEnvironmentSensorListener extends AbstractSensorListener{

    public AbstractEnvironmentSensorListener(SensingKit kit){
        super(kit);
    }

    @Override
    protected JSObject toJSON(float[] values){

        JSObject data = new JSObject();
        data.put(keyValue, values[0]);

        return data;
    }

}
