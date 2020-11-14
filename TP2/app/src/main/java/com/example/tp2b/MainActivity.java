package com.example.tp2b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Button submit;
    private EditText editText1;
    private int Fenetre1ID = 101;
    private TextInputLayout inputNom;
    private TextInputLayout inputAge;
    private TextInputLayout inputPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        submit = findViewById(R.id.submit);
        editText1 = findViewById(R.id.editText1);
        inputNom = findViewById(R.id.inputNom);
        inputAge = findViewById(R.id.inputAge);
        inputPrenom = findViewById(R.id.inputPrenom);

        submit.setOnClickListener(this);
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
        if(v == submit){
            Intent defineIntent = new Intent(this,MainActivity2.class);
            try {
                defineIntent.putExtra("Personne",new Personne(
                        inputNom.getEditText().getText().toString(),
                        inputPrenom.getEditText().getText().toString(),
                        parseInt(inputAge.getEditText().getText().toString())));
                this.startActivityForResult(defineIntent,Fenetre1ID);
            }
            catch (Exception e){}
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