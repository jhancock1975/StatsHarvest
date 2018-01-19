package com.kewlala.statsharvest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kewlala.statsharvest.PhraseFragment;
import com.kewlala.statsharvest.R;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PhraseFragment())
                .commit();
    }
}