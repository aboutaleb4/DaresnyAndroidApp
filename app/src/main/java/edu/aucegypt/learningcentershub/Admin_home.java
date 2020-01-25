package edu.aucegypt.learningcentershub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;


public class Admin_home extends AppCompatActivity {
    private static String[] message = new String[1];
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedInstanceState=getIntent().getExtras();
        String lcid = savedInstanceState.getString("lcid");
        Network(lcid);
        setContentView(R.layout.admin_home);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle b = new Bundle();
        b.putString("LCname",message[0]);
        Fragment hf =  new home_frag();
        hf.setArguments(b);
        b = new Bundle();
        b.putString("LCID",lcid);
        b.putString("LCname",message[0]);
        Fragment navbar =  new NavBar_LC();
        navbar.setArguments(b);
        fragmentTransaction.replace(R.id.fragment,navbar);
        fragmentTransaction.replace(R.id.fragment_1, new TopBar());
        fragmentTransaction.replace(R.id.fragment_2, hf);
        fragmentTransaction.commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


    }
    private void Network(String id){
        String url2 = url+"/myroute/LCinfo?id="+ id;

        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final Request request = new Request.Builder()
                .url(url2)
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
                            Admin_home.message[0] = myResponseReader.getString("LCname");

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
    void chooseImage()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                String path =  extras.getString("media-path");
                uri =  Uri.parse(new File(path).toString());
                ImageView t=findViewById(R.id.row_edit3);
                t.setImageURI(Uri.parse(path));
            }
        }
    }
}
