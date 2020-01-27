package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FiltersFragment extends Fragment implements View.OnClickListener {

    ArrayList<String> CatNames = new ArrayList<String>();
    ArrayList<String> AreaNames = new ArrayList<String>();
    ArrayList<Integer> DateFilters = new ArrayList<Integer>();
    public ArrayList<String> getAreaNames() {
        return AreaNames;
    }

    public void setAreaNames(ArrayList<String> areaNames) {
        AreaNames = areaNames;
    }

    private filtersFragmentOnClickListener listener;

    Button closeBtn;
    Button applyBtn;

    RecyclerView recyclerView_CategoryFilter;
    RecyclerView recyclerView_AreaFilter;

    SeekBar seekBar;
    TextView textView;

    CheckBox cbBox1, cbBox2, cbBox3, cbBox4;

    int seekBarVal=0;


    public ArrayList<String> getCatNames() {
        return CatNames;
    }

    public void setCatNames(ArrayList<String> catNames) {
        CatNames = catNames;
    }




    private filtersListAdapter catAdapter;
    private filtersListAdapter areaAdapter;

    public interface filtersFragmentOnClickListener {

        public void onClickClose();
        public void onClickApply(ArrayList<String> CatNames, ArrayList<String> AreaNames, int Price);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_filters, container, false);

        closeBtn = (Button)  view.findViewById(R.id.closeBtn);
        applyBtn =(Button) view.findViewById(R.id.applyBtn);

        recyclerView_CategoryFilter = view.findViewById(R.id.recyclerView_CategoryFilter);
        recyclerView_AreaFilter = view.findViewById(R.id.recyclerView_AreaFilter);

        seekBar = view.findViewById(R.id.sbId);
        textView = view.findViewById(R.id.price_txt);

        cbBox1 = (CheckBox) view.findViewById(R.id.cbBox1);
        cbBox2 = (CheckBox) view.findViewById(R.id.cbBox2);
        cbBox3 = (CheckBox) view.findViewById(R.id.cbBox3);
        cbBox4 = (CheckBox) view.findViewById(R.id.cbBox4);


        closeBtn.setOnClickListener(this);
        applyBtn.setOnClickListener(this);
        // Inflate the layout for this fragment

        initRecyclerView_Cat(view);
        initRecyclerView_Area(view);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 6 * seekBar.getThumbOffset())) / seekBar.getMax();
                seekBarVal = progress;
                textView.setText("" + progress);
                textView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                textView.setY(seekBar.getY()-70);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

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
        catAdapter = new filtersListAdapter(catNames, view.getContext(),"Category", communication);
        recyclerView_CategoryFilter.setAdapter(catAdapter);
        recyclerView_CategoryFilter.setNestedScrollingEnabled(false);
    }

    private void initRecyclerView_Area(View view) {
        recyclerView_AreaFilter.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView_AreaFilter.setLayoutManager(linearLayoutManager);

        final String areaNames [] = getResources().getStringArray(R.array.Areas);
        areaAdapter = new filtersListAdapter(areaNames,view.getContext(), "Area", communication);
        recyclerView_AreaFilter.setAdapter(areaAdapter);
        recyclerView_AreaFilter.setNestedScrollingEnabled(false);
    }

    filtersListAdapter.FragmentCommunication communication=new filtersListAdapter.FragmentCommunication() {
        @Override
        public void setCatNames(ArrayList<String> CatNames) {
            FiltersFragment.this.setCatNames(CatNames);
        }

        @Override
        public void setAreaNames(ArrayList<String> AreaNames) {
            FiltersFragment.this.setAreaNames(AreaNames);
        }


    };


    public void onClick(View view){

        switch (view.getId()){

            case R.id.closeBtn:
                listener.onClickClose();
                break;

            case R.id.applyBtn:
                listener.onClickApply(CatNames, AreaNames, seekBarVal);
                break;


            case R.id.cbBox1:
                if(cbBox1.isChecked())

                break;

            case R.id.cbBox2:
                break;

            case R.id.cbBox3:
                break;


            case R.id.cbBox4:
                break;




        }
    }
}