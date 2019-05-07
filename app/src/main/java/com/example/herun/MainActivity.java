package com.example.herun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText number1 ;
    EditText number2 ;
    TextView result ;
    ArrayList<String> jisuan  = new ArrayList<>();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.editText1);
        number2 = findViewById(R.id.editText2);
        result = findViewById(R.id.textView_result);
    }

    public void add(View view){

        int num1 = Integer.parseInt(number1.getText().toString());
        int num2 = Integer.parseInt(number2.getText().toString());
        int all = num1+num2;
        result.setText("Result is:"+all);
        String res="Result is:"+ all;
        jisuan.add(res);

    }
    public void history(View view){

        Intent intent = new Intent(MainActivity.this,history.class);
        intent.putStringArrayListExtra("history",jisuan);
        startActivity(intent);

    }
}
