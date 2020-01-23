package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FiltersActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView_CategoryFilter;
    RecyclerView recyclerView_AreaFilter;

    private filtersFragmentOnClickListener listener;
    private filtersListAdapter catAdapter;
    private filtersListAdapter areaAdapter;
    Button closeBtn;

    public interface filtersFragmentOnClickListener {

        public void onClickClose();

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_filters);

        closeBtn = (Button)  findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(this);
        initRecyclerView_Cat();
        initRecyclerView_Area();



    }

    private void initRecyclerView_Cat() {
        recyclerView_CategoryFilter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_CategoryFilter.setLayoutManager(linearLayoutManager);

        final String catNames [] = getResources().getStringArray(R.array.categories);
        catAdapter = new filtersListAdapter(catNames,this);
        recyclerView_CategoryFilter.setAdapter(catAdapter);
        recyclerView_CategoryFilter.setNestedScrollingEnabled(false);
    }
    private void initRecyclerView_Area() {
        recyclerView_AreaFilter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_AreaFilter.setLayoutManager(linearLayoutManager);

        final String areaNames [] = getResources().getStringArray(R.array.Areas);
        areaAdapter = new filtersListAdapter(areaNames,this);
        recyclerView_AreaFilter.setAdapter(areaAdapter);
        recyclerView_AreaFilter.setNestedScrollingEnabled(false);
    }


    public void onClick(View view){
        listener.onClickClose();
    }
}