package com.coolapp.testhack;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;

/**
 * Created by Ganesh on 5/30/2015.
 */
public class BGService extends Service {

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        UploadReceiver receiver = new UploadReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        registerReceiver(receiver, filter);
        return START_STICKY;
    }

}
