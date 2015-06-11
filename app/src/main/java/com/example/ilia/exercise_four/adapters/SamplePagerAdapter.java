package com.example.ilia.exercise_four.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ilia.exercise_four.fragments.PageFragment;

import java.util.ArrayList;

public class SamplePagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_PAGES = 10;
    private ArrayList<String> mGroups;
    private ArrayList<Integer> mIdresources;

    public SamplePagerAdapter(FragmentManager fm,ArrayList<String> arrayList,ArrayList<Integer> idresources) {
        super(fm);
        mGroups=arrayList;
        mIdresources=idresources;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int index) {

        return
                PageFragment.newInstance(mGroups.get(index),mIdresources.get(index));
    }

    @Override
    public int getCount() {

        return mGroups!=null ? mGroups.size() : 0;
    }
}