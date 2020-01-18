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
        if (menuItem.getTitle().toString().equals("Home")) {
            Intent i = new Intent("edu.aucegypt.learningcentershub.ADMIN_HOME");
            startActivity(i);
            return true;
        }
        if (menuItem.getTitle().toString().equals("Add Course"))
        {
            Intent i = new Intent("edu.aucegypt.learningcentershub.ADD_COURSE");
            startActivity(i);
            return true;
        }
        return false;
    }
}