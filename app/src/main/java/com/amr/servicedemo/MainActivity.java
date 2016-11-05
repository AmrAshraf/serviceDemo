package com.amr.servicedemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myService s;
    ServiceConnection serviceConnection;
    Intent i;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null)
                Toast.makeText(context, bundle.getString("output"), Toast.LENGTH_SHORT).show();

            NotificationManager notificationManager;
            NotificationCompat.Builder builder = (NotificationCompat.Builder) new
                    NotificationCompat.Builder(MainActivity.this)
                    .setContentTitle("message")
                    .setContentInfo("my name is amr")
                    .setTicker("alarm").setSmallIcon(R.mipmap.ic_launcher);
            builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
            builder.setAutoCancel(true);
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1, builder.build());
            Toast.makeText(context, "size of list now is :"+s.array.size(), Toast.LENGTH_SHORT).show();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(broadcastReceiver, new IntentFilter("amr"));
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                s = ((myService.MyBinder) service).getService();
                Toast.makeText(MainActivity.this, "connected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        bindService(new Intent(this,myService.class),serviceConnection, Context.BIND_AUTO_CREATE);
       // startService(new Intent(this,myService.class));
        startService(new Intent(this,myIntentService.class));

    }
}
