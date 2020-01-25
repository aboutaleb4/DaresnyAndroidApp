package edu.aucegypt.learningcentershub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Category;
import edu.aucegypt.learningcentershub.data.Course;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class CoursesList_frag extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Course> arrayList;
    FrameLayout filters_layout;
    ConstraintLayout main_layout;
    Button filterBtn;


    coursesListAdapter adapter;
    private coursesFragOnClickListener listener;

    public interface coursesFragOnClickListener {

        public void onFiltersBtnClick();
        ;;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_courses_list, container, false);

        String [] name = getResources().getStringArray(R.array.courses_4);

        int [] image = {R.drawable.science,
                R.drawable.programming,
                R.drawable.engineering,
                R.drawable.language};

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        filters_layout = (FrameLayout) view.findViewById(R.id.filters_layout);
        main_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);
        filterBtn = (Button) view.findViewById(R.id.filtersBtn);

        filterBtn.setOnClickListener(this);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        for (int i = 0; i < image.length; i++) {
//            Course itemModel = new Course();
//            itemModel.setCourseName(name[i]);
//            itemModel.setImage(image[i]);
//            arrayList.add(itemModel);
//
//        }


        String url_api = url + "myroute/getCourses";

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

                    Type courseListType = new TypeToken<ArrayList<Course>>(){}.getType();

                    ArrayList<Course> courseArrayList = gson.fromJson(response.body().string(), courseListType);
                    adapter = new coursesListAdapter(getContext(), courseArrayList);


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


        adapter = new coursesListAdapter(getContext(), arrayList);
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new OnQueryTextListener() {
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
        return  view;
    }

    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof coursesFragOnClickListener){
            listener = (coursesFragOnClickListener) context;

        }else {
            throw  new ClassCastException(context.toString()
                + " must implement coursesFragOnClickListener.onFiltersBtnClick.");
        }
    }

    public void onClick(View view){
        listener.onFiltersBtnClick();
    }
}
