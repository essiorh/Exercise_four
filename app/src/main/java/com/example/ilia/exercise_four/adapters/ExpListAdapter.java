package com.example.ilia.exercise_four.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilia.exercise_four.interfaces.IConnectFragmentWithActivity;
import com.example.ilia.exercise_four.R;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;
    private Activity mActivity;
    private int[] offset;
    public ExpListAdapter(Context context, ArrayList<ArrayList<String>> groups,Activity activity) {
        mContext = context;
        mGroups = groups;
        mActivity = activity;
        offset=new int[mGroups.size()];
        int off=0;
        for (int i=0;i<mGroups.size();i++) {
            offset[i]=off;
            int vr=mGroups.get(i).size();
            off+=vr;
        }

    }

    public int getOffset(int group) {
        return mGroups!=null ? offset[group] : 0;
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

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        TypedArray stringListForExpanedList = convertView.getResources().obtainTypedArray(R.array.android_version);
        CharSequence[] strings= stringListForExpanedList.getTextArray(groupPosition);
        String string  = (String) strings[0];
        textGroup.setText("Group " + string);
        CheckBox checkBoxFavorite=(CheckBox)convertView.findViewById(R.id.checkFavoriteGroup);
        checkBoxFavorite.setChecked(true);


        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));
        CheckBox checkBoxFavorite=(CheckBox)convertView.findViewById(R.id.checkFavorite);
        checkBoxFavorite.setChecked(true);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}