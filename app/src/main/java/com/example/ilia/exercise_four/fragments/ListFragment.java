package com.example.ilia.exercise_four.fragments;

import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;
import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.adapters.DefListAdapter;
import com.example.ilia.exercise_four.adapters.ExpListAdapter;

import java.util.ArrayList;

/**
 * Created by ilia on 08.06.15.
 */
public class ListFragment extends Fragment implements Spinner.OnItemSelectedListener,ISetCurrentItem {
    private ExpandableListView expandableListView;
    private ListView defaultListView;
    private Spinner mSpinner;
    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout inflateView = (RelativeLayout) inflater.inflate(R.layout.list_fragment, container, false);
        expandableListView = (ExpandableListView) inflateView.findViewById(R.id.exp_list);
        mSpinner = (Spinner) inflateView.findViewById(R.id.list_spinner);
        defaultListView = (ListView) inflateView.findViewById(R.id.def_list);

        mSpinner.setOnItemSelectedListener(this);
        ArrayList<ArrayList<String>> groups = new ArrayList<>();
        TypedArray stringListForExpanedList = getResources().obtainTypedArray(R.array.android_version);

        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            ArrayList<String> children1 = new ArrayList<>();
            CharSequence[] strings= stringListForExpanedList.getTextArray(i);
            for (int j=0;j<strings.length;j++) {
                if (j != 0) {
                    children1.add((String) strings[j]);
                }
            }
            groups.add(children1);
        }
        ExpListAdapter adapter = new ExpListAdapter(inflateView.getContext(), groups,getActivity());
        expandableListView.setAdapter(adapter);



        ArrayList<String> stringListForDefaultList = new ArrayList<>();
        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            CharSequence[] strings= stringListForExpanedList.getTextArray(i);
            for (int j=0;j<strings.length;j++) {
                if (j != 0) {
                    stringListForDefaultList.add((String) strings[j]);
                }
            }
        }
        ListAdapter mListAdapter = new DefListAdapter(inflateView.getContext(), stringListForDefaultList);
        defaultListView.setAdapter(mListAdapter);
        return inflateView;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==1) {
            defaultListView.setVisibility(View.VISIBLE);
            expandableListView.setVisibility(View.GONE);
        } else {
            defaultListView.setVisibility(View.GONE);
            expandableListView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void setCurrentItem(int currentItem) {
        defaultListView.setSelection(currentItem);
        defaultListView.setSelection(currentItem);
    }
}
