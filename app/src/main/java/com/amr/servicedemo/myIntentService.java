package com.amr.servicedemo;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class myIntentService extends IntentService
{


    public myIntentService() {
        super(null);
    }
    public myIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            Toast.makeText(this,"make some thing in IntentService in background",Toast.LENGTH_LONG).show();
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Intent intent2 = new Intent("amr");
        intent2.putExtra("output", "IntentService finish");
        sendBroadcast(intent2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}