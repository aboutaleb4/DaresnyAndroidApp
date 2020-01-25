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

import java.io.File;


public class Admin_home extends AppCompatActivity {
    public static String[] message = new String[1];
    Uri uri;
    public static String lcid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState=getIntent().getExtras();
         lcid = savedInstanceState.getString("lcid");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_home);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_1, new TopBar());
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
        fragmentTransaction.replace(R.id.fragment_2, hf);
        fragmentTransaction.commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


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
