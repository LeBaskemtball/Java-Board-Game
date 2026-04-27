package MODEL.Cards;

import MODEL.Pawns.Pawn;
import MODEL.Players.Player;

/**
 * The SpecialCard class represents a special card in the game, which can have specific effects when used.
 */
public class SpecialCard extends Card{

    /**
     * Constructor for the SpecialCard.
     *
     * @param name  The name of the card.
     * @param value The value of the card.
     */
    public SpecialCard(String name, int value){
        super(name, value);
    }

    /**
     * Checks if the current card matches another special card.
     *
     * @param c The card to match against.
     * @return True if the cards match, false otherwise.
     */
    @Override
    public boolean matchCard(Card c){
        if(c instanceof SpecialCard){
            return super.matchCard(c);
        }
        return false;
    }

    /**
     * Applies the effect of the card to the target player and pawn. This method should be overridden by subclasses.
     *
     * @param targetPlayer The player to whom the effect of the card is applied.
     * @param targetPawn   The pawn affected by the card.
     */
    public void useCard(Player targetPlayer, Pawn targetPawn){
    }
}
