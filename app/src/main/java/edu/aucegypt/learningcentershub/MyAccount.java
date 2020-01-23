package edu.aucegypt.learningcentershub;


import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment8, new NavBar());
        fragmentTransaction.replace(R.id.fragment8_1, new TopBar());
        fragmentTransaction.replace(R.id.fragment8_2, new MyAccount_frag());
        fragmentTransaction.commit();
    }

}