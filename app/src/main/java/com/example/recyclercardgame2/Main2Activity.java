package com.example.recyclercardgame2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.recyclercardgame2.model.Card;
import com.example.recyclercardgame2.model.CardMatchingGame;

public class Main2Activity extends AppCompatActivity {

    private CardMatchingGame game = new CardMatchingGame(52);
    TextView textView_score;
    CardGameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView_score = findViewById(R.id.textView);

        GridView gridView = findViewById(R.id.gridView);

        final CardAdapter adapter = new CardAdapter();
        gridView.setAdapter(adapter);

    }
    public void reset(View v){
        game.reset();
        textView_score.setText("score:0");
        adapter.notifyDataSetChanged();
    }

    class CardAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return game.count();
        }

        @Override
        public Object getItem(int position) {
            return game.cardAtIndex(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button cardButton = null ;
            if(convertView==null){
                cardButton = new Button(Main2Activity.this);
            } else {
                cardButton = (Button) convertView;
            }
            Card card = game.cardAtIndex(position);
            if(card.isChosen()){
                cardButton.setBackgroundResource(R.drawable.blankcard);
                cardButton.setText(card.getContents());
            } else {
                cardButton.setBackgroundResource(R.drawable.stanfordtree);
                cardButton.setText("");
            }

            cardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.chooseCardAtIndex(position);
                    CardAdapter.this.notifyDataSetChanged();
                }
            });

            return cardButton;
        }
    }
}
