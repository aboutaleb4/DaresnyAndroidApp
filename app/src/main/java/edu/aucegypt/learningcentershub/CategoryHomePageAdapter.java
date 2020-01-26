package edu.aucegypt.learningcentershub;

        import android.content.Context;
        import android.content.Intent;
        import android.view.View;
        import android.view.LayoutInflater;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

        import edu.aucegypt.learningcentershub.data.Category;

        import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class CategoryHomePageAdapter extends RecyclerView.Adapter<CategoryHomePageAdapter.viewHolder>{

    Context context;
    ArrayList<Category> arrayList;

    public CategoryHomePageAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listitem, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.name.setText(arrayList.get(position).getCatName());

        new DownloadImageTask(viewHolder.image)
                .execute(url+"images/"+ arrayList.get(position).getCatImage());

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
            image = (ImageView) itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
//            name.getText();
            Intent toCatCourses = new Intent(context, CatCoursesActivity.class);
            toCatCourses.putExtra("CatName", name.getText());
            context.startActivity(toCatCourses);
        }
    }
}
