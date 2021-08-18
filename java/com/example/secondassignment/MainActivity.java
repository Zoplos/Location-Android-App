package com.example.secondassignment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver receiver;
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},7);
            return;
        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, locationListener);
    }


    private void registerReceivers(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        receiver = new MyBroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                    Toast.makeText(context, "Phone is charging", Toast.LENGTH_SHORT).show();
                } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){


                    Intent startService = new Intent(MainActivity.this,StartedService.class);
                    //Toast.makeText(context, "Phone is not charging", Toast.LENGTH_SHORT).show();
                    context.startService(startService);
                }
            }
        };
        registerReceiver(receiver,intentFilter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == 7){
            for(int i=0; i<permissions.length; i++){
                if(permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)){
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        registerReceivers();
                    }
                }
            }
        }
    }

}