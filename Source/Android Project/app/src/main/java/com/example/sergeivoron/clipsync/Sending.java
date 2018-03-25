package com.example.sergeivoron.clipsync;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;

import java.sql.Time;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Sending extends Service {

    String path;
    DatabaseReference database;
    DatabaseReference myRef;

    /**
    private ClipboardManager.OnPrimaryClipChangedListener listener = new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {

            ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);

            String data = clipboard.getText().toString();
            myRef.setValue(data);
        }
    };
     **/

    @Override
    public void onCreate() {

        path = Container.getCode();
        database = FirebaseDatabase.getInstance().getReference();
        myRef = database.child(path);

        //((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).addPrimaryClipChangedListener(listener);

        if (database != null && myRef != null && path != null) {

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                       String value = dataSnapshot.getValue().toString();

                    ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);

                    clipboard.setText(value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            });
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

        return Service.START_STICKY;
    }
}
