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
            roll = game.rollDie(0, 1);
            System.out.println(" " + Arrays.toString(game.getDiceNumbers()));
            String returnStringGame = game.playTurn(roll);

            // Output options
            System.out.println("GUI Play Action: " + returnStringGame);
            
            // Buy property
            if (returnStringGame.equals("NoOwner")) {
                game.buyProperty();
            }

            // Pay rent
            else if (returnStringGame.equals("PayRent") || returnStringGame.equals("PayTax")) {
                game.pay();
            }

            System.out.println();
            // Output owned properties
            System.out.println("Owned Properties: ");            
            for(Square property : game.getPlayers().get(game.getCurPlayerTurn()).getAllProperties()) {
                System.out.println(property.getName());
            }

            choice = sc.next().toLowerCase().charAt(0);

            if (choice == 's') {
                game.sellProperty("Old Kent Road");
            }

            // End player turn 
            String returnStringEnd = game.endTurn();
            System.out.println("GUI End Action: " + returnStringEnd);
            
            
       }
    }
}