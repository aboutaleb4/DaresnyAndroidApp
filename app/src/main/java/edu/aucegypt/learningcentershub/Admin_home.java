package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Admin_home extends AppCompatActivity{
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new NavBar_LC());
        fragmentTransaction.replace(R.id.fragment_1, new TopBar());
        fragmentTransaction.commit();

        listView = findViewById(R.id.text_lc_home_actions);
        String [] Data = {"Edit Information","Edit Courses"};
        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,Data);
        listView.setAdapter(adapter);



        //background
        //name assume from database on log -in
    }


}