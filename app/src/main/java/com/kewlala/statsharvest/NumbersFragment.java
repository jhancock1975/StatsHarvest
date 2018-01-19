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

import com.example.android.miwok.Word;
import com.example.android.miwok.WordAdapter;
import com.kewlala.statsharvest.ListItemClickListener;
import com.kewlala.statsharvest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    MediaPlayer mediaPlayer;
    ListItemClickListener clickListener;

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final List<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one,
                R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two,
                R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three,
                R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,
                R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five,
                R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six,
                R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven,
                R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight,
                R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine,
                R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten,
                R.raw.number_ten));


        ArrayAdapter<Word> itemsAdapter =
                new WordAdapter(getActivity(),  words, R.color.category_numbers);

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
        Log.d("numbers", "onstop mediaPlayer == " + mediaPlayer);
        clickListener.releaseMediaPlayer();

    }
}
