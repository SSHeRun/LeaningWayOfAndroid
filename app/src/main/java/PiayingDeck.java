/**
 * Created by 圣三一 on 2019-03-12.
 */
public class PiayingDeck extends Deck {
    public PiayingDeck()
    {
        super();
        for(String suit : PlayingCard.validSuits()){
            for (int rank = 1;rank<=PlayingCard.maxRank();rank++){
                PlayingCard card = new PlayingCard();
                card.setRank(rank);
                card.setSuit(suit);
                addCard(card);
            }
        }
    }
}
