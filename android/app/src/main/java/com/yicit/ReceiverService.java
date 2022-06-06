package com.yicit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.InternalBeaconConsumer;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

public class ReceiverService extends Service implements InternalBeaconConsumer, MonitorNotifier {
    private BeaconManager beaconManager;
    private MonitorNotifier monitorNotifier;
    public static final Region bt_Region = new Region("BT_unique_Identifier", null, null, null);
    private static final String TAG = "ReceiverService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        beaconManager.getInstanceForApplication(ReceiverService.this).addMonitorNotifier(ReceiverService.this);

                        beaconManager.startMonitoring(bt_Region);
                    }
                }
        ).start();

        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("On Destroy!");
        super.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onBeaconServiceConnect() {

    }

    @Override
    public void didEnterRegion(Region region) {

    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {

    }
}