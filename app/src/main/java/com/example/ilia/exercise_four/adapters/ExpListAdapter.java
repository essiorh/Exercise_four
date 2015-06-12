package com.example.ilia.exercise_four.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.interfaces.ICheckFavorite;
import com.example.ilia.exercise_four.models.ItemContainer;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter implements CheckBox.OnClickListener,
        Filterable,ICheckFavorite {

    private ArrayList<ArrayList<ItemContainer>> mGroups;
    private ArrayList<ArrayList<ItemContainer>> mFilteredGroups;

    private Context mContext;
    private int[] offset;



    public ExpListAdapter(Context context, ArrayList<ArrayList<ItemContainer>> groups) {
        mContext = context;
        mGroups = groups;
        mFilteredGroups=new ArrayList<>();
        mFilteredGroups.addAll(groups);
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
        textGroup.setText("Group " + mGroups.get(groupPosition).get(0).getTitle());
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
                ((CheckBox) vParent.findViewById(R.id.checkFavoriteGroup)).setChecked(flag);
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


    @Override
    public void checkFavorite(boolean flag) {
        mGroups.clear();
        if (flag) {
            for (int i = 0; i < mFilteredGroups.size(); i++) {
                ArrayList<ItemContainer> itemContainers = new ArrayList<>();
                for (int j = 0; j < mFilteredGroups.get(i).size(); j++) {
                    boolean dataNames = mFilteredGroups.get(i).get(j).getFavorite();
                    if (flag == dataNames) {
                        if (itemContainers.size() == 0) {
                            itemContainers.add(mFilteredGroups.get(i).get(0));
                        }
                        itemContainers.add(mFilteredGroups.get(i).get(j));
                    }
                }
                if (itemContainers.size() != 0) {
                    mGroups.add(itemContainers);
                }
            }
        } else {
            mGroups.addAll(mFilteredGroups);
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (constraint.length()==0) {
                    mGroups.addAll(mFilteredGroups);
                } else {
                    mGroups = (ArrayList<ArrayList<ItemContainer>>) results.values;
                }
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                mGroups.clear();

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < mFilteredGroups.size(); i++) {
                    ArrayList<ItemContainer> itemContainers=new ArrayList<>();
                    for (int j=0;j<mFilteredGroups.get(i).size();j++) {
                        String dataNames = mFilteredGroups.get(i).get(j).getTitle();
                        String lowdataNames = dataNames.toLowerCase();

                        if (lowdataNames.contains(constraint.toString())) {
                            if (itemContainers.size()==0) {
                                itemContainers.add(mFilteredGroups.get(i).get(0));
                            }
                            itemContainers.add(mFilteredGroups.get(i).get(j));
                        }
                    }
                    if (itemContainers.size()!=0) {
                        mGroups.add(itemContainers);
                    }
                }

                results.count = mGroups.size();
                results.values = mGroups;

                return results;
            }
        };

        return filter;
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
}