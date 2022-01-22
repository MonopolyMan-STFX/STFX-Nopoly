
/**
 * Square parent class for Monopoly Board
 * @author Mr. Monopoly
 * @course ICS4UC
 * @date 2022/01/22
 */

public class Square {
    // Attributes
    protected String name = "";

    /**
     * Constructor
     * @param name name of the square
     */
    public Square(String name) {
        this.name = name;
    }

    /* Getters */
    /**
     * getName
     * @return the name of the square
     */
    public String getName() {
        return this.name;
    }

    /**
     * toString function
     */
    @Override
    public String toString() {
        return "Square{" +
                "name='" + name + '\'' +
                '}';
    }
}