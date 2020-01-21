package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class CourseInfo_frag extends Fragment {

    LinearLayout expandableLearningCenter;
    Button arrowBtnLearningCenter;
    CardView cardViewLearningCenter;
    Button registerBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_course_info, container, false);

        expandableLearningCenter = (LinearLayout) view.findViewById(R.id.expandableLearningCenter);
        arrowBtnLearningCenter = (Button) view.findViewById(R.id.arrowBtnLearningCenter);
        cardViewLearningCenter = (CardView) view.findViewById(R.id.cardViewLearningCenter);
        registerBtn = (Button) view.findViewById(R.id.registerBtn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment selectedFragment = null;
                selectedFragment = new CourseRegisterActivity();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment6_2, selectedFragment).commit();

            }
        });



        arrowBtnLearningCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChangeBounds transition = new ChangeBounds();
                transition.setDuration(100L);                       // Sets a duration of 100 millisecondss
                if(expandableLearningCenter.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardViewLearningCenter,transition);
                    expandableLearningCenter.setVisibility(View.VISIBLE);
                    arrowBtnLearningCenter.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                }else {
                    TransitionManager.beginDelayedTransition(cardViewLearningCenter,transition);
                    expandableLearningCenter.setVisibility(View.GONE);
                    arrowBtnLearningCenter.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

                }
            }
        });

        return view;
    }
}
