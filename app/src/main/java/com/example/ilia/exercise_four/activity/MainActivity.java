package com.example.ilia.exercise_four.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.adapters.SamplePagerAdapter;
import com.example.ilia.exercise_four.fragments.ListFragment;
import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IConnectFragmentWithActivity {
    private ViewPager mViewPager;
    private SamplePagerAdapter mMyFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frg_list, new ListFragment(),"1").commit();

        mViewPager = (ViewPager) findViewById(R.id.pager);

        TypedArray stringListForExpanedList = getResources().obtainTypedArray(R.array.android_version);

        ArrayList<String> stringListForDefaultList = new ArrayList<>();
        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            CharSequence[] strings= stringListForExpanedList.getTextArray(i);
            for (int j=0;j<strings.length;j++) {
                if (j != 0) {
                    stringListForDefaultList.add((String) strings[j]);
                } else {
                    if (strings.length==1) {
                        stringListForDefaultList.add((String) strings[j]);
                    }
                }
            }
        }
        mMyFragmentPagerAdapter = new SamplePagerAdapter(getSupportFragmentManager(),stringListForDefaultList);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(int selectedItem) {
        mViewPager.setCurrentItem(selectedItem);
    }
}
