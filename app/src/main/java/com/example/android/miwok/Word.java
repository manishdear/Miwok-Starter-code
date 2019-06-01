package com.example.android.miwok;

import java.io.IOException;
import java.util.ArrayList;

public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private final static int No_IMAGE = -1;
    private int image = No_IMAGE;
    private int audio;

    public Word(String defaultTranslation, String miwokTranslation, int audio){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audio =audio;
        //this.image = image;
    }

    public Word(String defaultTranslation, String miwokTranslation, int image, int audio){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.image = image;
        this.audio = audio;
    }

    public String getMiwokTranslation(){
        return miwokTranslation;
    }

    public String getDefaultTranslation(){
        return defaultTranslation ;
    }

    public int getImage(){
        return image;
    }

    public boolean hasImage(){
        return  image != No_IMAGE;
    }

    public int getAudio(){
        return audio;
    }
}
