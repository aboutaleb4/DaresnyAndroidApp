package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class user_info_frag extends Fragment {
    RecyclerView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.user_info, container, false);
        listView = view.findViewById(R.id.user_info_detail);
        String [] data = getResources().getStringArray(R.array.user_info);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter(getContext(), data));

        return view;

    }
}


class rvadapter extends RecyclerView.Adapter<rvadapter.ViewHolder2>{
    String[] rows;
    Context mContext;

    public rvadapter(Context context, String[] Names) {
        this.rows = Names;
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, parent, false);
        return  new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.text1.setText(rows[position]);
        holder.text2.setText(rows[position]);

    }

    @Override
    public int getItemCount() {
        return rows.length;
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{

        TextView text1;
        TextView text2;

        public ViewHolder2(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.row_text2);
            text2 = itemView.findViewById(R.id.row_text3);
        }
    }
}
