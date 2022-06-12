package com.yicit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

public class BTReceiver1 extends Service implements MonitorNotifier {
    private static final String TAG = "DistanceCalculator";
    //BT- we will use this Region objects with unique id , for start or stop monitoring or ranging. The unique id must be matched.
    public static final Region bt_Region = new Region("BT_unique_Identifier", null, null, null);
    //BT-This is flag for there is beacon inside of the region
    public static boolean insideRegion = false;
    private BeaconManager beaconManager;
    private MonitorNotifier monitorNotifier;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        BeaconManager beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(BTReceiver1.this);
                        beaconManager.getInstanceForApplication(BTReceiver1.this).addMonitorNotifier(BTReceiver1.this);
                        beaconManager.setDebug(true);
                        //beaconManager.startMonitoring(bt_Region);
                        if (BTReceiver1.insideRegion) {
                            Log.e(TAG,"There is beacons.");
                            startService(new Intent(BTReceiver1.this, BTRanging1.class));
                        }
                        else {
                            Log.e(TAG,"No beacons");
                        }






                        Notification.Builder builder = new Notification.Builder(BTReceiver1.this);

                        builder.setContentTitle("Scanning");
                        Intent intent1 = new Intent(BTReceiver1.this, BTRanging1.class);
                        PendingIntent pendingIntent = PendingIntent.getService(BTReceiver1.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);



                        //BT-Anlamadım
                        builder.setContentIntent(pendingIntent);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel("My Notification Channel ID",
                                    "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT);
                            channel.setDescription("My Notification Channel Description");
                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.createNotificationChannel(channel);
                            builder.setChannelId(channel.getId());
                        }

                        //BT-for background scanning
                        beaconManager.enableForegroundServiceScanning(builder.build(), 456);
                        beaconManager.setEnableScheduledScanJobs(false);
                        beaconManager.setBackgroundBetweenScanPeriod(0);
                        beaconManager.setBackgroundScanPeriod(1100);
                        Log.d(TAG, "setting up background monitoring in app onCreate");
                        beaconManager.addMonitorNotifier(BTReceiver1.this);
                        for (Region region: beaconManager.getMonitoredRegions()) {
                            beaconManager.stopMonitoring(region);
                        }

                        beaconManager.startMonitoring(bt_Region);

                    }
                }
        ).start();

        stopSelf();




        return START_NOT_STICKY;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void didEnterRegion(Region region) {
        Log.d(TAG, "did enter region.");
        insideRegion = true;
        // Send a notification to the user whenever a Beacon
        // matching a Region (defined above) are first seen.
        Log.d(TAG, "Sending notification.");
        sendNotification();
    }


    //BT-if there is a beacon in ther region, the app send notification to user
    private void sendNotification() {
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder;
        //BT-If version of phone and version of codes are suitable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Beacon Reference Notifications",
                    "Beacon Reference Notifications", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
            builder = new Notification.Builder(this, channel.getId());
        }
        else {
            builder = new Notification.Builder(this);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(new Intent(this, BTRanging1.class));
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("detect a beacon");
        builder.setContentText("Tap here to see details");
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(1, builder.build());
    }



    @Override
    public void didExitRegion(Region region) {  insideRegion = false;

    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {

    }
}
