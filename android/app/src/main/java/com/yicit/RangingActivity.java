package com.yicit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.altbeacon.beacon.BeaconManager;

public class RangingActivity extends Service {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Log.d(TAG, "didRangeBeaconsInRegion called with beacon count:  "+beacons.size());
                    Beacon firstBeacon = beacons.iterator().next();
                }
            }

        };
        beaconManager.addRangeNotifier(rangeNotifier);
        beaconManager.startRangingBeacons(com.yicit.BTBluetoothApp.bt_Region);
    }
*/
    /*
    @Override
    protected void onPause() {
        super.onPause();
        beaconManager.stopRangingBeacons(com.yicit.BTBluetoothApp.bt_Region);
        beaconManager.removeAllRangeNotifiers();
    }*/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
