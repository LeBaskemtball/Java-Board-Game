package MODEL.Cards;

/**
 * The NumberCard class represents a number card in the game. It is a specific type of card that matches
 * only other NumberCards.
 */
public class NumberCard extends Card{

    /**
     * Constructor for the NumberCard.
     *
     * @param name  The name of the card.
     * @param value The value of the card.
     */
    public NumberCard(String name, int value){
        super(name, value);
    }

    /**
     * Checks if the current card matches another card. NumberCards only match other NumberCards with the same name and value.
     *
     * @param c The card to match against.
     * @return True if the cards match, false otherwise.
     */
    @Override
    public boolean matchCard(Card c){
        if(c instanceof NumberCard){
            return super.matchCard(c);
        }
        return false;
    }
}
