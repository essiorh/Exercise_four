package com.example.ilia.exercise_four.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.interfaces.IDeleteElement;
import com.example.ilia.exercise_four.models.ItemContainer;

import java.util.ArrayList;

/**
 * Created by ilia on 08.06.15.
 */
public class DefListAdapter extends ArrayAdapter<String> implements IDeleteElement {
    private ArrayList<String> mGroups;
    private Context mContext;

    public DefListAdapter(Context context, ArrayList<String> groups){
        super(context,R.layout.group_view);
        mContext = context;
        mGroups = groups;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mGroups.size();
    }

    @Override
    public String getItem(int position) {
        return mGroups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }
        TextView textChild = (TextView) convertView.findViewById(R.id.textGroup);
        textChild.setText(mGroups.get(position));

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void deleteElement(String position) {
        mGroups.remove(position);
        notifyDataSetChanged();

    }

    @Override
    public void deleteExpandableElement(ItemContainer position) {

    }
}
