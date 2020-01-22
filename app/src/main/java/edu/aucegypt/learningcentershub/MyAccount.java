package edu.aucegypt.learningcentershub;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyAccount extends AppCompatActivity {
    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_frag);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment8, new NavBar());
        fragmentTransaction.replace(R.id.fragment8_1, new TopBar());
        fragmentTransaction.commit();

        Category = getResources().getStringArray(R.array.category_4);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_id_3);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Category, categoryIcon);
        recyclerView.setAdapter(adapter);
    }

}