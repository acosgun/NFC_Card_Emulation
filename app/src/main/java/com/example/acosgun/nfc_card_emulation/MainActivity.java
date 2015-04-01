package com.example.acosgun.nfc_card_emulation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView1);

        Context context = this;
        PackageManager pm = context.getPackageManager();
        boolean hasNfcHce = pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);

        if(!hasNfcHce)
        {
            textView.setText("DON'T HAVE HCE!");
            Log.i(TAG, "DON'T HAVE HCE!");
        }
        else{
            Log.i(TAG, "HCE AVAILABLE");
            textView.setText("HCE AVAILABLE");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
