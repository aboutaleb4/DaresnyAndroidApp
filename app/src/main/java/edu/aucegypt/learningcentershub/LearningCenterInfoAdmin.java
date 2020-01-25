package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LearningCenterInfoAdmin extends AppCompatActivity implements View.OnClickListener {
    EditText name, desc, aptno, floor, city, area, build, street, phone,email;
    String id;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState=getIntent().getExtras();
        setContentView(R.layout.activity_learningcenter_info_admin);
        String LCname = savedInstanceState.getString("LCname");
        String Logo = savedInstanceState.getString("Logo");
        String Description = savedInstanceState.getString("Description");
        String Email = savedInstanceState.getString("Email");
        String PhoneNo = savedInstanceState.getString("PhoneNo");
        String Street = savedInstanceState.getString("Street");
        String BuildingNo = savedInstanceState.getString("BuildingNo");
        String FloorNo = savedInstanceState.getString("FloorNo");
        String AptNo = savedInstanceState.getString("AptNo");
        String City = savedInstanceState.getString("City");
        String Area = savedInstanceState.getString("Area");
        String Longtitude = savedInstanceState.getString("Longtitude");
        String Latitude = savedInstanceState.getString("Latitude");
        id = savedInstanceState.getString("id");
        name = findViewById(R.id.lc_edit_name);
        name.setText(LCname);
        desc = findViewById(R.id.lc_edit_desc);
        desc.setText(Description);
        aptno = findViewById(R.id.lc_edit_addre_aptno);
        aptno.setText(AptNo);
        floor = findViewById(R.id.lc_edit_addre_floorno);
        floor.setText(FloorNo);
        city = findViewById(R.id.lc_edit_addre_city);
        city.setText(City);
        area = findViewById(R.id.lc_edit_addre_area);
        area.setText(Area);
        build = findViewById(R.id.lc_edit_addre_build);
        build.setText(BuildingNo);
        street = findViewById(R.id.lc_edit_addre_street);
        street.setText(Street);
        phone = findViewById(R.id.lc_edit_phone);
        phone.setText(PhoneNo);
        email = findViewById(R.id.lc_edit_email);
        email.setText(Email);
        Toolbar myToolbar = findViewById(R.id.topbar3);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton button = findViewById(R.id.buttonback);
        button.setOnClickListener(this);

        //load exisiting info
    }

    @Override
    public void onClick(View view) {
        try {
            Network();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finish();
    }

    private void Network() throws JSONException {
        String url = "http://192.168.1.7:3000/myroute/LCinfoupdate";

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = "{\"name\":\""+name.getText().toString()+"\", \"email\":\""+email.getText().toString()+"\", \"phone\": \""+phone.getText().toString()
                +"\", \"logo\": 0, \"desc\":\""+desc.getText().toString()+"\", \"id\":"+id+", \"street\": \""+street.getText().toString()+"\", \"build\":\""+build.getText().toString()
                +"\",\"floor\":\""+floor.getText().toString()+"\", \"apt\":"+aptno.getText().toString()+",\"city\": \""+city.getText().toString()+"\", \"area\":\""+area.getText().toString()+"\"}";
        final RequestBody body = RequestBody.create(json,JSON);
        final Request request = new Request.Builder()
                .url(url)
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
}