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

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Course;

public class coursesListAdapter extends RecyclerView.Adapter<coursesListAdapter.viewHolder> implements Filterable {

    Context context;
    ArrayList<Course> arrayList,arrayListFiltered;

    public coursesListAdapter(Context context, ArrayList<Course> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.courses_list_item, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayListFiltered.get(position).getCourseName());
        viewHolder.image.setImageResource(arrayListFiltered.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return arrayListFiltered.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView image;

        public viewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            Intent toCourseInfoIntent = new Intent(context, CourseInfoActivity.class);
            context.startActivity(toCourseInfoIntent);
        }
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                ArrayList<Course> arrayListFilter = new ArrayList<Course>();

                if(constraint == null|| constraint.length() == 0) {
                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    for (Course itemModel : arrayList) {
                        if(itemModel.getCourseName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            arrayListFilter.add(itemModel);
                        }
                    }
                    results.count = arrayListFilter.size();
                    results.values = arrayListFilter;

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayListFiltered = (ArrayList<Course>) results.values;
                notifyDataSetChanged();

                if(arrayListFiltered.size() == 0) {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                }

            }
        };
        return filter;
    }
}
