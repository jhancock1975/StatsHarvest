package com.kewlala.statsharvest;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhancock2010 on 1/14/18.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {

        super(fm);

        this.context= context;
        tabTitles = new ArrayList<String>();
        tabTitles.add(context.getString(R.string.tab_title_data_entry));
        tabTitles.add(context.getString(R.string.tab_title_configure_gps_logger));
        tabTitles.add(context.getString(R.string.tab_title_stats));
    }

    Context context;
    private List<String> tabTitles;


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DataEntryFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else  {
            return new ColorsFragment();
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles.get(position);
    }

    public static final int NUM_TABS=3;
    @Override
    public int getCount() {
        return NUM_TABS;
    }
}
