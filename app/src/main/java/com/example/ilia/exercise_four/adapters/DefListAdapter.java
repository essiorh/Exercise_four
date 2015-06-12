package com.example.ilia.exercise_four.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.models.ItemContainer;

import java.util.ArrayList;

/**
 * Created by ilia on 08.06.15.
 */
public class DefListAdapter implements ListAdapter {
    private ArrayList<ItemContainer> mGroups;
    private Context mContext;
    private LayoutInflater mLayoutInflater = null;

    public DefListAdapter(Context context, ArrayList<ItemContainer> groups){
        mContext = context;
        mGroups = groups;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public Object getItem(int position) {
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
            convertView = mLayoutInflater.inflate(R.layout.list_item_view, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.label);
        textChild.setText(mGroups.get(position).getTitle());




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

}
