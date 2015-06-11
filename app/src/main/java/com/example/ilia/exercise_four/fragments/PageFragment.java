package com.example.ilia.exercise_four.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ilia.exercise_four.R;

public class PageFragment extends Fragment {

    public static PageFragment newInstance(String title) {

        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page_fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(getArguments().getString("title"));
        return view;
    }
}