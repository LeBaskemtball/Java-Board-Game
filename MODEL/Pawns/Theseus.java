package MODEL.Pawns;

import MODEL.Findings.Fresco;
import MODEL.Findings.RareFinding;
import MODEL.Findings.Statue;
import MODEL.Players.Player;
import MODEL.Table.Path;
import MODEL.Table.Position;

/**
 * The Theseus class represents a specific type of pawn, Theseus. Theseus can attack and destroy rare findings,
 * statues, and frescoes, and can also be stunned. Additionally, Theseus has a unique scoring mechanic where
 * his points are doubled based on his position.
 */
public class Theseus extends Pawn{
    private boolean stunned;
    private int attacks;

    /**
     * Constructor for creating a Theseus pawn.
     *
     * @param pawnName      The name of the pawn (set as "Theseus").
     * @param owner         The player who owns the pawn.
     * @param currentPosition The current position of the pawn.
     * @param path          The path the pawn is on.
     * @param isHidden      Whether the pawn is hidden or not.
     */
    public Theseus(String pawnName, Player owner, Position currentPosition, Path path, boolean isHidden){
        super("Theseus", owner, currentPosition, path, isHidden);
        this.stunned = false;
        this.attacks = 3;
    }

    /**
     * Calculates the points based on the pawn's current position, with points doubled for Theseus.
     *
     * @return The points for the current position of Theseus, doubled.
     */
    public int Points(){
        return getCurrentPosition().getPoints() * 2;
    }

    /**
     * Checks if Theseus is stunned.
     *
     * @return True if Theseus is stunned, otherwise false.
     */
    public boolean isStunned() {
        return stunned;
    }

    /**
     * Gets the number of remaining attacks for Theseus.
     *
     * @return The number of remaining attacks.
     */
    public int getAttacks(){
        return attacks;
    }

    /**
     * Attacks and destroys a rare finding.
     *
     * @param finding The rare finding to be destroyed.
     */
    public void attackRareFinding(RareFinding finding) {
        if (attacks <= 0) {
            System.out.println("Theseus has used his attacks.");
            return;
        }

        System.out.println("Theseus destroys the rare finding: "+ finding.getName());
        getOwner().calculateScore();
        finding.destroy();
        attacks--;
        reveal();
    }

    /**
     * Attacks and destroys a statue.
     *
     * @param statue The statue to be destroyed.
     */
    public void attackStatue(Statue statue){
        if (attacks <= 0) {
            System.out.println("Theseus has used his attacks.");
            return;
        }

        System.out.println("Theseus destroys the statue: "+ statue.getName());
        getOwner().calculateScore();
        statue.destroy();
        attacks--;
        reveal();
    }

    /**
     * Attacks and destroys a fresco.
     *
     * @param fresco The fresco to be destroyed.
     */
    public void attackFresco(Fresco fresco){
        if (attacks <= 0) {
            System.out.println("Theseus has used his attacks.");
            return;
        }

        System.out.println("Theseus destroys the fresco: "+ fresco.getName());
        getOwner().calculateScore();
        fresco.destroy();
        attacks--;
        reveal();
    }

    /**
     * Sets the stunned status of Theseus.
     *
     * @param stunned True if Theseus is stunned, otherwise false.
     */
    public void setStunned(boolean stunned){
        this.stunned = stunned;
    }

    /**
     * Reveals Theseus if hidden.
     */
    public void reveal(){
        if(isHidden()){
            setHidden(false);
            System.out.println("Theseus is revealed.");
        }
    }

    /**
     * Returns a string representation of the Theseus pawn.
     *
     * @return A string describing the Theseus pawn and its current position.
     */
    @Override
    public String toString(){
        return "Theseus pawn of "+ getOwner().getName() +" at "+ getCurrentPosition();
    }
}
