package com.coolapp.testhack;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Ganesh on 5/30/2015.
 */
public class UploadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context ctx, Intent intent){
        createNotification(ctx);
    }

    public void createNotification(Context ctx){

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(ctx, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("New information detected")
                .setContentTitle("Attention")
                .setContentIntent(pIntent);
        NotificationManager manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
