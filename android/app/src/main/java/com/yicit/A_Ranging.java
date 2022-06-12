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

public class A_Ranging extends Service {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                System.out.println("booooooooook");
                if (beacons.size() > 0) {
                    Log.d(TAG, "didRangeBeaconsInRegion called with beacon count:  " + beacons.size());
                    Beacon firstBeacon = beacons.iterator().next();

                    Log.d(TAG, "The first beacon " + firstBeacon.toString() + " is about " + firstBeacon.getDistance() + " meters away.");
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    DocumentReference newCityRef = db.collection("becons").document();


                    deneme d= new deneme(firstBeacon.toString(),firstBeacon.getDistance());
                    newCityRef.set(d);
                }
            }

        };
        beaconManager.addRangeNotifier(rangeNotifier);
        beaconManager.startRangingBeacons(A_BTApp.bt_Region);



        stopSelf();
        return START_NOT_STICKY;
    }



    /*
    private void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                Log.d(TAG)
            }
        });
    }*/


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
