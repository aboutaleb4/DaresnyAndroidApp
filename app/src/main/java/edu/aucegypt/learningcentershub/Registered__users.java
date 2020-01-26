package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Registered__users extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        savedInstanceState=getIntent().getExtras();
        String [] fnames = new String[0];
        String [] lnames = new String[0];
        String extra = null;
    if (savedInstanceState != null) {
            fnames =  savedInstanceState.getStringArray("fnames");
             lnames = savedInstanceState.getStringArray("lnames");
             extra =  getIntent().getStringExtra("data");

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_users);


        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new reg_user();
        Bundle b = new Bundle();
        b.putStringArray("fname",fnames);
        b.putStringArray("lname",lnames);
        b.putString("data",extra);
        fragment.setArguments(b);
        fragmentTransaction.replace(R.id.Frame_, fragment);
        fragmentTransaction.commit();
        Toolbar myToolbar = findViewById(R.id.topbar5);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton button = findViewById(R.id.buttonback2);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        finish();
    }

}

