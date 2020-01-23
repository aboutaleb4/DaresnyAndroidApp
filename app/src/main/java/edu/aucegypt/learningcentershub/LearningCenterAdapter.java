package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;
public class LearningCenterAdapter extends RecyclerView.Adapter<LearningCenterAdapter.LearningCenterViewHolder>  {

    String[] names;
    String[] category;
    String[] location;
    int[] icons;
    Context mContext;


    public LearningCenterAdapter(Context context, String[] Names, String[] Category, String[] Location, int[] Icons) {
        this.names = Names;
        this.category = Category;
        this.location = Location;
        this.icons = Icons;
        this.mContext = context;
    }

    @NonNull
    @Override
    public LearningCenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_learningcenter_listitem, parent, false);
        return new LearningCenterAdapter.LearningCenterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningCenterAdapter.LearningCenterViewHolder holder, final int position) {
        holder.image.setImageResource(icons[position]);
        holder.name.setText(names[position]);
        holder.category.setText(category[position]);
        holder.location.setText(location[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }



    public class LearningCenterViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView category;
        TextView location;

        public LearningCenterViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.learningcenter_image_view);
            name = itemView.findViewById(R.id.learningcenter_name_view);
            category = itemView.findViewById(R.id.learningcenter_category_view);
            location = itemView.findViewById(R.id.learningcenter_location_view);
        }
    }
}
