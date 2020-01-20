package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static java.security.AccessController.getContext;

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

