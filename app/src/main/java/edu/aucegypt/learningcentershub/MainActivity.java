package edu.aucegypt.learningcentershub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment6, new NavBar());
        fragmentTransaction.replace(R.id.fragment6_1, new TopBar());
        fragmentTransaction.replace(R.id.fragment6_2, new main_frag());
        fragmentTransaction.commit();


    }

}

