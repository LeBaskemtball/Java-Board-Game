package MODEL.Findings;

/**
 * The RareFinding class represents a specific type of finding: a rare finding.
 * It extends the Finding class and inherits its properties and behaviors.
 */
public class RareFinding extends Finding{

    /**
     * Constructor for creating a new RareFinding.
     *
     * @param name        The name of the rare finding.
     * @param imagePath   The image path for the rare finding.
     * @param points      The points associated with the rare finding.
     * @param description A description of the rare finding.
     */
    public RareFinding(String name, String imagePath, int points, String description){
        super(name, imagePath, points, description);
    }
}
