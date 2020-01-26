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

public class LearningCenterAdapter extends RecyclerView.Adapter<LearningCenterAdapter.viewHolder>{

    Context context;
    ArrayList<LearningCenter> arrayList;

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
}
