package com.example.projem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView value;
    Button plus, minus, ayar;

    int currentValue = 0;
    int upperLimit;
    int lowerLimit;
    boolean upperVib;
    boolean upperSound;
    boolean lowerVib;
    boolean lowerSound;

    AyarClass ayarClass;
    Vibrator vibrator = null;
    MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        value = (TextView)findViewById(R.id.value);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        ayar = (Button)findViewById(R.id.ayar);

        Context context = getApplicationContext();
        ayarClass = AyarClass.getInstance(context);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        player = MediaPlayer.create(context,R.raw.bildirim);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(1);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(-1);
            }
        });
        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this,AyarActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ayarClass.loadValues();
        getPreferences();
    }
        private void getPreferences(){
            upperLimit = ayarClass.upperLimit;
            lowerLimit = ayarClass.lowerLimit;
            currentValue = ayarClass.currentValue;
            upperVib = ayarClass.upperVib;
            upperSound = ayarClass.upperSound;
            lowerVib = ayarClass.lowerSound;
            lowerSound = ayarClass.lowerSound;

        }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
            int action = event.getAction();
            int keyCode = event.getKeyCode();
                switch (keyCode){
                    case KeyEvent.KEYCODE_VOLUME_UP:
                        if(action == KeyEvent.ACTION_DOWN)
                            valueUpdate(+5);
                            return true;
                    case KeyEvent.KEYCODE_VOLUME_DOWN:
                        if(action == KeyEvent.ACTION_DOWN)
                            valueUpdate(-5);
                        return true;
                }
        return super.dispatchKeyEvent(event);
    }
    private void valueUpdate(int step){
        if (step<0){
            if (currentValue+step<lowerLimit){
                currentValue = lowerLimit;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));}
                player.start();

            }
            else
                currentValue +=step;
        }
        else{
                if (currentValue+step>upperLimit){
                    currentValue = upperLimit;
                    player.start();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));}
                }
                else
                    currentValue +=step;
        }
        value.setText(String.valueOf(currentValue));
    }
}