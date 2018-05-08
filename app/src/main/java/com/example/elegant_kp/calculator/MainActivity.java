package com.example.elegant_kp.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn_clear;
    Button btn_negative;
    Button btn_del;
    Button btn_div;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_mul;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_sub;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_add;
    Button btn_0;
    Button btn_point;
    Button btn_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        btn_0 = findViewById(R.id.zero);
        btn_1 = findViewById(R.id.one);
        btn_2 = findViewById(R.id.two);
        btn_3 = findViewById(R.id.three);
        btn_4 = findViewById(R.id.four);
        btn_5 = findViewById(R.id.five);
        btn_6 = findViewById(R.id.six);
        btn_7 = findViewById(R.id.seven);
        btn_8 = findViewById(R.id.eight);
        btn_9 = findViewById(R.id.nine);
        btn_clear = findViewById(R.id.clear);
        btn_negative = findViewById(R.id.negative);
        btn_del = findViewById(R.id.del);
        btn_div = findViewById(R.id.div);
        btn_mul = findViewById(R.id.mul);
        btn_add = findViewById(R.id.add);
        btn_sub = findViewById(R.id.sub);
        btn_point = findViewById(R.id.point);
        btn_equal = findViewById(R.id.equal);



    }
}