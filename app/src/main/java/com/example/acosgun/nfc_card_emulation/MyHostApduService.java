package com.example.acosgun.nfc_card_emulation;

import android.content.SharedPreferences;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by acosgun on 3/9/15.
 */
public class MyHostApduService extends HostApduService {

    private static final String TAG = "MyHCEService";
    public static final String PREFS_NAME = "MyPrefsFile";

    String human_name;
    int floor_num;

    @Override
    public byte[] processCommandApdu(byte[] apdu, Bundle extras) {
        Log.i(TAG, "processCommandApdu");



        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        human_name= settings.getString("name", "Guest");
        floor_num=settings.getInt("floor", 0);
        if (human_name == null || human_name.isEmpty()) {
            human_name = "Guest";
        }
        if(floor_num <= 0 || floor_num >=127)
        {
            Toast toast = Toast.makeText(this, "Please enter a valid floor number..", Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }

        byte[] arr=human_name.getBytes();

        byte[] res = new byte[3+arr.length];
        res[0]=(byte) floor_num;
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
