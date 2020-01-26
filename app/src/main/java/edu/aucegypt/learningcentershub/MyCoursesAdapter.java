package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;
public class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.viewHolder> {
    Context context;
    ArrayList<Course> arrayList;

    public MyCoursesAdapter(Context context, ArrayList<Course> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_courses_list_view, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayList.get(position).getCourseName());

        new DownloadImageTask(viewHolder.image)
                .execute(url+"images/"+ arrayList.get(position).getCourseImage());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView image;

        public viewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Home_course_name_view);
            image = (ImageView) itemView.findViewById(R.id.Home_course_image_view);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            int itemPosition = getAdapterPosition();
            Intent toCatCourses = new Intent(context, LearningCenterInfoActivity.class);
            toCatCourses.putExtra("LCID", arrayList.get(itemPosition).getLCID());
            context.startActivity(toCatCourses);
        }
    }
}

