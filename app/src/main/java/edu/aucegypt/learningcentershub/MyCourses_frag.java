package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyCourses_frag extends Fragment {

    String[] favourites;
    int[] favouritesIcons = {R.drawable.science, R.drawable.science, R.drawable.science,
            R.drawable.science, R.drawable.science, R.drawable.science, R.drawable.science, R.drawable.science
            , R.drawable.science, R.drawable.science, R.drawable.science, R.drawable.science};
    String[] registered;
    int[] registeredIcons = {R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming
            ,R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming
            ,R.drawable.programming, R.drawable.programming, R.drawable.programming, R.drawable.programming};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_courses_frag, container, false);

        favourites = getResources().getStringArray(R.array.favourites);
        registered = getResources().getStringArray(R.array.registered);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_favourites);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), favourites, favouritesIcons);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView_1 = view.findViewById(R.id.recyclerview_registered);
        recyclerView_1.setLayoutManager(new GridLayoutManager(getContext(), 4));
        RecyclerViewAdapter adapter_1 = new RecyclerViewAdapter(getContext(), registered, registeredIcons);
        recyclerView_1.setAdapter(adapter_1);

        return view;
    }
}
