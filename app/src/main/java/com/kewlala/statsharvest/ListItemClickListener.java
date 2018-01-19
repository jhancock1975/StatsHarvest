package com.kewlala.statsharvest;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.android.miwok.Word;

import java.util.List;

/**
 * Created by jhancock2010 on 1/13/18.
 */

public class ListItemClickListener implements OnItemClickListener {

    public ListItemClickListener(MediaPlayer mediaPlayer, Context context, List<Word> words) {
        this.mediaPlayer = mediaPlayer;
        this.context = context;
        this.words = words;
    }

    MediaPlayer mediaPlayer;
    Context context;
    List<Word> words;

    AudioManager am;
    AudioManager.OnAudioFocusChangeListener afChangeListener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            Log.d("phrases", "audiofocus change i == " +  i);
            switch (i){
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    pausePlayback();
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.d("phrases", "got audio focus.");
                    mediaPlayer.start();
            }
        }
    };

    public void pausePlayback() {
        if (ListItemClickListener.this.mediaPlayer != null) {
            ListItemClickListener.this.mediaPlayer.pause();
            ListItemClickListener.this.mediaPlayer.seekTo(0);
        }
    }


    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("numbers", "onCreate.onItemClick");
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(context,
                words.get(i).getSoundId());
         am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        // Request audio focus for playback
        int result = am.requestAudioFocus(afChangeListener,
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this.completionListener);
        }

    }
    public void releaseMediaPlayer() {

        Log.d("releaseMediaPlayer", "mediaPlayer == " + mediaPlayer);

        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
        if (this.am != null){
            this.am.abandonAudioFocus(afChangeListener);
        }
    }

}
