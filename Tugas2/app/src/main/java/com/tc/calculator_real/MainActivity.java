package com.tc.calculator_real;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathExpression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtNum,txtHasil;
    private Button clear, c, delete, divide, btn9,btn8, btn7,plus, btn6,btn5,btn4,minus,btn3,btn2,btn1,multi,btn0, sum;

    Integer summary=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate
        txtNum = (TextView) findViewById(R.id.txtNum);
        txtHasil = (TextView) findViewById(R.id.txtHasil);

        clear = (Button) findViewById(R.id.clear);
        c = (Button) findViewById(R.id.c);
        delete = (Button) findViewById(R.id.delete);
        divide = (Button) findViewById(R.id.divide);

        btn9 = (Button) findViewById(R.id.btn9);
        btn8 = (Button) findViewById(R.id.btn8);
        btn7 = (Button) findViewById(R.id.btn7);
        plus = (Button) findViewById(R.id.plus);

        btn6 = (Button) findViewById(R.id.btn6);
        btn5 = (Button) findViewById(R.id.btn5);
        btn4 = (Button) findViewById(R.id.btn4);
        minus = (Button) findViewById(R.id.minus);

        btn3 = (Button) findViewById(R.id.btn3);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1 = (Button) findViewById(R.id.btn1);
        multi = (Button) findViewById(R.id.multi);

        btn0 = (Button) findViewById(R.id.btn0);
        sum = (Button) findViewById(R.id.sum);

        clear.setOnClickListener(this);
        c.setOnClickListener(this);
        delete.setOnClickListener(this);
        divide.setOnClickListener(this);

        btn9.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn7.setOnClickListener(this);
        plus.setOnClickListener(this);

        btn6.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn4.setOnClickListener(this);
        minus.setOnClickListener(this);

        btn3.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        multi.setOnClickListener(this);

        btn0.setOnClickListener(this);
        sum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String newString = txtNum.getText().toString();
        switch (v.getId()){
            case R.id.sum:
                getSum(newString);
                break;
            case R.id.minus:
                txtNum.setText(newString+"-");
                break;
            case R.id.divide:
                txtNum.setText(newString+"/");
                break;
            case R.id.plus:
                txtNum.setText(newString+"+");
                break;
            case R.id.multi:
                txtNum.setText(newString+"*");
                break;
            case R.id.btn0:
                txtNum.setText(newString+"0");
                break;
            case R.id.btn1:
                txtNum.setText(newString+"1");
                break;
            case R.id.btn2:
                txtNum.setText(newString+"2");
                break;
            case R.id.btn3:
                txtNum.setText(newString+"3");
                break;
            case R.id.btn4:
                txtNum.setText(newString+"4");
                break;
            case R.id.btn5:
                txtNum.setText(newString+"5");
                break;
            case R.id.btn6:
                txtNum.setText(newString+"6");
                break;
            case R.id.btn7:
                txtNum.setText(newString+"7");
                break;
            case R.id.btn8:
                txtNum.setText(newString+"8");
                break;
            case R.id.btn9:
                txtNum.setText(newString+"9");
                break;
            case R.id.c:
            case R.id.clear:
            case R.id.delete:
                txtNum.setText("");
                txtHasil.setText("");
                break;
        }
    }

    private void getSum(String value){
        double val =0;

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(value);

        ArrayList<String> number = new ArrayList<>();

        while (matcher.find()){
            number.add(matcher.group(0));
        }

        Pattern pattern1 = Pattern.compile("[\\D]+");
        Matcher matcher1 = pattern1.matcher(value);

        String operator="^";

        while (matcher1.find()){
            operator = matcher1.group(0);
        }

        if(operator.equals('^')){
            txtHasil.setText(number.get(0));
        }
        else{
            switch (operator){
                case "+":
                    val = Integer.valueOf(number.get(0)) + Integer.valueOf(number.get(1));
                    txtHasil.setText(String.valueOf((int) Math.round(val)));
                    break;
                case "-":
                    val = Integer.valueOf(number.get(0)) - Integer.valueOf(number.get(1));
                    txtHasil.setText(String.valueOf((int) Math.round(val)));
                    break;
                case "/":
                    val = Double.valueOf(number.get(0)) / Double.valueOf(number.get(1));
                    txtHasil.setText(String.valueOf(val));
                    break;
                case "*":
                    val = Integer.valueOf(number.get(0)) * Integer.valueOf(number.get(1));
                    txtHasil.setText(String.valueOf((int) Math.round(val)));
                    break;
            }
        }


    }
}
