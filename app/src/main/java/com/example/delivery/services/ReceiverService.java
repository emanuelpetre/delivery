package com.example.delivery.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.example.delivery.R;
import com.example.delivery.activities.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ReceiverService extends BroadcastReceiver {

    private NotificationManager mNotificationManager;
    private static int NOTIFICATION_ID;
    private static String PRIMARY_CHANNEL_ID;

    public void onReceive(Context context, Intent intent) {
        PRIMARY_CHANNEL_ID = "primary_notification_channel";
        NOTIFICATION_ID = 0;
        createNotificationChannel(context);
        mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        String text = intent.getExtras().getString("address");
        deliverNotification(context, text);
    }

    private void deliverNotification(Context context, String text)
    {
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle("Alarm")
                .setContentText(text)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void createNotificationChannel(Context context) {
        mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Alarm notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
