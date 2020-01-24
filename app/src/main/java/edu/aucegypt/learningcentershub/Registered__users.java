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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Registered__users extends Fragment  {
    RecyclerView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.registered_users, container, false);
        listView = view.findViewById(R.id.registred_users_users);
        String[] Data = {"User 1", "User 2"}; //comes from database
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter3(getContext(), Data));
        listView.setNestedScrollingEnabled(false);
        return view;
    }


}


class rvadapter3 extends RecyclerView.Adapter<rvadapter3.ViewHolder3> implements View.OnClickListener{
    String[] rows;
    public Context mContext;
    private static String[] message = new String[14];

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
           Network(String.valueOf(1));
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
           ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, selectedFragment).commit();

       }
       else if (((TextView)view).getText().toString()=="Course 1"){
           Intent i = new Intent(mContext,CourseInfoAdmin.class);
           mContext.startActivity(i);
        }
       else {
           Fragment selectedFragment = null;
           selectedFragment = new user_info_frag();
           ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment2_2, selectedFragment).commit();

       }
    }
    private void Network(String id){
        String url = "http://192.168.1.7:3000/myroute/LCinfodisplay?id="+ id;

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    JSONObject myResponseReader;
                    if (myResponse != "") {
                        try {
                            myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                            message[0] = myResponseReader.getString("LCname");
                            message[1] = myResponseReader.getString("Logo");
                            message[2] = myResponseReader.getString("Description");
                            message[3] = myResponseReader.getString("Email");
                            message[4] = myResponseReader.getString("PhoneNo");
                            message[5] = myResponseReader.getString("Street");
                            message[6] = myResponseReader.getString("BuildingNo");
                            message[7] = myResponseReader.getString("FloorNo");
                            message[8] = myResponseReader.getString("AptNo");
                            message[9] = myResponseReader.getString("Area");
                            message[10] = myResponseReader.getString("City");
                            message[11] = myResponseReader.getString("Longtitude");
                            message[12] = myResponseReader.getString("Latitude");
                            message[13] = myResponseReader.getString("LCID");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }

    public class ViewHolder3 extends RecyclerView.ViewHolder  {

        TextView text1;

        public ViewHolder3(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }


    }
}


