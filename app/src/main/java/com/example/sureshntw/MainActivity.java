package com.example.sureshntw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button bConvert;
    private TextView tvWords;
    private EditText etNumber;

    private  static  final String[] tensNames={
      "",
      "ten",
            "twenty",
            "thirty",
            "fourty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety",
    };

    private  static  final String[] numNames={
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen",

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = (EditText) findViewById(R.id.etNumber);
        tvWords = (TextView) findViewById(R.id.tvWords);
        bConvert = (Button) findViewById(R.id.bConvert);

        bConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             tvWords.setText(bConvert(Integer.parseInt(etNumber.getText().toString())));
            }
        });

    }
    private static String convertLessThenOneThosand (int number) {
        String sofar;
        if(number % 100 < 20) {
            sofar = numNames[number % 10];
            number /=10;
        }else
        {
            sofar =numNames[number % 10];
            number /=10;

            sofar =tensNames[number % 10] +sofar;
            number /=10;
        }
        if (number==0) return  sofar;
        return  numNames[number] + "hundred" + sofar;
    }
    public static String bConvert(long number)
    {
        if(number == 0){
            return  "zero";
        }

        String snumber = Long.toString(number);
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber =df.format(number);

        int billions = Integer.parseInt(snumber.substring(0,3));

        int millions = Integer.parseInt(snumber.substring(3,6));
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        switch (billions){
            case 0:
                tradBillions = "";
                break;

            case 1:
                tradBillions = convertLessThenOneThosand(billions) + "billion ";

                break;
                default:
                    tradBillions = convertLessThenOneThosand(billions) + "billion";

        }
        String result = tradBillions;

        String tradMillions;
        switch (millions){
            case 0:
                tradMillions = "";
                break;

            case 1:
                tradMillions = convertLessThenOneThosand(millions) + "million ";

                break;
            default:
                tradMillions = convertLessThenOneThosand(millions) + "million";

        }
         result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands){
            case 0:
                tradHundredThousands = "";
                break;

            case 1:
                tradHundredThousands =  "one thousand ";

                break;
            default:
                tradHundredThousands = convertLessThenOneThosand(hundredThousands) + "thousand";

        }
        result = result + tradHundredThousands;

        String tradThousand;
        tradThousand =convertLessThenOneThosand(thousands);

        result = result +tradThousand;


        return  result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", "");
    }
}
