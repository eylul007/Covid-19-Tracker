package com.yicit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class BTRanging1 extends Service {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Log.d(TAG, "didRangeBeaconsInRegion called with beacon count:  "+beacons.size());
                    Beacon firstBeacon = beacons.iterator().next();

                    Log.e(TAG,"The first beacon " + firstBeacon.toString() + " is about " + firstBeacon.getDistance() + " meters away.");
                }
            }

        };
        beaconManager.addRangeNotifier(rangeNotifier);
        beaconManager.startRangingBeacons(BTBluetoothApp.bt_Region);

        return START_NOT_STICKY;
    }

   /* @Override
    protected void onPause() {
        super.onPause();
        beaconManager.stopRangingBeacons(BTBluetoothApp.bt_Region);
        beaconManager.removeAllRangeNotifiers();
    }*/



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
