import java.util.*;

/**
 * Test game program
 */

class TestGame {
    public static void main(String[] args) {
        // Create test game
        Game game = new Game("squares.txt", "cards.txt");

        // Create test players
        game.createPlayer("Joe");
        game.createPlayer("Bob");
        //game.createPlayer("Mark");

        int roll = 0;

        /* Input based test program */
        // Set up user input 
        Scanner sc = new Scanner(System.in);
        char choice = 'o';

        // Loop until quit
        while(choice != 'q') {
            
            // Output player turn
            System.out.println("\n" + game.getPlayers().get(game.getCurPlayerTurn()).getName()+ "'s turn:");
            
            // Roll dice and play turn
            roll = game.rollDie();
            System.out.println(" " + Arrays.toString(game.getDiceNumbers()));
            String returnStringGame = game.playTurn(roll);

            // Output options
            System.out.println("GUI Action: " + returnStringGame);
            
            // Buy property
            if (returnStringGame.equals("NoOwner")) {
                game.buyProperty(game.getPlayers().get(game.getCurPlayerTurn()));
            }

            // Pay rent
            else if (returnStringGame.equals("PayRent") || returnStringGame.equals("PayTax")) {
                game.pay();
            }

            System.out.println();
            // Output owned properties
            System.out.println("Owned Properties: ");            
            for(Property property : game.getPlayers().get(game.getCurPlayerTurn()).getAllProperties()) {
                System.out.println(property.getName());
            }
            
            
            choice = sc.next().toLowerCase().charAt(0);

            // End player turn 
            game.endTurn();
       }
       

        /* Simple Test Buy Property */

        // // get Joe player obj, print name
        // Player testPlayer = game.getPlayers().get(0);
        // System.out.println(testPlayer.getName());

        // // where is joe right now and name of sqaure
        // System.out.println("Player posIndex : " + testPlayer.getPosition());
        // System.out.println( "Player is on : " + game.getBoard().get(0).getName());

        // // move joe up 1 to Old Kend Road
        // testPlayer.moveUp(1);

        // // Joe index in board, and name of square
        // System.out.println("Player posIndex : " + testPlayer.getPosition());
        // System.out.println( "Player is on : " + game.getBoard().get(testPlayer.getPosition()).getName());

        // // How much money joe has
        // System.out.println("Joe Balance: " + testPlayer.getBalance());

        // // Joe trys to buy Old Kent Road
        // if(game.buyProperty(testPlayer)) {
        //     // Print the owner of old kent road
        //     System.out.println("Owner is: " + ((Property) game.getBoard().get(testPlayer.getPosition())).getOwner().getName());
        // }

        // // Print the property joe owns
        // System.out.println("Joe's Owned Property: " + testPlayer.getAllProperties().get(0).getName());

        /*
        //Test sell/remove property
        Player testPlayer = game.getPlayers().get(1);
        System.out.println(testPlayer.getName());

        //where is Bob right now and name of sqaure
        System.out.println("Player posIndex : " + testPlayer.getPosition());
        System.out.println( "Player is on : " + game.getBoard().get(0).getName());

        //move Bob up 1 to Old Kend Road
        testPlayer.moveUp(1);

        //Bob position in board and name of tiles
        System.out.println("Player posIndex : " + testPlayer.getPosition());
        System.out.println( "Player is on : " + game.getBoard().get(testPlayer.getPosition()).getName());

        //How much money Bob has
        System.out.println("Bob Balance: " + testPlayer.getBalance());

        //Bob trys to buy Old Kent Road
        if(game.buyProperty(testPlayer)) {
             // Print the owner of old kent road
             System.out.println("Owner is: " + ((Property) game.getBoard().get(testPlayer.getPosition())).getOwner().getName());
             System.out.println("Bob Balance: " + testPlayer.getBalance());
         }

         //Remove Old Kent Road from Bob's properties
         game.sellProperty(testPlayer, "Old Kent Road");
         //Remove Properties Test
         //testPlayer.removeProperty((Property) game.getBoard().get(testPlayer.getPosition()));

        //Print the property Bob owns
         System.out.println("Bob's Owned Property: " + testPlayer.getAllProperties().size());
         System.out.println("Bob Balance: "+testPlayer.getBalance());*/
    }
}