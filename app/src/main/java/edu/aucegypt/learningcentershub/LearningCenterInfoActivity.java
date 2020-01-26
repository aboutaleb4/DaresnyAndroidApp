package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.LearningCenter;
import edu.aucegypt.learningcentershub.ui.login.LoginActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class LearningCenterInfoActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    TextView phone;
    TextView address;
    JSONObject myResponseReader;
    ImageView logo;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningcenter_info);
        name = (TextView) findViewById(R.id.learningcenter_info_name);
        description = (TextView) findViewById(R.id.learningcenter_info_description);
        phone = (TextView) findViewById(R.id.learningcenter_info_phonenumber);
        logo = (ImageView) findViewById(R.id.learningcenter_info_image);
        address = (TextView) findViewById(R.id.learningcenter_info_address);

        String url_api = url + "myroute/LCinfo";

        Bundle mBundle = getIntent().getExtras();
        url_api = url_api + "?id=" + Integer.toString(mBundle.getInt("LCID"));

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url_api)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    if (myResponse != "") {

                            LearningCenterInfoActivity.this.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                myResponseReader = new JSONObject(myResponse);
                                                name.setText(myResponseReader.getString("LCname"));
                                                description.setText(myResponseReader.getString("Description"));
                                                phone.setText(myResponseReader.getString("PhoneNo"));
                                                new DownloadImageTask(logo)
                                                        .execute(url+"images/"+ myResponseReader.getString("Logo"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                            );

                    }

                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
}