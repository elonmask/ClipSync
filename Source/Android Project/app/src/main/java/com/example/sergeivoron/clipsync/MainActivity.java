package com.example.sergeivoron.clipsync;

import android.animation.ObjectAnimator;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static android.R.animator.fade_in;

public class MainActivity extends AppCompatActivity {

    String TAG = "TAG";
    String path;
    EditText text;
    TextView v;
    Container c;
    Button start;
    Animation fade_out;
    AnimationSet s;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        s = new AnimationSet(false);
        s.addAnimation(fade_out);

        text = (EditText) findViewById(R.id.edit);
        start = (Button) findViewById(R.id.button2);
        v = (TextView) findViewById(R.id.text);
        v.setText("");

        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("key", "") != null) {

                if (sharedPreferences.getString("key", "").length() == 5) {

                    text.setText(sharedPreferences.getString("key",""));
                    start.performClick();
                }

            }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, Sending.class));
    }

    public void paste(View view) {

        editor.putString("key", text.getText().toString());
        editor.commit();

        Container.setCode(text.getText().toString());
        startService(new Intent(this, Sending.class));
        text.setText("");
        v.setText("Sync is On");

        start.startAnimation(s);
        text.startAnimation(s);

        start.setVisibility(View.GONE);
        text.setVisibility(View.GONE);

    }
}
