import java.util.*;

/**
 * This is the main game class
 *
 * @author Aaron, Craig, Kyle, Basel
 */

class Game {

  // Attributes
  private ArrayList<String> board = new ArrayList<String>();
  private ArrayList<Player> players = new ArrayList<Player>();
  private int dice1 = 0;
  private int dice2 = 0;
  private int curPlayerTurn = 0;

  // Constructor
  public Game() {
    // Set data as attributes
  }

  /**
   * toString function
   */
  public String toString() {
    String result = "";
    return result;
  }

  /*
   * Roll Die
   * 
   * @return total
   */
  public int rollDie() {
    System.out.println("Rolling...");
    Random rand = new Random();
    // Randomize value of two dice
    // dice1 = rand.nextInt(6)+1;
    // dice2 = rand.nextInt(6)+1;
    dice1 = rand.nextInt(2) + 1;
    dice2 = rand.nextInt(2) + 1;
    // Calculate and return total
    int total = dice1 + dice2;
    System.out.println("Roll: " + total);
    return total;
  }

  /*
   * Check if rolled doubles
   * 
   * @return if double
   */
  public boolean checkDouble() {
    boolean isDouble = false;
    if (dice1 == dice2) {
      isDouble = true;
    }
    return isDouble;
  }

  /*
   * Play turn
   */
  public void playTurn(int roll) {

    // Move player

    // Buy current property
    // Do you want to sell?
    // Buy house

    if (checkDouble() == true) {
      playTurn(rollDie());
    }

    curPlayerTurn++;
    if (curPlayerTurn > players.size()) {
      curPlayerTurn = 0;
    }
  }

  /*
   * Get current player turn
   * 
   * @return player
   */
  public int getCurPlayerTurn() {
    return curPlayerTurn;
  }

  /*
   * Create new player
   * 
   * @param player name
   */
  public void createPlayer(String name) {
    players.add(new Player(name, 1500));
  }

  public static void main(String[] args) {
    int roll;
    // Create test game
    Game game = new Game();
    // Create test players
    game.createPlayer("Joe");
    game.createPlayer("Bob");

    // Testing roll and play turn functions for each player...
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