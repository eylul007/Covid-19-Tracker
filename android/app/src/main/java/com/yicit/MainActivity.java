package com.yicit;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import expo.modules.ReactActivityDelegateWrapper;

public class MainActivity extends ReactActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // Set the theme to AppTheme BEFORE onCreate to support 
    // coloring the background, status bar, and navigation bar.
    // This is required for expo-splash-screen.
    setTheme(R.style.AppTheme);
    super.onCreate(savedInstanceState);

    /*
    // Creating an object of ExecutorService class to
    // create fixed size thread pool
    ExecutorService es = Executors.newFixedThreadPool(2);

    Thread t = new Thread(() -> startService(new Intent(this, Transmitter.class)));

    Intent intent = new Intent(this, BTBluetoothApp.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    //t.start();
    es.execute(t);
    es.execute(new Thread(() -> this.startActivity(intent)));

    es.shutdown();*/
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
