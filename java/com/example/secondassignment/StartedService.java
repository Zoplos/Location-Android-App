package com.example.secondassignment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ServiceCompat;

public class StartedService extends Service {
    private LocationManager locationManager;
    LocationListener locationListener;

    //@SuppressLint("MissingPermission")

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        locationManager.removeUpdates(locationListener);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast.makeText(StartedService.this, "phone is not charging, im a service", Toast.LENGTH_SHORT).show();



        Uri uri = Uri.parse("content://com.example.secondassignment.recordscontentprovider/records/");
        ContentResolver resolver = this.getContentResolver();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Toast.makeText(StartedService.this, location.toString(), Toast.LENGTH_SHORT).show();
                //Uri uri = Uri.parse("content://com.example.secondassignment.recordscontentprovider/records/");
                ContentValues values = new ContentValues();
                values.put("longitude", location.getLongitude());
                values.put("latitude", location.getLatitude());
                Long dt = System.currentTimeMillis() / 1000;
                String time = dt.toString();
                values.put("timestamp", time);
                Uri result = resolver.insert(uri, values);
            }
        };

        getLocation();

        return START_REDELIVER_INTENT;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
    }



}
