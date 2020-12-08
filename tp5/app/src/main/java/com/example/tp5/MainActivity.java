package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button callBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Récupération des éléments de l'UI
        callBtn = findViewById(R.id.callButton);
        textView = findViewById(R.id.editText);

        // Event bouton
        callBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == callBtn){
            URL newurl = null;
            try {
                newurl = new URL("http://www.google.com");
                CallWebAPI c = new CallWebAPI(textView);
                c.execute(newurl.toString()) ;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void readStream(InputStream in) {
        try {
            // récupération des données sous un tableau de byte
            ByteArrayOutputStream into = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            for (int n; 0 < (n = in.read(buf));) {
                into.write(buf, 0, n);
            }
            into.close();
            String text = new String(into.toByteArray(), "UTF-8");

            // Mise à jour de
            textView.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}