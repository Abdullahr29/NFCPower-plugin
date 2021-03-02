import { WebPlugin } from '@capacitor/core';
import { NFCPowerPlugin } from './definitions';

export class NFCPowerWeb extends WebPlugin implements NFCPowerPlugin {
  constructor() {
    super({
      name: 'NFCPower',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

const NFCPower = new NFCPowerWeb();

export { NFCPower };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(NFCPower);
