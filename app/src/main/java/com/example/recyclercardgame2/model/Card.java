package com.example.recyclercardgame2.model;

/**
 * Created by liguiyang on 16/3/7.
 */
public abstract class Card {
    private boolean _chosen;
    private  boolean _matched;
    public abstract String getContents();
    public boolean isChosen(){
        return _chosen;
    }
    public void setChosen(boolean chosen){
        _chosen = chosen;
    }
    public boolean isMatched(){
        return _matched;
    }
    public void setMatched(boolean matched){
        _matched = matched;
    }
    public int match(Card[] otherCards){
        int score = 0;
        for (Card card : otherCards) {
           if (card.getContents().equals(this)){
               score = 1;
           }
        }
        return score;
    }
}
