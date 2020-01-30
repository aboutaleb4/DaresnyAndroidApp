package edu.aucegypt.learningcentershub.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.ui.user.homescreen.MainActivity;

public class StartingActivity extends AppCompatActivity {
    ImageView logo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        logo = (ImageView) findViewById(R.id.startingactivityimage);
        logo.setImageResource(R.drawable.mainlogo);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1000);  //Delay of 1 second1
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(StartingActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();

    }


}
