package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Category;
import edu.aucegypt.learningcentershub.data.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class CategoriesList_frag extends Fragment {

   // private categoriesOnClickListener listener;
    categoriesListAdapter adapter;
    private RecyclerView recyclerView;
    private SearchView searchView;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_categories, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchView = view.findViewById(R.id.searchView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setNestedScrollingEnabled(false);



        String url_api = url + "myroute/getCategories";

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url_api)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();

                    Type catListType = new TypeToken<ArrayList<Category>>(){}.getType();

                    ArrayList<Category> categoryArrayList = gson.fromJson(response.body().string(), catListType);
                    adapter = new categoriesListAdapter(getContext(), categoryArrayList);


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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;

    }





}
