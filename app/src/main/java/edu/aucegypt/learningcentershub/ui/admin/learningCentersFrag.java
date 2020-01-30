package edu.aucegypt.learningcentershub.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.ui.admin.rvadapter3;

public class learningCentersFrag extends Fragment{
    RecyclerView listView;
    ArrayList<String> courses ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            Bundle b = getArguments();
            courses = b.getStringArrayList("Courses");
        }
        View view = inflater.inflate(R.layout.lc_corses_frag, container, false);
        listView = view.findViewById(R.id.text_lc_courses_actions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        String[] stockArr = new String[courses.size()];
        stockArr = courses.toArray(stockArr);
        listView.setAdapter(new rvadapter3(getContext(), stockArr));
        listView.setNestedScrollingEnabled(false);
        return view;
    }




}
