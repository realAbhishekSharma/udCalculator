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

public class PaperCost extends AppCompatActivity {

    ArrayList<String> SizeArray = new ArrayList<>();
    final String[] sizeList = new String[]{"Select","23 x 36","24 x 36","17 x 22","Others"};
    ArrayList<String> gsmArray = new ArrayList<>();
    String[] gsmList = new String[]{"Select","45","58","70","80","90","100","130","150", "200", "230", "250", "300"};
    private final float W_KG_VALUE = 1550000F;

    TextView ctpCost;
    EditText ratePerKGInput, paperSheetInput;
    Button reset;

    //Spinner
    Spinner sizeSpinner,gsmSpinner;

    // Variable
    int ratePerKG=0, paperSheet=0, gsm;
    float length=0F, breadth=0F,paperWeight, paperCost,ratePerSheet;
    float lengthTemp = 0F,breadthTemp = 0F;

    TextView paperWeightView,paperCostView,ratePerSheetView;

    //Size array initializing
    ArrayAdapter<String> sizeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_cost);
        getSupportActionBar().setTitle("Paper Cost");

        //Formula TextView Casting
        paperCostView = (TextView) findViewById(R.id.paperCostPC);
        paperWeightView = (TextView) findViewById(R.id.paperWeightPC);
        ratePerSheetView = (TextView) findViewById(R.id.ratePerSheetPC);

        //Other Casting
        reset = (Button) findViewById(R.id.resetPC);
        ratePerKGInput = (EditText) findViewById(R.id.ratePerKGPC);
        paperSheetInput = (EditText) findViewById(R.id.paperSheetPC);


        //Spinner Casting
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinnerPC);
        gsmSpinner = (Spinner) findViewById(R.id.gsmSpinnerPC);



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
                    breadth = 36;

                }else if (position == 2){
                    length = 24;
                    breadth = 36;

                }else if (position == 3){
                    length = 17;
                    breadth = 22;

                }else if (position == 4 ){
                    openDialogBox();
                }
                if (position > 0 && position <= 3){
                    virtualDone();
                    SizeArray.set(4, "Others");
                    if (SizeArray.size() ==6) {
                        SizeArray.remove(5);
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

        //Calculating Value when paper Sheet value change
        paperSheetInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    virtualDone();
                }else {
                    paperSheetInput.setHint("?");
                    cleanCalculation();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    // All Method Creation------------------------------------------------------------------

    // Opening DialogBox And setting Value of Paper Size
    private void openDialogBox(){

        AlertDialog.Builder dialogBox = new AlertDialog.Builder(PaperCost.this);
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
                    Toast.makeText(PaperCost.this, "Paper Size can't be empty", Toast.LENGTH_SHORT).show();

                }else {

                    length = Float.parseFloat(lengthInput.getText().toString());
                    breadth = Float.parseFloat(breadthInput.getText().toString());
                    lengthTemp = length;
                    breadthTemp = breadth;
                    alertDialog.dismiss();

                    //For Setting Name And Calculating Value
                    if (SizeArray.size() == 5){
                        SizeArray.add( "----Others "+Float.toString(length)+" X "+Float.toString(breadth));

                    }else if (SizeArray.size() == 6){
                        SizeArray.remove(5);
                        SizeArray.add( "----Others "+Float.toString(length)+" X "+Float.toString(breadth));
                    }

                    sizeSpinner.setAdapter(sizeAdapter);
                    sizeSpinner.setSelection(5);
                    virtualDone();
                    //
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                SizeArray.set(4, "Others");
                sizeSpinner.setSelection(0);


            }
        });

    }

    // Virtual Done
    private void virtualDone(){

        if (ratePerKGInput.getText().toString().equals("") || paperSheetInput.getText().toString().equals("")){

            if (paperSheetInput.getText().toString().equals("")){
                paperSheetInput.setHint("?");

            }
            if (ratePerKGInput.getText().toString().equals("")){
                ratePerKGInput.setHint("?");

            }


        }else {

            ratePerKG = Integer.parseInt(ratePerKGInput.getText().toString());
            paperSheet = Integer.parseInt(paperSheetInput.getText().toString());


            paperWeight = (length*breadth*gsm*paperSheet)/W_KG_VALUE;
            paperCost = paperWeight*ratePerKG;
            ratePerSheet = paperCost/paperSheet;

            paperWeightView.setText(paperWeight + "");
            paperCostView.setText(paperCost + "");
            ratePerSheetView.setText(ratePerSheet + "");
        }

    }

    // Reset All Method
    private void resetAll(){
        paperSheetInput.setText("");
        ratePerKGInput.setText("");
        paperWeightView.setText("");
        paperCostView.setText("");
        ratePerSheetView.setText("");
        SizeArray.clear();
        SizeArray.addAll(Arrays.asList(sizeList));
        sizeSpinner.setSelection(0);
        gsmSpinner.setSelection(0);
        lengthTemp=0;
        breadthTemp=0;
    }
    //Clean Calculation
    private void cleanCalculation(){
        paperWeightView.setText("");
        paperCostView.setText("");
        ratePerSheetView.setText("");

    }
}