package MODEL.Pawns;

import MODEL.Players.Player;
import MODEL.Table.Path;
import MODEL.Table.Position;

/**
 * The Pawn class represents a generic pawn in the game, with attributes for its name, owner,
 * current position, path, and whether it is hidden or not. It also includes methods to move
 * the pawn, check if it's in a checkpoint or at the end of the path, and reveal it if hidden.
 */
public class Pawn{
    private String pawnName;            //The name of the Pawn
    private Player owner;               //The owner of the Pawn
    private Position currentPosition;   //The current position of the Pawn
    private Path path;                  //The path that the pawn is in
    private boolean isHidden;           //The status of the pawn

    /**
     * Constructor for creating a new pawn.
     *
     * @param pawnName      The name of the pawn.
     * @param owner         The player who owns the pawn.
     * @param currentPosition The current position of the pawn.
     * @param path          The path the pawn is on.
     * @param isHidden      Whether the pawn is hidden or not.
     */
    public Pawn(String pawnName, Player owner, Position currentPosition, Path path, boolean isHidden){
        this.pawnName = pawnName;
        this.owner = owner;
        this.currentPosition = currentPosition;
        this.path = path;
        this.isHidden = isHidden;
    }


    // Getters and setters for the attributes
    public String getPawnName(){
        return pawnName;
    }
    public Player getOwner(){
        return owner;
    }
    public Position getCurrentPosition(){
        return currentPosition;
    }
    public Path getPath(){
        return path;
    }
    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden){
        this.isHidden = hidden;
    }

    /**
     * Moves the pawn to a new position.
     *
     * @param newPosition The new position to move to.
     */
    public void moveTo(Position newPosition){
        this.currentPosition = newPosition;
    }

    /**
     * Checks if the pawn is in a checkpoint (step 7 or above).
     *
     * @return True if the pawn is in a checkpoint, otherwise false.
     */
    public boolean inCheckpoint(){
        return currentPosition.getStepNumber() >= 7;
    }

    /**
     * Checks if the pawn is at the end position of the path.
     *
     * @return True if the pawn is at the end, otherwise false.
     */
    public boolean inEnd(){
        return path.getEndPosition().equals(currentPosition);
    }

    /**
     * Returns a string representation of the pawn.
     *
     * @return A string describing the pawn and its current position.
     */
    @Override
    public String toString(){
        return "Pawn of "+ owner.getName() +" is at "+ currentPosition;
    }
}
