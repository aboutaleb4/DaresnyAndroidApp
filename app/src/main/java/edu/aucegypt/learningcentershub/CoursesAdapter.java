package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>  {

    String[] names;
    String[] learningcenter;
    String[] price;
    int[] icons;
    Context mContext;


    public CoursesAdapter(Context context, String[] Names, String[] LearningCenters, String[] Price, int[] Icons) {
        this.names = Names;
        this.learningcenter = LearningCenters;
        this.price = Price;
        this.names = Names;
        this.icons = Icons;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_course_listitem, parent, false);
        return new CoursesAdapter.CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CourseViewHolder holder, final int position) {
        holder.image.setImageResource(icons[position]);
        holder.name.setText(names[position]);
        holder.learningcenter.setText(learningcenter[position]);
        holder.price.setText(price[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }



    public class CourseViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView learningcenter;
        TextView price;

        public CourseViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.course_image_view);
            name = itemView.findViewById(R.id.name_view);
            learningcenter = itemView.findViewById(R.id.learningcenter_view);
            price = itemView.findViewById(R.id.price_view);
        }
    }
}
