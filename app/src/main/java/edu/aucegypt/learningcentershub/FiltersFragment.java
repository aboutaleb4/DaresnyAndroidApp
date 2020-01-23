package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FiltersFragment extends Fragment implements View.OnClickListener {

    private filtersFragmentOnClickListener listener;

    Button closeBtn;

    RecyclerView recyclerView_CategoryFilter;
    RecyclerView recyclerView_AreaFilter;

    private filtersListAdapter catAdapter;
    private filtersListAdapter areaAdapter;

    public interface filtersFragmentOnClickListener {

        public void onClickClose();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_filters, container, false);

        closeBtn = (Button)  view.findViewById(R.id.closeBtn);
        recyclerView_CategoryFilter = view.findViewById(R.id.recyclerView_CategoryFilter);
        recyclerView_AreaFilter = view.findViewById(R.id.recyclerView_AreaFilter);

        closeBtn.setOnClickListener(this);
        // Inflate the layout for this fragment

        initRecyclerView_Cat(view);
        initRecyclerView_Area(view);


        return  view;
    }

    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof filtersFragmentOnClickListener){
            listener = (filtersFragmentOnClickListener) context;
        }else {
            throw  new ClassCastException(context.toString()
                    + " must implement filtersFragmentOnClickListener.onClickClose.");
        }
    }

    private void initRecyclerView_Cat(View view) {
        recyclerView_CategoryFilter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView_CategoryFilter.setLayoutManager(linearLayoutManager);

        final String catNames [] = getResources().getStringArray(R.array.categories);
        catAdapter = new filtersListAdapter(catNames, view.getContext());
        recyclerView_CategoryFilter.setAdapter(catAdapter);
        recyclerView_CategoryFilter.setNestedScrollingEnabled(false);
    }
    private void initRecyclerView_Area(View view) {
        recyclerView_AreaFilter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView_AreaFilter.setLayoutManager(linearLayoutManager);

        final String areaNames [] = getResources().getStringArray(R.array.Areas);
        areaAdapter = new filtersListAdapter(areaNames,view.getContext());
        recyclerView_AreaFilter.setAdapter(areaAdapter);
        recyclerView_AreaFilter.setNestedScrollingEnabled(false);
    }


    public void onClick(View view){
        listener.onClickClose();
    }
}