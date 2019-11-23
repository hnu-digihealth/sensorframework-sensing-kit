declare module "@capacitor/core" {
  interface PluginRegistry {
    SensingKit: SensingKitPlugin;
  }
}

export interface SensingKitPlugin {

  isSensorAvailable(options: {name: string}): Promise<{isAvailable: boolean}>;

  isSensorActive(options: {name: string}): Promise<{isActive: boolean}>;

  start(options: {name: string, frequency?: number}): Promise<void>;

  stop(options: {name: string}): Promise<void>;

}

export const sensorChangedEventName = (sensorName: string) => `${sensorName}SensorChanged`;
export const accuracyChangedEventName = (sensorName: string) => `${sensorName}AccuracyChanged`;







