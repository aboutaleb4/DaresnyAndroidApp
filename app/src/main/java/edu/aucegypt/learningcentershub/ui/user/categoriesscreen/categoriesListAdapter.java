package edu.aucegypt.learningcentershub.ui.user.categoriesscreen;

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

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.service.network.DownloadImageTask;
import edu.aucegypt.learningcentershub.service.data.Category;

import static edu.aucegypt.learningcentershub.service.network.APIcall.url;

public class categoriesListAdapter extends RecyclerView.Adapter<categoriesListAdapter.viewHolder> implements Filterable {

    Context context;
    ArrayList<Category> arrayList,arrayListFiltered;

    public categoriesListAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListFiltered = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cat_list_item, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayListFiltered.get(position).getCatName());

        new DownloadImageTask(viewHolder.image)
                .execute(url+"images/"+ arrayListFiltered.get(position).getCatImage());

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
//            name.getText();
            Intent toCatCourses = new Intent(context, CatCoursesActivity.class);
            toCatCourses.putExtra("CatName", name.getText());
            context.startActivity(toCatCourses);
        }
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                ArrayList<Category> arrayListFilter = new ArrayList<Category>();

                if(constraint == null|| constraint.length() == 0) {
                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    for (Category itemModel : arrayList) {
                        if(itemModel.getCatName().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
                arrayListFiltered = (ArrayList<Category>) results.values;
                notifyDataSetChanged();

                if(arrayListFiltered.size() == 0) {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                }

            }
        };
        return filter;
    }
}
