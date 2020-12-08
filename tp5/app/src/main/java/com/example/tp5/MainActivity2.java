package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button searchBtn;
    private TextView resultIP;
    private TextView ipAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Récupération des éléments de l'UI
        searchBtn = findViewById(R.id.search);
        resultIP = findViewById(R.id.resultIP);
        ipAdress = findViewById(R.id.ipAdress);

        // Event bouton
        searchBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == searchBtn){
            CallWebAPI2 c = new CallWebAPI2(resultIP);
            c.execute(ipAdress.getText().toString()) ;
        }
    }
}