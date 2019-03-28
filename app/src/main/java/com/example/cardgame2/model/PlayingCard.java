package com.example.cardgame2.model;

import java.util.Arrays;

public class PlayingCard extends Card {
    private String suit;
    private int rank;
    public static String[] validSuits(){
        return new String[]{"♥","♦","♠","♣"};
    }
    public static String[] rankString(){
        return new String[]{"?","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    }
    public static int maxRank(){
        return PlayingCard.rankString().length - 1;
    }
    public void setSuit(String aSuit){
        if(Arrays.asList(PlayingCard.validSuits()).contains(aSuit)){
            suit = aSuit;
        }
    }
    public void setRank(int aRank){
        if(aRank <= PlayingCard.maxRank()){
            rank = aRank;
        }
    }
    public String getSuit(){
        return suit;
    }
    public int getRank(){
        return rank;
    }
    @Override
    public String getContents() {
        return PlayingCard.rankString()[rank] + suit;
    }
}
