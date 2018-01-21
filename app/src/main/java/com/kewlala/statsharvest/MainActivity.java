/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kewlala.statsharvest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String APP_DEBUG_TAG = "StatsHarvest";
    private FileManager fileManager = new FileManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_category);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MainActivity", "onPrepareOptionsMenu - inflating menu");
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MainActivity", "action bar selected - determining action");
        switch (item.getItemId()) {
            case R.id.action_save:
                // User chose the "Settings" item, show the app settings UI...
                Log.d("MainActivity", "action bar selected - save action");
                return true;
            case R.id.action_open:
                fileManager.openFile(this);

            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            default: return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Log.d(MainActivity.APP_DEBUG_TAG, "start onActivityResult");

        switch (requestCode) {
            case FileManager.FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {

                    Log.d(MainActivity.APP_DEBUG_TAG, "MainActivity::onActivityResult - " +
                            "got FILE_SELECT_CODE request code");

                    final Uri uri = data.getData();
                    Log.d(MainActivity.APP_DEBUG_TAG, "MainActivity::onActivityResult - " +
                            "uri = " + uri);

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        String fileName = DocumentsContract.getDocumentId(uri);
                        Log.d(MainActivity.APP_DEBUG_TAG, "MainActivty::onActivityResult " +
                                "fileName == " + fileName);
                    }

                }
                break;
        }
    }
}
