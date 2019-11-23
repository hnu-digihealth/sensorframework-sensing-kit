export type SensorOptions = { frequency: number };

export interface Sensor extends EventTarget {

    readonly activated: boolean;

    readonly hasReading: boolean;

    readonly timestamp: DOMHighResTimeStamp;

    start(): void;

    stop(): void;

    onreading: (...args: any[]) => void;

    onactivate: (...args: any[]) => void;

    onerror: (...args: any[]) => void;

}

export interface OrientationSensor extends Sensor{
    readonly quaternion: number[];
}

export interface AmbientLightSensor extends Sensor {
    readonly illuminance: number;
}

export interface Accelerometer extends Sensor{
    readonly x: number;
    readonly y: number;
    readonly z: number;
}

export interface Gyroscope extends Sensor{
    readonly x: number;
    readonly y: number;
    readonly z: number;
}

export interface Magnetometer extends Sensor{
    readonly x: number;
    readonly y: number;
    readonly z: number;
}
