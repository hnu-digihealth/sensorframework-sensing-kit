## Availability

The web implementation of this plugin uses the [Generic Sensor API](https://developer.mozilla.org/en-US/docs/Web/API/Sensor_APIs)
which is not implemented in many Browsers by now. 

| SensorType | Web | Android | iOS |
| --- | :---: | :---: | :---: |
| AMBIENT_LIGHT | <ul><li> [x] * </li></ul> | <ul><li> [x] </li></ul>  | |
| AMBIENT_PRESSURE |  | <ul><li> [x] </li></ul> | |
| RELATIVE_HUMIDITY | | <ul><li> [x] </li></ul> | |
| ACCELEROMETER | <ul><li> [x] * </li></ul> | <ul><li> [x] </li></ul> | |
| GRAVITY | | <ul><li> [x] </li></ul> | |
| GYROSCOPE | <ul><li> [x] * </li></ul> | <ul><li> [x] </li></ul> | |
| LINEAR_ACCELERATION | <ul><li> [x] * </li></ul> | <ul><li> [x] </li></ul> | |
| ROTATION_VECTOR | | <ul><li> [x] </li></ul> | |
| GAME_ROTATION_VECTOR | | <ul><li> [x] </li></ul> | |
| GEOMAGNETIC_ROTATION_VECTOR | | <ul><li> [x] </li></ul> | |
| MAGENTIC_FIELD | <ul><li> [x] * </li></ul> | <ul><li> [x] </li></ul> | |
| PROXIMITY | | <ul><li> [x] </li></ul> | |
| ABSOLUTE_ORIENTATION | <ul><li> [x] * </li></ul> | | |
| RELATIVE_ORIENTATION | <ul><li> [x] * </li></ul> | | | 

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
