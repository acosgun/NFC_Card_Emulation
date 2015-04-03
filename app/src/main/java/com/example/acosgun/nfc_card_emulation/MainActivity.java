package com.example.acosgun.nfc_card_emulation;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    public static final String PREFS_NAME = "MyPrefsFile";
    TextView textView;
    EditText floorEditText;
    EditText nameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        PackageManager pm = context.getPackageManager();
        boolean hasNfcHce = pm.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);

        textView = (TextView) findViewById(R.id.textView1);
        floorEditText = (EditText) findViewById(R.id.floorEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String human_name= settings.getString("name", "Guest");
        int floor_num=settings.getInt("floor", 0);

        floorEditText.setText(floor_num+"");
        nameEditText.setText(human_name);
        textView.setText("TAP THE PHONE TO BACK OF THE TABLET TO CALL ELEVATOR");

        if(!hasNfcHce)
        {
            //textView.setText("DON'T HAVE HCE!");
            Log.i(TAG, "DON'T HAVE HCE!");
        }
        else{
            Log.i(TAG, "HCE AVAILABLE");
            //textView.setText("HCE AVAILABLE");
        }



        nameEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s)
            {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("name", s.toString());
                editor.commit();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        floorEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                String str = s.toString();
                int floor;
                try {
                    floor = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    floor = 0;
                }
                editor.putInt("floor",floor);
                editor.commit();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

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
