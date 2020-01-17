package edu.aucegypt.learningcentershub;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class TopBar extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.topbar, container, false);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Toolbar myToolbar =(Toolbar) getActivity().findViewById(R.id.topbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);

        myToolbar.setLogo(R.drawable.logo);
    }
}
