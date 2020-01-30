package edu.aucegypt.learningcentershub.ui.user.learningcenterinfoscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.service.data.Course;
import edu.aucegypt.learningcentershub.service.network.DownloadImageTask;
import edu.aucegypt.learningcentershub.service.data.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.service.network.APIcall.url;

public class LearningCenterInfoActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    TextView phone;
    TextView address;
    JSONObject myResponseReader;
    JSONObject myResponseReader1;
    ImageView logo;
    RecyclerView recyclerView_lc_courses;

    Address addressObject;
    int LCID;

    LCCoursesListAdapter adapter;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningcenter_info);

        setTitle(" ");

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        name = (TextView) findViewById(R.id.learningcenter_info_name);
        description = (TextView) findViewById(R.id.learningcenter_info_description);
        phone = (TextView) findViewById(R.id.learningcenter_info_phonenumber);
        logo = (ImageView) findViewById(R.id.learningcenter_info_image);
        address = (TextView) findViewById(R.id.learningcenter_info_address1);
        recyclerView_lc_courses = (RecyclerView) findViewById(R.id.recyclerView_lc_courses);

        recyclerView_lc_courses.setHasFixedSize(true);
        recyclerView_lc_courses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView_lc_courses.setItemAnimator(new DefaultItemAnimator());



        String url_api = url + "myroute/LCinfo";

        Bundle mBundle = getIntent().getExtras();
        LCID = mBundle.getInt("LCID");
        url_api = url_api + "?id=" + Integer.toString(LCID);

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

        String url_api_getAddress = url + "myroute/getAddress";

        url_api_getAddress = url_api_getAddress + "?id=" + Integer.toString(mBundle.getInt("LCID"));

        OkHttpClient client_1 = new OkHttpClient();

        final Request request_1 = new Request.Builder()
                .url(url_api_getAddress)
                .build();

        client_1.newCall(request_1).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse1 = response.body().string();
                    Gson gson = new Gson();

                    final Type typeAddress = new TypeToken<Address>(){}.getType();

                    addressObject = gson.fromJson(myResponse1, typeAddress);

                    if (myResponse1 != "") {

                        LearningCenterInfoActivity.this.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {

                                        if(addressObject.getFloorNo() == null || addressObject.getAptNo() == null)
                                        {
                                            address.setText(addressObject.getBuildingNo()+" "+
                                                    addressObject.getStreet()+" ,"+
                                                    addressObject.getArea()+" ,"+
                                                    addressObject.getCity());
                                        }
                                        else{
                                            address.setText(addressObject.getBuildingNo()+" "+
                                                    addressObject.getStreet()+" ,"+
                                                    addressObject.getArea()+" ,"+
                                                    addressObject.getCity()+"\n"+"Floor: "+
                                                    addressObject.getFloorNo()+"\n"+"Apartment: "+
                                                    addressObject.getAptNo());
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


        String url_api_courses = url + "myroute/getCoursesInLearningCenter?id="+Integer.toString(LCID);


        final Request request_courses = new Request.Builder()
                .url(url_api_courses)
                .build();

        client.newCall(request_courses).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();

                    Type courseListType = new TypeToken<ArrayList<Course>>(){}.getType();

                    ArrayList<Course> courseArrayList = gson.fromJson(response.body().string(), courseListType);
                    adapter = new LCCoursesListAdapter(LearningCenterInfoActivity.this, courseArrayList);


                    LearningCenterInfoActivity.this.runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView_lc_courses.setAdapter(adapter);
                                }
                            }
                    );



                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}