package MODEL.Cards;

/**
 * The Card class represents a card in the game, with a name and value.
 */
public class Card{
    private String name;    //The Palace names
    private int value;      //The value of the card

    /**
     * Constructor for a Card.
     *
     * @param name  The name of the card.
     * @param value The value of the card.
     */
    public Card(String name, int value){
        this.name = name;
        this.value = value;
    }

    /**
     * Gets the name of the card.
     *
     * @return The name of the card.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the value of the card.
     *
     * @return The value of the card.
     */
    public int getValue(){
        return value;
    }

    /**
     * Checks if the current card matches another card based on name and value.
     *
     * @param c The card to match against.
     * @return True if the cards match, false otherwise.
     */
    public boolean matchCard(Card c){
        return this.name.equals(c.getName()) && (this.value == c.getValue() || c instanceof SpecialCard);
    }
}
