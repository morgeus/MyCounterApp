package com.example.projem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AyarActivity extends AppCompatActivity {

    int limitust = 10;
    int limitalt = 0;

    Button limitazalt, limitartir, limitazalt2, limitartir2;
    EditText ustlimit, altlimit;
    CheckBox tit, ses, tit2, ses2;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayar);

        limitartir = (Button)findViewById(R.id.limitartir);
        limitazalt = (Button)findViewById(R.id.limitazalt);
        limitartir2 = (Button)findViewById(R.id.limitartir2);
        limitazalt2 = (Button)findViewById(R.id.limitazalt2);
        ustlimit = (EditText) findViewById(R.id.ustlimit);
        altlimit = (EditText) findViewById(R.id.altlimit);
        tit = (CheckBox)findViewById(R.id.tit);
        ses = (CheckBox) findViewById(R.id.ses);
        tit2 = (CheckBox) findViewById(R.id.tit2);
        ses2 = (CheckBox) findViewById(R.id.ses2);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        ustlimit.setText(String.valueOf(limitust));

            limitartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    limitust++;
                    ustlimit.setText(String.valueOf(limitust));
                }
            });
        limitazalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitust--;
                ustlimit.setText(String.valueOf(limitust));
            }
        });
        ustlimit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                limitust = Integer.valueOf(ustlimit.getText().toString());
            }
        });

        altlimit.setText(String.valueOf(limitalt));

        limitartir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitalt++;
                altlimit.setText(String.valueOf(limitalt));
            }
        });
        limitazalt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limitalt--;
                altlimit.setText(String.valueOf(limitalt));
            }
        });
        altlimit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                limitalt = Integer.valueOf(altlimit.getText().toString());
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        limitust = Integer.valueOf(ustlimit.getText().toString());
        editor.putInt("ustlimit", limitust);
        editor.commit();
    }
}