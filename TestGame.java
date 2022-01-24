/**
 * Test game program
 **/
class TestGame {
    public static void main(String[] args) throws Exception {
        // Create test game
        Game game = new Game();

        // Create test players
        game.createPlayer("Joe");
        game.createPlayer("Bob");
        game.createPlayer("Man");

        // Testing roll and play turn functions for each player...
        int roll = 0;
        System.out.println("\nPlayer" + (game.getCurPlayerTurn() + 1) + "'s turn");
        roll = game.rollDie();
        game.playTurn(roll);
        System.out.println("Next turn: Player" + (game.getCurPlayerTurn() + 1));

        System.out.println("\nPlayer" + (game.getCurPlayerTurn() + 1) + "'s turn");
        roll = game.rollDie();
        game.playTurn(roll);
        System.out.println("Next turn: Player" + (game.getCurPlayerTurn() + 1));

        System.out.println("\nPlayer" + (game.getCurPlayerTurn() + 1) + "'s turn");
        roll = game.rollDie();
        game.playTurn(roll);
        System.out.println("Next turn: Player" + (game.getCurPlayerTurn() + 1));
    }
}