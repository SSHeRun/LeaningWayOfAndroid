package com.example.recyclercardgame2.model;

import java.util.Arrays;

public class PlayingCard extends Card{
    private String _suit;
    private int _rank;

    public String getSuit(){
        return _suit;
    }
    public void setSuit(String suit){
        if(Arrays.asList(PlayingCard.validSuits()).contains(suit)){
            _suit =  suit;
        }
    }
    public int getRank(){
        return _rank;
    }
    public void setRank(int rank){
        if(rank <= PlayingCard.maxRank()) {
            _rank = rank;
        }
    }
    public static String[] validSuits(){
        return new String[]{"♥","♦","♠","♣"};
    }
    private static String[] rankStrings(){
        return new String[]{"?","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    }
    public static int maxRank(){
        return PlayingCard.rankStrings().length - 1;
    }

    @Override
    public int match(Card[] otherCards) {
        int score = 0;
        if(otherCards.length == 1){
            PlayingCard otherCard = (PlayingCard) otherCards[0];
            if(otherCard.getRank()==this.getRank()){
                score =  4;
            }else if (otherCard.getSuit().equals(this.getSuit())){
                score = 1;
            }
        }

        return score;
    }

    @Override
    public String getContents() {
        return PlayingCard.rankStrings()[_rank] + _suit;
    }
}
