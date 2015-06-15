package com.example.ilia.exercise_four.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.adapters.SamplePagerAdapter;
import com.example.ilia.exercise_four.fragments.ListFragment;
import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;
import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IConnectFragmentWithActivity, ViewPager.OnPageChangeListener{
    public static final String LIST_FRAGMENT = "1";
    private ViewPager mViewPager;
    private SamplePagerAdapter mMyFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frg_list, new ListFragment(), LIST_FRAGMENT).commit();

        mViewPager = (ViewPager) findViewById(R.id.pager);

        TypedArray stringListForExpanedList = getResources().obtainTypedArray(R.array.android_version);

        ArrayList<String> stringListForDefaultList = new ArrayList<>();
        ArrayList<Integer> intListForIdImages=new ArrayList<>();

        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            CharSequence[] strings = stringListForExpanedList.getTextArray(i);
            for (int j = 0; j < strings.length; j++) {

                stringListForDefaultList.add((String) strings[j]);
                intListForIdImages.add(getIdResource(i));
            }
        }
        mMyFragmentPagerAdapter = new SamplePagerAdapter(getSupportFragmentManager(), stringListForDefaultList,intListForIdImages);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }
    @Override public void onPageSelected(int position) {
        ((ISetCurrentItem) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT)).setCurrentItem(position);
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
    private int getIdResource(int counter) {
        switch (counter){
            case 0: return R.drawable.applepie;
            case 1: return R.drawable.bananabread;
            case 2: return R.drawable.cupcake;
            case 3: return R.drawable.donut;
            case 4: return R.drawable.eclair;
            case 5: return R.drawable.froyo;
            case 6: return R.drawable.gingerbread;
            case 7: return R.drawable.honeycomb;
            case 8: return R.drawable.icecreamsandwich;
            case 9: return R.drawable.jellybean;
            case 10: return R.drawable.kitkat;
            case 11: return R.drawable.lollipop;
            case 12: return R.drawable.lollipop;
            default: return R.drawable.abc_btn_default_mtrl_shape;
        }
    }

    @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {    }

    @Override public void onPageScrollStateChanged(int state) {    }
}
