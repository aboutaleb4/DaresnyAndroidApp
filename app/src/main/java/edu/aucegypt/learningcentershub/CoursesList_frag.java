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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    ArrayList<Course> courseArrayList;
    ArrayList<Course> courseArrayListFiltered = new ArrayList<Course>();
    ArrayList<Course> courseArrayListFilteredArea = new ArrayList<Course>();
    ArrayList<Course> courseArrayListFilteredPrice = new ArrayList<Course>();
    ArrayList<Course> courseArrayListFilteredDate = new ArrayList<Course>();


    coursesListAdapter adapter;
    ArrayList<String> CatNamesFilters;
    ArrayList<String> AreaNamesFilters;
    ArrayList<Integer> DateFilters;

    int PriceFilter = 0;

    private coursesFragOnClickListener listener;

    public interface coursesFragOnClickListener {

        public void onFiltersBtnClick();
        ;;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_courses_list, container, false);
        if(getArguments().getBoolean("isFilter")) {
            if (getArguments().getBoolean("isFilterCat"))
                CatNamesFilters = (ArrayList<String>) getArguments().getSerializable("CatNames");
            if(getArguments().getBoolean("isFilterArea"))
                AreaNamesFilters = (ArrayList<String>) getArguments().getSerializable("AreaNames");
            if(getArguments().getBoolean("isFilterPrice")){
                PriceFilter = getArguments().getInt("Price");

            }
            if(getArguments().getBoolean("isFilterStartDate"))
                DateFilters = (ArrayList<Integer>) getArguments().getSerializable("Start Date");

        }


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        filters_layout = (FrameLayout) view.findViewById(R.id.filters_layout);
        main_layout = (ConstraintLayout) view.findViewById(R.id.main_layout);
        filterBtn = (Button) view.findViewById(R.id.filtersBtn);

        filterBtn.setOnClickListener(this);

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        String url_api = url + "myroute/getCourses";

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url_api)
                .build();

      //  if(courseArrayList == null) {
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {

                        Gson gson = new Gson();

                        Type courseListType = new TypeToken<ArrayList<Course>>() {
                        }.getType();

                        if(courseArrayList != null)
                            courseArrayList.clear();

                        courseArrayList = gson.fromJson(response.body().string(), courseListType);

                        if (getArguments().getBoolean("isFilter")) {

                            if (CatNamesFilters != null) {
                                filterCategory(courseArrayList);
                                adapter = new coursesListAdapter(getContext(), courseArrayListFiltered);
                            }

                            if (AreaNamesFilters != null) {

                                if (CatNamesFilters != null) {
                                    filterArea(courseArrayListFiltered);
                                } else {
                                    filterArea(courseArrayList);
                                }
                                adapter = new coursesListAdapter(getContext(), courseArrayListFilteredArea);

                            }


                            if (PriceFilter != 0) {

                                if (AreaNamesFilters == null && CatNamesFilters == null) {
                                    filterPrice(courseArrayList);
                                } else if (AreaNamesFilters == null && CatNamesFilters != null){
                                    filterPrice(courseArrayListFiltered);
                                }else if (AreaNamesFilters != null){
                                    filterPrice(courseArrayListFilteredArea);

                                }
                                adapter = new coursesListAdapter(getContext(), courseArrayListFilteredPrice);

                            }
                            if(DateFilters != null){
                                if (AreaNamesFilters == null && CatNamesFilters == null && PriceFilter == 0) {
                                    filterDate(courseArrayList);
                                } else if (AreaNamesFilters == null && CatNamesFilters != null && PriceFilter == 0){
                                    filterDate(courseArrayListFiltered);
                                }else if (AreaNamesFilters != null && CatNamesFilters != null && PriceFilter == 0){
                                    filterDate(courseArrayListFilteredArea);
                                }else if (PriceFilter != 0){
                                    filterDate(courseArrayListFilteredPrice);

                                }
                                adapter = new coursesListAdapter(getContext(), courseArrayListFilteredDate);

                            }



                        } else {
                            adapter = new coursesListAdapter(getContext(), courseArrayList);
                        }


                        getActivity().runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {


                                        //  courseArrayListFiltered = filterCategory(CatNamesFilters, courseArrayList);

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

       // }


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

    public void filterCategory(ArrayList<Course> courseArrayList){

        for (Course itemModel : courseArrayList) {
            if (CatNamesFilters.contains(itemModel.getCatName())) {
                courseArrayListFiltered.add(itemModel);
            }
        }
    }
    public void filterDate(ArrayList<Course> courseArrayList){


        for (Course itemModel : courseArrayList) {


            Date StTime_date = Calendar.getInstance().getTime();
            Date EndTime_date = itemModel.getStDate();

            long startTime = StTime_date.getTime();
            long endTime = EndTime_date.getTime();
            long diffTime = endTime - startTime;
            long diffDays = diffTime / (1000 * 60 * 60 * 24);

            for(int i=0; i< DateFilters.size(); i++){
                if (DateFilters.get(i) >= diffDays) {
                    if(!courseArrayListFilteredDate.contains(itemModel))
                    courseArrayListFilteredDate.add(itemModel);
                }
            }
        }
    }


    public void filterArea(ArrayList<Course> courseArrayList){

        for (Course itemModel : courseArrayList) {
            if (AreaNamesFilters.contains(itemModel.getArea())) {
                if(!courseArrayListFilteredArea.contains(itemModel))
                    courseArrayListFilteredArea.add(itemModel);
            }
        }
    }


    public void filterPrice(ArrayList<Course> courseArrayList){

        for (Course itemModel : courseArrayList) {
            if (itemModel.getPrice()<= PriceFilter) {
                if(!courseArrayListFilteredPrice.contains(itemModel))
                    courseArrayListFilteredPrice.add(itemModel);
            }
        }
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
