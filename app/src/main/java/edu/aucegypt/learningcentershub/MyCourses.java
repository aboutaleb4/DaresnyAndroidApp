package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyCourses extends AppCompatActivity {

    String[] favourites;
    int[] favouritesIcons = {R.drawable.science, R.drawable.programming, R.drawable.engineering,
            R.drawable.language, R.drawable.business, R.drawable.music, R.drawable.graphicdesign, R.drawable.cooking
            , R.drawable.business, R.drawable.music, R.drawable.graphicdesign, R.drawable.cooking};
    String[] registered;
    int[] registeredIcons = {R.drawable.science, R.drawable.programming, R.drawable.engineering,
            R.drawable.language, R.drawable.business, R.drawable.music, R.drawable.graphicdesign, R.drawable.cooking
            , R.drawable.business, R.drawable.music, R.drawable.graphicdesign, R.drawable.cooking};


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_courses_frag);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment7, new NavBar());
        fragmentTransaction.replace(R.id.fragment7_1, new TopBar());
        fragmentTransaction.commit();

        favourites = getResources().getStringArray(R.array.favourites);
        registered = getResources().getStringArray(R.array.registered);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_favourites);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, favourites, favouritesIcons);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView_1 = findViewById(R.id.recyclerview_registered);
        recyclerView_1.setLayoutManager(new GridLayoutManager(this, 3));
        RecyclerViewAdapter adapter_1 = new RecyclerViewAdapter(this, registered, registeredIcons);
        recyclerView_1.setAdapter(adapter_1);
    }
}
