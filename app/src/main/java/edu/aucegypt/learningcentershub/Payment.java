package edu.aucegypt.learningcentershub;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Payment extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.payment);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment5_1, new TopBar());
        fragmentTransaction.commit();

        webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.paypal.com/eg/signin");

    }
}
