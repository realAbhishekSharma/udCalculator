package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrontList extends AppCompatActivity {


    private static int colorCheck; // 1 for Four Color and 0 for Single Color // 2 for Sticker
    Button fourColor, singleColor, billPrint,stickerPrint, paperCostCalculator, setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_list);


        fourColor = (Button) findViewById(R.id.fourColor);
        singleColor = (Button) findViewById(R.id.singleColor);
        billPrint = (Button) findViewById(R.id.billPrint);
        stickerPrint = (Button) findViewById(R.id.stickerPrint);
        paperCostCalculator = (Button) findViewById(R.id.paperCostCalculator);
        setting = (Button) findViewById(R.id.setting);

        fourColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck = 1;
                openActivity(1);
            }
        });

        singleColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck = 0;
                openActivity(2);
            }
        });

        billPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(4);
            }
        });

        stickerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck= 2;
                openActivity(3);
            }
        });

        paperCostCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(5);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(6);
            }
        });

    }

    public void openActivity(int i) {

        if (i == 1 || i==2 || i == 3) {
            Intent intent = new Intent(this, SideSelection.class);
            startActivity(intent);
        }else if (i == 4){
            Intent intent = new Intent(this, BillSelection.class);
            startActivity(intent);
        }else if (i == 5){
            Intent intent = new Intent(this, PaperCost.class);
            startActivity(intent);
        }else if (i == 6){
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        }
    }

    public static int frontCheck(){
        return colorCheck;
    }



}