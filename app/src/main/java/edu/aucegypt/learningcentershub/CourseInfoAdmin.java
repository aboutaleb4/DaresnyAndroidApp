package edu.aucegypt.learningcentershub;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
    Calendar    myCalendar, myCalendar2;
    DatePickerDialog.OnDateSetListener date, date2;
    DatePickerDialog DPD,DPD2;

    int LOAD_IMAGE = 1;
    public static ArrayList<String> message_f = new ArrayList<>();
    public static ArrayList<String> message_l = new ArrayList<>();
    public static ArrayList<ArrayList<String>> message_o = new ArrayList<>();

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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, LOAD_IMAGE);
            }
        });
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
         myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view1, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate(std);

            }};
        DPD = new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        std.setInputType(InputType.TYPE_NULL);
        std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DPD.show();
            }

        });

        myCalendar2 = Calendar.getInstance();
        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view1, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate(end);

            }};
        DPD2 = new DatePickerDialog(this, date2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH));
        end.setInputType(InputType.TYPE_NULL);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DPD2.show();
            }

        });

        TextView faves = findViewById(R.id.crseLikes);
        faves.setText(likes);

        TextView reg = findViewById(R.id.crseuser);
        reg.setText(enroll);

        LinearLayout linearLayout = findViewById(R.id.reg_click);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Registered__users.class);
                i.putExtra("fnames",message_f.toArray(new String[message_f.size()]));
                i.putExtra("lnames",message_l.toArray(new String[message_l.size()]));
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == LOAD_IMAGE) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image

                final Uri imageUri = data.getData();
                final InputStream imageStream  ;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    void setDate(View view)
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ((EditText)view).setText(sdf.format(myCalendar.getTime()));
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
                                Network_users_info(String.valueOf(jsonObject.getInt("UID")));
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
    private void Network_users_info(String id) throws JSONException {
        String url2 = url+"myroute/LCuserinfo?id="+id;

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
                                JSONObject jsonObject = myResponseReader.getJSONObject(0);
                                ArrayList <String> arrayList = new ArrayList<>();
                                arrayList.add(jsonObject.getString("KLevel"));
                                arrayList.add(jsonObject.getString("Area"));
                                arrayList.add(jsonObject.getString("City"));
                            arrayList.add(jsonObject.getString("Fname"));
                            arrayList.add(jsonObject.getString("Lname"));
                            arrayList.add(jsonObject.getString("Email"));
                            arrayList.add(jsonObject.getString("PhoneNo"));
                            message_o.add(arrayList);

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





