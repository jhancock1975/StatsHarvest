package com.kewlala.statsharvest;


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

import com.example.android.miwok.ListItemClickListener;
import com.example.android.miwok.Word;
import com.example.android.miwok.WordAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    MediaPlayer mediaPlayer;
    ListItemClickListener clickListener;

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final List<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red,
                R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green,
                R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown,
                R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray,
                R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black,
                R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white,
                R.raw.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.color_mustard_yellow));


        ArrayAdapter<Word> itemsAdapter =
                new WordAdapter(getActivity(),  words, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);


        clickListener = new ListItemClickListener(mediaPlayer, getActivity(),
                words);

        listView.setOnItemClickListener(clickListener);


        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("colors", "onstop mediaPlayer == " + mediaPlayer);
        clickListener.releaseMediaPlayer();

    }
}
