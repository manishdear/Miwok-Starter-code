package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CatagoryAdapter extends FragmentPagerAdapter {
    public CatagoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0)
            return new ColorFragment();
        else if(position == 1)
            return new FamilyFragment();
        else if(position == 2)
            return new NumberFragment();
        else
            return new PhraseFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
