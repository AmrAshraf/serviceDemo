package com.amr.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class myService extends Service {

    private final IBinder mBinder = new MyBinder();

    public List<Integer> array=new ArrayList<Integer>();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    public class MyBinder extends Binder {
        myService getService() {
            return myService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<9999999;i++)
                {
                    array.add(i,i);
                    Log.v("app",i+" ");
                }

            }
        });
        thread.start();

        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
