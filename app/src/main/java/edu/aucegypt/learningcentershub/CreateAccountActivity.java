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

import org.w3c.dom.Text;

import edu.aucegypt.learningcentershub.ui.login.LoginActivity;

public class CreateAccountActivity extends AppCompatActivity  implements View.OnClickListener, RecyclerViewAdapter.RecyclerViewListner {
    String[] category;
    String t = "";
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
        t = t + category[position];
        textView2.setText(t);
    }
}