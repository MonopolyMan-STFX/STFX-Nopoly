
/**
 * Square parent class for Monopoly Board
 * @author JM
 * @course ICS4UC
 * @date 2022/01/22
 */
public class Square {
    // Attributes
    protected int position = -1;
    protected String name = "";

    /**
     * toString function
     */
    @Override
    public String toString() {
        return "Square{" +
                "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Constructor
     * @param position
     * @param name
     */
    public Square(int position, String name) {
        this.position = position;
        this.name = name;
    }


    /**
     * Getters
     */
    public int getPosition() {
        return this.position;
    }

    public String name() {
        return this.name;
    }

}