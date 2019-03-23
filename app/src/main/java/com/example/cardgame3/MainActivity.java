package com.example.cardgame3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.cardgame3.model.CardMatchingGame;
import com.example.cardgame3.model.PlayingCard;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CardMatchingGame game = new CardMatchingGame(16);
    private ArrayList<Button> buttons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        ViewGroup viewGroup = findViewById(R.id.content);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            ViewGroup vg = (ViewGroup) viewGroup.getChildAt(i);
            for (int j = 0; j < vg.getChildCount(); j++) {
                if ((vg.getChildAt(j) instanceof Button) && vg.getChildAt(j).getId() != R.id.reset) {
                    Button button = (Button) vg.getChildAt(j);
                    buttons.add(button);
                }
            }
        }
    }
    public void chooseCard(View v) {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (button.getId() == v.getId()) {
                game.chooseCardAtIndex(i);
            }
        }
        updateUI();
    }
    public void reset(View v){
        game.reset();
        updateUI();
    }
    private void updateUI(){
        for (int i = 0;i < buttons.size();i++){
            Button button = buttons.get(i);
            PlayingCard card = (PlayingCard) game.cardAtIndex(i);
            button.setAlpha(card.isMatched()?(float)0.5:1);
            if (card.isChosen()){
                button.setBackgroundResource(R.drawable.blankcard);
                button.setText(card.getContents());
                button.setTextSize(20);
            }else{
                button.setBackgroundResource(R.drawable.stanfordtree);
                button.setText("");
            }
            TextView textView = findViewById(R.id.scorebar);
            textView.setText("score:" + game.getScore());
            textView.setTextSize(20);
//            textView.setTextColor(Color.parseColor("#ff5e9cff"));
        }
    }
}
