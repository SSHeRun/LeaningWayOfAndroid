package com.example.cardgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardgame2.model.PlayingCard;
import com.example.cardgame2.model.PlayingDeck;

public class MainActivity extends AppCompatActivity {
    public int count=0;
    private PlayingDeck game = new PlayingDeck();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count++;
                TextView tv = (TextView) findViewById(R.id.textView);
                tv.setText("已经翻了" + count + "次");
                Button btn = (Button) v;

                if (btn.getText() == "") {
                    PlayingCard card = (PlayingCard) game.drawRandomCard();
                    if(card != null) btn.setText(card.getContents());
                    btn.setBackgroundResource(R.drawable.blankcard);
                } else {
                    btn.setText("");
                    btn.setBackgroundResource(R.drawable.stanfordtree);
                }
            }
        });
    }
}
