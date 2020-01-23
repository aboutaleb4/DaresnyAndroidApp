package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main_frag extends Fragment implements RecyclerViewAdapter.RecyclerViewListner, View.OnClickListener {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] LearningCenters;
    int[] learningCentersIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    String[] Courses;
    String[] Courses_learningCenter;
    String[] Courses_Price;
    int[] coursesIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};

    private categoriesOnClickListener listener;
    private learningCenterOnClickListener listener2;
    TextView seeAllCategories;
    TextView seeAllLearningCenters;

    public interface categoriesOnClickListener
    {
         void onCategoriesListener();
    }

    public interface learningCenterOnClickListener
    {
        void onLearningCenterListener();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_frag, container, false);

        Category = getResources().getStringArray(R.array.category_4);
        LearningCenters = getResources().getStringArray(R.array.learningcenters_4);
        Courses = getResources().getStringArray(R.array.courses_4);
        Courses_learningCenter = getResources().getStringArray(R.array.courses_learningcenter_4);
        Courses_Price = getResources().getStringArray(R.array.courses_price_4);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), Category, categoryIcon, this);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView_1 = view.findViewById(R.id.recyclerview_id_1);
        recyclerView_1.setLayoutManager(new GridLayoutManager(getContext(),4));
        RecyclerViewAdapter adapter_1 = new RecyclerViewAdapter(getContext(), LearningCenters, learningCentersIcon, this);
        recyclerView_1.setAdapter(adapter_1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView_2 = view.findViewById(R.id.recyclerview_id_2);
        recyclerView_2.setLayoutManager(layoutManager);
        CoursesAdapter adapter_2 = new CoursesAdapter(getContext(), Courses, Courses_learningCenter, Courses_Price, coursesIcon);
        recyclerView_2.setAdapter(adapter_2);

        //view = inflater.inflate(R.layout.activity_categories, container, false);
        seeAllCategories = (TextView) view.findViewById(R.id.seeallcategories);
        seeAllCategories.setOnClickListener(this);
        //view = inflater.inflate(R.layout.activity_categories, container, false);
        seeAllLearningCenters = (TextView) view.findViewById(R.id.seealllearningcenters);
        seeAllLearningCenters.setOnClickListener(this);


        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof categoriesOnClickListener){
            listener = (categoriesOnClickListener) context;
        }else {
            throw  new ClassCastException(context.toString()
                    + " must implement categoriesOnClickListener.");
        }
        if(context instanceof learningCenterOnClickListener){
            listener2 = (learningCenterOnClickListener) context;
        }else {
            throw new ClassCastException(context.toString()
                    + " must implement learningCenterOnClickListener.");
        }

    }

    public void onClick(View view){
        listener.onCategoriesListener();
    }

    public void onClickLearningCenters(View view){
        listener2.onLearningCenterListener();
    }


    @Override
    public void RecyclerViewClick(int position) {

    }




}