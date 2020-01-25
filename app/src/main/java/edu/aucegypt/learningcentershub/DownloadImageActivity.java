package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class DownloadImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        new DownloadImageTask((ImageView) findViewById(R.id.learningcenter_info_image))
                .execute( url + "images/tech.png");
    }
}

