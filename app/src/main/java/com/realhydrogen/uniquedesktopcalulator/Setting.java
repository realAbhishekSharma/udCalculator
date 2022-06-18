package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    EditText maphlithoBox, RYGRateBox, whiteRateBox, bondRateBox,sripurRateBox;
    EditText singleCTP,single1stIMP, single2ndIMP;
    EditText fourCTP,four1stIMP, four2ndIMP;
    Button saveButton;

    final String DATABASE = "MyData";

    SharedPreferences databasePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        databasePref = getApplicationContext().getSharedPreferences(DATABASE, MODE_PRIVATE);

        maphlithoBox = (EditText) findViewById(R.id.maphlithoRate);
        RYGRateBox = (EditText) findViewById(R.id.RYGRate);
        whiteRateBox = (EditText) findViewById(R.id.whiteRate);
        bondRateBox = (EditText) findViewById(R.id.bondRate);
        sripurRateBox = (EditText) findViewById(R.id.sripurRate);

        singleCTP = (EditText) findViewById(R.id.singleCTPRate);
        single1stIMP = (EditText) findViewById(R.id.single1stRate);
        single2ndIMP = (EditText) findViewById(R.id.single2ndRate);

        fourCTP = (EditText) findViewById(R.id.fourCTPRate);
        four1stIMP = (EditText) findViewById(R.id.four1stRate);
        four2ndIMP = (EditText) findViewById(R.id.four2ndRate);
        saveButton = (Button) findViewById(R.id.saveSetting);

        setAllValue();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkEmpty()){
                    setSaveValue();
                    Toast.makeText(getApplicationContext(), "Data Updated.", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Field can't Be Empty", Toast.LENGTH_LONG).show();
                }
            }
        });

        saveButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                resetToDefault();
                setAllValue();
                Toast.makeText(getApplicationContext(), "Data Reset To Default.", Toast.LENGTH_LONG).show();
                return true;
            }
        });



    }

    @SuppressLint("SetTextI18n")
    private void setAllValue(){
        maphlithoBox.setText(databasePref.getFloat(getResources().getString(R.string.MAPHLITO), Float.parseFloat(getResources().getString(R.string.MAPHLITO_VALUE)))+"");
        RYGRateBox.setText(databasePref.getFloat(getResources().getString(R.string.RYG_PAPER), Float.parseFloat(getResources().getString(R.string.RYG_PAPER_VALUE)))+"");
        whiteRateBox.setText(databasePref.getFloat(getResources().getString(R.string.WHITE), Float.parseFloat(getResources().getString(R.string.WHITE_VALUE)))+"");
        bondRateBox.setText(databasePref.getFloat(getResources().getString(R.string.BOND), Float.parseFloat(getResources().getString(R.string.BOND_VALUE)))+"");
        sripurRateBox.setText(databasePref.getFloat(getResources().getString(R.string.SRIPUR), Float.parseFloat(getResources().getString(R.string.SRIPUR_VALUE)))+"");

        singleCTP.setText(databasePref.getInt(getResources().getString(R.string.SINGLE_CTP_RATE), Integer.parseInt(getResources().getString(R.string.SINGLE_CTP_RATE_VALUE)))+"");
        single1stIMP.setText(databasePref.getInt(getResources().getString(R.string.SINGLE_FIRST_IMP), Integer.parseInt(getResources().getString(R.string.SINGLE_FIRST_IMP_VALUE)))+"");
        single2ndIMP.setText(databasePref.getFloat(getResources().getString(R.string.SINGLE_SECOND_IMP), Float.parseFloat(getResources().getString(R.string.SINGLE_SECOND_IMP_VALUE)))+"");

        fourCTP.setText(databasePref.getInt(getResources().getString(R.string.FOUR_CTP_RATE), Integer.parseInt(getResources().getString(R.string.FOUR_CTP_RATE_VALUE)))+"");
        four1stIMP.setText(databasePref.getInt(getResources().getString(R.string.FOUR_FIRST_IMP), Integer.parseInt(getResources().getString(R.string.FOUR_FIRST_IMP_VALUE)))+"");
        four2ndIMP.setText(databasePref.getFloat(getResources().getString(R.string.FOUR_SECOND_IMP), Float.parseFloat(getResources().getString(R.string.FOUR_SECOND_IMP_VALUE)))+"");
    }


    private void setSaveValue(){

        databasePref.edit().putFloat(getResources().getString(R.string.MAPHLITO), Float.parseFloat(maphlithoBox.getText().toString())).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.RYG_PAPER), Float.parseFloat(RYGRateBox.getText().toString())).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.WHITE), Float.parseFloat(whiteRateBox.getText().toString())).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.BOND), Float.parseFloat(bondRateBox.getText().toString())).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.SRIPUR), Float.parseFloat(sripurRateBox.getText().toString())).apply();

        databasePref.edit().putInt(getResources().getString(R.string.SINGLE_CTP_RATE), Integer.parseInt(singleCTP.getText().toString())).apply();
        databasePref.edit().putInt(getResources().getString(R.string.SINGLE_FIRST_IMP), Integer.parseInt(single1stIMP.getText().toString())).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.SINGLE_SECOND_IMP), Float.parseFloat(single2ndIMP.getText().toString())).apply();

        databasePref.edit().putInt(getResources().getString(R.string.FOUR_CTP_RATE), Integer.parseInt(fourCTP.getText().toString())).apply();
        databasePref.edit().putInt(getResources().getString(R.string.FOUR_FIRST_IMP), Integer.parseInt(four1stIMP.getText().toString())).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.FOUR_SECOND_IMP), Float.parseFloat(four2ndIMP.getText().toString())).apply();
    }

    private boolean checkEmpty(){
        if(
        maphlithoBox.getText().toString().equals("") ||
        RYGRateBox.getText().toString().equals("") ||
        whiteRateBox.getText().toString().equals("") ||
        bondRateBox.getText().toString().equals("") ||
        sripurRateBox.getText().toString().equals("") ||
        singleCTP.getText().toString().equals("") ||
        single1stIMP.getText().toString().equals("") ||
        single2ndIMP.getText().toString().equals("") ||
        fourCTP.getText().toString().equals("") ||
        four1stIMP.getText().toString().equals("") ||
        four2ndIMP.getText().toString().equals("") ){
            return true;
        }else {
            return false;
        }
    }

    private void resetToDefault(){

        databasePref.edit().putFloat(getResources().getString(R.string.MAPHLITO), Float.parseFloat(getResources().getString(R.string.MAPHLITO_VALUE))).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.RYG_PAPER), Float.parseFloat(getResources().getString(R.string.RYG_PAPER_VALUE))).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.WHITE), Float.parseFloat(getResources().getString(R.string.WHITE_VALUE))).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.BOND), Float.parseFloat(getResources().getString(R.string.BOND_VALUE))).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.SRIPUR), Float.parseFloat(getResources().getString(R.string.SRIPUR_VALUE))).apply();

        databasePref.edit().putInt(getResources().getString(R.string.SINGLE_CTP_RATE), Integer.parseInt(getResources().getString(R.string.SINGLE_CTP_RATE_VALUE))).apply();
        databasePref.edit().putInt(getResources().getString(R.string.SINGLE_FIRST_IMP), Integer.parseInt(getResources().getString(R.string.SINGLE_FIRST_IMP_VALUE))).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.SINGLE_SECOND_IMP), Float.parseFloat(getResources().getString(R.string.SINGLE_SECOND_IMP_VALUE))).apply();

        databasePref.edit().putInt(getResources().getString(R.string.FOUR_CTP_RATE), Integer.parseInt(getResources().getString(R.string.FOUR_CTP_RATE_VALUE))).apply();
        databasePref.edit().putInt(getResources().getString(R.string.FOUR_FIRST_IMP), Integer.parseInt(getResources().getString(R.string.FOUR_FIRST_IMP_VALUE))).apply();
        databasePref.edit().putFloat(getResources().getString(R.string.FOUR_SECOND_IMP), Float.parseFloat(getResources().getString(R.string.FOUR_SECOND_IMP_VALUE))).apply();
    }

}