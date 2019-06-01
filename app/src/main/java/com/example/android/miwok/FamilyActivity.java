package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        final  ArrayList<Word> word = new ArrayList<>();

        word.add(new Word("Father", "әpә",R.drawable.family_father, R.raw.family_father));
        word.add(new Word("Mother", "әṭa",R.drawable.family_mother, R.raw.family_mother));
        word.add(new Word("Son", "angsi",R.drawable.family_son, R.raw.family_son));
        word.add(new Word("daughter", "tune",R.drawable.family_daughter, R.raw.family_daughter));
        word.add(new Word("Older Brother", "taachi",R.drawable.family_older_brother, R.raw.family_older_brother));
        word.add(new Word("Younger Brother", "chalitti",R.drawable.family_younger_brother, R.raw.family_younger_brother));
        word.add(new Word("Older Sister", "teṭe",R.drawable.family_older_sister, R.raw.family_older_sister));
        word.add(new Word("Younger Sister", "kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        word.add(new Word("Grand Mother", "ama",R.drawable.family_grandmother, R.raw.family_grandmother));
        word.add(new Word("Grand Father", "paapa",R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter itemsAdapter = new WordAdapter(this, word,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.familyActivity);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word words = word.get(position);

                releaseMediaPlayer();
                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, words.getAudio());

                // Start the audio file
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        releaseMediaPlayer();
                    }
                });
            }
        });

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}