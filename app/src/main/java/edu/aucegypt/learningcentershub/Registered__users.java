package edu.aucegypt.learningcentershub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class Registered__users extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.registered_users, container, false);
        listView = view.findViewById(R.id.registred_users_users);
        String[] Data = {"User 1", "User 2"}; //comes from database
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, Data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment selectedFragment = null;
                selectedFragment = new user_info_frag();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2_2, selectedFragment).commit();
    }
}
