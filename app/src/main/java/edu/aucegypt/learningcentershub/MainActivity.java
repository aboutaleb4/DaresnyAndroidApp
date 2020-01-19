package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] LearningCenters;
    int[] learningCentersIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] Courses;
    int[] coursesIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Category = getResources().getStringArray(R.array.category_4);
        LearningCenters = getResources().getStringArray(R.array.learningcenters_4);
        Courses = getResources().getStringArray(R.array.courses_4);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Category, categoryIcon);
        recyclerView.setAdapter(adapter);
        RecyclerView recyclerView_1 = findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter_1 = new RecyclerViewAdapter(this, LearningCenters, learningCentersIcon);
        recyclerView_1.setAdapter(adapter_1);
        RecyclerView recyclerView_2 = findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter_2 = new RecyclerViewAdapter(this, Courses, coursesIcon);
        recyclerView_2.setAdapter(adapter_2);

    }
}
