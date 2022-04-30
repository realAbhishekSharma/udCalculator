package com.realhydrogen.uniquedesktopcalulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    EditText value;
    Button button;
    RateValue maplithoRate;
    TextView vieww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        value = (EditText) findViewById(R.id.getValue);
        button = (Button) findViewById(R.id.button);
        maplithoRate = new RateValue();
        vieww = (TextView) findViewById(R.id.textView2);

        vieww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vieww.setText(maplithoRate.getRate()+"");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplithoRate.setRate(Float.parseFloat(value.getText().toString()));
            }
        });

    }
}