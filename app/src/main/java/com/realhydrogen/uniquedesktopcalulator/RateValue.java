package com.realhydrogen.uniquedesktopcalulator;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class RateValue extends AppCompatActivity {
    final String MAPLITHO = "maplitho";
    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("PaperRate",MODE_PRIVATE);

    public float getMaphlithoRate(){
        return sharedPreferences.getFloat(MAPLITHO, 5.5f);
    }

    public void setMaphlithoRate(float value){
        sharedPreferences.edit().putFloat(MAPLITHO, value).apply();
    }

}