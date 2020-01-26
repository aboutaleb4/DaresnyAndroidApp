package edu.aucegypt.learningcentershub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class CourseInfoAdmin extends AppCompatActivity implements View.OnClickListener {

    EditText name, desc, video,std, end, price, reg;
    TextView cat;
    ImageView imageView;
    String CID;
    public static ArrayList<String> message_f = new ArrayList<>();
    public static ArrayList<String> message_l = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        savedInstanceState=getIntent().getExtras();
         CID = savedInstanceState.getString("CID");
        try {
            Network_users();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info_admin);

        String CourseName = savedInstanceState.getString("CourseName");
        String CourseImage = savedInstanceState.getString("CourseImage");
        String Price = savedInstanceState.getString("Price");
        String RegFees = savedInstanceState.getString("RegFees");
        String StDate = savedInstanceState.getString("StDate");
        String EndDate = savedInstanceState.getString("EndDate");
        String Description = savedInstanceState.getString("Description");
        String Video = savedInstanceState.getString("Video");
        String LCID = savedInstanceState.getString("LCID");
        String likes = savedInstanceState.getString("likes");
        String enroll = savedInstanceState.getString("enroll");

        String CatName = savedInstanceState.getString("CatName");
        name = findViewById(R.id.crseName);
        name.setText(CourseName);
        cat = findViewById(R.id.crseCategory);
        cat.setText(CatName);
        imageView = findViewById(R.id.crseimage);
        //imageView.setImageResource(Integer.getInteger(CourseImage));
        desc = findViewById(R.id.crsedesc);
        desc.setText(Description);
        video = findViewById(R.id.crsevideo);
        video.setText(Video);
        std = findViewById(R.id.crsestd);
        std.setText(StDate);
        end = findViewById(R.id.crseend);
        end.setText(EndDate);
        price = findViewById(R.id.crseprice);
        price.setText(Price);
        reg = findViewById(R.id.crsereg);
        reg.setText(RegFees);

        TextView faves = findViewById(R.id.crseLikes);
        faves.setText(likes);

        TextView reg = findViewById(R.id.crseuser);
        reg.setText(enroll);

        LinearLayout linearLayout = findViewById(R.id.reg_click);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Registered__users.class);
                i.putExtra("fnames",message_f.toArray());
                i.putExtra("lnames",message_l.toArray());
                startActivity(i);

            }
        });

        Toolbar myToolbar = findViewById(R.id.topbar4);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton button = findViewById(R.id.buttonback);
        button.setOnClickListener(this);
        //load exisiting info
    }

    @Override
    public void onClick(View view) {
        //should save data
        try {
            Network();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();
    }
    private void Network() throws JSONException {
        String url2 = url+"myroute/Courseupdate";

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = "{\"name\":\""+name.getText().toString()+"\", \"video\":\""+video.getText().toString()+"\", \"std\": \""+std.getText().toString()
                +"\", \"end\":\""+ end.getText().toString()+"\", \"desc\":\""+desc.getText().toString()
                +"\",\"reg\":"+reg.getText().toString()+", \"price\":"+price.getText().toString()+", \"id\":"+CID+"}";
        final RequestBody body = RequestBody.create(json,JSON);
        final Request request = new Request.Builder()
                .url(url2)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    JSONObject myResponseReader;
                    if (myResponse != "") {
                        try {
                            myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }
    private void Network_users() throws JSONException {
        String url2 = url+"myroute/LCcourseenrollnames?id="+CID;

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url2)
                .build();


        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    JSONArray myResponseReader;
                    if (myResponse != "") {
                        try {
                            myResponseReader = new JSONArray(myResponse);
                            for (int i = 0; i<myResponseReader.length();i++) {
                                JSONObject jsonObject = myResponseReader.getJSONObject(i);
                                message_f.add(jsonObject.getString("Fname"));
                                message_l.add(jsonObject.getString("Lname"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }


    }





