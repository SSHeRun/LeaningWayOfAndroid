package com.example.recyclercardgame2.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CardMatchingGame implements Serializable {
    private ArrayList<Card> cards;
    public ArrayList<String> playHistory = new ArrayList<>();

    private int count = 0;
    private int score = 0;

    public CardMatchingGame(int count) {
        this.count = count;
        Deck deck = new PlayingDeck();
        cards = new ArrayList<Card>();
        for (int i = 0; i < count; i++) {
            Card card = deck.drawRandomCard();
            if (card != null) {
                cards.add(card);
            }
        }
    }

    public void reset() {
        Deck deck = new PlayingDeck();
        cards = new ArrayList<Card>();
        for (int i = 0; i < count; i++) {
            Card card = deck.drawRandomCard();
            if (card != null) {
                cards.add(card);
            }
        }
    }

    public Card cardAtIndex(int index){
        return (index < cards.size()) ? cards.get(index) : null;
    }

    public int getScore(){
        return score;
    }

    public int count() {
        return count;
    }


    final int MISMATCH_PENALTY = 2;
    final int MATCH_BONUS = 4;
    final int COST_TO_CHOOSE = 1;

    public void chooseCardAtIndex(int index){
        Card card = cardAtIndex(index);
        String play = "";
        if(!card.isMatched()){
            if(card.isChosen()){
                play = card.getContents() + " Card Down";
                card.setChosen(false);
            }else{
                play = card.getContents() + " Card Up";
                for (Card otherCard : cards) {
                    if(otherCard.isChosen() && !otherCard.isMatched()){
                        int matchScore = card.match(new Card[] {otherCard});
                        if (matchScore > 0) {
                            play = card.getContents() + " matched " + otherCard.getContents();
                            score += matchScore * MATCH_BONUS;
                            otherCard.setMatched(true);
                            card.setMatched(true);
                        }else{
                            play = card.getContents() + " don't matche " + otherCard.getContents();
                            score -=MISMATCH_PENALTY;
                            otherCard.setChosen(false);
                        }
                        break;
                    }
                }
                score -= COST_TO_CHOOSE;
                card.setChosen(true);
            }
            playHistory.add(play);
        }
    }


}
