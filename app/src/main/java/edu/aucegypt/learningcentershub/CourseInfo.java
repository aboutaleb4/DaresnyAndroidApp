package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
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

import edu.aucegypt.learningcentershub.data.Course;
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

    CardView cardViewLearningCenter;
    CardView cardViewCourseInfo;

    Button arrowBtnLearningCenter;
    Button arrowBtnCourseInfo;
    Button registerBtn;
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


    JSONObject myResponseReader;
    boolean isfavourite;
    Course course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        arrowBtnLearningCenter = (Button) findViewById(R.id.arrowBtnLearningCenter);
        arrowBtnCourseInfo = (Button) findViewById(R.id.arrowBtnCourseInfo);
        cardViewLearningCenter = (CardView) findViewById(R.id.cardViewLearningCenter);
        cardViewCourseInfo = (CardView) findViewById(R.id.cardViewDescription);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_category = (TextView) findViewById(R.id.tv_category);

        tv_learningCenterName = (TextView) findViewById(R.id.tv_learningCenterName);
        tv_learningCenterAddress =(TextView) findViewById(R.id.tv_learningCenterAddress);
        tv_learningCenterPhone =(TextView) findViewById(R.id.tv_learningCenterPhone);
        tv_learningCenterEmail = (TextView) findViewById(R.id.tv_learningCenterEmail);

        tv_course_description = (TextView) findViewById(R.id.tv_course_description);
        tv_reg_fees = (TextView) findViewById(R.id.tv_reg_fees);

        tv_stdate = (TextView) findViewById(R.id.tv_stdate);
        tv_enddate = (TextView) findViewById(R.id.tv_enddate);

        course_logo = (ImageView) findViewById(R.id.course_logo);

        registerBtn = (Button)findViewById(R.id.registerBtn);
        favourite = (CheckBox)findViewById(R.id.favourite);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CourseRegisterActivity.class);
                startActivity(i);
            }
        });

        arrowBtnLearningCenter.setOnClickListener(this);
        arrowBtnCourseInfo.setOnClickListener(this);

        String url_api_1 = url + "myroute/checkFavorites?uid=37&cid="+Integer.toString(CID);

        OkHttpClient client_1 = new OkHttpClient();

        final Request request_1 = new Request.Builder()
                .url(url_api_1)
                .build();

        client_1.newCall(request_1).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

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
                   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                       if(isChecked) {
                           String url_api_1 = url + "myroute/addToFavorites?uid=37&cid="+Integer.toString(CID);

                           OkHttpClient client_1 = new OkHttpClient();

                           final Request request_1 = new Request.Builder()
                                   .url(url_api_1)
                                   .build();

                           client_1.newCall(request_1).enqueue(new Callback() {

                               @Override
                               public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                               }

                               @Override
                               public void onFailure(@NotNull Call call, @NotNull IOException e) {

                               }
                           });

                       }
                       else
                       {
                           String url_api_1 = url + "myroute/removeFromFavorites?uid=37&cid="+Integer.toString(CID);

                           OkHttpClient client_1 = new OkHttpClient();

                           final Request request_1 = new Request.Builder()
                                   .url(url_api_1)
                                   .build();

                           client_1.newCall(request_1).enqueue(new Callback() {

                               @Override
                               public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                               }

                               @Override
                               public void onFailure(@NotNull Call call, @NotNull IOException e) {

                               }
                           });

                       }
                   }
               }
        );



        String url_api = url + "myroute/getCourseInfo?id="+Integer.toString(CID);

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url_api)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();

                    final Type courseType = new TypeToken<Course>(){}.getType();

                    course = gson.fromJson(response.body().string(), courseType);


                    CourseInfo.this.runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {

                                    new DownloadImageTask(course_logo)
                                            .execute(url+"images/"+ course.getCourseImage());

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
        }


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}




