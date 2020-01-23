package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class LearningCenter_frag extends Fragment {

    // private learningCenterOnClickListener listener;
    TextView seeAllLearningCenters;



    public View OnCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_learningcenters, container, false);
        return view;

    }





}
