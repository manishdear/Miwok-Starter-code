package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private MediaPlayer mediaPlayer;
    private int bColorRes;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int bColorRes) {
        super(context, 0, objects);
        this.bColorRes = bColorRes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Word currentWord = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImage());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = (View) listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), bColorRes);
        textContainer.setBackgroundColor(color);

        TextView miwokText = (TextView) listItemView.findViewById(R.id.miwok_word);
        miwokText.setText(currentWord.getMiwokTranslation());

        TextView defaultText = (TextView) listItemView.findViewById(R.id.english_word);
        defaultText.setText(currentWord.getDefaultTranslation());

        return listItemView;
    }
}