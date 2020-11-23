package com.example.tp3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service implements IBackgroundService {

    private Timer timer ;
    private ArrayList<IBackgroundServiceListener> listeners = null;
    private BackgroundServiceBinder binder ;

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        Log.d(this.getClass().getName(), "onCreate");

        binder = new BackgroundServiceBinder(this);
        listeners = new ArrayList< IBackgroundServiceListener >();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(this.getClass().getName(), "onStart");
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // Executer de votre tâche
                //System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                fireDataChanged(new Date());
            }
        }, 0, 1000);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        this.listeners.clear();
        Log.d(this.getClass().getName(), "onDestroy");
        this.timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void addListener(IBackgroundServiceListener listener) {
        if(listeners != null){
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(IBackgroundServiceListener listener) {
        if(listeners != null){
            listeners.remove(listener);
        }
    }

    // Notification des listeners
    private void fireDataChanged(Object data){
        if(listeners != null){
            for(IBackgroundServiceListener listener: listeners){
                listener.dataChanged(data);
            }
        }
    }

}