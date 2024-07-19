package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.AidcManager.CreatedCallback;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.InvalidScannerNameException;

public class MainActivity extends AppCompatActivity {

    private TextView deviceInfoTxt;
    private Button btnAutomaticBarcode;
    private Button btnClientBarcode;
    private Button btnScannerSelectBarcode;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.MODEL.startsWith("VM1A")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        deviceInfoTxt = (TextView) findViewById(R.id.deviceIdInfo);
        String str = "";
        str = "SERIAL: " + Build.SERIAL + "\n"
                + "MODEL: " + Build.MODEL + "\n"
                + "ID: " + Build.ID + "\n"
                + "Manufacture: " + Build.MANUFACTURER + "\n"
                + "brand: " + Build.BRAND + "\n"
                + "type: " + Build.TYPE + "\n"
                + "user: " + Build.USER + "\n"
                + "BASE: " + Build.VERSION_CODES.BASE + "\n"
                + "INCREMENTAL " + Build.VERSION.INCREMENTAL + "\n"
                + "SDK  " + Build.VERSION.SDK + "\n"
                + "BOARD: " + Build.BOARD + "\n"
                + "BRAND " + Build.BRAND + "\n"
                + "HOST " + Build.HOST + "\n"
                + "FINGERPRINT: " + Build.FINGERPRINT + "\n"
                + "Version Code: " + Build.VERSION.RELEASE;
        deviceInfoTxt.setText(str);




        ActivitySetting();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }



    /**
     * Create buttons to launch demo activities.
     */
    public void ActivitySetting() {
        btnAutomaticBarcode = (Button) findViewById(R.id.buttonAutomaticBarcode);
        btnAutomaticBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the intent action string from AndroidManifest.xml
                Intent barcodeIntent = new Intent("android.intent.action.AUTOMATICBARCODEACTIVITY");
                startActivity(barcodeIntent);
            }
        });

        btnClientBarcode = (Button) findViewById(R.id.buttonClientBarcode);
        btnClientBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the intent action string from AndroidManifest.xml
                Intent barcodeIntent = new Intent("android.intent.action.CLIENTBARCODEACTIVITY");
                startActivity(barcodeIntent);
            }
        });

          addFragment(android.R.id.content, new AutomaticBarcodeFragment(), AutomaticBarcodeFragment.FRAGMENT_TAG);
    }



    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

}
