package com.example.tp2b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button btn2;
    private EditText editText2;
    private Personne pers;
    private TextView infoNom;
    private TextView infoPrenom;
    private TextView infoAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        editText2 = findViewById(R.id.editText2);
        infoNom = findViewById(R.id.infoNom);
        infoPrenom = findViewById(R.id.infoPrenom);
        infoAge = findViewById(R.id.infoAge);

        Bundle objetbunble = this.getIntent().getExtras();
        if(objetbunble != null){
            String InfoPasse = objetbunble.getString("passInfo");
            if(InfoPasse != null)
                editText2.setText(InfoPasse);
        }
        pers = (Personne) this.getIntent().getSerializableExtra("Personne");
        if(pers != null){
            infoNom.setText("Nom : " + pers.getNom());
            infoPrenom.setText("Prenom : " + pers.getPrenom());
            infoAge.setText("Age : " + pers.getAge());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btn2){
            Intent defineIntent = new Intent(this,MainActivity.class);
            Bundle objetbunble = new Bundle();
            objetbunble.putString("passInfo",getString(R.string.hello_world));
            defineIntent.putExtras(objetbunble);
            setResult(Activity.RESULT_OK,defineIntent);
            finish();
        }
    }
}