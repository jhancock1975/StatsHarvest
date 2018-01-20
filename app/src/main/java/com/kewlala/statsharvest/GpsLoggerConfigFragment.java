package com.kewlala.statsharvest;


import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GpsLoggerConfigFragment extends Fragment {


    public GpsLoggerConfigFragment() {
        // Required empty public constructor
    }

    MediaPlayer mediaPlayer;
    ListItemClickListener clickListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gps_config_layout, container, false);

        rootView.setBackgroundColor(getResources().getColor(R.color.gps_config_layout_background));

        TextView latitudeText =(TextView) rootView.findViewById(R.id.layout_latitude_value);


        TextView longitudeText =(TextView) rootView.findViewById(R.id.layout_longitude_value);

        try {

            GpsControl gpsControl = new GpsControl(getContext());

            Location location = gpsControl.getLocation();

            latitudeText.setText(location.getLatitude() +"");

            longitudeText.setText(location.getLongitude() +"");

        } catch (SecurityException e){
            Log.d(MainActivity.APP_DEBUG_TAG, this.getClass().getSimpleName() +
                    ":: did not get permission to read gps");

            Log.d(MainActivity.APP_DEBUG_TAG, this.getClass().getSimpleName() + "::" + e);

            //TODO: make latitute/longitude readings invisible in GPS configuration

        }

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("familiy", "onstop mediaPlayer == " + mediaPlayer);
        clickListener.releaseMediaPlayer();

    }


}
