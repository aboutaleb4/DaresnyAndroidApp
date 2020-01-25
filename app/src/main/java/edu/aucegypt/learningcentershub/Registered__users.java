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
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

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
    ArrayList<String> cname = new ArrayList<>();
    ArrayList<Integer> cid = new ArrayList<>();

    private static String[] message = new String[14];
    private static String[] message2 = new String[11];

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
           Network_course(String.valueOf(1));
           Bundle b = new Bundle();
           b.putStringArrayList("Courses",cname);
           selectedFragment.setArguments(b);
           ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, selectedFragment).commit();

       }
       else {
           Network_course(String.valueOf(1));
           if (cname.contains(((TextView)view).getText().toString())){
               int r = cname.indexOf(((TextView)view).getText().toString());
               int id  = cid.get(r);
               Intent i = new Intent(mContext,CourseInfoAdmin.class);
               Network_course_info(String.valueOf(id));
               i.putExtra("CID",message2[0]);
               i.putExtra("CourseName",message2[1]);
               i.putExtra("CourseImage",message2[2]);
               i.putExtra("Price",message2[3]);
               i.putExtra("RegFees",message2[4]);
               i.putExtra("StDate",message2[5]);
               i.putExtra("EndDate",message2[6]);
               i.putExtra("Description",message2[7]);
               i.putExtra("Video",message2[8] );
               i.putExtra("LCID",message2[9] );
               i.putExtra("CatName",message2[10] );
               mContext.startActivity(i);
           }
           else {
               Fragment selectedFragment = null;
               selectedFragment = new user_info_frag();
               ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment2_2, selectedFragment).commit();

           }
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

    private void Network_course(String id){
        String url2 = url+"/myroute/LCcourses?id="+ id;

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url2)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    JSONArray myResponseReader;
                    if (myResponse != "") {
                        try {
                            myResponseReader = new JSONArray(myResponse);
                            for (int i = 0; i<myResponseReader.length();i++) {
                                JSONObject jsonObject = myResponseReader.getJSONObject(i);
                                cname.add(jsonObject.getString("CourseName"));
                                cid.add(jsonObject.getInt("CID"));
                            }
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
    private void Network_course_info(String id){
        String url2 = url+"/myroute/LCcourse?id="+ id;

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url2)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    JSONObject myResponseReader;
                        try {
                            myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));
                                message2[0] = String.valueOf(myResponseReader.getInt("CID"));
                                message2[1] = myResponseReader.getString("CourseName");
                                message2[2] = String.valueOf(myResponseReader.getInt("CourseImage"));
                                message2[3] = String.valueOf(myResponseReader.getInt("Price"));
                                message2[4] = String.valueOf(myResponseReader.getInt("RegFees"));
                                message2[5] = myResponseReader.getString("StDate");
                                message2[6] = myResponseReader.getString("EndDate");
                                message2[7] = myResponseReader.getString("Description");
                                message2[8] = myResponseReader.getString("Video");
                                message2[9] = String.valueOf(myResponseReader.getInt("LCID"));
                                message2[10] = myResponseReader.getString("CatName");

                        } catch (JSONException e) {
                            e.printStackTrace();
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


