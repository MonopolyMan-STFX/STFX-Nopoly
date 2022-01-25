import java.util.*;

/**
 * Test game program
 **/

class TestGame {
    public static void main(String[] args) {
        // Create test game
        Game game = new Game("squares.txt");

        // Create test players
        game.createPlayer("Joe");
        game.createPlayer("Bob");
        //game.createPlayer("Mark");

        int roll = 0;

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
        Scanner sc = new Scanner(System.in);
        char choice = 'o';
        while(choice != 'q') {
            // Start test game
            System.out.println("\n" + game.getPlayers().get(game.getCurPlayerTurn()).getName()+ "'s turn:");
            roll = game.rollDie();
            System.out.println(Arrays.toString(game.getDiceNumbers()));
            game.playTurn(roll);
            System.out.println("Next turn: " + game.getPlayers().get(game.getCurPlayerTurn()).getName());
            choice = sc.next().toLowerCase().charAt(0);
        }
    }
}