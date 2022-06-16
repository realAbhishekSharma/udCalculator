package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
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

public class SingleColorDoubleSide extends AppCompatActivity {

    ArrayList<String> SizeArray = new ArrayList<>();
    final String[] sizeList = new String[]{"Select","23 x 18","24 x 18","12 x 18","17 x 22","Others"};
    ArrayList<String> gsmArray = new ArrayList<>();
    String[] gsmList = new String[]{"Select","58","70","90","100","130","150", "200", "230", "250", "300"};
    private final float W_KG_VALUE  = 1550000F;
    private int FIRST_IMP_COST_1_COLOR;
    private float SECOND_IMP_1_COLOR;

    SharedPreferences databasePref;
    final String DATABASE = "MyData";

    Spinner sizeSpinner, gsmSpinner;
    Switch laminationSwitch, doubleLaminationSwitch;
    TextView ctpCost;
    EditText ratePerKGInput, splitSizeInput, quantityInput;
    Button reset;

    // Variable
    int ratePerKG, splitSize, quantity, gsm;
    float length=0F, breadth=0F,weightPerSheet, costPerSheet,paperCost,costPerPcs,customerCost;
    int totalSheet, impression, printingCost, totalCost,ctpCostPrice,lamination;
    float lengthTemp = 0F,breadthTemp = 0F;

    TextView weightPerSheetView,costPerSheetView,paperCostView,costPerPcsView;
    TextView totalSheetView,impressionView, printingCostView, totalCostView,customerCostView,laminationView;
    //Size array initializing
    ArrayAdapter<String> sizeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_color_double_side);

        databasePref = getApplicationContext().getSharedPreferences(DATABASE, MODE_PRIVATE);
        FIRST_IMP_COST_1_COLOR = databasePref.getInt(getResources().getString(R.string.SINGLE_FIRST_IMP), Integer.parseInt(getResources().getString(R.string.SINGLE_FIRST_IMP_VALUE)));
        SECOND_IMP_1_COLOR = databasePref.getFloat(getResources().getString(R.string.SINGLE_SECOND_IMP), Float.parseFloat(getResources().getString(R.string.SINGLE_SECOND_IMP_VALUE)));
        getSupportActionBar().setTitle("Single Color Double Side ("+SECOND_IMP_1_COLOR+") ("+FIRST_IMP_COST_1_COLOR+")" );

        //Formula TextView Casting
        weightPerSheetView = (TextView) findViewById(R.id.weightPerSheet);
        costPerSheetView = (TextView) findViewById(R.id.costPerSheet);
        paperCostView = (TextView) findViewById(R.id.papercost);
        costPerPcsView = (TextView) findViewById(R.id.costPerPcs);
        totalSheetView = (TextView) findViewById(R.id.totalSheet);
        impressionView = (TextView) findViewById(R.id.impression);
        printingCostView = (TextView) findViewById(R.id.printingCost);
        totalCostView = (TextView) findViewById(R.id.totalCost);
        customerCostView = (TextView) findViewById(R.id.customerCost);
        laminationView = (TextView) findViewById(R.id.laminationCost);

        //Other Casting
        reset = (Button) findViewById(R.id.reset);
        laminationSwitch = (Switch) findViewById(R.id.laminationSwitch);
        doubleLaminationSwitch = (Switch) findViewById(R.id.doubleLaminationSwitch);
        ctpCost = (TextView) findViewById(R.id.ctpCost);
        ctpCost.setText(databasePref.getInt(getResources().getString(R.string.SINGLE_CTP_RATE), Integer.parseInt(getResources().getString(R.string.SINGLE_CTP_RATE_VALUE)))+"");
        ratePerKGInput = (EditText) findViewById(R.id.ratePerKG);
        splitSizeInput = (EditText) findViewById(R.id.splitSize);
        quantityInput = (EditText) findViewById(R.id.quantity);

        //Spinner Casting
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        gsmSpinner = (Spinner) findViewById(R.id.gsmSpinner);



        //Size array initializing
        SizeArray.addAll(Arrays.asList(sizeList));
        sizeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, SizeArray);
        sizeSpinner.setAdapter(sizeAdapter);


        //GSM Array initializing
        gsmArray.addAll(Arrays.asList(gsmList));
        ArrayAdapter<String> gsmAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, gsmArray);
        gsmSpinner.setAdapter(gsmAdapter);

        //Size Spinner Selection
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    length = 0;
                    breadth = 0;
                    cleanCalculation();

                }else if (position == 1){
                    length = 23;
                    breadth = 18;

                }else if (position == 2){
                    length = 24;
                    breadth = 18;

                }else if (position == 3){
                    length = 12;
                    breadth = 18;

                }else if (position == 4){
                    length = 17;
                    breadth = 22;

                }else if (position == 5 ){
                    openDialogBox();
                }
                if (position > 0 && position <= 4){
                    virtualDone();
                    SizeArray.set(5, "Others");
                    if (SizeArray.size() ==7) {
                        SizeArray.remove(6);
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //GSM Spinner Selection
        gsmSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    gsm = 0;
                    cleanCalculation();
                }else {
                    gsm = Integer.parseInt(gsmSpinner.getSelectedItem().toString());
                    virtualDone();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        //Calculating Value when rate per kg value change
        ratePerKGInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    virtualDone();
                }else {
                    ratePerKGInput.setHint("?");
                    cleanCalculation();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Lamination Switch Change Listener
        laminationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                virtualDone();
            }
        });
        // Double Lamination Switch change listener
        doubleLaminationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                virtualDone();
            }
        });

    }


    // All Method Creation------------------------------------------------------------------

    // Opening DialogBox And setting Value of Paper Size
    private void openDialogBox(){
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(SingleColorDoubleSide.this);
        View view = getLayoutInflater().inflate(R.layout.dialogbox_layout, null);

        final EditText lengthInput,breadthInput;
        Button okay,cancel;
        lengthInput = (EditText) view.findViewById(R.id.lengthIn);
        breadthInput = (EditText) view.findViewById(R.id.breadthIn);
        okay = (Button) view.findViewById(R.id.okay);
        cancel = (Button) view.findViewById(R.id.cancel);

        dialogBox.setView(view);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        if (lengthTemp != 0 || breadthTemp != 0){
            lengthInput.setText(lengthTemp+"");
            breadthInput.setText(breadthTemp+"");
        }

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((lengthInput.getText().toString().equals("") || breadthInput.getText().toString().equals(""))){
                    Toast.makeText(SingleColorDoubleSide.this, "Paper Size can't be empty", Toast.LENGTH_SHORT).show();

                }else {
                    length = Float.parseFloat(lengthInput.getText().toString());
                    breadth = Float.parseFloat(breadthInput.getText().toString());
                    lengthTemp = length;
                    breadthTemp = breadth;
                    alertDialog.dismiss();

                    //For Setting Name And Calculating Value
                    if (SizeArray.size() == 6){
                        SizeArray.add( "----Others "+Float.toString(length)+" X "+Float.toString(breadth));

                    }else if (SizeArray.size() == 7){
                        SizeArray.remove(6);
                        SizeArray.add( "----Others "+Float.toString(length)+" X "+Float.toString(breadth));
                    }

                    sizeSpinner.setAdapter(sizeAdapter);
                    sizeSpinner.setSelection(6);
                    virtualDone();
                    //


                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                SizeArray.set(5, "Others");
                sizeSpinner.setSelection(0);


            }
        });

    }

    // Virtual Done
    private void virtualDone(){

        if (ratePerKGInput.getText().toString().equals("") || splitSizeInput.getText().toString().equals("") || quantityInput.getText().toString().equals("")){

            if (splitSizeInput.getText().toString().equals("")){
                splitSizeInput.setHint("?");

            }
            if (ratePerKGInput.getText().toString().equals("")){
                ratePerKGInput.setHint("?");

            }
            if (quantityInput.getText().toString().equals("")){
                quantityInput.setHint("?");

            }


        }else {

            ratePerKG = Integer.parseInt(ratePerKGInput.getText().toString());
            splitSize = Integer.parseInt(splitSizeInput.getText().toString());
            ctpCostPrice = Integer.parseInt(ctpCost.getText().toString());
            quantity = Integer.parseInt(quantityInput.getText().toString());

            weightPerSheet = (length * breadth * gsm) / W_KG_VALUE;
            costPerSheet = weightPerSheet * ratePerKG;
            totalSheet = (int) Math.ceil((float) quantity / (float) splitSize);
            impression = 2 * totalSheet;
            paperCost = costPerSheet * totalSheet;

            if (impression - 1000 < 0) {
                printingCost = FIRST_IMP_COST_1_COLOR;
            } else {
                printingCost = FIRST_IMP_COST_1_COLOR + (int) Math.ceil((float)(impression - 1000)*SECOND_IMP_1_COLOR);

            }

            lamination = (int) (0.01F * length * breadth * totalSheet);
            totalCost = (int) Math.ceil(paperCost) + printingCost + ctpCostPrice;
            laminationView.setText("");
            if (doubleLaminationSwitch.isChecked()) {
                lamination *= 2;
            }

            if (laminationSwitch.isChecked()) {
                totalCost += lamination;
                laminationView.setText(lamination + "");
            }

            costPerPcs = (float) totalCost / (float) quantity;
            customerCost = (costPerPcs * 1.5F);


            weightPerSheetView.setText(weightPerSheet + "");
            costPerSheetView.setText(costPerSheet + "");
            paperCostView.setText(paperCost + "");
            costPerPcsView.setText(costPerPcs + "");
            totalSheetView.setText(totalSheet + "");
            impressionView.setText(impression + "");
            printingCostView.setText(printingCost + "");
            totalCostView.setText(totalCost + "");
            customerCostView.setText(customerCost + "");
        }

    }

    // Reset All Method
    private void resetAll(){
        splitSizeInput.setText("");
        ratePerKGInput.setText("");
        quantityInput.setText("");
        weightPerSheetView.setText("");
        costPerSheetView.setText("");
        paperCostView.setText("");
        costPerPcsView.setText("");
        totalSheetView.setText("");
        impressionView.setText("");
        printingCostView.setText("");
        totalCostView.setText("");
        customerCostView.setText("");
        laminationView.setText("");
        SizeArray.clear();
        SizeArray.addAll(Arrays.asList(sizeList));
        sizeSpinner.setSelection(0);
        gsmSpinner.setSelection(0);
        lengthTemp = 0;
        breadthTemp=0;
    }
    //Clean Calculation
    private void cleanCalculation(){
        weightPerSheetView.setText("");
        costPerSheetView.setText("");
        paperCostView.setText("");
        costPerPcsView.setText("");
        totalSheetView.setText("");
        impressionView.setText("");
        printingCostView.setText("");
        totalCostView.setText("");
        customerCostView.setText("");
        laminationView.setText("");

    }
}