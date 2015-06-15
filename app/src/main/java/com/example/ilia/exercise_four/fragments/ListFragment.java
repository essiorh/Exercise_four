package com.example.ilia.exercise_four.fragments;

import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.support.v4.internal.view.SupportMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ilia.exercise_four.adapters.workViewHolder;
import com.example.ilia.exercise_four.interfaces.ICheckFavorite;
import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;
import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;
import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.adapters.DefListAdapter;
import com.example.ilia.exercise_four.adapters.ExpListAdapter;
import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;
import com.example.ilia.exercise_four.interfaces.ISetCurrentItem;
import com.example.ilia.exercise_four.models.ItemContainer;

import java.util.ArrayList;

/**
 * Created by ilia on 08.06.15.
 */
public class ListFragment extends Fragment implements
        CheckBox.OnClickListener, ListView.OnItemClickListener,
        Spinner.OnItemSelectedListener, ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupClickListener, ISetCurrentItem {

public class ListFragment extends Fragment implements Spinner.OnItemSelectedListener,
                                                      ExpandableListView.OnChildClickListener,
                                                      ExpandableListView.OnGroupClickListener,
                                                      ISetCurrentItem {
    private ExpandableListView expandableListView;
    private ListView defaultListView;
    private Spinner mSpinner;
    private         ExpListAdapter adapter;
    private         ListAdapter mListAdapter;
    private         ArrayList<ArrayList<ItemContainer>> groups;
    private         TextView mTextView;
    private         CheckBox checkBoxFavorite;

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout inflateView = (RelativeLayout) inflater.inflate(R.layout.list_fragment, container, false);
        expandableListView = (ExpandableListView) inflateView.findViewById(R.id.exp_list);
        mSpinner = (Spinner) inflateView.findViewById(R.id.list_spinner);
        defaultListView = (ListView) inflateView.findViewById(R.id.def_list);
        checkBoxFavorite = (CheckBox) inflateView.findViewById(R.id.filter_favorite);
        checkBoxFavorite.setOnClickListener(this);

        mTextView = (TextView) inflateView.findViewById(R.id.filter);
        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSpinner.setOnItemSelectedListener(this);
        groups = new ArrayList<>();
        TypedArray stringListForExpanedList = getResources().obtainTypedArray(R.array.android_version);

        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            ArrayList<ItemContainer> children1 = new ArrayList<>();
            CharSequence[] strings = stringListForExpanedList.getTextArray(i);
            for (int j = 0; j < strings.length; j++) {

                ItemContainer itemContainer = new ItemContainer((String) strings[j], getIdResource(i), false, "");
                children1.add(itemContainer);

            }
            groups.add(children1);
        }
        adapter = new ExpListAdapter(inflateView.getContext(), groups);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(this);
        expandableListView.setOnGroupClickListener(this);


        ArrayList<ItemContainer> stringListForDefaultList = new ArrayList<>();
        for (int i = 0; i < stringListForExpanedList.length(); i++) {
            CharSequence[] strings = stringListForExpanedList.getTextArray(i);
            for (int j = 0; j < strings.length; j++) {

                ItemContainer itemContainer = new ItemContainer((String) strings[j], getIdResource(i), false, "");
                stringListForDefaultList.add(itemContainer);

            }
        }
        stringListForExpanedList.recycle();

        mListAdapter = new workViewHolder(getActivity(), stringListForDefaultList);

        defaultListView.setAdapter(mListAdapter);
        defaultListView.setOnItemClickListener(this);

        registerForContextMenu(defaultListView);
        registerForContextMenu(expandableListView);
        return inflateView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.list_spinner:
                if (position == 1) {
                    defaultListView.setVisibility(View.VISIBLE);
                    expandableListView.setVisibility(View.GONE);
                } else {
                    defaultListView.setVisibility(View.GONE);
                    expandableListView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo)item.getMenuInfo();
        int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
        ItemContainer itemContainer =(ItemContainer) this.adapter.getChild(groupPos, childPos);

        adapter.deleteExpandableElement(itemContainer);
        adapter.notifyDataSetChanged();
        //String[] menuItems = getResources().getStringArray(R.array.menu);
        //String menuItemName = menuItems[menuItemIndex];
        //String listItemName = Countries[info.position];

        //TextView text = (TextView)findViewById(R.id.footer);
        //text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
        return true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void setCurrentItem(int currentItem) {
        defaultListView.setSelection(currentItem);
        for (int i=0;i<groups.size();i++) {
            if (adapter.getOffset(i)>currentItem) {
                expandableListView.setSelectedChild(i - 1, currentItem - adapter.getOffset(i-1),false);
                break;
            }
        }
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        IConnectFragmentWithActivity listener = (IConnectFragmentWithActivity) getActivity();
        int off= adapter.getOffset(groupPosition);
        int pos=off+childPosition;
        listener.onItemSelected(pos);

        CheckBox checkBox=(CheckBox)v.findViewById(R.id.checkFavorite);
        checkBox.setChecked(!checkBox.isChecked());

        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        IConnectFragmentWithActivity listener = (IConnectFragmentWithActivity) getActivity();
        int off= adapter.getOffset(groupPosition);
        listener.onItemSelected(off);
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("mylog", "itemClick: position = " + position + ", id = "
                + id);

        IConnectFragmentWithActivity listener = (IConnectFragmentWithActivity) getActivity();
        int off = position;
        listener.onItemSelected(off);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter_favorite:
            adapter.checkFavorite(((CheckBox) v).isChecked());
                ICheckFavorite iCheckFavorite= (ICheckFavorite) mListAdapter;
                iCheckFavorite.checkFavorite(((CheckBox) v).isChecked());
                break;
        }

        return false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


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
