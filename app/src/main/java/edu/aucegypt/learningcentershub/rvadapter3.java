package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rvadapter3 extends RecyclerView.Adapter<rvadapter3.ViewHolder3> implements View.OnClickListener{
    String[] rows;
    public Context mContext;
    public static ArrayList<String> cname = new ArrayList<>();
    public static ArrayList<Integer> cid = new ArrayList<>();

    public static String[] message = new String[14];
    public static String[][] message2 = new String[10][11];

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
            Intent i = new Intent(mContext,LearningCenterInfoAdmin.class);
            i.putExtra("LCname",message[0]);
            i.putExtra("Logo",message[1]);
            i.putExtra("Description",message[2]);
            i.putExtra("Email",message[3]);
            i.putExtra("PhoneNo",message[4]);
            i.putExtra("Street",message[5]);
            i.putExtra("BuildingNo",message[6]);
            i.putExtra("FloorNo",message[7]);
            i.putExtra("AptNo",message[8] );
            i.putExtra("Area",message[9] );
            i.putExtra("City",message[10] );
            i.putExtra("Longtitude",message[11] );
            i.putExtra("Latitude",message[12] );
            i.putExtra("id",message[13] );

            mContext.startActivity(i);

        }
        else if (((TextView)view).getText().toString()=="Edit Courses"){
            Fragment selectedFragment = null;
            selectedFragment = new learningCentersFrag();
            Bundle b = new Bundle();
            b.putStringArrayList("Courses",cname);
            selectedFragment.setArguments(b);
            ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, selectedFragment).commit();

        }
        else if (cname.contains(((TextView)view).getText().toString())){
                int r = cname.indexOf(((TextView)view).getText().toString());
                Intent i = new Intent(mContext,CourseInfoAdmin.class);
                i.putExtra("CID",message2[r][0]);
                i.putExtra("CourseName",message2[r][1]);
                i.putExtra("CourseImage",message2[r][2]);
                i.putExtra("Price",message2[r][3]);
                i.putExtra("RegFees",message2[r][4]);
                i.putExtra("StDate",message2[r][5]);
                i.putExtra("EndDate",message2[r][6]);
                i.putExtra("Description",message2[r][7]);
                i.putExtra("Video",message2[r][8] );
                i.putExtra("LCID",message2[r][9] );
                i.putExtra("CatName",message2[r][10] );
                mContext.startActivity(i);
            }
            else {
                Fragment selectedFragment = null;
                selectedFragment = new user_info_frag();
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


