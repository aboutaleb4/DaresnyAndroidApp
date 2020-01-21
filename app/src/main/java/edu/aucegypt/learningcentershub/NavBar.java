package edu.aucegypt.learningcentershub;


import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class NavBar extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.navbar, container, false);
        BottomNavigationView navigationView = view.findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;
        /*switch (menuItem.getTitle().toString()) {
            case "Home":
                selectedFragment = new main_frag();
                break;
            case "My Account":
                selectedFragment = new MyAccount_frag();
                break;
            case "Search":
                selectedFragment = new CoursesList_frag();
                break;

            case "My Courses":
                selectedFragment =  new MyCourses_frag();
                break;


        }
*/
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment6_2, selectedFragment).commit();
        return true;
    }


}