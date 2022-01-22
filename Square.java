
/**
 * Square parent class for Monopoly Board
 * 
 * @author Mr. Monopoly
 * @course ICS4UC
 * @date 2022/01/22
 */

public class Square {

  // Attributes
  protected String name = "";

  /**
   * Constructor
   * 
   * @param String name
   */
  public Square(String name) {
    this.name = name;
  }

  /**
   * getName
   * 
   * @return String name
   */
  public String getName() {
    return this.name;
  }

  /**
   * toString function
   * 
   * @return String
   */
  @Override
  public String toString() {
    return "Square{" + "name='" + name + '\'' + '}';
  }
}