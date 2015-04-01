package com.example.acosgun.nfc_card_emulation;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by acosgun on 3/9/15.
 */
public class MyHostApduService extends HostApduService {

    private static final String TAG = "MyHCEService";
    String human_name = "Akansel";
    int floor_num = 7;

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        Log.i(TAG, "processCommandApdu");



        //byte[] arr= HexStringToByteArray(human_name);
        byte[] arr=human_name.getBytes();

        byte[] res = new byte[3+arr.length];
        res[0]=(byte) 7;
        for(int i=0; i<arr.length; i++)
        {
         res[i+1]=arr[i];
        }
        res[arr.length+1]=(byte) 0x90;
        res[arr.length+2]=(byte) 0x00;
        return res;
    }
    @Override
    public void onDeactivated(int reason) {
        Log.i(TAG, "OnDeactivated - reason : " + String.valueOf(reason));
        return;
    }
}
