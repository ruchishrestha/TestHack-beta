package com.coolapp.testhack;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Ganesh on 5/30/2015.
 */
public class UploadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context ctx, Intent intent){
        final String action = intent.getAction();
        if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
            if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)){
                createNotification(ctx);
            } else {
                // wifi connection was lost
            }
        }

    }

    public void createNotification(Context ctx){

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(ctx, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Wifi connection detected")
                .setContentTitle("Attention")
                .setContentIntent(pIntent);
        NotificationManager manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
