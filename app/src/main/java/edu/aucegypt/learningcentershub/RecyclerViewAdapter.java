package edu.aucegypt.learningcentershub;
import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.aucegypt.learningcentershub.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    String[] names;
    int[] icons;
    Context mContext;
    Boolean[] isSelected;
    private RecyclerViewListner mrecyclerViewListner;
    /////////////////////////////////////////////////
    private SparseBooleanArray selectedItems;
////////////////////////////////////////////////////

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
////////////////////////////////////////////////////////////////////////////////////////////////

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
