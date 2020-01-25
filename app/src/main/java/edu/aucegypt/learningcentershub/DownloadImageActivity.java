package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import edu.aucegypt.learningcentershub.Network.APIcall;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class DownloadImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);

        new DownloadImageTask((ImageView) findViewById(R.id.image1))
                .execute( url + "images/tech.png");
    }
}

