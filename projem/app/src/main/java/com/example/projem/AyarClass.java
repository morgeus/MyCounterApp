package com.example.projem;

import android.content.Context;
import android.content.SharedPreferences;

public class AyarClass {
    int upperLimit;
    int lowerLimit;
    int currentValue;
    boolean upperVib;
    boolean upperSound;
    boolean lowerVib;
    boolean lowerSound;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static AyarClass ayarClass = null;

    private AyarClass(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static AyarClass getInstance(Context context){
        if (ayarClass == null){
            ayarClass = new AyarClass(context);
        }
        return ayarClass;
    }
    public void setValues(int upperLimit, int lowerLimit, int currentValue, boolean upperVib,
                          boolean upperSound, boolean lowerVib, boolean lowerSound){
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.currentValue = currentValue;
        this.upperVib = upperVib;
        this.upperSound = upperSound;
        this.lowerVib = lowerVib;
        this.lowerSound = lowerSound;

    }
    public void saveValues(){
        editor.putInt("upperLimit",upperLimit);
        editor.putInt("lowerLimit",lowerLimit);
        editor.putInt("currentValue",currentValue);
        editor.putBoolean("upperVib",upperVib);
        editor.putBoolean("upperSound",upperSound);
        editor.putBoolean("lowerVib",lowerVib);
        editor.putBoolean("lowerSound",lowerSound);
        editor.commit();
    }
    public void loadValues(){
        upperLimit = sharedPreferences.getInt("upperLimit",10);
        lowerLimit = sharedPreferences.getInt("lowerLimit",0);
        currentValue = sharedPreferences.getInt("currentValue",0);
        upperVib = sharedPreferences.getBoolean("upperVib",true);
        upperSound = sharedPreferences.getBoolean("upperSound",true);
        lowerVib = sharedPreferences.getBoolean("lowerVib",true);
        lowerSound = sharedPreferences.getBoolean("lowerSound",true);
    }
}
