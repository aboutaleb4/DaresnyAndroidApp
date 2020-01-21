package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements CoursesList_frag.coursesFragOnClickListener {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] LearningCenters;
    int[] learningCentersIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] Courses;
    int[] coursesIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    FrameLayout filters_layout;
    ConstraintLayout main_layout;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_frag);


        filters_layout = (FrameLayout) findViewById(R.id.filters_layout);
        main_layout = (ConstraintLayout) findViewById(R.id.main_layout);


        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment6, new NavBar());
        fragmentTransaction.replace(R.id.fragment6_1, new TopBar());
        //fragmentTransaction.replace(R.id.filters_layout, new FiltersFragment());
        fragmentTransaction.commit();


        Category = getResources().getStringArray(R.array.category_4);
        LearningCenters = getResources().getStringArray(R.array.learningcenters_4);
        Courses = getResources().getStringArray(R.array.courses_4);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Category, categoryIcon);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView_1 = findViewById(R.id.recyclerview_id_1);
        recyclerView_1.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter_1 = new RecyclerViewAdapter(this, LearningCenters, learningCentersIcon);
        recyclerView_1.setAdapter(adapter_1);

        RecyclerView recyclerView_2 = findViewById(R.id.recyclerview_id_2);
        recyclerView_2.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter_2 = new RecyclerViewAdapter(this, Courses, coursesIcon);
        recyclerView_2.setAdapter(adapter_2);

    }

    public void onFiltersBtnClick() {
        final ChangeBounds transition = new ChangeBounds();
        transition.setDuration(100L);                       // Sets a duration of 100 millisecondss

        if(filters_layout.getVisibility()== View.GONE){
            TransitionManager.beginDelayedTransition(main_layout,transition);
            filters_layout.setVisibility(View.VISIBLE);
        }
    }


}