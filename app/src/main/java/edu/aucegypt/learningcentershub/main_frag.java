package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main_frag extends Fragment {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] LearningCenters;
    int[] learningCentersIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] Courses;
    int[] coursesIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_frag, container, false);

        Category = getResources().getStringArray(R.array.category_4);
        LearningCenters = getResources().getStringArray(R.array.learningcenters_4);
        Courses = getResources().getStringArray(R.array.courses_4);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), Category, categoryIcon);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView_1 = view.findViewById(R.id.recyclerview_id_1);
        recyclerView_1.setLayoutManager(new GridLayoutManager(getContext(),4));
        RecyclerViewAdapter adapter_1 = new RecyclerViewAdapter(getContext(), LearningCenters, learningCentersIcon);
        recyclerView_1.setAdapter(adapter_1);

        RecyclerView recyclerView_2 = view.findViewById(R.id.recyclerview_id_2);
        recyclerView_2.setLayoutManager(new GridLayoutManager(getContext(),4));
        RecyclerViewAdapter adapter_2 = new RecyclerViewAdapter(getContext(), Courses, coursesIcon);
        recyclerView_2.setAdapter(adapter_2);
        recyclerView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),CourseInfo.class);
                startActivity(i);
            }
        });
        return view;

    }
}