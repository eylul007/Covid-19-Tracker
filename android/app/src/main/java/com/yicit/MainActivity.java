package com.yicit;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import org.altbeacon.beacon.BeaconManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import expo.modules.ReactActivityDelegateWrapper;

public class MainActivity extends ReactActivity {
  private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
  protected static final String TAG = "MainActivity";
  private static final int PERMISSION_REQUEST_FINE_LOCATION = 1;
  private static final int PERMISSION_REQUEST_BACKGROUND_LOCATION = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme to AppTheme BEFORE onCreate to support 
    // coloring the background, status bar, and navigation bar.
    // This is required for expo-splash-screen.
    setTheme(R.style.AppTheme);
    super.onCreate(savedInstanceState);
    verifyBluetooth();
    requestPermissions();
    startService(new Intent(this, A_BTApp.class));
    startService(new Intent(this, Transmitter.class));
  }

  private void verifyBluetooth() {
    try {
      if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bluetooth not enabled");
        builder.setMessage("Please enable bluetooth in settings and restart this application.");
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialog) {
            finishAffinity();
          }
        });
        builder.show();
      }
    }
    catch (RuntimeException e) {
      final AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("Bluetooth LE not available");
      builder.setMessage("Sorry, this device does not support Bluetooth LE.");
      builder.setPositiveButton(android.R.string.ok, null);
      builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

        @Override
        public void onDismiss(DialogInterface dialog) {
          finishAffinity();
        }

      });
      builder.show();

    }

  }

  private void requestPermissions() {


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
              == PackageManager.PERMISSION_GRANTED) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
          if (this.checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                  != PackageManager.PERMISSION_GRANTED) {
            if (!this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
              final AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setTitle("This app needs background location access");
              builder.setMessage("Please grant location access so this app can detect beacons in the background.");
              builder.setPositiveButton(android.R.string.ok, null);
              builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @TargetApi(23)
                @Override
                public void onDismiss(DialogInterface dialog) {
                  requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                          PERMISSION_REQUEST_BACKGROUND_LOCATION);
                }

              });
              builder.show();
            }
            else {
              final AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setTitle("Functionality limited");
              builder.setMessage("Since background location access has not been granted, this app will not be able to discover beacons in the background.  Please go to Settings -> Applications -> Permissions and grant background location access to this app.");
              builder.setPositiveButton(android.R.string.ok, null);
              builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                }

              });
              builder.show();
            }
          }
        }
      } else {
        if (!this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
          requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                          Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                  PERMISSION_REQUEST_FINE_LOCATION);
        }
        else {
          final AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Functionality limited");
          builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons.  Please go to Settings -> Applications -> Permissions and grant location access to this app.");
          builder.setPositiveButton(android.R.string.ok, null);
          builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
            }

          });
          builder.show();
        }

      }
    }
  }



  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {

    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case PERMISSION_REQUEST_FINE_LOCATION: {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Log.d(TAG, "fine location permission granted");
        } else {
          final AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Functionality limited");
          builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons.");
          builder.setPositiveButton(android.R.string.ok, null);
          builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
            }

          });
          builder.show();
        }
        return;
      }
      case PERMISSION_REQUEST_BACKGROUND_LOCATION: {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Log.d(TAG, "background location permission granted");
        } else {
          final AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Functionality limited");
          builder.setMessage("Since background location access has not been granted, this app will not be able to discover beacons when in the background.");
          builder.setPositiveButton(android.R.string.ok, null);
          builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
            }

          });
          builder.show();
        }
        return;
      }
    }
  }

  /**
   * Returns the name of the main component registered from JavaScript.
   * This is used to schedule rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "main";
  }

  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new ReactActivityDelegateWrapper(
      this,
      new ReactActivityDelegate(this, getMainComponentName()) {
      @Override
      protected ReactRootView createRootView() {
        return new RNGestureHandlerEnabledRootView(MainActivity.this);
      }
    });
  }
}
