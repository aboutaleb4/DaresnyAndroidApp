package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;

public class CoursesList extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Course> arrayList;



    coursesListAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        String [] name = getResources().getStringArray(R.array.courses_4);

        int [] image = {R.drawable.science,
                R.drawable.programming,
                R.drawable.engineering,
                R.drawable.language};

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        searchView = (SearchView) findViewById(R.id.searchView);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < image.length; i++) {
            Course itemModel = new Course();
            itemModel.setCourseName(name[i]);
            itemModel.setImage(image[i]);
            arrayList.add(itemModel);

        }


        adapter = new coursesListAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
