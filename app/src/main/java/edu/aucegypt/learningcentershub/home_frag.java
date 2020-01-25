package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class home_frag extends Fragment{
    RecyclerView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String strtext = null;
        if (getArguments() != null) {
            Bundle b = getArguments();
            strtext = b.getString("LCname");
        }
        View view = inflater.inflate(R.layout.home_frag, container, false);
        listView = view.findViewById(R.id.text_lc_home_actions);
        TextView textView = view.findViewById(R.id.text_lc_home_name);
        textView.setText(strtext);
        String [] Data = {"Edit Information","Edit Courses"};
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter3(getContext(), Data));
        listView.setNestedScrollingEnabled(false);
        return view;
    }




}
