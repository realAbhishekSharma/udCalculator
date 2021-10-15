package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class FrontList extends AppCompatActivity {


    private static int colorCheck; // 1 for Four Color and 0 for Single Color // 2 for Sticker
    Button fourColor, singleColor, billPrint,stickerPrint, paperCostCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_list);

        fourColor = (Button) findViewById(R.id.fourColor);
        singleColor = (Button) findViewById(R.id.singleColor);
        billPrint = (Button) findViewById(R.id.billPrint);
        stickerPrint = (Button) findViewById(R.id.stickerPrint);
        paperCostCalculator = (Button) findViewById(R.id.paperCostCalculator);

        fourColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck = 1;
                openSingleSide4C(1);
            }
        });

        singleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck = 0;
                openSingleSide4C(2);
            }
        });

        billPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSingleSide4C(4);
            }
        });

        paperCostCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSingleSide4C(5);
            }
        });

        stickerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck= 2;
                openSingleSide4C(3);
            }
        });

    }

    public void openSingleSide4C(int i) {

        if (i == 1 || i==2 || i == 3) {
            Intent intent = new Intent(this, SideSelection.class);
            startActivity(intent);
        }else if (i == 4){
            Intent intent = new Intent(this, BillSelection.class);
            startActivity(intent);
        }else if (i == 5){
            Intent intent = new Intent(this, PaperCost.class);
            startActivity(intent);
        }
    }

    public static int frontCheck(){
        return colorCheck;
    }



}