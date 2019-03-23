package com.example.cardgame3.model;

import java.util.Arrays;

public class PlayingCard extends Card {
    private String suit;
    private int rank;
    public String getSuit() {
        return suit;
    }
    public void setSuit(String asuit) {
        if (Arrays.asList(PlayingCard.validSuits()).contains(asuit)) {
            this.suit = asuit;
        }
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int aRank) {
        if (aRank <= PlayingCard.maxRank()) {
           rank = aRank;
        }
    }
    public static String[] validSuits(){
        return new String[]{"♥︎","♦︎","♠︎","♣︎"};
    }
    private static String[] rankString(){
        return new String[]{"?","A","2","3","4","5","6","7","8","9","10","J","Q","k"};
    }
    public static int maxRank(){
        return PlayingCard.rankString().length - 1;
    }
    @Override
    public int match(Card[] otherCards) {
        int score = 0;
        if (otherCards.length == 1){
            PlayingCard otherCard = (PlayingCard) otherCards[0];
            if (otherCard.getRank() == this.getRank()){
                score = 4;
            }else if (otherCard.getSuit().equals(this.getSuit())){
                score = 1;
            }
        }
        return score;
    }
    @Override
    public String getContents() {
        return PlayingCard.rankString()[rank] + suit;
    }
}
