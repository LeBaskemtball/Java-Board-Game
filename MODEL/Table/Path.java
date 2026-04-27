package MODEL.Table;

import java.util.List;

/**
 * The Path class represents a path consisting of a series of positions. It allows access
 * to specific positions by step number and provides information about the path's end position.
 */
public class Path{
    private String name;                //Names of the Palaces
    private List<Position> positions;   //List of positions on each path

    /**
     * Constructor for creating a new path.
     *
     * @param name       The name of the path.
     * @param positions  The list of positions on the path.
     */
    public Path(String name, List<Position> positions){
        this.name = name;
        this.positions = positions;
    }

    public String getName(){
        return name;
    }

    /**
     * Gets the position at the specified step number.
     *
     * @param stepNum The step number in the path.
     * @return The position at the specified step number.
     * @throws IllegalArgumentException If the step number is out of bounds.
     */
    public Position getPosition(int stepNum){
        if(stepNum < 1 || stepNum > positions.size()){
            throw new IllegalArgumentException("Step number out of bounds: "+ stepNum);
        }
        return positions.get(stepNum);
    }

    /**
     * Gets the last position on the path.
     *
     * @return The last position on the path.
     */
    public Position getEndPosition(){
        return positions.get(positions.size() - 1);
    }

}
