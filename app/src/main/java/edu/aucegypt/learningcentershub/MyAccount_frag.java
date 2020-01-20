package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyAccount_frag extends Fragment {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_account_frag, container, false);

        Category = getResources().getStringArray(R.array.category_4);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_id_3);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), Category, categoryIcon);
        recyclerView.setAdapter(adapter);
        return view;

    }
}