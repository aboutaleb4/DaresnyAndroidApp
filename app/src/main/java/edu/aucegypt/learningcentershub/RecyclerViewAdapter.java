package edu.aucegypt.learningcentershub;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.aucegypt.learningcentershub.data.Category;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    Context context;
    ArrayList<Category> arrayList;
    private RecyclerViewListner mrecyclerViewListner;

    public RecyclerViewAdapter(Context context, ArrayList<Category> arrayList, RecyclerViewListner recyclerViewListner) {
        this.context = context;
        this.arrayList = arrayList;
        this.mrecyclerViewListner = recyclerViewListner;
    }

    @Override
    public  viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_listitem, viewGroup, false);
        return new viewHolder(view, mrecyclerViewListner);
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
        Boolean isSelected = false;
        TextView name;
        ImageView image;
        CardView card;
        RecyclerViewListner recyclerViewListner;
        public viewHolder(View itemView, RecyclerViewListner recyclerViewListner ) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Home_course_name_view);
            image = (ImageView) itemView.findViewById(R.id.image_view);
            card = (CardView) itemView.findViewById(R.id.card_view);
            this.recyclerViewListner = recyclerViewListner;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            recyclerViewListner.RecyclerViewClick(getAdapterPosition());
            if (!isSelected)
                card.setBackgroundColor(Color.parseColor("#008095"));
            else
                card.setBackgroundColor(Color.parseColor("#ffffff"));
            isSelected = !isSelected;

        }

    }

    public interface RecyclerViewListner{
        void RecyclerViewClick(int position);
    }
}







 /*   /////////////////////////////////////////////////
    private SparseBooleanArray selectedItems;
////////////////////////////////////////////////////*/

/*
    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
/*////////////////////////////////////////////////////////////////////////////////////////////////
/*
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    String[] names;
    int[] icons;
    Context mContext;
    Boolean[] isSelected;
    private RecyclerViewListner mrecyclerViewListner;

    public RecyclerViewAdapter(Context context, String[] Names, int[] Icons, RecyclerViewListner recyclerViewListner) {
        this.names = Names;
        this.icons = Icons;
        this.mContext = context;
        this.mrecyclerViewListner = recyclerViewListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view, mrecyclerViewListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.image.setImageResource(icons[position]);
        holder.name.setText(names[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name;
        RecyclerViewListner recyclerViewListner;
        public ViewHolder(View itemView, RecyclerViewListner recyclerViewListner) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            this.recyclerViewListner = recyclerViewListner;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            recyclerViewListner.RecyclerViewClick(getAdapterPosition());
        }
    }
    public interface RecyclerViewListner{
        void RecyclerViewClick(int position);
    }
}
*/