package edu.aucegypt.learningcentershub.ui.user.registercoursescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.ui.user.homescreen.MainActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.service.network.APIcall.url;

public class CourseRegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("login_shared_preference", MODE_PRIVATE);
        final Boolean status = prefs.getBoolean("status", false);
        final int uid = prefs.getInt("uid", 0); //0 is the default value.
        setContentView(R.layout.activity_course_register);

        super.onCreate(savedInstanceState);
        final EditText Fname = findViewById(R.id.signup_input_name);
        final EditText Lname = findViewById(R.id.signup_input_last_name);
        final EditText PhoneNo = findViewById(R.id.signup_input_number);
        final EditText BDate = findViewById(R.id.signup_birthdate);
        Button reg = (Button) findViewById(R.id.btn_signup);
        final Bundle mBundle = getIntent().getExtras();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status) {
                    String jsonString;
                    JSONObject json = new JSONObject();
                    try {
                        json.put("Fname", Fname.getText());
                        json.put("Lname", Lname.getText().toString());
                        json.put("PhoneNo", PhoneNo.getText().toString());
                        json.put("BDate", BDate.getText().toString());
                        json.put("UID", uid);
                        json.put("CID", mBundle.getInt("cid"));
                        jsonString = json.toString();


                        String url_api = url + "myroute/registerCourse";

                        OkHttpClient client = new OkHttpClient();
                        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

                        final RequestBody body = RequestBody.create(jsonString, JSON);
                        final Request request = new Request.Builder()
                                .url(url_api)
                                .post(body)
                                .build();

                        client.newCall(request).enqueue(new Callback() {

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    final String myResponse = response.body().string();
                                            Intent mIntent;
                                            mIntent = new Intent(CourseRegisterActivity.this, Payment.class);
                                            startActivity(mIntent);
                                    }
                                }


                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                    Intent i = new Intent(CourseRegisterActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }
    
}
