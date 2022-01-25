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
        //game.createPlayer("Man");

        // Testing roll and play turn functions for each player...
        int roll = 0;

        // Loop for 5 turns
        for (int i = 0; i<20; i++) {
            System.out.println("\nPlayer" + (game.getCurPlayerTurn() + 1) + "'s turn");
            roll = game.rollDie();
            System.out.println(Arrays.toString(game.getDiceNumber()));
            game.playTurn(roll);
            System.out.println("Next turn: Player" + (game.getCurPlayerTurn() + 1));
        }
    }
}