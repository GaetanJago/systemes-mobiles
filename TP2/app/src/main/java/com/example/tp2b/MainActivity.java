package com.example.tp2b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private EditText editText1;
    private int Fenetre1ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        editText1 = findViewById(R.id.editText1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn1){
            Intent defineIntent = new Intent(this,MainActivity2.class);
            Bundle objetbunble = new Bundle();
            objetbunble.putString("passInfo",editText1.getText().toString());
            defineIntent.putExtras(objetbunble);
            this.startActivityForResult(defineIntent,Fenetre1ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Si le résultat provient d’une demande de la fenêtre1
        if (requestCode == Fenetre1ID) {
            // le code retour est bon
            if (resultCode == Activity.RESULT_OK) {
                //récupérer les informations
                //et les afficher dans TextView
                Bundle objetbunble = data.getExtras();
                if(objetbunble != null){
                    String InfoPasse= objetbunble.getString("passInfo");
                    editText1.setText(InfoPasse);
                }

            }
        }
    }
}