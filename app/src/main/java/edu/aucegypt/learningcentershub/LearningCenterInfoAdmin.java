package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LearningCenterInfoAdmin extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningcenter_info_admin);

        Toolbar myToolbar = findViewById(R.id.topbar3);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton button = findViewById(R.id.buttonback);
        button.setOnClickListener(this);
        //load exisiting info
    }

    @Override
    public void onClick(View view) {
        //should save data
        finish();
    }
}