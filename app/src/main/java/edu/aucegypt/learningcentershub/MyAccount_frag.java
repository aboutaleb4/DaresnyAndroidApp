package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyAccount_frag extends Fragment implements RecyclerViewAdapter.RecyclerViewListner {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    public static String [] message = new String[4];
    Button signout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_account_frag, container, false);
        TextView name = view.findViewById(R.id.full_name);
        name.setText(message[0]);
        EditText email = view.findViewById(R.id.emailedit);
        email.setText(message[1]);
        EditText phone = view.findViewById(R.id.phonenumberedit);
        phone.setText(message[2]);
        signout = view.findViewById(R.id.signout_myaccount);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        /*Category = getResources().getStringArray(R.array.category_4);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_id_3);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        //RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), Category, categoryIcon, this);
        recyclerView.setAdapter(adapter);*/
        return view;

    }

    @Override
    public void RecyclerViewClick(int position) {

    }
}