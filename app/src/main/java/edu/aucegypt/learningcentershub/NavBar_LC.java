package edu.aucegypt.learningcentershub;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class NavBar_LC extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.navbar_lc, container, false);
        BottomNavigationView navigationView = view.findViewById(R.id.navigation2);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        return view;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;
        switch (menuItem.getTitle().toString()) {
            case "Home":
                selectedFragment = new home_frag();
                break;
            case "Add Course":
                selectedFragment = new add_course_frag();
                break;

    }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, selectedFragment).commit();
        return true;
    }
}