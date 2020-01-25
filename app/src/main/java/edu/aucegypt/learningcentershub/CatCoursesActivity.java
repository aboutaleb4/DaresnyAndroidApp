package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CatCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_courses);

        String CatName;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            CatName = bundle.getString("CatName");
            setTitle(CatName);
        }

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button





    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
