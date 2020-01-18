package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class Add_course extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment2, new NavBar_LC());
        fragmentTransaction.replace(R.id.fragment2_1, new TopBar());
        fragmentTransaction.commit();

        listView = findViewById(R.id.add_course_info);
        String [] data = getResources().getStringArray(R.array.course_info);
        ArrayList<String> Data = new ArrayList<String>();
        for (int i = 0 ; i<data.length;i++) {
            Data.add(data[i]);
        }
        listView.setAdapter(new rowAdapter(this, Data));



    }
}

class rowAdapter extends ArrayAdapter<String> {
    public rowAdapter(Context context, ArrayList<String> row ){
        super(context,0,row);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String row = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.row_text);
        textView.setText(row);
        return convertView;
    }
}