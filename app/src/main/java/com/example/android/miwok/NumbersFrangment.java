package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NumbersFrangment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT  ||  focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                        }
                        // Pause playback
                        // Lower the volume, keep playing
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        mMediaPlayer.start();
                    }
                }
            };

    public NumbersFrangment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> word = new ArrayList<>();
        word.add(new Word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        word.add(new Word("Two", "otiiko", R.drawable.number_two, R.raw.number_two));
        word.add(new Word("Three", "tolookoso", R.drawable.number_three, R.raw.number_three));
        word.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        word.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        word.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        word.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        word.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        word.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        word.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), word, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word words = word.get(position);

                mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


                // release the media player before the new instance of media player created
                releaseMediaPlayer();


                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.getAudio());

                    // Start the audio file
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        return rootView;
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
