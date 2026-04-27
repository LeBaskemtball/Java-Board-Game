package MODEL.Cards;

import MODEL.Pawns.Pawn;
import MODEL.Players.Player;
import MODEL.Table.Path;
import MODEL.Table.Position;

/**
 * The Ariadne class represents a special card in the game that allows a player to move a pawn
 * forward by 2 steps on its current path.
 */
public class Ariadne extends SpecialCard{

    /**
     * Constructor for the Ariadne card.
     *
     * @param name The name of the card.
     */
    public Ariadne(String name){
        super(name, 0);
    }

    /**
     * Uses the Ariadne card to move the target pawn 2 steps forward on its current path.
     * If the target pawn is not a valid type (not an instance of Pawn), no effect is applied.
     *
     * @param targetPlayer The player to whom the pawn belongs.
     * @param targetPawn The pawn that is being moved forward.
     */
    @Override
    public void useCard(Player targetPlayer, Pawn targetPawn){
        if(targetPawn instanceof Pawn){
            Path path = targetPawn.getPath();
            Position currentPosition = targetPawn.getCurrentPosition();
            int currentStep = currentPosition.getStepNumber();

            //Move the pawn 2 steps forward
            int newStep = currentStep + 2;
            if(newStep > path.getPosition(currentStep).getStepNumber()){
                newStep = path.getPosition(newStep).getStepNumber();
            }

            Position newPosition = path.getPosition(newStep);

            targetPawn.moveTo(newPosition);
            System.out.println("Ariadne card used! "+ targetPawn.getClass().getSimpleName() +
                    " of "+ targetPlayer.getName() +
                    " moved forward to "+ newPosition);
        }else{
            System.out.println("Ariadne card has no effect on this target.");
        }
    }
}
