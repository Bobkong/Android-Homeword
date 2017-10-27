package com.example.bob.lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Bob on 2017/10/25.
 */

public class MyReceiver extends BroadcastReceiver {
    public static final String ACTION_SUGGEST = "com.example.bob.lab3.MyReceiver.suggest";
    public static final String ACTION_DYNAMIC = "com.example.bob.lab3.MyReceiver.dynamic";
    public static final String ONCLICK = "com.app.onclick";
    String name;
    int num,pic;
    PendingIntent pi;
    @Override
    public void onReceive(Context context, Intent intent) {
           num = intent.getIntExtra("num",0);
           pic = DataManager.getInstance().findMainItem(num).getPic();
           Intent i = new Intent(context, DetailActivity.class)
                   .putExtra("mId",num);
           pi= PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
           name = DataManager.getInstance().findMainItem(num).getName();
       //Intent click_intent = new Intent(this,DetailActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        if (intent.getAction().equals(ACTION_SUGGEST)) {
            builder.setContentTitle(context.getString(R.string.notification_title_STATICATION))
                    .setContentText(name)
                    .setTicker(context.getString(R.string.have_a_news))
                    .setContentIntent(pi)
                    .setSmallIcon(pic)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), pic))
                    .setAutoCancel(true);
        }
        else{
            builder.setContentTitle(context.getString(R.string.notification_title_DYNAMIC))
                    .setContentText(name)
                    .setTicker(context.getString(R.string.have_a_news))
                    .setContentIntent(pi)
                    .setSmallIcon(pic)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), pic))
                    .setAutoCancel(true);
        }
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = builder.build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,notify);
    }
}
