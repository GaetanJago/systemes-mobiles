package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnConnexion;
    private Button btnDeconnexion;
    private Button btnStop;

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnStart = findViewById(R.id.buttonStart);
        this.btnConnexion = findViewById(R.id.buttonConnexion);
        this.btnDeconnexion = findViewById(R.id.buttonDeconnexion);
        this.btnStop = findViewById(R.id.buttonStop);

        this.editText = findViewById(R.id.editText);
    }
}