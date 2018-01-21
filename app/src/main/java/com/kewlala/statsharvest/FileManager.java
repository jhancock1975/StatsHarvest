package com.kewlala.statsharvest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jhancock2010 on 1/21/18.
 */

class FileManager {

    public static final int FILE_SELECT_CODE = 0;


    public void openFile(Activity activity){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            activity.startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(activity, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
