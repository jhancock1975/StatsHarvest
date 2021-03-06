package com.kewlala.statsharvest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jhancock2010 on 1/7/18.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int bgColor) {
        super(context, 0, objects);
        this.bgColor = bgColor;
    }

    int bgColor;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView =  LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }



        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        RelativeLayout textWrapper = (RelativeLayout) listItemView.findViewById(R.id.text_wrapper);

        textWrapper.setBackgroundColor(ContextCompat.getColor(getContext(),this.bgColor));
        Log.d(this.getClass().getSimpleName()+"getView::",
                "color == " + bgColor);



        // Find the TextView in the list_item.xml layout with the ID miwok
        EditText miwokTextView = (EditText) listItemView.findViewById(R.id.miwok);
        // Get the version number from the current word object and
        // set this text on the Miwok TextView
        miwokTextView.setText(currentWord.getMiwok());

        TextView rowNum = (TextView) listItemView.findViewById(R.id.row_number);
        rowNum.setText(position+"");

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
