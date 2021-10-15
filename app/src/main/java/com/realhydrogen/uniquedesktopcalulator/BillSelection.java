package com.realhydrogen.uniquedesktopcalulator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BillSelection extends AppCompatActivity {

    Button normalPaper,maplithoPaper,bondPaper,sripurPaper;
    private static int billValue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_selection);

        normalPaper = (Button) findViewById(R.id.normalPaper);
        maplithoPaper = (Button) findViewById(R.id.maplithoPaper);
        bondPaper = (Button) findViewById(R.id.bondpaper);
        sripurPaper = (Button) findViewById(R.id.sripurPaper);

        normalPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billValue = 1;
                openSingleSide4C();
            }
        });

        maplithoPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billValue = 2;
                openSingleSide4C();
            }
        });

        bondPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billValue = 3;
                openSingleSide4C();
            }
        });

        sripurPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billValue = 4;
                openSingleSide4C();
            }
        });

    }

    public void openSingleSide4C() {

        Intent intent = new Intent(this, BillBookMiniOffset.class);
        startActivity(intent);
    }
    public static int billCheck(){
        return billValue;

    }
}