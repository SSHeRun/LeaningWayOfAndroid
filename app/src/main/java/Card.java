/**
 * Created by 圣三一 on 2019-03-12.
 */
public abstract class Card {
    private boolean chosen;
    private boolean matched;
    public abstract  String getContens();
    public  boolean isChosen()
    {
        return  chosen;
    }
    public boolean isMatched()
    {
        return matched;
    }
    public int match(Card[] otherCards)
    {
        int score = 0;
        for (Card card : otherCards)
        {
            if(card.getContens().equals(this)) {
                score = 1;
            }
        }
        return score;
    }


}
