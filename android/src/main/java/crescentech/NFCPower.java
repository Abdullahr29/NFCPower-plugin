package crescentech;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;


import android.nfc.FormatException;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.NdefMessage;
import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;

@NativePlugin
public class NFCPower extends Plugin {

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);

        new Thread(new Runnable() {
            private Tag tag;

            public void run() {
                Ndef ndef = Ndef.get(tag);

                try {
                    while (true) {
                        try {
                            Thread.sleep(30000);

                            ndef.connect();
                            NdefMessage msg = ndef.getNdefMessage();

                            // TODO: do something

                        } catch (IOException e) {
                            // if the tag is gone we might want to end the thread:
                            break;
                        } finally {
                            try {
                                ndef.close();
                            } catch (Exception e) {}
                        }
                    }
                } catch (InterruptedException | FormatException e) {
                }
            }
        }).start();
    }

    //https://stackoverflow.com/questions/37045486/continuously-detect-an-nfc-tag-on-android

}
