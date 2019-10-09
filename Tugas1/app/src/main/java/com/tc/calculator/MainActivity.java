package com.tc.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnCalculate;
    EditText txtPanjang, txtLebar;
    TextView txtLuas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Hitung Luas Persegi Panjang");

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        txtPanjang = (EditText) findViewById(R.id.txtPanjang);
        txtLebar = (EditText) findViewById(R.id.txtLebar);

        txtLuas = (TextView) findViewById(R.id.txtLuas);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double p = Double.valueOf(txtPanjang.getText().toString().trim());
                double l = Double.valueOf(txtLebar.getText().toString().trim());

                txtLuas.setText("Luas : " + String.valueOf(p*l));
            }
        });
    }
}
