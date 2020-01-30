package edu.aucegypt.learningcentershub.ui.user.mycoursesscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.ui.TopBar;
import edu.aucegypt.learningcentershub.ui.user.homescreen.NavBar;

public class MyCourses extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment7, new NavBar());
        fragmentTransaction.replace(R.id.fragment7_1, new TopBar());
        fragmentTransaction.replace(R.id.fragment7_2, new MyCourses_frag());
        fragmentTransaction.commit();


    }

}

