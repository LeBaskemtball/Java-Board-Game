package MODEL.Findings;

/**
 * The Statue class represents a specific type of finding: a statue.
 * It extends the Finding class and inherits its properties and behaviors.
 */
public class Statue extends Finding{

    /**
     * Constructor for creating a new Statue finding.
     *
     * @param name        The name of the statue.
     * @param imagePath   The image path for the statue.
     * @param points      The points associated with the statue.
     * @param description A description of the statue.
     */
    public Statue(String name, String imagePath, int points, String description){
        super(name, imagePath, points, description);
    }
}
