## Availability

| SensorType | Web | Android | iOS |
| --- | --- | --- | --- |
| AMBIENT_LIGHT | - [x] * | - [x] | |
| AMBIENT_PRESSURE |  | - [x] | |
| RELATIVE_HUMIDITY | | - [x] | |
| ACCELEROMETER | - [x] * | - [x] | |
| GRAVITY | | - [x] | |
| GYROSCOPE | - [x] * | - [x] | |
| LINEAR_ACCELERATION | - [x] * | - [x] | |
| ROTATION_VECTOR | | - [x] | |
| GAME_ROTATION_VECTOR | | - [x] | |
| GEOMAGNETIC_ROTATION_VECTOR | | - [x] | |
| MAGENTIC_FIELD | - [x] * | - [x] | |
| PROXIMITY | | - [x] | |
| ABSOLUTE_ORIENTATION | - [x] * | | |
| RELATIVE_ORIENTATION | - [x] * | | | 

\* requires `chrome://flags/#enable-generic-sensor-extra-classes` to be enabled


## Usage

Below, an example of working with a mobile device's ambient light sensor

```typescript
import "cap-sensing-kit";
import {Plugins} from "@capacitor/core";
import {SensorType, sensorChangedEventName} from "cap-sensing-kit";
const {SensingKit} = Plugins;

const name = SensorType.AMBIENT_LIGHT; 

const {isAvailable} = await SensingKit.isSensorAvailable({name});
const {isActive} = await SensingKit.isSensorActive({name});

if(isAvailable && !isActive){
    await SensingKit.start({name});
}

const event = sensorChangedEventName(name);
const listener = SensingKit.addListener(event, (data) => {

    //Do something ... for example:
    const {value} = data;
    console.log(`Ambient light level changed...illuminance is ${value} lx`);
};

//...

await SensingKit.stop({name});
listener.remove()
```

## API Documentation
