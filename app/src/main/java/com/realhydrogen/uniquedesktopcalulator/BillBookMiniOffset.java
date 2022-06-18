package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

public class BillBookMiniOffset extends AppCompatActivity {

    float MAPLITHO_RATE;
    float RYG_RATE;
    float WHITE;
    float BOND;
    float SRIPUR;
    float PAPER_RATE = 0;

    SharedPreferences databasePref;
    final String DATABASE = "MyData";
/*
    ArrayList<String> billPaperArray = new ArrayList<>();
    final String[] billPaperList = new String[]{"25","50","100"};

    ArrayList<String> printSizeArray = new ArrayList<>();
    final String[] printSizeList = new String[]{"3","4","5", "6"};

    ArrayList<String> copiesArray = new ArrayList<>();
    final String[] copiesList = new String[]{"2","3","4"};

    ArrayList<String> paperTypeArray = new ArrayList<>();
    final String[] paperTypeList = new String[]{"Maplitho","Red","Yellow","Green", "White", "Bond"};*/

    Spinner billPaperSpinner, printSizeSpinner, copiesSpinner, paperTypeSpinnerA, paperTypeSpinnerB, paperTypeSpinnerC, paperTypeSpinnerD;
    TextView oneColorSheetView, bindingCostOut, totalSheetView, impressionView, paperCostView, printingCostView, totalCostView, costPerBookView, customerCostView;
    EditText billQuantityIn, billSizeIn, bindingCostIn,printSizeIn,billPaperIn, copiesIn;
    Switch impressionSwitch;
    Button reset;

    // All Variable
    int billQuantity =0, billSize=0, bindingCost=0;
    int billPaper=0, printSize=0,copies=0;/*
    float typeA =0F, typeB=0F, typeC=0F, typeD=0F;*/
    int onePaperSheet=0, totalSheet=0, totalBindingCost=0,impression=0,paperCost=0, printingCost=0,totalCost=0;
    float costPerBook=0,customerCost=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_book_mini_offset);

        databasePref = getApplicationContext().getSharedPreferences(DATABASE, MODE_PRIVATE);
        MAPLITHO_RATE = databasePref.getFloat(getResources().getString(R.string.MAPHLITO), Float.parseFloat(getResources().getString(R.string.MAPHLITO_VALUE)));
        RYG_RATE = databasePref.getFloat(getResources().getString(R.string.RYG_PAPER), Float.parseFloat(getResources().getString(R.string.RYG_PAPER_VALUE)));
        WHITE = databasePref.getFloat(getResources().getString(R.string.WHITE), Float.parseFloat(getResources().getString(R.string.WHITE_VALUE)));
        BOND = databasePref.getFloat(getResources().getString(R.string.BOND), Float.parseFloat(getResources().getString(R.string.BOND_VALUE)));
        SRIPUR = databasePref.getFloat(getResources().getString(R.string.SRIPUR), Float.parseFloat(getResources().getString(R.string.SRIPUR_VALUE)));


        if (BillSelection.billCheck() == 1){
            getSupportActionBar().setTitle("Bill Normal Paper ("+RYG_RATE+")");
            PAPER_RATE = RYG_RATE;
        }else if (BillSelection.billCheck() == 2){
            getSupportActionBar().setTitle("Bill Maplitho Paper ("+MAPLITHO_RATE+")");
            PAPER_RATE = MAPLITHO_RATE;
        }else if (BillSelection.billCheck() == 3){
            getSupportActionBar().setTitle("Bill Bond Paper ("+BOND+")");
            PAPER_RATE = BOND;
        }else if (BillSelection.billCheck() == 4){
            getSupportActionBar().setTitle("Bill Sripur Paper ("+SRIPUR+")");
            PAPER_RATE = SRIPUR;
        }

        // TextView Casting
        oneColorSheetView = (TextView) findViewById(R.id.oneColorSheet);
        bindingCostOut = (TextView) findViewById(R.id.bindingCostOut);
        totalSheetView = (TextView) findViewById(R.id.totalSheet);
        impressionView = (TextView) findViewById(R.id.impression);
        paperCostView = (TextView) findViewById(R.id.paperCost1);
        printingCostView = (TextView) findViewById(R.id.printingCost);
        totalCostView = (TextView) findViewById(R.id.totalCost);
        costPerBookView = (TextView) findViewById(R.id.costPerBook);
        customerCostView = (TextView) findViewById(R.id.customerCost);

        //Spinner Casting
        /*billPaperSpinner = (Spinner) findViewById(R.id.billPapperSpinner);
        printSizeSpinner = (Spinner) findViewById(R.id.printSizeSpinner);
        copiesSpinner = (Spinner) findViewById(R.id.copiesSpinner);
        paperTypeSpinnerA = (Spinner) findViewById(R.id.typeSpinnerA);
        paperTypeSpinnerB = (Spinner) findViewById(R.id.typeSpinnerB);
        paperTypeSpinnerC = (Spinner) findViewById(R.id.typeSpinnerC);
        paperTypeSpinnerD = (Spinner) findViewById(R.id.typeSpinnerD);*/

        //EditText Casting
        billQuantityIn = (EditText) findViewById(R.id.billQuantityIn);
        billSizeIn= (EditText) findViewById(R.id.billSizeIn);
        bindingCostIn = (EditText) findViewById(R.id.bindingCostIn);

        printSizeIn = (EditText) findViewById(R.id.printSizeIn);
        billPaperIn = (EditText) findViewById(R.id.billPaperIn);
        copiesIn = (EditText) findViewById(R.id.copiesIn);

        //Button Casting
        reset = (Button) findViewById(R.id.reset);
        impressionSwitch = (Switch) findViewById(R.id.impressionSwitch);
/*

        //Bill Paper initializing
        billPaperArray.addAll(Arrays.asList(billPaperList));
        final ArrayAdapter<String> billPaperAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, billPaperArray);
        billPaperSpinner.setAdapter(billPaperAdapter);

        //Print Size initializing
        printSizeArray.addAll(Arrays.asList(printSizeList));
        final ArrayAdapter<String> printSizeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, printSizeArray);
        printSizeSpinner.setAdapter(printSizeAdapter);

        //Copies Spinner initializing
        copiesArray.addAll(Arrays.asList(copiesList));
        final ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, copiesArray);
        copiesSpinner.setAdapter(sizeAdapter);

        //Paper Type Initializing
        paperTypeArray.addAll(Arrays.asList(paperTypeList));
        final ArrayAdapter<String> paperTypeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, paperTypeArray);
        billPaperSpinner.setAdapter(paperTypeAdapter);
        paperTypeSpinnerA.setAdapter(paperTypeAdapter);
        paperTypeSpinnerB.setAdapter(paperTypeAdapter);
        paperTypeSpinnerC.setAdapter(paperTypeAdapter);
        paperTypeSpinnerD.setAdapter(paperTypeAdapter);
*/

        //Change Listener of Bill Size
        billQuantityIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    resetCalculation();

                }else{
                    if (chekValue()){
                        virtualDone();
                    }


                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        billSizeIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    resetCalculation();

                }else{
                    if (chekValue()){
                        virtualDone();
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        billPaperIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    resetCalculation();

                }else{
                    if (chekValue()){
                        virtualDone();
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        printSizeIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    resetCalculation();

                }else{
                    if (chekValue()){
                        virtualDone();
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        copiesIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    resetCalculation();

                }else{
                    if (chekValue()){
                        virtualDone();
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
/*

        // Bill Paper Spinner Selection
        billPaperSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (billQuantityIn.getText().toString().equals("") && billPaperSpinner.getSelectedItemPosition() != 0 ){
                    Toast.makeText(BillBookMiniOffset.this, "Fill Bill Quantity First.", Toast.LENGTH_SHORT).show();
                }
                if (billSizeIn.getText().toString().equals("") && billPaperSpinner.getSelectedItemPosition() != 0){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size First.", Toast.LENGTH_SHORT).show();
                }else if (billQuantityIn.getText().length() > 0 && billSizeIn.getText().length() > 0){
                    if (position == 0 || position == 1 || position == 2) {
                        billPaper = Integer.parseInt(billPaperSpinner.getSelectedItem().toString());
                        virtualDone();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Print Size Spinner Selection
        printSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (billQuantityIn.getText().toString().equals("") && printSizeSpinner.getSelectedItemPosition() !=0){
                    Toast.makeText(BillBookMiniOffset.this, "Fill Bill Quantity First.", Toast.LENGTH_SHORT).show();
                }
                if (billSizeIn.getText().toString().equals("") && printSizeSpinner.getSelectedItemPosition() !=0){
                    Toast.makeText(BillBookMiniOffset.this, "Fill Bill Size First.", Toast.LENGTH_SHORT).show();
                }else if (billQuantityIn.getText().length() > 0 && billSizeIn.getText().length() > 0) {
                    if (i == 0 || i == 1 || i == 2 || i == 3) {
                        printSize = Integer.parseInt(printSizeSpinner.getSelectedItem().toString());
                        virtualDone();
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Copies Spinner Selection
        copiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (billQuantityIn.getText().toString().equals("") && billPaperSpinner.getSelectedItemPosition() != 0 ){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Quantity.", Toast.LENGTH_SHORT).show();
                }else if (billSizeIn.getText().toString().equals("") && billPaperSpinner.getSelectedItemPosition() != 0){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size.", Toast.LENGTH_SHORT).show();
                }else {
                    if (i == 0 || i == 1 || i == 2) {
                        copies = Integer.parseInt(copiesSpinner.getSelectedItem().toString());
                    }
                }
                if (i == 0){
                    paperTypeSpinnerD.setVisibility(View.VISIBLE);
                    paperTypeSpinnerC.setVisibility(View.VISIBLE);
                    paperTypeSpinnerB.setVisibility(View.INVISIBLE);
                    paperTypeSpinnerA.setVisibility(View.INVISIBLE);
                }else if (i == 1){
                    paperTypeSpinnerD.setVisibility(View.VISIBLE);
                    paperTypeSpinnerC.setVisibility(View.VISIBLE);
                    paperTypeSpinnerB.setVisibility(View.VISIBLE);
                    paperTypeSpinnerA.setVisibility(View.INVISIBLE);
                }else if (i == 2){
                    paperTypeSpinnerD.setVisibility(View.VISIBLE);
                    paperTypeSpinnerC.setVisibility(View.VISIBLE);
                    paperTypeSpinnerB.setVisibility(View.VISIBLE);
                    paperTypeSpinnerA.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Type Spinner A Selection
        paperTypeSpinnerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (billQuantityIn.getText().toString().equals("") && paperTypeSpinnerA.getSelectedItemPosition() != 0 ){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Quantity.", Toast.LENGTH_SHORT).show();
                }
                if (billSizeIn.getText().toString().equals("") && paperTypeSpinnerA.getSelectedItemPosition() != 0){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size.", Toast.LENGTH_SHORT).show();
                }else {
                    if (i == 0){
                        typeA = MAPLITHO_RATE;
                    }else if (i == 1 || i ==2 || i == 3){
                        typeA = RYG_RATE;
                    }else if (i == 4){
                        typeA = WHITE;
                    }else if (i == 5){
                        typeA = BOND;
                    }
                    virtualDone();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Type Spinner A Selection
        paperTypeSpinnerB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (billQuantityIn.getText().toString().equals("") && paperTypeSpinnerB.getSelectedItemPosition() != 0 ){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Quantity.", Toast.LENGTH_SHORT).show();
                }
                if (billSizeIn.getText().toString().equals("") && paperTypeSpinnerB.getSelectedItemPosition() != 0){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size.", Toast.LENGTH_SHORT).show();
                }else{
                    if (i == 0){
                        typeB = MAPLITHO_RATE;
                    }else if (i == 1 || i ==2 || i == 3){
                        typeB = RYG_RATE;
                    }else if (i == 4){
                        typeB = WHITE;
                    }else if (i == 5){
                        typeB = BOND;
                    }
                    virtualDone();
                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Type Spinner A Selection
        paperTypeSpinnerC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if (billQuantityIn.getText().toString().equals("") && paperTypeSpinnerC.getSelectedItemPosition() != 0 ){
                            Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Quantity.", Toast.LENGTH_SHORT).show();
                }
                if (billSizeIn.getText().toString().equals("") && paperTypeSpinnerC.getSelectedItemPosition() != 0){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size.", Toast.LENGTH_SHORT).show();
                }else {
                    if (i == 0){
                        typeC = MAPLITHO_RATE;
                    }else if (i == 1 || i ==2 || i == 3){
                        typeC = RYG_RATE;
                    }else if (i == 4){
                        typeC = WHITE;
                    }else if (i == 5){
                        typeC = BOND;
                    }
                    virtualDone();
                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Type Spinner A Selection
        paperTypeSpinnerD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (billQuantityIn.getText().toString().equals("") && paperTypeSpinnerD.getSelectedItemPosition() != 0 ){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Quantity.", Toast.LENGTH_SHORT).show();
                }
                if (billSizeIn.getText().toString().equals("") && paperTypeSpinnerD.getSelectedItemPosition() != 0){
                    Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size.", Toast.LENGTH_SHORT).show();
                }else {
                    if (i == 0){
                        typeD = MAPLITHO_RATE;
                    }else if (i == 1 || i ==2 || i == 3){
                        typeD = RYG_RATE;
                    }else if (i == 4){
                        typeD = WHITE;
                    }else if (i == 5){
                        typeD = BOND;
                    }
                    virtualDone();
                }


            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/

        // Change Listener of Binding Cost
        bindingCostIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    resetCalculation();

                }else{
                    if (chekValue()){
                        virtualDone();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Impression Switch
        impressionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (billQuantityIn.getText().length() > 0 && billSizeIn.getText().length() > 0){
                    virtualDone();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAll();
            }
        });

    }



    private void resetAll() {
        billQuantityIn.setText("");
        billSizeIn.setText("");
        billPaperIn.setText("");
        printSizeIn.setText("");
        copiesIn.setText("");



        /*billPaperSpinner.setSelection(0);
        printSizeSpinner.setSelection(0);
        copiesSpinner.setSelection(0);
        paperTypeSpinnerA.setSelection(0);
        paperTypeSpinnerB.setSelection(0);
        paperTypeSpinnerC.setSelection(0);
        paperTypeSpinnerD.setSelection(0);*/
        bindingCostIn.setText("");
        resetCalculation();
    }

    private void resetCalculation() {
        oneColorSheetView.setText("");
        bindingCostOut.setText("");
        totalSheetView.setText("");
        impressionView.setText("");
        paperCostView.setText("");
        printingCostView.setText("");
        totalCostView.setText("");
        costPerBookView.setText("");
        customerCostView.setText("");

    }
    private void readValue(){
        billQuantity = Integer.parseInt(billQuantityIn.getText().toString());
        billSize = Integer.parseInt(billSizeIn.getText().toString());
        billPaper = Integer.parseInt(billPaperIn.getText().toString());
        printSize = Integer.parseInt(printSizeIn.getText().toString());
        copies = Integer.parseInt(copiesIn.getText().toString());
        bindingCost = Integer.parseInt(bindingCostIn.getText().toString());
    }
    private boolean chekValue(){
        if( billQuantityIn.getText().toString().equals("")
                || billSizeIn.getText().toString().equals("")
                || billPaperIn.getText().toString().equals("")
                || printSizeIn.getText().toString().equals("")
                || copiesIn.getText().toString().equals("")
                || bindingCostIn.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    private void virtualDone() {
        readValue();
        /*if( billQuantityIn.getText().toString().equals("")
                || billSizeIn.getText().toString().equals("")
                || billPaperIn.getText().toString().equals("")
                || printSizeIn.getText().toString().equals("")
                || copiesIn.getText().toString().equals("")
                || bindingCostIn.getText().toString().equals("")){
            return;
        }*/

        if (bindingCostIn.getText().toString().equals("") || billSizeIn.getText().toString().equals("") || bindingCostIn.getText().toString().equals("")) {
            if (billQuantityIn.getText().toString().equals("")) {
                Toast.makeText(BillBookMiniOffset.this, "Fill Bill Quantity First done.", Toast.LENGTH_SHORT).show();
            } else if (billSizeIn.getText().toString().equals("")) {
                Toast.makeText(BillBookMiniOffset.this, "Also Fill Bill Size.", Toast.LENGTH_SHORT).show();
            } else if (bindingCostIn.getText().toString().equals("")) {
                Toast.makeText(BillBookMiniOffset.this, "Also Fill Binding Cost.", Toast.LENGTH_SHORT).show();
            }
        }else {

            onePaperSheet = (int) Math.ceil((billQuantity*billPaper/billSize));
            totalSheet = copies* onePaperSheet;
            impression = totalSheet*printSize;
            if (!impressionSwitch.isChecked()){
                impression = onePaperSheet *printSize*(copies -1);
            }

            /*if (paperTypeSpinnerD.getVisibility() == View.VISIBLE && paperTypeSpinnerC.getVisibility() == View.VISIBLE){
                paperCost = (int) Math.ceil(onePaperSheet *(typeC+typeD));
                if (paperTypeSpinnerB.getVisibility() == View.VISIBLE){
                    paperCost = (int) Math.ceil(onePaperSheet *(typeB+typeC+typeD));
                    if (paperTypeSpinnerA.getVisibility() == View.VISIBLE){
                        paperCost = (int) Math.ceil(onePaperSheet *(typeA+typeB+typeC+typeD));
                    }
                }
            }*/

            paperCost = (int) (Math.ceil((copies-1)*onePaperSheet*PAPER_RATE +(onePaperSheet*WHITE)));


            if (impression - 1000 < 0) {
                printingCost = 275;
            } else {
                printingCost = 275 + (int)Math.ceil((impression - 1000)*0.075F);
            }
            totalBindingCost = bindingCost*billQuantity;
            totalCost = paperCost+printingCost+totalBindingCost;
            costPerBook = (float) totalCost/billQuantity;
            customerCost = costPerBook*1.5F;

            oneColorSheetView.setText(onePaperSheet +"");
            bindingCostOut.setText(totalBindingCost+"");
            totalSheetView.setText(totalSheet+"");
            impressionView.setText(impression+"");
            paperCostView.setText(paperCost+"");
            printingCostView.setText(printingCost+"");
            totalCostView.setText(totalCost+"");
            costPerBookView.setText(costPerBook+"");
            customerCostView.setText(customerCost+"");
        }
    }
}