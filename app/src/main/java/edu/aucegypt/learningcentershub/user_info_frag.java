package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class user_info_frag extends Fragment {
    RecyclerView listView;
    String [] data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.user_info, container, false);
        listView = view.findViewById(R.id.user_info_detail);
        if (getArguments() != null) {
            data  = new String [8];
            Bundle b = getArguments();
            data[0] = b.getString("Fname") + " " +b.getString("Lname");
            data[1] = b. getString("PhoneNo");
            data[2] =  b.getString("Email");
            data[3] = b.getString("KLevel");
            data[4] = b.getString("Area");
            data[5] = b.getString("City");

        }
        String [] data2  = getResources().getStringArray(R.array.user_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter(getContext(), data,data2 ));

        return view;

    }
}


class rvadapter extends RecyclerView.Adapter<rvadapter.ViewHolder2>{
    String[] rows1, row2;
    Context mContext;

    public rvadapter(Context context, String[] Names, String [] names2) {
        this.rows1 = names2;
        this.row2 = Names;
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
        holder.text1.setText(rows1[position]);
        holder.text2.setText(row2[position]);

    }

    @Override
    public int getItemCount() {
        return rows1.length;
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
