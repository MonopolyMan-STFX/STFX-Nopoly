
/**
 * Square parent class for Monopoly Board
 * @author Mr. Monopoly
 * @course ICS4UC
 * @date 2022/01/22
 */
public class Square {
    // Attributes   
    protected int position = -1;
    protected String name = "";

    /**
     * Constructor
     * @param position position of the square
     * @param name name of the square
     */
    public Square(int position, String name) {
        this.position = position;
        this.name = name;
    }

    /*
     * Getters
     */
    public int getPosition() {
        return this.position;
    }

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
                "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }
}