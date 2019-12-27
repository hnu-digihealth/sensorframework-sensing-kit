import { WebPlugin } from '@capacitor/core';
import { SensingKitPlugin } from './definitions';
import {SensorNameResolver} from "./core/sensor-resolver";
import {Sensor} from "./core/sensor-web-definitions";
import {sensorChangedEventName, sensorErrorEventName} from "./definitions";

export class SensingKitWeb extends WebPlugin implements SensingKitPlugin {

  private runningSensors: Map<string, Sensor> = new Map<string, Sensor>();

  constructor() {
    super({
      name: 'SensingKit',
      platforms: ['web']
    });
  }

  async isSensorAvailable(options: {name: string}){

    const resolverEntry = SensorNameResolver[options.name];

    if( !resolverEntry ){
      return {isAvailable: false};
    }

    if(!resolverEntry["class"]) {
      return {isAvailable: false};
    }

    return {isAvailable: true};
  }

  async isSensorActive(options: {name: string}){

    const sensor = this.runningSensors.get(options.name);

    if(!sensor){
      return {isActive: false};
    }

    const isActive = sensor.activated;

    return {isActive};
  }

  async start(options: {name:string, frequency?: number}){

    const {name, frequency} = options;

    const {isAvailable} = await this.isSensorAvailable({name});

    if(!isAvailable){
      throw new Error(`${name}: Sensor not available`);
    }

    const {isActive} = await this.isSensorActive({name});

    if(isActive){
      return ;
    }

    const sensorClass = SensorNameResolver[name].class;
    const {permissions, maxFrequency} = SensorNameResolver[name];

    const hasRequiredPermissions = await this.checkPermissions(permissions);

    if(!hasRequiredPermissions){
      throw new Error(`${name}: Missing one of the following permissions ${permissions.join(" , ")}`)
    }

    const useFrequency = (frequency != undefined) ? (frequency < maxFrequency) ? frequency : maxFrequency : maxFrequency;

    const sensor = new sensorClass({frequency: useFrequency});

    sensor.onreading = () => {
      const {getValue} = SensorNameResolver[name];
      const data = getValue(sensor);

      this.notifyListeners(sensorChangedEventName(name), data);
    };

    sensor.onerror = (error) => {
      this.notifyListeners(sensorErrorEventName(name), error);
    };

    sensor.onactivate = () => {
      this.runningSensors.set(name, sensor);
    };

    sensor.start();
  }

  async stop(options: {name:string}){
    const {name} = options;

    const {isActive} = await this.isSensorActive({name});

    if(!isActive){
      return ;
    }

    const sensor = this.runningSensors.get(name);
    sensor.stop();
    this.runningSensors.delete(name);
  }


  private async checkPermissions(permissions: string[]): Promise<boolean>{

    const states = await Promise.all(permissions.map((permission) => {
      return navigator.permissions.query({name: (permission as PermissionName)});
    }));

    return states.every((permission) => permission.state === "granted");
  }

}

const SensingKit = new SensingKitWeb();

export { SensingKit };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(SensingKit);
