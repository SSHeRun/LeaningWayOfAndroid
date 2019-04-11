package com.example.recyclercardgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.recyclercardgame2.model.CardMatchingGame;

public class MainActivity extends AppCompatActivity {

    private CardMatchingGame game = new CardMatchingGame(52);
    TextView textView_score;
    CardGameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_score = findViewById(R.id.textView);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CardGameAdapter(game);

        recyclerView.setAdapter(adapter);

        adapter.setonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) v.getTag();
                game.chooseCardAtIndex(index);
                adapter.notifyDataSetChanged();
                textView_score.setText("score:"+game.getScore());
            }
        });
    }
    public void reset(View v){
        game.reset();
        textView_score.setText("score:0");
        adapter.notifyDataSetChanged();
    }
}
