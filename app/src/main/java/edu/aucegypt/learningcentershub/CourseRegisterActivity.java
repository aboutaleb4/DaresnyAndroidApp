package edu.aucegypt.learningcentershub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CourseRegisterActivity extends AppCompatActivity {


    EditText firstNameField;
    EditText lastNameField;
    EditText emailField;
    EditText phoneField;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_register);

        Button reg = (Button) findViewById(R.id.btn_signup);

        firstNameField = (EditText) findViewById(R.id.signup_input_name);
        lastNameField = (EditText) findViewById(R.id.signup_input_last_name);
        emailField = (EditText) findViewById(R.id.signup_input_email);
        phoneField = (EditText) findViewById(R.id.signup_input_number);
        database = FirebaseDatabase.getInstance().getReference().child("Students Registered Course");

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fName = firstNameField.getText().toString().trim();
                String lName = lastNameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();

                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("First Name",fName);
                dataMap.put("Last Name",lName);
                dataMap.put("Email",email);
                dataMap.put("Phone",phone);

                database.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(CourseRegisterActivity.this,"Course Registered",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(CourseRegisterActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                    }
                });

                Intent i = new Intent(CourseRegisterActivity.this, Payment.class);
                startActivity(i);
            }
        });

    }
    
}
