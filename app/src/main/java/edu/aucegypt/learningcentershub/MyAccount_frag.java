package edu.aucegypt.learningcentershub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class MyAccount_frag extends Fragment implements RecyclerViewAdapter.RecyclerViewListner {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    public static String [] message = new String[4];
    Button signout;
    public static  EditText email, phone;
    public static TextView name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("login_shared_preference", MODE_PRIVATE).edit();
        View view = inflater.inflate(R.layout.my_account_frag, container, false);
         name = view.findViewById(R.id.full_name);
        if (message[0]=="null null") message[0] = "";
        name.setText(message[0]);
         email = view.findViewById(R.id.emailedit);
        if (message[1]=="null") message[1] = "";
        email.setText(message[1]);
         phone = view.findViewById(R.id.phonenumberedit);
        if (message[2]=="null") message[2] = "";
        phone.setText(message[2]);
        signout = view.findViewById(R.id.signout_myaccount);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("status", false);
                editor.putInt("uid", 0);
                editor.apply();
            }
        });
        /*Category = getResources().getStringArray(R.array.category_4);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_id_3);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        //RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), Category, categoryIcon, this);
        recyclerView.setAdapter(adapter);*/
        return view;

    }
    public static void cleanup(){
        for (int i = 0; i<message.length;i++)
        {
            message[i]= "";
        }
        email.setText(message[1]);
        phone.setText(message[2]);
        name.setText(message[0]);


    }
    @Override
    public void RecyclerViewClick(int position) {

    }
}