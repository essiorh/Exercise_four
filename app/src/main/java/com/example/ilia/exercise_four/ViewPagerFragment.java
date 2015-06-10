package com.example.ilia.exercise_four;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

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


        String[] arrayListForDefaultList = getResources().getStringArray(R.array.android_version);
        ArrayList<String> stringListForDefaultList = new ArrayList<>();
        for (int i = 0; i < arrayListForDefaultList.length; i++) {
            stringListForDefaultList.add(arrayListForDefaultList[i]);
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
