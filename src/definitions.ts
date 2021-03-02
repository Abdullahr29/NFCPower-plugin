declare module '@capacitor/core' {
  interface PluginRegistry {
    NFCPower: NFCPowerPlugin;
  }
}

export interface NFCPowerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  noNFC(message: string): Promise<{result: string}>;
}
