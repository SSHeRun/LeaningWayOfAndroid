package com.example.cardgame3.model;

import java.util.ArrayList;
import java.util.Random;

public abstract class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    private Random r = new Random();
    public void addCard(Card card,boolean atTop){
        if (atTop) {
            cards.add(0,card);
        }else {
            cards.add(card);
        }
    }
    public void addCard(Card card){
        addCard(card,false);
    }
    public Card drawRandomCard(){
        Card randomCard = null;
        if(cards.size()>0){
            randomCard  = cards.remove(r.nextInt(cards.size()));
        }
        return  randomCard;
    }
}
