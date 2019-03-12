package com.example.cardgame1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int flipCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button card = (Button)v;
                if (card.getText() =="") {
                    card.setText("♥️❥");
                    card.setBackgroundResource(R.drawable.blankcard);
                }else{
                    card.setText("");
                    card.setBackgroundResource(R.drawable.stanfordtree);
                }
                flipCount++;
                TextView textView = findViewById(R.id.textView);
                textView.setText("已经翻牌"+flipCount+"次");
            }
        });
    }
}
