package edu.aucegypt.learningcentershub;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static edu.aucegypt.learningcentershub.Network.APIcall.url;

public class MyAccount_frag extends Fragment implements RecyclerViewAdapter.RecyclerViewListner {

    String[] Category;
    int[] categoryIcon = {R.drawable.science, R.drawable.programming, R.drawable.engineering, R.drawable.language};
    public static String [] message = new String[4];
    Button signout;
    public static  EditText email, phone;
    public static TextView name;
    String ph, em, fn;
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
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ph = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            Network();
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                em = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Network();

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fn = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Network();

            }
        });
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
    void Network(){SharedPreferences prefs = this.getActivity().getSharedPreferences("login_shared_preference", MODE_PRIVATE);
        Boolean status = prefs.getBoolean("status", false);
        int uid = prefs.getInt("uid", 0); //0 is the default value.
        if (!status)
            return;
        String url2 = url+"myroute/userupdate";
        OkHttpClient client = new OkHttpClient();
        String [] name = fn.split(" ");
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = "{\"fname\":\""+name[0]+"\", \"lname\":"+name[1]
                +", \"phone\":\""+ph+"\", \"email\": \""+em
                +"\",\"id\":"+ uid + "}";

        final RequestBody body = RequestBody.create(json,JSON);
        final Request request = new Request.Builder()
                .url(url2)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    JSONObject myResponseReader;
                    if (myResponse != "") {
                        try {
                            myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }});


    }

}