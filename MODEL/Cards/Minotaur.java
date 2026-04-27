package MODEL.Cards;

import MODEL.Pawns.Archaeologist;
import MODEL.Pawns.Pawn;
import MODEL.Pawns.Theseus;
import MODEL.Players.Player;
import MODEL.Table.Path;
import MODEL.Table.Position;

/**
 * The Minotaur class represents a special card in the game that can stun Theseus or move an Archaeologist pawn back.
 */
public class Minotaur extends SpecialCard{

    /**
     * Constructor for the Minotaur card.
     *
     * @param name The name of the card.
     */
    public Minotaur(String name){
        super(name, 0);
    }

    /**
     * Uses the Minotaur card to either stun the Theseus pawn or move an Archaeologist pawn back by 2 steps.
     * The effect depends on the current position of the target pawn.
     *
     * @param targetPlayer The player to whom the pawn belongs.
     * @param targetPawn The pawn that is the target of the Minotaur card's effect.
     */
    @Override
    public void useCard(Player targetPlayer, Pawn targetPawn){
        Path path = targetPawn.getPath();
        Position currentPosition = targetPawn.getCurrentPosition();
        int currentStep = currentPosition.getStepNumber();

        //Cant attack
        if(currentStep >= 7){
            System.out.println("Minotaur Card has no effect. Target Pawn is on the 7th tile or above");
            return;
        }

        if(targetPawn instanceof Theseus){
            //Stun Theseus

            Theseus theseus = (Theseus) targetPawn;
            theseus.setStunned(true);
            theseus.setHidden(false);   //Make the pawn visible
            System.out.println("Minotaur Card used! Theseus pawn of "+ targetPlayer.getName() +
                    " won the fight but is stunned for the next round and visible for the entire game!");

        }else if(targetPawn instanceof Archaeologist){
            //Move Archaeologist 2 steps back

            if(currentStep == 1){
                System.out.println("Minotaur Card has no effect. The Archaeologist pawn is at the 1st tile.");
            }else if(currentStep == 2){
                //Move the pawn 1 step back if it's on the 2nd tile
                Position newPosition = path.getPosition(1);
                targetPawn.moveTo(newPosition);
                targetPawn.setHidden(false);    //Make the pawn visible
                System.out.println("Minotaur Card used! Archaeologist moved back to the 1st tile and became visible!");
            }else if(currentStep > 2){
                Archaeologist archaeologist = (Archaeologist) targetPawn;
                path = archaeologist.getPath();
                currentPosition = archaeologist.getCurrentPosition();
                currentStep = currentPosition.getStepNumber();

                //Move the Archaeologist
                int newStep = currentStep - 2;
                Position newPosition = path.getPosition(newStep);
                archaeologist.moveTo(newPosition);
                System.out.println("Minotaur Card used! Archaeologist pawn of "+ targetPlayer.getName() +
                        " moved back 2 steps to "+ newPosition);
            }
        }
    }
}
