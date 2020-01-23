package edu.aucegypt.learningcentershub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import edu.aucegypt.learningcentershub.ui.login.LoginActivity;

public class CreateAccountActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText firstNameField;
    EditText lastNameField;
    EditText emailField;
    EditText passwordField;
    private DatabaseReference database;
    RecyclerView mListView;
    String[] category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering,
            R.drawable.language, R.drawable.business, R.drawable.music, R.drawable.graphicdesign, R.drawable.cooking};

    public CreateAccountActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        category = getResources().getStringArray(R.array.category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, category, categoryIcon);
        recyclerView.setAdapter(adapter);
        TextView textView = findViewById(R.id.login);
        textView.setOnClickListener(this);
        Button b = findViewById(R.id.createaccount);
        b.setOnClickListener(this);

        b= (Button) findViewById(R.id.createaccount);
        firstNameField = (EditText) findViewById(R.id.firstname);
        lastNameField = (EditText) findViewById(R.id.lastname);
        emailField = (EditText) findViewById(R.id.email);
        passwordField = (EditText) findViewById(R.id.password);
        database = FirebaseDatabase.getInstance().getReference().child("User");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firstNameField = findViewById(R.id.firstname);
                //lastNameField = findViewById(R.id.lastname);
                //emailField = findViewById(R.id.email);
                //passwordField = findViewById(R.id.password);
                String fName = firstNameField.getText().toString().trim();
                String lName = lastNameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("First Name",fName);
                dataMap.put("Last Name",lName);
                dataMap.put("Email",email);
                dataMap.put("Password",password);

                database.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(CreateAccountActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CreateAccountActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
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
}
