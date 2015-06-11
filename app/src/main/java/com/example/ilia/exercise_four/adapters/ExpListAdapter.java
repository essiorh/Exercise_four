package com.example.ilia.exercise_four.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.models.ItemContainer;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter implements CheckBox.OnClickListener {

    private ArrayList<ArrayList<ItemContainer>> mGroups;
    private Context mContext;
    private int[] offset;

    public ExpListAdapter(Context context, ArrayList<ArrayList<ItemContainer>> groups) {
        mContext = context;
        mGroups = groups;
        offset = new int[mGroups.size()];
        int off = 0;
        for (int i = 0; i < mGroups.size(); i++) {
            offset[i] = off;
            int vr = mGroups.get(i).size();
            off += vr;
        }

    }

    public int getOffset(int group) {
        return mGroups != null ? offset[group] : 0;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        if (isExpanded) {
            //Изменяем что-нибудь, если текущая Group раскрыта
        } else {
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        TypedArray stringListForExpanedList = convertView.getResources().obtainTypedArray(R.array.android_version);
        CharSequence[] strings = stringListForExpanedList.getTextArray(groupPosition);
        String string = (String) strings[0];
        textGroup.setText("Group " + string);
        CheckBox checkBoxFavorite = (CheckBox) convertView.findViewById(R.id.checkFavoriteGroup);
        boolean flag = true;
        for (int i = 0; i < mGroups.get(groupPosition).size(); i++) {
            if (!mGroups.get(groupPosition).get(i).getFavorite()) {
                flag = false;
            }
        }
        checkBoxFavorite.setChecked(flag);
        checkBoxFavorite.setOnClickListener(this);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }
        final ItemContainer itemContainer = mGroups.get(groupPosition).get(childPosition);
        final int finGroupPosition=groupPosition;
        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(itemContainer.getTitle());
        CheckBox checkBoxFavorite = (CheckBox) convertView.findViewById(R.id.checkFavorite);
        checkBoxFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemContainer.setFavorite(((CheckBox) v).isChecked() ? true : false);


                boolean flag = true;
                for (int i = 0; i < mGroups.get(finGroupPosition).size(); i++) {
                    if (!mGroups.get(finGroupPosition).get(i).getFavorite()) {
                        flag = false;
                    }
                }
                View vParent = (View) v.getParent().getParent();
                ((CheckBox)vParent.findViewById(R.id.checkFavoriteGroup)).setChecked(flag);
                notifyDataSetChanged();
            }
        });
        checkBoxFavorite.setChecked(mGroups.get(groupPosition).get(childPosition).getFavorite());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkFavoriteGroup:
                ExpandableListView expandableListView = (ExpandableListView) v.getParent().getParent();

                View vParent = (View) v.getParent();
                int groupPosition = expandableListView.getPositionForView(vParent);

                for (int i = 0; i < mGroups.get(groupPosition).size(); i++) {

                    mGroups.get(groupPosition).get(i).setFavorite(((CheckBox) v).isChecked() ? true : false);
                }
                notifyDataSetChanged();
                break;
            case R.id.checkFavorite:

        }
    }
}