package MODEL.Table;

import MODEL.Findings.Finding;

/**
 * The Position class represents a position on the game board. It holds information
 * about the position's name, points, any findings at that position, and the step number
 * in the path.
 */
public class Position{
    private String name;
    private int points;
    private boolean hasFinding;
    private Finding finding;
    private int stepNumber;

    /**
     * Constructor for creating a new position.
     *
     * @param name       The name of the position.
     * @param points     The points associated with this position.
     * @param hasFinding Whether the position has a finding.
     * @param finding    The finding at this position (can be null if no finding).
     * @param stepNumber The step number of this position in the path.
     */
    public Position(String name, int points, boolean hasFinding, Finding finding, int stepNumber){
        this.name = name;
        this.points = points;
        this.hasFinding = hasFinding;
        this.finding = finding;
        this.stepNumber = stepNumber;
    }


    public boolean hasFinding(){
        return hasFinding;
    }
    public Finding getFinding(){
        return finding;
    }
    public int getPoints(){
        return points;
    }
    public int getStepNumber(){
        return stepNumber;
    }
}
