package com.example.ilia.exercise_four.fragments;

import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;
import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.adapters.SamplePagerAdapter;

import java.util.ArrayList;

/**
 * Created by ilia on 08.06.15.
 */
public class ViewPagerFragment extends Fragment implements ISetCurrentItem {
    private ViewPager mViewPager;
    private SamplePagerAdapter mMyFragmentPagerAdapter;

    public ViewPagerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout inflateView = (LinearLayout) inflater.inflate(R.layout.viewpager_fragment, container, false);
        mViewPager = (ViewPager) inflateView.findViewById(R.id.pager);



        TypedArray stringListForExpanedList = getResources().obtainTypedArray(R.array.android_version);

        ArrayList<String> stringListForDefaultList = new ArrayList<>();
        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            CharSequence[] strings= stringListForExpanedList.getTextArray(i);
            for (int j=0;j<strings.length;j++) {
                if (j != 0) {
                    stringListForDefaultList.add((String) strings[j]);
                }
            }
        }
        mMyFragmentPagerAdapter = new SamplePagerAdapter(getActivity().getSupportFragmentManager(),stringListForDefaultList);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        return inflateView;
    }

    @Override
    public void setCurrentItem(int currentItem) {
        mViewPager.setCurrentItem(currentItem);
    }
}
