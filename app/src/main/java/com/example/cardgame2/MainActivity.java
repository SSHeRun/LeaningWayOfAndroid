package com.example.cardgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int flipCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
    }

    public void buttonClicked(View v) {
        if (v.getId() == R.id.button) {
            Button card = (Button) v;
            if(card.getText() == "") {
                card.setText("♥️♥️♥️");
                card.setBackgroundResource(R.drawable.blankcard);
            }else {
                card.setText("");
                card.setBackgroundResource(R.drawable.stanfordtree);
            }
            flipCount++;
            TextView textView = findViewById(R.id.textView);
            textView.setText("已经翻牌"+flipCount+"次");
        }
    }
}

