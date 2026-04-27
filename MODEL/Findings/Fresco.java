package MODEL.Findings;

/**
 * The Fresco class represents a specific type of finding: a fresco.
 * It extends the Finding class and inherits its properties and behaviors.
 */
public class Fresco extends Finding{

    /**
     * Constructor for creating a new Fresco finding.
     *
     * @param name        The name of the fresco.
     * @param imagePath   The image path for the fresco.
     * @param points      The points associated with the fresco.
     * @param description A description of the fresco.
     */
    public Fresco(String name, String imagePath, int points, String description){
        super(name, imagePath, points, description);
    }
}
