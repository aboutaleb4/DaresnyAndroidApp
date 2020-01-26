package edu.aucegypt.learningcentershub;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class reg_user extends Fragment {
    RecyclerView listView;
    String [] Fname, Lname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reg_user_frag, container, false);
        listView = view.findViewById(R.id.registred_users_users);
        if (getArguments() != null) {
            Bundle b = getArguments();
            Fname = b.getStringArray("fnames");
            Lname = b.getStringArray("lnames");
        }
        String[] Data = {""};
        if (Fname != null) {
             Data = new String[Fname.length];
            for (int i = 0; i < Fname.length; i++) {
                Data[i] = Fname[i] + " " + Lname[i];
            }
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter3(getContext(), Data));
        listView.setNestedScrollingEnabled(false);
        return view;
    }
}
