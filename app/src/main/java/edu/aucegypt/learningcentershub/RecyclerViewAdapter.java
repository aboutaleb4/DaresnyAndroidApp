package edu.aucegypt.learningcentershub;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
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
import edu.aucegypt.learningcentershub.data.Category;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;


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
        TextView name;
        ImageView image;
        RecyclerViewListner recyclerViewListner;
        public viewHolder(View itemView, RecyclerViewListner recyclerViewListner ) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_view);
            image = (ImageView) itemView.findViewById(R.id.image_view);
            this.recyclerViewListner = recyclerViewListner;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            recyclerViewListner.RecyclerViewClick(getAdapterPosition());
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
