package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SideSelection extends AppCompatActivity {
    Button singleSide,doubleSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_selection);

        singleSide = (Button) findViewById(R.id.singleSide);
        doubleSide = (Button) findViewById(R.id.doubleSide);

        // 1 for Four Color and 0 for Single Color // 2 for Sticker
        if (FrontList.frontCheck() == 2){
            singleSide.setText("Single Color");
            doubleSide.setText("Four Color");
        }else {
            singleSide.setText("Single Side");
            doubleSide.setText("Double Side");
        }

        singleSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FrontList.frontCheck() == 1) {
                    openActivity(1);
                }else if (FrontList.frontCheck() == 0){
                    openActivity(3);
                }else if (FrontList.frontCheck() == 2){
                    openActivity(5);
                }
            }
        });

        doubleSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FrontList.frontCheck() == 1) {
                    openActivity(2);
                }else if (FrontList.frontCheck() == 0){
                    openActivity(4);
                }else if (FrontList.frontCheck() == 2){
                    openActivity(6);
                }
            }
        });


    }

    public void openActivity(int i) {

        if (i == 1) {
            Intent intent = new Intent(this, FourColorSingleSideActivity.class);
            startActivity(intent);
        }else if (i == 2){
            Intent intent = new Intent(this, FourColorDoubleSide.class);
            startActivity(intent);
        }else if (i == 3){
            Intent intent = new Intent(this, SingleColorSingleSide.class);
            startActivity(intent);
        }else if (i == 4){
            Intent intent = new Intent(this, SingleColorDoubleSide.class);
            startActivity(intent);
        }else if (i == 5){
            Intent intent = new Intent(this, StickerCostSingleColor.class);
            startActivity(intent);
        }else if (i == 6){
            Intent intent = new Intent(this, StickerCostFourColor.class);
            startActivity(intent);
        }
    }
}