import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * CPT - Main
 * @author  Mr. Reid, Craig, Kyle, Aaron, Mr. Monopoly, DN, RL, SA
 */

class Main {
  public static void main(String[] args) {
    // // A change - who did this?
    // JFrame frame = new JFrame("ICS4UC Quad 2");
    // frame.setSize(300, 300);
    // frame.setLocation(5, 5);
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // // Makes a window called hello world
    // JLabel label = new JLabel("Hello world", SwingConstants.CENTER);
    // frame.add(label);

    // frame.show();

    /* Test Game*/
    // Game.main(args);

    /* Test Monopoly Board */

     // create monopoly board
    MonopolyBoard board = new MonopolyBoard();

    // test get rent and add house
    Property testProperty = board.getProperty("Old Kent Road");
    System.out.println("Old kent road rent: " + testProperty.getRent());
    testProperty.addHouse();
    System.out.println("Old Kent road 1 house Rent: " + testProperty.getRent());

    // is 2nd square a property, use when another player lands on a square
    System.out.println("Is sqaure 3 a property?: " + board.isProperty(3));
    // get property name
    System.out.println("get Name of square 3: " + board.getProperty(3).getName());

    
  }
}