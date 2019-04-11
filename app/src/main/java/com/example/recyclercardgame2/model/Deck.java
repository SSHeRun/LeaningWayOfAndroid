package com.example.recyclercardgame2.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by liguiyang on 16/3/7.
 */
public class Deck {
    private ArrayList<Card> _cards = new ArrayList<>();
    private Random r = new Random();

    public void addCard(Card card,boolean atTop){
        if (atTop) {
            _cards.add(0,card);
        }else{
            _cards.add(card);
        }
    }

    public void addCard(Card card){
        addCard(card,false);
    }

    public Card drawRandomCard(){
        Card randomCard = null;
        if (_cards.size()>0){
            randomCard = _cards.remove(r.nextInt(_cards.size()));
        }
        return randomCard;
    }
}
