package com.example.recyclercardgame2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recyclercardgame2.model.Card;
import com.example.recyclercardgame2.model.CardMatchingGame;

public class CardGameAdapter extends RecyclerView.Adapter<CardGameAdapter.ViewHolder> {

    private CardMatchingGame game;
    private View.OnClickListener monClickListener;

    public void setonClickListener(View.OnClickListener monClickListener) {
        this.monClickListener = monClickListener;
    }


    public CardGameAdapter(CardMatchingGame game) {
        this.game = game;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = new Button(viewGroup.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,360));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Card card = game.cardAtIndex(i);

        viewHolder.cardButton.setAlpha(card.isMatched() ? (float)0.5: 1);
        if(card.isChosen()){
            viewHolder.cardButton.setBackgroundResource(R.drawable.blankcard);
            viewHolder.cardButton.setText(card.getContents());
        } else {
            viewHolder.cardButton.setBackgroundResource(R.drawable.stanfordtree);
            viewHolder.cardButton.setText("");
        }

        viewHolder.cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monClickListener != null ) {
                    v.setTag(i);
                    monClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return game.count();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button cardButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardButton = (Button) itemView;
        }
    }
}
