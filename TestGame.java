import java.util.*;

/**
 * Test game program
 */

class TestGame {
    public static void main(String[] args) {
        // Create test game
        Game game = new Game("squares.txt");

        // Create test players
        game.createPlayer("Joe");
        // game.createPlayer("Bob");
        //game.createPlayer("Mark");

        // int roll = 0;

        // Automatic test program
        // Loop for 20 turns
        // for (int i = 0; i<20; i++) {
        //     System.out.println("\nPlayer" + (game.getCurPlayerTurn() + 1) + "'s turn");
        //     roll = game.rollDie();
        //     System.out.println(Arrays.toString(game.getDiceNumbers()));
        //     game.playTurn(roll);
        //     System.out.println("Next turn: Player" + (game.getCurPlayerTurn() + 1));
        // }

        // Input based test program
        // Set up user input
//        Scanner sc = new Scanner(System.in);
//        char choice = 'o';
//        while(choice != 'q') {
//            // Start test game
//            System.out.println("\n" + game.getPlayers().get(game.getCurPlayerTurn()).getName()+ "'s turn:");
//            roll = game.rollDie();
//            System.out.println(Arrays.toString(game.getDiceNumbers()));
//            game.playTurn(roll);
//            System.out.println("Next turn: " + game.getPlayers().get(game.getCurPlayerTurn()).getName());
//            choice = sc.next().toLowerCase().charAt(0);
//        }


        /* Simple Test Buy Property */

        // get Joe player obj, print name
        Player testPlayer = game.getPlayers().get(0);
        System.out.println(testPlayer.getName());

        // where is joe right now and name of sqaure
        System.out.println("Player posIndex : " + testPlayer.getPosition());
        System.out.println( "Player is on : " + game.getBoard().get(0).getName());

        // move joe up 1 to Old Kend Road
        testPlayer.moveUp(1);

        // Joe index in board, and name of square
        System.out.println("Player posIndex : " + testPlayer.getPosition());
        System.out.println( "Player is on : " + game.getBoard().get(testPlayer.getPosition()).getName());

        // How much money joe has
        System.out.println("Joe Balance: " + testPlayer.getBalance());

        // Joe trys to buy Old Kent Road
        if(game.buyProperty(testPlayer)) {
            // Print the owner of old kent road
            System.out.println("Owner is: " + ((Property) game.getBoard().get(testPlayer.getPosition())).getOwner().getName());
        }

        // Print the property joe owns
        System.out.println("Joe's Owned Property: " + testPlayer.getAllProperties().get(0).getName());
    }
}