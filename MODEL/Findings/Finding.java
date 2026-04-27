package MODEL.Findings;

/**
 * The Finding class represents a general finding in the game, which includes attributes such as
 * the name, image path, points, description, and its collection and destruction status.
 */
public class Finding{
    private final String name;
    private String imagePath;       //Path for the image for display
    private int points;
    private String description;
    private boolean isCollected;
    private boolean isDestroyed;

    /**
     * Constructor for creating a new Finding.
     *
     * @param name        The name of the finding.
     * @param imagePath   The image path for the finding.
     * @param points      The points associated with the finding.
     * @param description A description of the finding.
     */
    public Finding(String name, String imagePath, int points, String description){
        this.name = name;
        this.imagePath = imagePath;
        this.points = points;
        this.description = description;
        this.isCollected = false;
    }

    /**
     * Gets the name of the finding.
     *
     * @return The name of the finding.
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the image path of the finding.
     *
     * @return The image path of the finding.
     */
    public String getImagePath(){
        return imagePath;
    }

    /**
     * Gets the points associated with the finding.
     *
     * @return The points of the finding.
     */
    public int getPoints(){
        return points;
    }

    /**
     * Gets the description of the finding.
     *
     * @return The description of the finding.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Checks whether the finding has been collected.
     *
     * @return True if the finding has been collected, false otherwise.
     */
    public boolean isCollected(){
        return isCollected;
    }

    /**
     * Checks whether the finding has been destroyed.
     *
     * @return True if the finding has been destroyed, false otherwise.
     */
    public boolean isDestroyed(){
        return isDestroyed;
    }

    /**
     * Marks the finding as collected.
     */
    public void collect(){
        this.isCollected = true;
    }

    /**
     * Marks the finding as destroyed.
     */
    public void destroy(){
        this.isDestroyed = true;
    }
}
