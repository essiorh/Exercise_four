package com.example.ilia.exercise_four.fragments;

import android.app.ExpandableListActivity;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;
import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;
import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.adapters.DefListAdapter;
import com.example.ilia.exercise_four.adapters.ExpListAdapter;
import com.example.ilia.exercise_four.models.ItemList;

import java.util.ArrayList;

/**
 * Created by ilia on 08.06.15.
 */
public class ListFragment extends Fragment implements Spinner.OnItemSelectedListener,ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener, ISetCurrentItem {
    private ExpandableListView expandableListView;
    private ListView defaultListView;
    private Spinner mSpinner;
    private         ExpListAdapter adapter;
    private         ArrayList<ArrayList<ItemList>> groups;

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
        groups = new ArrayList<>();
        TypedArray stringListForExpanedList = getResources().obtainTypedArray(R.array.android_version);

        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            ArrayList<ItemList> children1 = new ArrayList<>();
            CharSequence[] strings= stringListForExpanedList.getTextArray(i);
            for (int j=0;j<strings.length;j++) {
                if (j != 0) {
                    ItemList itemList=new ItemList((String) strings[j],getIdResource(i),false,"");
                    children1.add(itemList);
                }
                else {
                    if (strings.length==1) {
                        ItemList itemList=new ItemList((String) strings[j],getIdResource(i),false,"");
                        children1.add(itemList);
                    }
                }
            }
            groups.add(children1);
        }
        adapter = new ExpListAdapter(inflateView.getContext(), groups);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);


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

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        IConnectFragmentWithActivity listener = (IConnectFragmentWithActivity) getActivity();
        int off= adapter.getOffset(groupPosition);
        int pos=off+childPosition;
        listener.onItemSelected(pos);

        CheckBox checkBox=(CheckBox)v.findViewById(R.id.checkFavorite);
        checkBox.setChecked(!checkBox.isChecked());
        groups.get(groupPosition).get(childPosition).setFavorite(checkBox.isChecked());


        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        IConnectFragmentWithActivity listener = (IConnectFragmentWithActivity) getActivity();
        int off= adapter.getOffset(groupPosition);
        listener.onItemSelected(off);

        CheckBox checkBox=(CheckBox)v.findViewById(R.id.checkFavoriteGroup);
        checkBox.setChecked(!checkBox.isChecked());

        return false;
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
}
