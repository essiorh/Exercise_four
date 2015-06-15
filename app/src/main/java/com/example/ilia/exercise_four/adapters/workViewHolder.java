package com.example.ilia.exercise_four.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ilia.exercise_four.R;
import com.example.ilia.exercise_four.interfaces.ICheckFavorite;
import com.example.ilia.exercise_four.models.ItemContainer;

import java.util.ArrayList;
import java.util.List;

public class workViewHolder extends ArrayAdapter implements ICheckFavorite, CheckBox.OnClickListener{

    private List<ItemContainer> list;
    private List<ItemContainer> listFavorite;
    private final Activity context;

    public workViewHolder(Activity context, List<ItemContainer> list) {
        super(context, R.layout.list_item_view, list);
        this.context = context;
        this.list = list;
        listFavorite=new ArrayList<>();
        listFavorite.addAll(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkFavoriteGroup:
                ListView listView = (ListView) v.getParent().getParent();

                View vParent = (View) v.getParent();
                int groupPosition = listView.getPositionForView(vParent);

                list.get(groupPosition).setFavorite(((CheckBox) v).isChecked() ? true : false);

                notifyDataSetChanged();
                break;
        }
    }

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.list_item_view, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
            viewHolder.checkbox
                    .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView,
                                                     boolean isChecked) {
                            ItemContainer element = (ItemContainer) viewHolder.checkbox
                                    .getTag();
                            element.setFavorite(buttonView.isChecked());

                        }
                    });
            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getTitle());
        holder.checkbox.setChecked(list.get(position).getFavorite());
        return view;
    }

    @Override
    public void checkFavorite(boolean flag) {
        list.clear();
        if (flag) {
            for (int i = 0; i < listFavorite.size(); i++) {

                boolean dataNames = listFavorite.get(i).getFavorite();
                if (flag == dataNames) {
                    list.add(listFavorite.get(i));
                }
            }
        } else {
            list.addAll(listFavorite);
        }
        notifyDataSetChanged();
    }
}
