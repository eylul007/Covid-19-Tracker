package com.yicit;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

public class A_Monitoring extends Service implements MonitorNotifier {
    protected static final String TAG = "MonitoringActivity";
    private static final int PERMISSION_REQUEST_FINE_LOCATION = 1;
    private static final int PERMISSION_REQUEST_BACKGROUND_LOCATION = 2;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onCommand");

        BeaconManager.getInstanceForApplication(this).addMonitorNotifier(this);
        // No need to start monitoring here because we already did it in
        // BTBluetoothApp.onCreate
        // check if we are currently inside or outside of that region to update the display
        if (A_BTApp.insideRegion) {
            Log.d(TAG,"There is beacons.");
            startService(new Intent(this, A_Ranging.class));
        }
        else {
            logToDisplay("No beacons");
        }

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void didEnterRegion(Region region) { logToDisplay("didEnterRegion called"); }
    @Override
    public void didExitRegion(Region region) {
        logToDisplay("didExitRegion called");
    }
    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        logToDisplay("didDetermineStateForRegion called with state: " + (state == 1 ? "INSIDE ("+state+")" : "OUTSIDE ("+state+")"));
    }

    public void onRangingClicked(View view) {
        Intent myIntent = new Intent(this, RangingActivity.class);
        this.startActivity(myIntent);
    }

    private String cumulativeLog = "";
    private void logToDisplay(String line) {
        cumulativeLog += line+"\n";
        runOnUiThread(new Runnable() {
            public void run() {
                Log.d(TAG,cumulativeLog);
            }
        });
    }
}
