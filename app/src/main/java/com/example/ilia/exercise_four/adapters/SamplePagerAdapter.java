package com.example.ilia.exercise_four.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ilia.exercise_four.fragments.PageFragment;

import java.util.ArrayList;

public class SamplePagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_PAGES = 10;
    private ArrayList<String> mGroups;

    public SamplePagerAdapter(FragmentManager fm,ArrayList<String> arrayList) {
        super(fm);
        mGroups=arrayList;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int index) {

        return
                PageFragment.newInstance(mGroups.get(index));
    }

    @Override
    public int getCount() {

        return NUMBER_OF_PAGES;
    }
}