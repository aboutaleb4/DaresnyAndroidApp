package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import edu.aucegypt.learningcentershub.data.LearningCenter;

public class MainActivity extends AppCompatActivity implements CoursesList_frag.coursesFragOnClickListener, FiltersFragment.filtersFragmentOnClickListener, main_frag.categoriesOnClickListener, main_frag.learningCenterOnClickListener{

    FrameLayout filters_layout;
    ConstraintLayout main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        filters_layout = (FrameLayout) findViewById(R.id.filters_layout);
        main_layout = (ConstraintLayout) findViewById(R.id.main_layout);


        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment6, new NavBar());
        fragmentTransaction.replace(R.id.fragment6_1, new TopBar());
        fragmentTransaction.replace(R.id.fragment6_2, new main_frag());
        fragmentTransaction.replace(R.id.filters_layout, new FiltersFragment());
       // fragmentTransaction.replace(R.id.fragmentseeallcategories, new Categories());
        //fragmentTransaction.replace(R.id.seealllearningcenters, new LearningCenter());
        fragmentTransaction.commit();




    }

    @Override
    public void onFiltersBtnClick() {
        final ChangeBounds transition = new ChangeBounds();
        transition.setDuration(100L);                       // Sets a duration of 100 millisecondss

        if(filters_layout.getVisibility()== View.GONE){
            TransitionManager.beginDelayedTransition(main_layout,transition);
            filters_layout.setVisibility(View.VISIBLE);
        }
    }



    public void onClickClose(){
        final ChangeBounds transition = new ChangeBounds();
        transition.setDuration(100L);                       // Sets a duration of 100 millisecondss

        if(filters_layout.getVisibility()!= View.GONE){
            TransitionManager.beginDelayedTransition(main_layout,transition);
            filters_layout.setVisibility(View.GONE);
        }
    }

    public void onCategoriesListener()
    {
        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment6_2, new Categories()).commit();

    }

    public void onLearningCenterListener()
    {
        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment6_2, new LearningCenter_frag()).commit();

    }
}

