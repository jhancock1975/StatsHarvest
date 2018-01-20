package com.kewlala.statsharvest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by jhancock2010 on 1/20/18.
 */

public class GpsControl {

    public GpsControl(Context context) throws SecurityException{

        this.context = context;

        // Acquire a reference to the system Location Manager
        this.locationManager =
                (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        StatsHarvestLocationListener locationListener =
                new StatsHarvestLocationListener(this);

        // Register the listener with the Location Manager to receive location updates
        try {

            this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, locationListener);

        } catch(SecurityException e){
            Log.d(MainActivity.APP_DEBUG_TAG, "GpsControl::getInstance - did not get " +
                    "permission to access the GPS");
            throw(e);
        }
    }

    private Context context;

    private Location curLocation;
    private LocationManager locationManager;

    private void makeUseOfNewLocation(Location location){
        Log.d("GpsControl", "makeUseOfNewLocation");
    }


    public Location getLocation() throws SecurityException{

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
