package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;
import edu.aucegypt.learningcentershub.data.Schedule;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;
import static edu.aucegypt.learningcentershub.utitlies.Utility.formatdouble;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.viewHolder>  {

    Context context;
    ArrayList<Schedule> arrayList;

    public scheduleAdapter(Context context, ArrayList<Schedule> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_list_item, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.day.setText(arrayList.get(position).getDay());

        viewHolder.start_time.setText(arrayList.get(position).getStartTime());

        viewHolder.end_time.setText(arrayList.get(position).getEndTime());

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView day;
        TextView start_time;
        TextView end_time;

        public viewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            end_time = (TextView) itemView.findViewById(R.id.end_time);


        }

        public void onClick(View view){


        }
    }

}
