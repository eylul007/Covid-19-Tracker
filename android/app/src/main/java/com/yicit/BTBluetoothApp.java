package com.yicit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;
//import org.altbeacon.beacon.TimedBeaconSimulator;

// BT- we cann override didEnterRegion, didExitRegion and didDetermineStateForRegion methods for the purposes. That purposes are explained when we use the methods
public class BTBluetoothApp extends Service implements MonitorNotifier {
    private static final String TAG = "DistanceCalculator";
    //BT- we will use this Region objects with unique id , for start or stop monitoring or ranging. The unique id must be matched.
    public static final Region bt_Region = new Region("BT_unique_Identifier", null, null, null);
    //BT-This is flag for there is beacon inside of the region
    public static boolean insideRegion = false;

    /**
     * Transmitter code between green comments
     */
    /*
    private static final String TAG2 = "MainActivity";
    private BeaconTransmitter mBeaconTransmitter;


    private int lock;

    @SuppressLint("MissingPermission")
    private void transmitStart(Beacon beacon) {
        BluetoothManager bluetoothManager = (BluetoothManager) this.getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter adapter = bluetoothManager.getAdapter();

        adapter.startDiscovery();
        while(adapter.isDiscovering());

        Log.d(TAG2,"Discovery has done!");

        mBeaconTransmitter.startAdvertising(beacon, new AdvertiseCallback() {
            @Override
            public void onStartFailure(int errorCode) {
                Log.e(TAG2, "Advertisement start failed with code: "+errorCode);
            }

            @Override
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                Log.i(TAG2, "Advertisement start succeeded.");
            }
        });

    }

    @TargetApi(21)
    private boolean checkPrerequisites() {

        if (Build.VERSION.SDK_INT < 18) {
            System.out.println("This OS doesnt support BLE!");

            return false;
        }
        if (!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            System.out.println("This device doesnt support BLE");

            return false;
        }
        if (!((BluetoothManager) getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter().isEnabled()){
            System.out.println("BLE not enabled!");

            return false;

        }

        try {
            // Check to see if the getBluetoothLeAdvertiser is available.  If not, this will throw an exception indicating we are not running Android L
            ((BluetoothManager) this.getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter().getBluetoothLeAdvertiser();
        }
        catch (Exception e) {
            return false;

        }

        return true;
    }
*/
    /**
     * Transmitter ends
     */

    /*
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);


    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Inside of the BTBluetooothApp!");
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    //BT-An accessor for this class
                    BeaconManager beaconManager = BeaconManager.getInstanceForApplication(BTBluetoothApp.this);

                    // By default the AndroidBeaconLibrary will only find AltBeacons.  If you wish to make it
                    // find a different type of beacon, you must specify the byte layout for that beacon's
                    // advertisement with a line like below.  The example shows how to find a beacon with the
                    // same byte layout as AltBeacon but with a beaconTypeCode of 0xaabb.  To find the proper
                    // layout expression for other beacon types, do a web search for "setBeaconLayout"
                    // including the quotes.
                    //
                    //beaconManager.getBeaconParsers().clear();
                    //beaconManager.getBeaconParsers().add(new BeaconParser().
                    //        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

                    beaconManager.setDebug(true);


                    // Uncomment the code below to use a foreground service to scan for beacons. This unlocks
                    // the ability to continually scan for long periods of time in the background on Andorid 8+
                    // in exchange for showing an icon at the top of the screen and a always-on notification to
                    // communicate to users that your app is using resources in the background.
                    //

                    //BT-For designing for app
                    //Notification.Builder builder = new Notification.Builder(this);

                    //builder.setContentTitle("Scanning");

                    startService(new Intent(BTBluetoothApp.this, MonitoringActivity.class));

        /*
        Intent intent2 = new Intent(this, MonitoringActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT
        );*/

                    //BT-Anlamadım
                    //builder.setContentIntent(pendingIntent);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification Channel ID",
                    "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Notification Channel Description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channel.getId());
        }*/

                    //BT-for background scanning
                    /**
                     * ???? enableForegroundService ????
                     */
                    //beaconManager.enableForegroundServiceScanning(builder.build(), 456);

                    // For the above foreground scanning service to be useful, you need to disable
                    // JobScheduler-based scans (used on Android 8+) and set a fast background scan
                    // cycle that would otherwise be disallowed by the operating system.
                    //
                    beaconManager.setEnableScheduledScanJobs(false);
                    beaconManager.setBackgroundBetweenScanPeriod(0);
                    beaconManager.setBackgroundScanPeriod(1100);


                    //BT-Bu log nedir burda açıklamasını yapmış zaten
                    Log.d(TAG, "setting up background monitoring in app onCreate");
                    beaconManager.addMonitorNotifier(BTBluetoothApp.this);

                    // If we were monitoring *different* regions on the last run of this app, they will be
                    // remembered.  In this case we need to disable them here
                    for (Region region : beaconManager.getMonitoredRegions()) {
                        beaconManager.stopMonitoring(region);
                    }

                    /* Transmitter starts */
                    // do your jobs here
/*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("In the start command!");
        if (checkPrerequisites()) {
            System.out.println("Bok");

            // Sets up to transmit as an AltBeacon-style beacon.  If you wish to transmit as a different
            // type of beacon, simply provide a different parser expression.  To find other parser expressions,
            // for other beacon types, do a Google search for "setBeaconLayout" including the quotes
            mBeaconTransmitter = new BeaconTransmitter(this, new BeaconParser().setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
            // Transmit a beacon with Identifiers 2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6 1 2
            Beacon beacon = new Beacon.Builder()
                    .setId1("2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6")
                    .setId2("1")
                    .setId3("2")
                    .setManufacturer(0x0000) // Choose a number of 0x00ff or less as some devices cannot detect beacons with a manufacturer code > 0x00ff
                    .setTxPower(-59)
                    .setDataFields(Arrays.asList(new Long[]{0l}))
                    .build();

            transmitStart(beacon);
        }
        */
                    /* Transmitter ends */


                    beaconManager.startMonitoring(bt_Region);

                    // If you wish to test beacon detection in the Android Emulator, you can use code like this:
                    // BeaconManager.setBeaconSimulator(new TimedBeaconSimulator() );
                    // ((TimedBeaconSimulator) BeaconManager.getBeaconSimulator()).createTimedSimulatedBeacons();
                    System.out.println("In the start command!");

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

        /*Transmitter part */
        //mBeaconTransmitter.stopAdvertising();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //BT-there is beacon in the region
    @Override
    public void didEnterRegion(Region arg0) {
        Log.d(TAG, "did enter region.");
        insideRegion = true;

 /*
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference newRef = db.collection("becons").document();
            newRef.set(arg0);
            newRef.set('A');*/


        System.out.println("Girdimmmm22222mm");
        // Send a notification to the user whenever a Beacon
        // matching a Region (defined above) are first seen.
        Log.d(TAG, "Sending notification.");
        //sendNotification();
    }

    //BT-No beacons in the region
    @Override
    public void didExitRegion(Region region) {
        insideRegion = false;
        // do nothing here. logging happens in MonitoringActivity
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        // do nothing here. logging happens in MonitoringActivity
    }

    /*
    //BT-if there is a beacon in ther region, the app send notification to user
    private void sendNotification() {
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
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
        stackBuilder.addNextIntent(new Intent(this, MonitoringActivity.class));
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentTitle("detect a beacon");
        builder.setContentText("Tap here to see details");
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(1, builder.build());
    }
    */
}
