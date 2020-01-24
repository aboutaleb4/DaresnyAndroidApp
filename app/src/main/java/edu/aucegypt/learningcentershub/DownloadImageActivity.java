package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        new DownloadImageTask((ImageView) findViewById(R.id.image1))
                .execute("http://10.40.47.60:3000/images/tech.png");
    }
}

