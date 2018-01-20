package com.kewlala.statsharvest;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by jhancock2010 on 1/20/18.
 */

class StatsHarvestLocationListener implements LocationListener{
    public StatsHarvestLocationListener(GpsControl gpsControl) {
        this.gpsControl = gpsControl;
    }

    private GpsControl gpsControl;
    @Override
    public void onLocationChanged(Location location) {
        gpsControl.setLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
