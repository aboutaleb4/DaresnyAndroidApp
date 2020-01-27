package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Address;
import edu.aucegypt.learningcentershub.data.Course;
import edu.aucegypt.learningcentershub.data.Instructor;
import edu.aucegypt.learningcentershub.data.Schedule;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;
import static edu.aucegypt.learningcentershub.utitlies.Utility.formatdouble;

public class CourseInfo extends AppCompatActivity implements View.OnClickListener {
    int CID;

    LinearLayout expandableLearningCenter;
    LinearLayout expandableCourseInfo;
    LinearLayout expandableCourseSchedule;
    LinearLayout expandableInstructor;

    CardView cardViewLearningCenter;
    CardView cardViewCourseInfo;
    CardView cardViewSchedule;
    CardView cardViewInstructor;

    Button arrowBtnLearningCenter;
    Button arrowBtnCourseInfo;
    Button arrowBtnCourseSchedule;
    Button registerBtn;
    Button arrowBtnInstructor;
    CheckBox favourite;

    ImageView course_logo;

    TextView tv_course_description;
    TextView tv_reg_fees;
    TextView tv_stdate;
    TextView tv_enddate;
    TextView tv_name;
    TextView tv_category;
    TextView tv_learningCenterName;
    TextView tv_learningCenterPhone;
    TextView tv_learningCenterAddress;
    TextView tv_learningCenterEmail;
    TextView tv_instructor_name;
    TextView tv_insstructor_bio;

   // TextView address;


    JSONObject myResponseReader;
    boolean isfavourite;

    Address addressObject;
    Instructor instructor;


    RecyclerView recyclerView_Schedule;

    scheduleAdapter mScheduleAdapter;

    Course course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("login_shared_preference", MODE_PRIVATE);
        Boolean status = prefs.getBoolean("status", false);
        final int uid = prefs.getInt("uid", 0); //0 is the default value.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            CID = bundle.getInt("CID");
            setTitle(" ");
        }

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        expandableLearningCenter = (LinearLayout) findViewById(R.id.expandableLearningCenter);
        expandableCourseInfo = (LinearLayout) findViewById(R.id.expandableCourseInfo);
        expandableCourseSchedule = (LinearLayout) findViewById(R.id.expandableCourseSchedule);
        expandableInstructor = (LinearLayout) findViewById(R.id.expandableInstructor);

        arrowBtnLearningCenter = (Button) findViewById(R.id.arrowBtnLearningCenter);
        arrowBtnCourseInfo = (Button) findViewById(R.id.arrowBtnCourseInfo);
        arrowBtnCourseSchedule = (Button) findViewById(R.id.arrowBtnSchedule);
        arrowBtnInstructor = (Button) findViewById(R.id.arrowBtnInstructor);

        cardViewLearningCenter = (CardView) findViewById(R.id.cardViewLearningCenter);
        cardViewCourseInfo = (CardView) findViewById(R.id.cardViewDescription);
        cardViewSchedule = (CardView) findViewById(R.id.cardViewSchedule);
        cardViewInstructor = (CardView) findViewById(R.id.cardViewInstructor);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_category = (TextView) findViewById(R.id.tv_category);
        Bundle mBundle = getIntent().getExtras();

        tv_learningCenterName = (TextView) findViewById(R.id.tv_learningCenterName);
        tv_learningCenterAddress =(TextView) findViewById(R.id.tv_learningCenterAddress);
        tv_learningCenterPhone =(TextView) findViewById(R.id.tv_learningCenterPhone);
        tv_learningCenterEmail = (TextView) findViewById(R.id.tv_learningCenterEmail);
        tv_instructor_name = (TextView) findViewById(R.id.tv_instructor_name);
        tv_insstructor_bio = (TextView) findViewById(R.id.tv_instructor_bio);

        tv_course_description = (TextView) findViewById(R.id.tv_course_description);
        tv_reg_fees = (TextView) findViewById(R.id.tv_reg_fees);

        tv_stdate = (TextView) findViewById(R.id.tv_stdate);
        tv_enddate = (TextView) findViewById(R.id.tv_enddate);

        course_logo = (ImageView) findViewById(R.id.course_logo);

        recyclerView_Schedule = (RecyclerView) findViewById(R.id.recyclerView_schedule);

        recyclerView_Schedule.setLayoutManager(new LinearLayoutManager(CourseInfo.this, LinearLayoutManager.VERTICAL, false));
        recyclerView_Schedule.setItemAnimator(new DefaultItemAnimator());

        registerBtn = (Button)findViewById(R.id.registerBtn);
        favourite = (CheckBox)findViewById(R.id.favourite);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CourseRegisterActivity.class);
                startActivity(i);
            }
        });
        if (!status)
        {
            favourite.setVisibility(View.GONE);
        }

        arrowBtnLearningCenter.setOnClickListener(this);
        arrowBtnCourseInfo.setOnClickListener(this);
        arrowBtnCourseSchedule.setOnClickListener(this);
        arrowBtnInstructor.setOnClickListener(this);

        if (status) {
            String url_api_1 = url + "myroute/checkFavorites?uid=" + Integer.toString(uid) + "&cid=" + Integer.toString(CID);

            OkHttpClient client_1 = new OkHttpClient();

            final Request request_1 = new Request.Builder()
                    .url(url_api_1)
                    .build();

            client_1.newCall(request_1).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response1) throws IOException {
                    if (response1.isSuccessful()) {
                        final String myResponse = response1.body().string();
                        if (myResponse != "") {

                            CourseInfo.this.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                myResponseReader = new JSONObject(myResponse);
                                                isfavourite = myResponseReader.getBoolean("status");
                                                favourite.setChecked(isfavourite);
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

            favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if (isChecked) {
                         String url_api_1 = url + "myroute/addToFavorites?uid=" + Integer.toString(uid) + "&cid=" + Integer.toString(CID);

                         OkHttpClient client_1 = new OkHttpClient();

                         final Request request_1 = new Request.Builder()
                                 .url(url_api_1)
                                 .build();

                         client_1.newCall(request_1).enqueue(new Callback() {

                             @Override
                             public void onResponse(@NotNull Call call, @NotNull Response response2) throws IOException {
                             }

                             @Override
                             public void onFailure(@NotNull Call call, @NotNull IOException e) {

                             }
                         });

                     } else {
                         String url_api_1 = url + "myroute/removeFromFavorites?uid=" + Integer.toString(uid) + "&cid=" + Integer.toString(CID);

                         OkHttpClient client_1 = new OkHttpClient();

                         final Request request_1 = new Request.Builder()
                                 .url(url_api_1)
                                 .build();

                         client_1.newCall(request_1).enqueue(new Callback() {

                             @Override
                             public void onResponse(@NotNull Call call, @NotNull Response response3) throws IOException {
                             }

                             @Override
                             public void onFailure(@NotNull Call call, @NotNull IOException e) {

                             }
                         });

                     }
                 }
             }
            );
        }


            String url_api = url + "myroute/getCourseInfo?id=" + Integer.toString(CID);

            String url_api_schedule = url + "myroute/getCourseSchedule?id=" + Integer.toString(CID);


            OkHttpClient client_2 = new OkHttpClient();

            final Request request = new Request.Builder()
                    .url(url_api)
                    .build();

            final Request request_schedule = new Request.Builder()
                    .url(url_api_schedule)
                    .build();

          client_2.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String myResponse = response.body().string();
                        if (myResponse != "") {
                            Gson gson = new Gson();

                            final Type courseType = new TypeToken<Course>() {
                            }.getType();

                            course = gson.fromJson(myResponse, courseType);


                            CourseInfo.this.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {

                                            new DownloadImageTask(course_logo)
                                                    .execute(url + "images/" + course.getCourseImage());

                                            tv_name.setText(course.getCourseName());
                                            tv_category.setText(course.getCatName());

                                            tv_learningCenterName.setText(course.getLCname());
                                            tv_learningCenterPhone.setText(course.getPhoneNo());
                                            tv_learningCenterEmail.setText(course.getEmail());


                                            tv_course_description.setText(course.getDescription());
                                            tv_reg_fees.setText("EGP " + formatdouble(course.getRegFees()));


                                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM F, yyyy");
                                            String StDate = dateFormat.format(course.getStDate());
                                            tv_stdate.setText(StDate);

                                            String EndDate = dateFormat.format(course.getEndDate());
                                            tv_enddate.setText(EndDate);


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


        OkHttpClient client_3 = new OkHttpClient();
        client_3.newCall(request_schedule).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                            final String myResponse = response.body().string();
                            if (myResponse != "") {
                            Gson gson = new Gson();
                            Type scheduleListType = new TypeToken<ArrayList<Schedule>>() {
                            }.getType();

                            ArrayList<Schedule> scheduleArrayList = gson.fromJson(myResponse, scheduleListType);
                            mScheduleAdapter = new scheduleAdapter(CourseInfo.this, scheduleArrayList);


                            CourseInfo.this.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            recyclerView_Schedule.setAdapter(mScheduleAdapter);
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

        OkHttpClient client_4 = new OkHttpClient();

        final Request request_2 = new Request.Builder()
                .url(url_api_getAddress)
                .build();

        client_4.newCall(request_2).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse1 = response.body().string();
                    Gson gson = new Gson();

                    final Type typeAddress = new TypeToken<Address>(){}.getType();

                    addressObject = gson.fromJson(myResponse1, typeAddress);

                    if (myResponse1 != "") {

                        CourseInfo.this.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {

                                        if(addressObject.getFloorNo() == null || addressObject.getAptNo() == null)
                                        {
                                            tv_learningCenterAddress.setText(addressObject.getBuildingNo()+" "+
                                                    addressObject.getStreet()+" ,"+
                                                    addressObject.getArea()+" ,"+
                                                    addressObject.getCity());
                                        }
                                        else{
                                            tv_learningCenterAddress.setText(addressObject.getBuildingNo()+" "+
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


        String url_api_getInstructor = url + "myroute/getInstructor";

        url_api_getInstructor = url_api_getInstructor + "?id=" + Integer.toString(mBundle.getInt("CID"));


        final Request request_getInstructor = new Request.Builder()
                .url(url_api_getInstructor)
                .build();

        OkHttpClient client_5 = new OkHttpClient();
        client_5.newCall(request_getInstructor).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse1 = response.body().string();
                    Gson gson = new Gson();

                    final Type typeInstructor = new TypeToken<Instructor>(){}.getType();

                    instructor = gson.fromJson(myResponse1, typeInstructor);

                    if (myResponse1 != "") {

                        CourseInfo.this.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {

                                        tv_instructor_name.setText(instructor.getFname() + "  "+instructor.getLname());
                                        tv_insstructor_bio.setText(instructor.getBio());

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


    @Override
    public void onClick(View v) {
        final ChangeBounds transition = new ChangeBounds();
        transition.setDuration(100L);                       // Sets a duration of 100 millisecondss
        switch (v.getId()){
            case R.id.arrowBtnLearningCenter:

                if(expandableLearningCenter.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardViewLearningCenter,transition);
                    expandableLearningCenter.setVisibility(View.VISIBLE);
                    arrowBtnLearningCenter.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                }else {
                    TransitionManager.beginDelayedTransition(cardViewLearningCenter,transition);
                    expandableLearningCenter.setVisibility(View.GONE);
                    arrowBtnLearningCenter.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

                }

                break;

            case  R.id.arrowBtnCourseInfo:

                if(expandableCourseInfo.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardViewCourseInfo,transition);
                    expandableCourseInfo.setVisibility(View.VISIBLE);
                    arrowBtnCourseInfo.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                }else {
                    TransitionManager.beginDelayedTransition(cardViewCourseInfo,transition);
                    expandableCourseInfo.setVisibility(View.GONE);
                    arrowBtnCourseInfo.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

                }
                break;

            case  R.id.arrowBtnSchedule:

                if(expandableCourseSchedule.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardViewSchedule,transition);
                    expandableCourseSchedule.setVisibility(View.VISIBLE);
                    arrowBtnCourseSchedule.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                }else {
                    TransitionManager.beginDelayedTransition(cardViewSchedule,transition);
                    expandableCourseSchedule.setVisibility(View.GONE);
                    arrowBtnCourseSchedule.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

                }
                break;

            case R.id.arrowBtnInstructor:
                if(expandableInstructor.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardViewInstructor,transition);
                    expandableInstructor.setVisibility(View.VISIBLE);
                    arrowBtnInstructor.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);

                }else {
                    TransitionManager.beginDelayedTransition(cardViewInstructor,transition);
                    expandableInstructor.setVisibility(View.GONE);
                    arrowBtnInstructor.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);

                }
                break;
        }


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}




