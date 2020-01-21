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

import butterknife.BindView;
import butterknife.ButterKnife;

public class FiltersActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView_CategoryFilter;

    private filtersFragmentOnClickListener listener;

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
        initRecyclerView();


    }

    private void initRecyclerView() {
        recyclerView_CategoryFilter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_CategoryFilter.setLayoutManager(linearLayoutManager);
    }


    public void onClick(View view){
        listener.onClickClose();
    }
}