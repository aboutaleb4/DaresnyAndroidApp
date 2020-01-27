package edu.aucegypt.learningcentershub.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.aucegypt.learningcentershub.Admin_home;
import edu.aucegypt.learningcentershub.MainActivity;
import edu.aucegypt.learningcentershub.MyAccount;
import edu.aucegypt.learningcentershub.MyAccount_frag;
import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.rvadapter3;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginViewModel loginViewModel;
    int uid;
    int lcid;
    public static Boolean status = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final SharedPreferences.Editor editor = getSharedPreferences("login_shared_preference", MODE_PRIVATE).edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.gotocreateAccount);
        textView.setOnClickListener(this);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        Button exitButton = findViewById(R.id.exit_login);
        exitButton.setOnClickListener(this);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonString;
                JSONObject json = new JSONObject();
                try {
                    json.put("Email", usernameEditText.getText());
                    json.put("Password", passwordEditText.getText().toString());
                    jsonString = json.toString();
                    String url_api = url + "myroute/login";

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
                            if (response.isSuccessful()){
                                final String myResponse = response.body().string();
                                JSONObject myResponseReader;
                                if (myResponse != "") {
                                    try {
                                        myResponseReader = new JSONObject(myResponse);
                                        status = myResponseReader.getBoolean("status");
                                        String message = myResponseReader.getString("message");
                                        int isadmin = myResponseReader.getInt("isadmin");
                                        if (status == true){
                                            Intent mIntent;
                                            Network_myaccount(String.valueOf(uid));
                                            if (isadmin == 1) {
                                                lcid = myResponseReader.getInt("lcid");
                                                mIntent = new Intent(LoginActivity.this, Admin_home.class);
                                                mIntent.putExtra("lcid",String.valueOf(lcid));
                                                Network_lcinfo(String.valueOf(lcid));
                                                Network_course(String.valueOf(lcid));
                                                Network_lcinfodisplay(String.valueOf(lcid));
                                            }
                                            else {
                                                uid = myResponseReader.getInt("UID");
                                                mIntent = new Intent(LoginActivity.this, MainActivity.class);
                                                editor.putBoolean("status", true);
                                                editor.putInt("uid", uid);
                                                editor.commit();
                                            }
                                            startActivity(mIntent);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                        }

                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
    }

    private void showLoginFailed(@StringRes Integer errorString) {
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.gotocreateAccount) {
            Intent i = new Intent("edu.aucegypt.learningcentershub.CREATE_ACCOUNT");
            startActivity(i);
        }
        if (view.getId()==R.id.exit_login){
            Intent i = new Intent("edu.aucegypt.learningcentershub.MAIN_ACTIVITY");
            startActivity(i);
        }
    }
    private void Network_lcinfo(String id){
        String url2 = url+"myroute/LCinfo?id="+ id;

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
                    JSONObject myResponseReader;
                    if (myResponse != "") {
                        try {
                            myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                            Admin_home.message[0] = myResponseReader.getString("LCname");

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
    private void Network_course(String id){
        String url2 = url+"myroute/LCcourses?id="+ id;

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
                                rvadapter3.cname.add(jsonObject.getString("CourseName"));
                                rvadapter3.cid.add(jsonObject.getInt("CID"));
                                Network_course_info(i,String.valueOf(jsonObject.getInt("CID")));
                                Network_course_likes(i,String.valueOf(jsonObject.getInt("CID")));
                                Network_course_enrolls(i,String.valueOf(jsonObject.getInt("CID")));
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
    public void Network_lcinfodisplay(String id){
    String url2 = url+"myroute/LCinfodisplay?id="+ id;

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
                JSONObject myResponseReader;
                if (myResponse != "") {
                    try {
                        myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                        rvadapter3.message[0] = myResponseReader.getString("LCname");
                        rvadapter3.message[1] = myResponseReader.getString("Logo");
                        rvadapter3.message[2] = myResponseReader.getString("Description");
                        rvadapter3.message[3] = myResponseReader.getString("Email");
                        rvadapter3.message[4] = myResponseReader.getString("PhoneNo");
                        rvadapter3.message[5] = myResponseReader.getString("Street");
                        rvadapter3.message[6] = myResponseReader.getString("BuildingNo");
                        rvadapter3.message[7] = myResponseReader.getString("FloorNo");
                        rvadapter3.message[8] = myResponseReader.getString("AptNo");
                        rvadapter3.message[9] = myResponseReader.getString("Area");
                        rvadapter3.message[10] = myResponseReader.getString("City");
                        rvadapter3.message[11] = myResponseReader.getString("Longtitude");
                        rvadapter3.message[12] = myResponseReader.getString("Latitude");
                        rvadapter3.message[13] = myResponseReader.getString("LCID");

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
    private void Network_course_info(final int i , String id){
        String url2 = url+"myroute/LCcourse?id="+ id;

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
                    JSONObject myResponseReader;
                    try {
                        myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                        rvadapter3.message2[i][0] = String.valueOf(myResponseReader.getInt("CID"));
                        rvadapter3.message2[i][1] = myResponseReader.getString("CourseName");
                        rvadapter3.message2[i][2] = String.valueOf(myResponseReader.getInt("CourseImage"));
                        rvadapter3.message2[i][3] = String.valueOf(myResponseReader.getInt("Price"));
                        rvadapter3.message2[i][4] = String.valueOf(myResponseReader.getInt("RegFees"));
                        rvadapter3.message2[i][5] = myResponseReader.getString("StDate");
                        rvadapter3.message2[i][6] = myResponseReader.getString("EndDate");
                        rvadapter3.message2[i][7] = myResponseReader.getString("Description");
                        rvadapter3.message2[i][8] = myResponseReader.getString("Video");
                        rvadapter3.message2[i][9] = String.valueOf(myResponseReader.getInt("LCID"));
                        rvadapter3.message2[i][10] = myResponseReader.getString("CatName");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }
    private void Network_course_likes(final int i , String id){
        String url2 = url+"myroute/LCcourselikes?id="+ id;

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
                    JSONObject myResponseReader;
                    try {
                        myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                        rvadapter3.message2[i][11] = String.valueOf(myResponseReader.getInt("COUNT(*)"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }
    private void Network_course_enrolls(final int i , String id){
        String url2 = url+"myroute/LCcourseenroll?id="+ id;

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
                    JSONObject myResponseReader;
                    try {
                        myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                        rvadapter3.message2[i][12] = String.valueOf(myResponseReader.getInt("COUNT(*)"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }
    private void Network_myaccount( String id){
        String url2 = url+"myroute/userinfo?id="+ id;

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
                    JSONObject myResponseReader;
                    try {
                        myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                        MyAccount_frag.message[0] = myResponseReader.getString("Fname")+" "+ myResponseReader.getString("Lname");
                        MyAccount_frag.message[1] = myResponseReader.getString("Email");
                        MyAccount_frag.message[2] = myResponseReader.getString("PhoneNo");
                        MyAccount_frag.message[3] = myResponseReader.getString("Prefrences");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }

}
