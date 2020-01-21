package edu.aucegypt.learningcentershub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment2, new NavBar_LC());
        fragmentTransaction.replace(R.id.fragment2_1, new TopBar());
      //  fragmentTransaction.replace(R.id.fragment2_2, new Registered__users());
        fragmentTransaction.commit();

    }
}
