package edu.aucegypt.learningcentershub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Registered__users extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_users);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment3, new NavBar_LC());
        fragmentTransaction.replace(R.id.fragment3_1, new TopBar());
        fragmentTransaction.commit();

        listView = findViewById(R.id.registred_users_users);
        String[] Data = {"User 1", "User 2"}; //comes from database
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent("edu.aucegypt.learningcentershub.USER_INFO");
        startActivity(intent);
    }
}
