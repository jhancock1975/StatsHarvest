package com.kewlala.statsharvest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by jhancock2010 on 1/20/18.
 */

public class GpsControl {

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20350;

    public GpsControl(Activity activity) throws SecurityException{

        Context context = activity.getApplicationContext();

        // Acquire a reference to the system Location Manager
        this.locationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        StatsHarvestLocationListener locationListener =
                new StatsHarvestLocationListener(this);

        // Register the listener with the Location Manager to receive location updates
        try {
            requestGps(activity);
            
            this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, locationListener);

        } catch(SecurityException e){
            Log.d(MainActivity.APP_DEBUG_TAG, "GpsControl::GpsControl - did not get " +
                    "permission to access the GPS");
            throw(e);
        }
    }

    private Location curLocation;
    private LocationManager locationManager;

    private void requestGps(Activity activity) {
        Log.d(MainActivity.APP_DEBUG_TAG, "GPSControl::requestGps start");
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Log.d(MainActivity.APP_DEBUG_TAG,
                    "GPSControl::requestGps we do not have permission " +
                            "to use coarse location yet");
            
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                Log.d(MainActivity.APP_DEBUG_TAG,
                        "GPSControl::we should show request permission rational");

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                Log.d(MainActivity.APP_DEBUG_TAG, "No explanation needed, we can " +
                                "request the permission.");
                        
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }



    private void makeUseOfNewLocation(Location location){
        Log.d("GpsControl", "makeUseOfNewLocation");
    }


    public Location getLocation(Context context) throws SecurityException{


        Log.d(MainActivity.APP_DEBUG_TAG, "GpsControl::getLocation - context = " + context);

        try {

            return this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        } catch (SecurityException e){

            Log.d(MainActivity.APP_DEBUG_TAG, "GpsControl::getLocation - did not get " +
                "permission to access the GPS");

            throw e;
        }
    }

    public void setLocation(Location curLocation) {
        this.curLocation = curLocation;
    }
}
