package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.aucegypt.learningcentershub.Network.APIcall;
import edu.aucegypt.learningcentershub.data.User;
import edu.aucegypt.learningcentershub.ui.login.LoginActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateAccountActivity extends AppCompatActivity  implements View.OnClickListener, RecyclerViewAdapter.RecyclerViewListner {
    EditText firstNameField;
    EditText lastNameField;
    EditText emailField;
    EditText passwordField;
    String category[];
    Boolean isSelected[] = {false, false, false, false, false, false, false, false};

    TextView textView2;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering,
            R.drawable.language, R.drawable.business, R.drawable.music, R.drawable.graphicdesign, R.drawable.cooking};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        category = getResources().getStringArray(R.array.category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, category, categoryIcon, this);
        recyclerView.setAdapter(adapter);

        TextView textView = findViewById(R.id.login);
        textView2 = findViewById(R.id.testText);
        textView.setOnClickListener(this);
        Button b = findViewById(R.id.exit_create_account);
        b.setOnClickListener(this);
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
            ArrayList<String> choosenCategories = new ArrayList<String>();
            String choosenCategoriesArray[] = {};
//           Intent i = new Intent("edu.aucegypt.learningcentershub.MAIN_ACTIVITY");
 //           startActivity(i);
            for(int i=1;i<=8;i++){
                if (isSelected[i]){
                    choosenCategories.add(category[i]);
                }
            }
            choosenCategoriesArray = choosenCategories.toArray(choosenCategoriesArray);
            User user = new User("", firstNameField.getText().toString().trim(), lastNameField.getText().toString().trim(), emailField.getText().toString().trim(), passwordField.getText().toString().trim(), "",choosenCategoriesArray , false);
            Gson gson = new Gson();
            String json = gson.toJson(user);

            String url = "http://10.40.47.60:3000/myroute/registerUser";

            OkHttpClient client = new OkHttpClient();
             final MediaType JSON = MediaType.get("application/json; charset=utf-8");

            final RequestBody body = RequestBody.create(json, JSON);
            final Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {

                    }

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });


        }
    }

    @Override
    public void RecyclerViewClick(int position) {
        isSelected[position] = !isSelected[position];
    }
}