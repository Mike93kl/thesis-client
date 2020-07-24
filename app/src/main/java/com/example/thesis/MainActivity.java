package com.example.thesis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import QueryManager.QueryManager;
import QueryManager.connection.ConnectionType;
import QueryManager.queries.Query;
import QueryManager.queryprocessor.QueryProcessor;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private QueryManager queryManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();

        checkPermissions();

        QueryManager queryManager = QueryManager.getInstance();
        queryManager.init(this,ConnectionType.BLUETOOTH);
        queryManager.setDebug(true);
        queryManager.connect();
    }


    public void checkPermissions(){
        if( ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED  ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED  ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.BODY_SENSORS
            },1);

        }
    }

    public void closeApp(View view) {
        finish();
        System.exit(0);
    }
}
