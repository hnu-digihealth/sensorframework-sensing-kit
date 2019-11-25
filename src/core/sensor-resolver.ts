import {
    Accelerometer,
    AmbientLightSensor,
    Gyroscope, Magnetometer,
    OrientationSensor,
    Sensor,
    SensorOptions
} from "./sensor-web-definitions";
import {SensorType} from "./sensor-type.enum";
import {SensorPermission} from "./sensor-permission.enum";

export const SensorNameResolver: {[name: string]: {class: {new (options: SensorOptions): Sensor}, permissions: string[], maxFrequency: number, getValue: (sensor: Sensor) => any}} = {
    [SensorType.AMBIENT_LIGHT]: {
        class: (window as any).AmbientLightSensor,
        permissions: [SensorPermission.AMBIENT_LIGHT],
        maxFrequency: 10,
        getValue: (sensor: AmbientLightSensor) => {
            const value = sensor.illuminance;
            return {value}
        }
    },
    [SensorType.ABSOLUTE_ORIENTATION]: {
        class: (window as any).AbsoluteOrientationSensor,
        maxFrequency: 60,
        permissions: [SensorPermission.ACCELEROMETER, SensorPermission.GYROSCOPE, SensorPermission.MAGNETOMETER],
        getValue: (sensor: OrientationSensor) => {
            const x = sensor.quaternion[0];
            const y = sensor.quaternion[1];
            const z= sensor.quaternion[2];
            const w = sensor.quaternion[3];

            return {x,y,z,w};
        }
    },
    [SensorType.ACCELEROMETER]: {
        class: (window as any). Accelerometer,
        permissions: [SensorPermission.ACCELEROMETER],
        maxFrequency: 60,
        getValue: (sensor: Accelerometer) => {
            const x = sensor.x;
            const y = sensor.y;
            const z = sensor.z;
            return {x,y,z};
        }
    },
    [SensorType.GYROSCOPE]:{
        class: (window as any).Gyroscope,
        permissions: [SensorPermission.GYROSCOPE],
        maxFrequency: 60,
        getValue: (sensor: Gyroscope) => {
            const x = sensor.x;
            const y = sensor.y;
            const z = sensor.z;
            return {x,y,z};
        }
    },
    [SensorType.LINEAR_ACCELERATION]: {
        class: (window as any).LinearAccelerationSensor,
        permissions: [SensorPermission.ACCELEROMETER],
        maxFrequency: 60,
        getValue: (sensor: Accelerometer) => {
            const x = sensor.x;
            const y = sensor.y;
            const z = sensor.z;
            return {x,y,z};
        }
    },
    [SensorType.MAGNETIC_FIELD]: {
        class: (window as any).Magnetometer,
        permissions: [SensorPermission.MAGNETOMETER],
        maxFrequency: 10,
        getValue: (sensor: Magnetometer) => {
            const x = sensor.x;
            const y = sensor.y;
            const z = sensor.z;
            return {x,y,z};
        }
    },
    [SensorType.RELATIVE_ORIENTATION]: {
        class: (window as any).RelativeOrientationSensor,
        permissions: [SensorPermission.ACCELEROMETER, SensorPermission.GYROSCOPE],
        maxFrequency: 60,
        getValue: ( sensor: OrientationSensor) => {
            const x = sensor.quaternion[0];
            const y = sensor.quaternion[1];
            const z= sensor.quaternion[2];
            const w = sensor.quaternion[3];

            return {x,y,z,w};
        }
    }
};
