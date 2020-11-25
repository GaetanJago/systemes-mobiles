package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnConnexion;
    private Button btnDeconnexion;
    private Button btnStop;

    private EditText editText;

    private IBackgroundService monservice;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("BackgroundService", "Connected!");
            monservice = ((BackgroundServiceBinder)service).getService();
            monservice.addListener(listener);
        }
        public void onServiceDisconnected(ComponentName name) {
            Log.i("BackgroundService", "Disconnected!");
        }
    };

    private IBackgroundServiceListener listener = new IBackgroundServiceListener() {
        public void dataChanged(final Object data) {
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    // Mise à jour de l'UI
                    Date date = (Date) data;
                    editText.setText(new SimpleDateFormat("HH:mm:ss").format(date));
                }
            });
        }
    };




    private boolean shouldUnbind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnStart = findViewById(R.id.buttonStart);
        this.btnConnexion = findViewById(R.id.buttonConnexion);
        this.btnDeconnexion = findViewById(R.id.buttonDeconnexion);
        this.btnStop = findViewById(R.id.buttonStop);

        this.btnStart.setOnClickListener(this);
        this.btnConnexion.setOnClickListener(this);
        this.btnDeconnexion.setOnClickListener(this);
        this.btnStop.setOnClickListener(this);

        this.editText = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        if(v == btnStart){
            Intent intent = new Intent(this,BackgroundService.class);
            startService(intent);
        }
        if(v == btnConnexion && !this.shouldUnbind){
            Intent intent = new Intent(this,BackgroundService.class);
            //Connexion au service
            if(bindService(intent,connection,BIND_AUTO_CREATE))
                shouldUnbind = true;

        }
        if(v == btnDeconnexion){
            if(connection != null && this.shouldUnbind){
                unbindService(connection);
                monservice.removeListener(listener);
                this.shouldUnbind = false;
            }

        }
        if(v == btnStop){
            if(connection != null && this.shouldUnbind) {
                unbindService(connection);
                this.shouldUnbind = false;
            }
            Intent intent = new Intent(this, BackgroundService.class);
            stopService(intent);
        }
    }
}