package com.yicit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class RangingActivity extends Service {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // super.onResume();


                        RangeNotifier rangeNotifier = new RangeNotifier() {
                            @Override
                            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                                Log.d(TAG, "Inside of RangingAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                                if (beacons.size() > 0) {
                                    Log.d(TAG, "didRangeBeaconsInRegion called with beacon count:  " + beacons.size());
                                    Beacon firstBeacon = beacons.iterator().next();

                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    DocumentReference newRef = db.collection("becons").document();
                                    newRef.set(beacons);

                                }
                            }

                        };
                        beaconManager.addRangeNotifier(rangeNotifier);
                        beaconManager.startRangingBeacons(com.yicit.BTBluetoothApp.bt_Region);

        return START_NOT_STICKY;
    }

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
