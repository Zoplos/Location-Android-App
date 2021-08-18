package com.example.secondassignment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    /*@Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Phone is charging", Toast.LENGTH_SHORT).show();
        } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Phone is not charging", Toast.LENGTH_SHORT).show();
        }
    }*/
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
    }
}
