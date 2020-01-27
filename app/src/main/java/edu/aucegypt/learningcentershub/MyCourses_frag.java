package edu.aucegypt.learningcentershub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;
import edu.aucegypt.learningcentershub.data.LearningCenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class MyCourses_frag extends Fragment{

    MyCoursesAdapter adapter;
    RecyclerView recyclerView;
    MyCoursesAdapter adapter_1;
    RecyclerView recyclerView_1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("login_shared_preference", MODE_PRIVATE);
        Boolean status = prefs.getBoolean("status", false);
        int uid = prefs.getInt("uid", 0); //0 is the default value.
        View view = inflater.inflate(R.layout.my_courses_frag, container, false);

        if (status) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_favourites);
            recyclerView.setLayoutManager(layoutManager);

            String url_api = url + "myroute/getUserLikedCourses";

            url_api = url_api + "?id=" + Integer.toString(uid);


            OkHttpClient client = new OkHttpClient();

            final Request request = new Request.Builder()
                    .url(url_api)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();

                        Type catListType = new TypeToken<ArrayList<Course>>() {
                        }.getType();

                        ArrayList<Course> FavouriteCoursesArrayList = gson.fromJson(response.body().string(), catListType);
                        adapter = new MyCoursesAdapter(getContext(), FavouriteCoursesArrayList);


                        getActivity().runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                        );
                    }

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });

        LinearLayoutManager layoutManager_1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_1 = (RecyclerView) view.findViewById(R.id.recyclerview_registered);
        recyclerView_1.setLayoutManager(layoutManager_1);

        String url_api_1 = url + "myroute/getUserRegisteredCourses";

        url_api_1 = url_api_1 + "?id=" + Integer.toString(uid);

        OkHttpClient client_1 = new OkHttpClient();

        final Request request_1 = new Request.Builder()
                .url(url_api_1)
                .build();

        client_1.newCall(request_1).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();

                    Type catListType = new TypeToken<ArrayList<Course>>(){}.getType();

                    ArrayList<Course> RegisteredCoursesArrayList = gson.fromJson(response.body().string(), catListType);
                    adapter_1 = new MyCoursesAdapter(getContext(), RegisteredCoursesArrayList);


                    getActivity().runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView_1.setAdapter(adapter_1);
                                }
                            }
                    );
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
        }
        return view;
    }
}