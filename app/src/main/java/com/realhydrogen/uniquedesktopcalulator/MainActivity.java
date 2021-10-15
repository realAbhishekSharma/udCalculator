package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                openSingleSide4C();

            }
        },1000);
    }

    public void openSingleSide4C() {
        Intent intent = new Intent(this, FrontList.class);
        startActivity(intent);
        finish();
    }

}