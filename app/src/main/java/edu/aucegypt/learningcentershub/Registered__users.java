package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Registered__users extends AppCompatActivity {
    RecyclerView listView;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_users);
        listView = findViewById(R.id.registred_users_users);
        String[] Data = {"User 1", "User 2"}; //comes from database
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter3(this, Data));
        listView.setNestedScrollingEnabled(false);
    }


}


class rvadapter3 extends RecyclerView.Adapter<rvadapter3.ViewHolder3> implements View.OnClickListener{
    String[] rows;
    public Context mContext;

    public rvadapter3(Context context, String[] Names) {
        this.rows = Names;
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        view.setOnClickListener(this);
        return  new ViewHolder3(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder3 holder, int position) {
        holder.text1.setText(rows[position]);

    }

    @Override
    public int getItemCount() {
        return rows.length;
    }
    @Override
    public void onClick(View view) {
       if (((TextView)view).getText().toString()=="Edit Information") {
           Fragment selectedFragment = null;
           //selectedFragment = new LearningCenterInfoActivity();
           ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, selectedFragment).commit();
       }
       else if (((TextView)view).getText().toString()=="Edit Courses"){
            //course info editable
       }
       else {
           Fragment selectedFragment = null;
           //selectedFragment = new user_info_frag();
           ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment2_2, selectedFragment).commit();

       }
    }


    public class ViewHolder3 extends RecyclerView.ViewHolder  {

        TextView text1;

        public ViewHolder3(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }


    }
}


