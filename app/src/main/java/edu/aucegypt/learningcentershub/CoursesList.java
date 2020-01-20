package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;

public class CoursesList extends Fragment {

    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Course> arrayList;



    coursesListAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.my_account_frag, container, false);

        String [] name = getResources().getStringArray(R.array.courses_4);

        int [] image = {R.drawable.science,
                R.drawable.programming,
                R.drawable.engineering,
                R.drawable.language};

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchView = (SearchView) view.findViewById(R.id.searchView);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < image.length; i++) {
            Course itemModel = new Course();
            itemModel.setCourseName(name[i]);
            itemModel.setImage(image[i]);
            arrayList.add(itemModel);

        }


        adapter = new coursesListAdapter(getContext(), arrayList);
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
        return  view;
    }
}
