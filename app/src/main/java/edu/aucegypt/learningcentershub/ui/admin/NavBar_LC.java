package edu.aucegypt.learningcentershub.ui.admin;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.ui.user.myaccountscreen.MyAccount_frag;


public class NavBar_LC extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    String  lcid,lcname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            Bundle b = getArguments();
            lcid = b.getString("LCID");
            lcname = b.getString("LCname");

        }
        View view =  inflater.inflate(R.layout.navbar_lc, container, false);
        BottomNavigationView navigationView = view.findViewById(R.id.navigation2);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        return view;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null, selectedFragment2= null;
        switch (menuItem.getTitle().toString()) {
            case "Home":
                selectedFragment = new home_frag();
                Bundle b2 = new Bundle();
                b2.putString("LCname",lcname);
                selectedFragment.setArguments(b2);

                break;
            case "Add Course":
                selectedFragment = new add_course_frag();
                Bundle b = new Bundle();
                b.putString("LCID",lcid);
                selectedFragment.setArguments(b);

                break;
            case "My Account":
                selectedFragment = new MyAccount_frag();
                break;

    }
    if (selectedFragment instanceof  add_course_frag)
    {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_21, selectedFragment).commit();
        Fragment frag = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_2);
        if (frag != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_2)).commit();


    }
    else {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, selectedFragment).commit();
        Fragment frag = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_21);
        if (frag != null)
            getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_21)).commit();

    }
           return true;
    }
}