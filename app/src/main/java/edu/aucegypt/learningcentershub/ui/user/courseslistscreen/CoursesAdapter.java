package edu.aucegypt.learningcentershub.ui.user.courseslistscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.service.network.DownloadImageTask;
import edu.aucegypt.learningcentershub.service.data.Course;
import edu.aucegypt.learningcentershub.ui.user.courseinfoscreen.CourseInfo;

import static edu.aucegypt.learningcentershub.service.network.APIcall.url;
import static edu.aucegypt.learningcentershub.service.utils.Utility.formatdouble;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.viewHolder> {

    Context context;
    ArrayList<Course> arrayList;

    public CoursesAdapter(Context context, ArrayList<Course> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public CoursesAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_course_listitem, viewGroup, false);
        return new CoursesAdapter.viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(CoursesAdapter.viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayList.get(position).getCourseName());

        new DownloadImageTask(viewHolder.image)
                .execute(url+"images/"+ arrayList.get(position).getCourseImage());

        viewHolder.LCname.setText((arrayList.get(position).getLCname()));

        viewHolder.Price.setText("EGP " + formatdouble(arrayList.get(position).getPrice()));

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView LCname;
        ImageView image;
        TextView Price;

        public viewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Home_course_name_view);
            image = (ImageView) itemView.findViewById(R.id.Home_course_image_view);
            LCname = (TextView) itemView.findViewById(R.id.Home_learningcenter_view);
            Price = itemView.findViewById(R.id.Home_price_view);


            itemView.setOnClickListener(this);

        }

        public void onClick(View view){

            int itemPosition = getAdapterPosition();
            Intent toCourseInfoIntent = new Intent(context, CourseInfo.class);

            toCourseInfoIntent.putExtra("CID", arrayList.get(itemPosition).getCID());
            toCourseInfoIntent.putExtra("LCID", arrayList.get(itemPosition).getLCID());
            context.startActivity(toCourseInfoIntent);
        }
    }
}
