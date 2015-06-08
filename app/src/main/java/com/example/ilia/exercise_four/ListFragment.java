package com.example.ilia.exercise_four;

import android.app.Fragment;
import android.database.DataSetObserver;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilia on 08.06.15.
 */
public class ListFragment extends Fragment implements Spinner.OnItemSelectedListener{
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
        String[] stringListForExpanedList = getResources().getStringArray(R.array.android_version);
        for (int i = 0; i < stringListForExpanedList.length; i++) {
            ArrayList<String> children1 = new ArrayList<>();
            children1.add("Child_1");
            children1.add("Child_2");
            groups.add(children1);
        }
        ExpListAdapter adapter = new ExpListAdapter(inflateView.getContext(), groups);
        expandableListView.setAdapter(adapter);
        String[] arrayListForDefaultList = getResources().getStringArray(R.array.android_version);
        ArrayList<String> stringListForDefaultList = new ArrayList<>();
        for (int i = 0; i < arrayListForDefaultList.length; i++) {
            stringListForDefaultList.add(arrayListForDefaultList[i]);
        }
        ListAdapter mListAdapter = new DefListAdapter(inflateView.getContext(), stringListForDefaultList);
        defaultListView.setAdapter(mListAdapter);
        return inflateView;
    }

    @Override
    public void onDestroy() {
        expandableListView.setAdapter(new ExpListAdapter(getActivity().getApplicationContext(), new ArrayList<ArrayList<String>>()) {
        });
        super.onDestroy();
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
}
