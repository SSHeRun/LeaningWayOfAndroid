package com.example.cardgame3.model;

import java.util.ArrayList;

public class CardMatchingGame {
    private ArrayList<Card> cards;
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
       score= 0;
        Deck deck = new PlayingDeck();
        cards = new ArrayList<Card>();
        for (int i = 0; i < count; i++) {
            Card card = deck.drawRandomCard();
            if (card != null) {
                cards.add(card);
            }
        }
    }

    public Card cardAtIndex(int index) {
        return (index < cards.size()) ? cards.get(index) : null;
    }

    public int getScore() {
        return score;
    }

    public int count() {
        return count;
    }

    final int MISMATCH_PENALY = 2;
    final int MATCH_BONUS = 4;
    final int COST_TO_CHOOSE = 1;

    public void chooseCardAtIndex(int index) {
        Card card = cardAtIndex(index);
        if (!card.isMatched()) {
            if (card.isChosen()) {
                card.setChosen(false);
            } else {
                for (Card otherCard : cards) {
                    if (otherCard.isChosen() && !otherCard.isMatched()) {
                        int matchScore = card.match(new Card[]{otherCard});
                        if (matchScore > 0) {
                            score += matchScore * MATCH_BONUS;
                            otherCard.setMatched(true);
                            card.setMatched(true);
                        } else {
                            score -= MISMATCH_PENALY;
                            otherCard.setChosen(false);
                        }
                        break;
                    }
                }
                score -= COST_TO_CHOOSE;
                card.setChosen(true);
            }
        }
    }
}
