package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.aucegypt.learningcentershub.Network.APIcall;
import edu.aucegypt.learningcentershub.data.Category;
import edu.aucegypt.learningcentershub.data.User;
import edu.aucegypt.learningcentershub.ui.login.LoginActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;
import static java.security.AccessController.getContext;

public class CreateAccountActivity extends AppCompatActivity  implements View.OnClickListener, RecyclerViewAdapter.RecyclerViewListner {
    EditText firstNameField;
    EditText lastNameField;
    EditText emailField;
    EditText passwordField;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    String category[];
    Boolean isSelected[] = {false, false, false, false, false, false, false, false};
    int uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences.Editor editor = getSharedPreferences("login_shared_preference", MODE_PRIVATE).edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        category = getResources().getStringArray(R.array.category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        String url_api = url + "myroute/getCategories";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url_api)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();

                    Type catListType = new TypeToken<ArrayList<Category>>(){}.getType();

                    ArrayList<Category> categoryArrayList = gson.fromJson(response.body().string(), catListType);
                    adapter = new RecyclerViewAdapter(CreateAccountActivity.this, categoryArrayList, CreateAccountActivity.this);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });

        TextView textView = findViewById(R.id.login);
        textView.setOnClickListener(this);
        Button b = findViewById(R.id.exit_create_account);
        b.setOnClickListener(this);
        Button c = findViewById(R.id.register_account);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ArrayList<String> choosenCategories = new ArrayList<String>();
                    String choosenCategoriesArray[] = {};
                    for(int i=0;i<8;i++){
                        if (isSelected[i]){
                            choosenCategories.add(category[i]);
                        }
                    }
                    choosenCategoriesArray = choosenCategories.toArray(choosenCategoriesArray);

                    // declaration and initialise String Array
//            String choosenCategoriesArray[] = new String[choosenCategories.size()];

                    // Convert ArrayList to object array
//            Object[] objArr = choosenCategories.toArray();

                    // Iterating and converting to String
//            int i = 0;
//            for (Object obj : objArr) {
//                choosenCategoriesArray[i++] = (String)obj;
//            }

                    User user = new User("", firstNameField.getText().toString().trim(), lastNameField.getText().toString().trim(), emailField.getText().toString().trim(), passwordField.getText().toString().trim(), "",choosenCategoriesArray , false);
                    Gson gson = new Gson();
                    String json = gson.toJson(user);

                    String url_api = url + "myroute/registerUser";

                    OkHttpClient client = new OkHttpClient();
                    final MediaType JSON = MediaType.get("application/json; charset=utf-8");

                    final RequestBody body = RequestBody.create(json, JSON);
                    final Request request = new Request.Builder()
                            .url(url_api)
                            .post(body)
                            .build();

                    client.newCall(request).enqueue(new Callback() {

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                final String myResponse = response.body().string();
                                JSONObject myResponseReader;
                                if (myResponse != "") {
                                    try {
                                        myResponseReader = new JSONObject(myResponse);
                                        uid = myResponseReader.getInt("id");
                                        editor.putBoolean("status", true);
                                        editor.putInt("uid", uid);
                                        editor.commit();

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

                Intent i = new Intent("edu.aucegypt.learningcentershub.MAIN_ACTIVITY");
                startActivity(i);

            }
        });
        firstNameField = (EditText) findViewById(R.id.firstname);
        lastNameField = (EditText) findViewById(R.id.lastname);
        emailField = (EditText) findViewById(R.id.email);
        passwordField = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.login) {
            Intent i = new Intent("edu.aucegypt.learningcentershub.LOGIN");
            startActivity(i);
        }
        if (view.getId()==R.id.exit_create_account) {
            Intent i = new Intent("edu.aucegypt.learningcentershub.MAIN_ACTIVITY");
            startActivity(i);
        }
    }
    @Override
    public void RecyclerViewClick(int position) {
        isSelected[position] = !isSelected[position];
    }
}