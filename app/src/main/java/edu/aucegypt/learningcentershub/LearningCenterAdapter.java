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

        import edu.aucegypt.learningcentershub.data.Category;
        import edu.aucegypt.learningcentershub.data.LearningCenter;

        import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class LearningCenterAdapter extends RecyclerView.Adapter<LearningCenterAdapter.viewHolder> implements  Filterable{

    Context context;
    ArrayList<LearningCenter> arrayList, arrayListFiltered;

    public LearningCenterAdapter(Context context, ArrayList<LearningCenter> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_learningcenter_listitem, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayList.get(position).getLCname());
        viewHolder.category.setText(arrayList.get(position).getCatName());
        new DownloadImageTask(viewHolder.image)
                .execute(url+"images/"+ arrayList.get(position).getLogo());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView category;
        ImageView image;

        public viewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.learningcenter_name_view);
            category = (TextView) itemView.findViewById(R.id.learningcenter_category_view);
            image = (ImageView) itemView.findViewById(R.id.learningcenter_image_view);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
//            name.getText();
            int itemPosition = getAdapterPosition();
            Intent toCatCourses = new Intent(context, LearningCenterInfoActivity.class);
            toCatCourses.putExtra("LCID", arrayList.get(itemPosition).getLCID());
            context.startActivity(toCatCourses);
        }
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                ArrayList<LearningCenter> arrayListFilter = new ArrayList<LearningCenter>();

                if(constraint == null|| constraint.length() == 0) {
                    results.count = arrayList.size();
                    results.values = arrayList;
                } else {
                    for (LearningCenter itemModel : arrayList) {
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
                arrayListFiltered = (ArrayList<LearningCenter>) results.values;
                notifyDataSetChanged();

                if(arrayListFiltered.size() == 0) {
                    Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                }

            }
        };
        return filter;
    }
}
