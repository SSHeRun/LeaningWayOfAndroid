package com.example.cardgame2.model;

public abstract class Card {
    private boolean chosen;
    private boolean matched;
    public abstract String getContents();
    public boolean isChosen(){
        return chosen;
    }
    public boolean isMatched(){
        return matched;
    }
    public int match(Card[] otherCards){
        int score = 0;
        for (Card card : otherCards){
            if (card.getContents().equals(this)){
                score = 1;
            }
        }
        return score;
    }
}
