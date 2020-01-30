package edu.aucegypt.learningcentershub.ui.user.registercoursescreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.aucegypt.learningcentershub.R;
import edu.aucegypt.learningcentershub.ui.TopBar;
import edu.aucegypt.learningcentershub.ui.user.homescreen.MainActivity;

public class Payment extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.payment);
        Button pay = (Button) findViewById(R.id.pay);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment5_1, new TopBar());
        fragmentTransaction.commit();

        webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.paypal.com/eg/signin");

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Payment.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
