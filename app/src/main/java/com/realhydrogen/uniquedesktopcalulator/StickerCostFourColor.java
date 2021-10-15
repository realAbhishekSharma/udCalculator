package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class StickerCostFourColor extends AppCompatActivity {

    private final int NORMAL_PRINTING_COST_4_COLOR  = 2000;

    Switch laminationSwitch;
    TextView sizeText;
    EditText splitSizeInput, stickerRateInput, quantityInput;
    Button reset;

    // Variable
    int length=15, breadth=20,splitSize,stickerRate, quantity;
    float stickerCost,costPerPcs,customerCost;
    int impression,totalSticker, ctpPlate,printingCost,totalCost,laminationCost;

    TextView totalStickerView,impressionView,stickerCostView,ctpCostView,printingCostView;
    TextView totalCostView,costPerPcsView,customerCostView,laminationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_cost_four_color);
        getSupportActionBar().setTitle("Sticker Cost Four Color");

        //Formula TextView Casting
        sizeText=(TextView) findViewById(R.id.sizeTextSCF);
        sizeText.setText("15 x 20");
        totalStickerView=(TextView) findViewById(R.id.totalStickerSCF);
        impressionView = (TextView) findViewById(R.id.impressionSCF);
        stickerCostView = (TextView) findViewById(R.id.stickerCostSCF);
        ctpCostView = (TextView) findViewById(R.id.ctpCostSCF);
        printingCostView = (TextView) findViewById(R.id.printingCostSCF);
        totalCostView = (TextView) findViewById(R.id.totalCostSCF);
        costPerPcsView = (TextView) findViewById(R.id.costPerPcsSCF);
        customerCostView = (TextView) findViewById(R.id.customerCostSCF);
        laminationView = (TextView) findViewById(R.id.laminationCostSCF);

        //Other Casting
        reset = (Button) findViewById(R.id.resetSCF);
        laminationSwitch = (Switch) findViewById(R.id.laminationSwitchSCF);
        splitSizeInput = (EditText) findViewById(R.id.splitSizeSCF);
        stickerRateInput = (EditText) findViewById(R.id.stickerRateSCF);
        quantityInput = (EditText) findViewById(R.id.quantitySCF);


        //Reset All Formula TextView
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAll();
            }
        });

        //Change Listener of Quantity
        quantityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() != 0) {
                    virtualDone();
                }else {
                    quantityInput.setHint("Input Quantity.");
                    cleanCalculation();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Calculating Value when split value change
        splitSizeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() != 0) {
                    virtualDone();
                }else {
                    splitSizeInput.setHint("?");
                    cleanCalculation();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Calculating Value when Sticker Rate value change
        stickerRateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() != 0) {
                    virtualDone();
                }else {
                    stickerRateInput.setHint("?");
                    cleanCalculation();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Lamination Switch Change Listener
        laminationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                virtualDone();
            }
        });
    }


    // All Method Creation------------------------------------------------------------------

    // Virtual Done
    private void virtualDone(){

        if (splitSizeInput.getText().toString().equals("") || stickerRateInput.getText().toString().equals("") || quantityInput.getText().toString().equals("")){

            if (splitSizeInput.getText().toString().equals("")){
                splitSizeInput.setHint("?");

            }
            if (stickerRateInput.getText().toString().equals("")){
                stickerRateInput.setHint("?");

            }
            if (quantityInput.getText().toString().equals("")){
                quantityInput.setHint("?");

            }


        }else {

            splitSize = Integer.parseInt(splitSizeInput.getText().toString());
            stickerRate = Integer.parseInt(stickerRateInput.getText().toString());
            quantity = Integer.parseInt(quantityInput.getText().toString());
            ctpPlate = Integer.parseInt(ctpCostView.getText().toString());

            impression = totalSticker= (int) Math.floor(quantity/splitSize);
            stickerCost = impression*stickerRate;

            if (impression - 1000 < 0) {
                printingCost = NORMAL_PRINTING_COST_4_COLOR;
            } else {
                printingCost = NORMAL_PRINTING_COST_4_COLOR + (impression - 1000);
            }
            laminationCost = (int) Math.floor(3*impression);
            totalCost = (int) Math.ceil(stickerCost) + printingCost + ctpPlate + laminationCost;
            laminationView.setText("");

            if (laminationSwitch.isChecked()) {
                totalCost += laminationCost;
                laminationView.setText(laminationCost + "");
            }

            costPerPcs = (float) totalCost / (float) quantity;
            customerCost = (costPerPcs * 1.5F);


            totalStickerView.setText(totalSticker + "");
            impressionView.setText(impression + "");
            stickerCostView.setText(stickerCost + "");
            printingCostView.setText(printingCost + "");
            totalCostView.setText(totalCost + "");
            costPerPcsView.setText(costPerPcs + "");
            customerCostView.setText(customerCost + "");
        }

    }

    // Reset All Method
    private void resetAll(){
        splitSizeInput.setText("");
        stickerRateInput.setText("");
        quantityInput.setText("");
        totalCostView.setText("");
        impressionView.setText("");
        stickerCostView.setText("");
        printingCostView.setText("");
        totalCostView.setText("");
        costPerPcsView.setText("");
        customerCostView.setText("");
        laminationView.setText("");
    }
    //Clean Calculation
    private void cleanCalculation(){

        totalStickerView.setText("");
        impressionView.setText("");
        stickerCostView.setText("");
        printingCostView.setText("");
        totalCostView.setText("");
        costPerPcsView.setText("");
        customerCostView.setText("");
        laminationView.setText("");

    }
}