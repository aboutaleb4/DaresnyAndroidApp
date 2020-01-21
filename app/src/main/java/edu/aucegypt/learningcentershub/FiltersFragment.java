package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FiltersFragment extends Fragment implements View.OnClickListener {

    private filtersFragmentOnClickListener listener;

    Button closeBtn;

    public interface filtersFragmentOnClickListener {

        public void onClickClose();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_filters, container, false);

        closeBtn = (Button)  view.findViewById(R.id.closeBtn);

        closeBtn.setOnClickListener(this);
        // Inflate the layout for this fragment


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


    public void onClick(View view){
        listener.onClickClose();
    }
}