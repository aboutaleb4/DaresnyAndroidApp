package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;

public class CoursesList_frag extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Course> arrayList;
    FrameLayout filters_layout;
    ConstraintLayout main_layout;
    Button filterBtn;


    coursesListAdapter adapter;
    private coursesFragOnClickListener listener;

    public interface coursesFragOnClickListener {

        public void onFiltersBtnClick();
        ;;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_courses_list, container, false);

        String [] name = getResources().getStringArray(R.array.courses_4);

        int [] image = {R.drawable.science,
                R.drawable.programming,
                R.drawable.engineering,
                R.drawable.language};

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        filters_layout = (FrameLayout) view.findViewById(R.id.filters_layout);
        main_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);
        filterBtn = (Button) view.findViewById(R.id.filtersBtn);

        filterBtn.setOnClickListener(this);

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

    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof coursesFragOnClickListener){
            listener = (coursesFragOnClickListener) context;

        }else {
            throw  new ClassCastException(context.toString()
                + " must implement coursesFragOnClickListener.onFiltersBtnClick.");
        }
    }

    public void onClick(View view){
        listener.onFiltersBtnClick();
    }
}
