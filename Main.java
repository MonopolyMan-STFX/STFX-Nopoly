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

    // create monopoly board
    Board board = new Board();

    /* Player interaction with board test */
    Player player1 = new Player("car", 1500);

    // move to angel
    player1.moveUp(3);
    System.out.println("Player moves 3: " + board.getSquareName(player1.getPosition()));

    // goes to go, get 200 :)
    player1.moveUp(1);
    System.out.println("Player is on go: " + board.getSquareName(player1.getPosition()));
    System.out.println(player1.getBalance());

    // go to kent, no money earned :(
    player1.moveUp(1);
    System.out.println( "Player is on kent: " + board.getSquareName(player1.getPosition()));

    // test buy kent
    if (board.isProperty(player1.getPosition())) {
        // null no owner yet
        Property currentProperty = board.getProperty(player1.getPosition());
        System.out.println("No one owns kent: " + currentProperty.getOwner());

        // try to withdraw and buy kent
        if(player1.withdraw(currentProperty.getCost())) {
            board.getProperty(player1.getPosition()).setOwner(player1);
        }
    }
    System.out.println("Player 1 aka car owns kent, cost 60 hard earned vbucks: " + board.getProperty("Old Kent Road").getOwner().getToken());
    System.out.println(player1.getBalance());


//        /* Test Board */
//        // Print Board
//        System.out.println(board.toString() + "\n");
//
//        // Test get rent and add house
//        Property testProperty = board.getProperty("Old Kent Road");
//        System.out.println("Old kent road rent: " + testProperty.getRent());
//        testProperty.addHouse();
//        System.out.println("Old Kent road 1 house Rent: " + testProperty.getRent());
//
//        // Is 2nd square a property, use when another player lands on a square
//        System.out.println("Is square 3 a property?: " + board.isProperty(3));
//        // Get property name
//        System.out.println("get Name of square 3: " + board.getProperty(3).getName());
//        System.out.println();
  }
}