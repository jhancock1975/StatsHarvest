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
        tabTitles.add(context.getString(R.string.category_numbers));
        tabTitles.add(context.getString(R.string.category_family));
        tabTitles.add(context.getString(R.string.category_colors));
        tabTitles.add(context.getString(R.string.category_phrases));
    }

    Context context;
    private List<String> tabTitles;


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else  {
            return new PhraseFragment();
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
