package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class filtersListAdapter extends RecyclerView.Adapter<filtersListAdapter.ViewHolder> {

    private final String names[];
    private final Context mContext;
    ArrayList<String> checkedListStr = new ArrayList<String>();
    FragmentCommunication mCommunication;

    private String filter_type;



    public String getFilter_type() {
        return filter_type;
    }

    public void setFilter_type(String filter_type) {
        this.filter_type = filter_type;
    }

    public filtersListAdapter(String[] names, Context mContext, String filter_type, FragmentCommunication communication) {
        this.names = names;
        this.mContext = mContext;
        this.filter_type = filter_type;
        this.mCommunication = communication;
    }

    public filtersListAdapter(String[] names, Context mContext) {
        this.names = names;
        this.mContext = mContext;
    }

    public interface FragmentCommunication {
        void setCatNames(ArrayList<String> CatNames);
        void setAreaNames(ArrayList<String> AreaNames);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.filter_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String name = names[position];
        holder.checkBox.setText(name);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(this);
        }

        public void onClick(View view){

            int itemPosition = getAdapterPosition();

            switch (filter_type){
                case "Category":
                    if(checkBox.isChecked())
                    {
                        Log.d(TAG, "onClick: "+checkBox.getText().toString());
                         checkedListStr.add(checkBox.getText().toString());
                         mCommunication.setCatNames(checkedListStr);

                    }
                    else
                    {

                        checkedListStr.remove(checkBox.getText().toString());
                        mCommunication.setCatNames(checkedListStr);

                    }
                    break;

                case "Area":

                    if(checkBox.isChecked())
                    {
                        Log.d(TAG, "onClick: "+checkBox.getText().toString());
                        checkedListStr.add(checkBox.getText().toString());
                        mCommunication.setAreaNames(checkedListStr);

                    }
                    else
                    {

                        checkedListStr.remove(checkBox.getText().toString());
                        mCommunication.setAreaNames(checkedListStr);

                    }


                    break;



            }
        }


    }
}
