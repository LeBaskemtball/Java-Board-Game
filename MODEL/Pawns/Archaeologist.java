package MODEL.Pawns;

import MODEL.Findings.Finding;
import MODEL.Players.Player;
import MODEL.Table.Path;
import MODEL.Table.Position;

/**
 * The Archaeologist class represents a specific type of pawn, the Archaeologist. It can collect findings
 * and calculate points based on its position. It also has a hidden status, which is revealed when a finding
 * is collected.
 */
public class Archaeologist extends Pawn{

    /**
     * Constructor for creating an Archaeologist pawn.
     *
     * @param pawnName      The name of the pawn (set as "Archaeologist").
     * @param owner         The player who owns the pawn.
     * @param currentPosition The current position of the pawn.
     * @param path          The path the pawn is on.
     * @param isHidden      Whether the pawn is hidden or not.
     */
    public Archaeologist(String pawnName, Player owner, Position currentPosition, Path path, boolean isHidden){
        super("Archeologist", owner, currentPosition, path, isHidden);
    }

    /**
     * Calculates the points based on the pawn's current position.
     *
     * @return The points for the current position of the Archaeologist.
     */
    public int Points(){
        return getCurrentPosition().getPoints();
    }

    /**
     * Collects a finding and updates the player's score.
     * If the pawn is hidden, it will be revealed.
     *
     * @param finding The finding to be collected.
     */
    public void collectFinding(Finding finding){
        System.out.println("Archaeologist collects the finding: "+ finding.getName());
        getOwner().calculateScore();
        getOwner().collectFindings(finding);

        if(isHidden()){
            setHidden(false);
            System.out.println("Archaeologist is now revealed.");
        }
    }

    /**
     * Returns a string representation of the Archaeologist pawn.
     *
     * @return A string describing the Archaeologist pawn and its current position.
     */
    @Override
    public String toString(){
        return "Archaeologist pawn of "+ getOwner().getName() +" at "+ getCurrentPosition();
    }
}
