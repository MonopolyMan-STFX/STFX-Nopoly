import java.util.*;
import java.io.*;
/**
 * This is the main game class
 *
 * @author Aaron, Craig, Kyle, Basel
 */

class Game {	
	// Attributes
	// FIXME MUST BE A PROPERTY OTHERWISE OTHER THINGS WILL NOT WORK DUE TO IT BEING A SQAURE
	private ArrayList<Square> board = new ArrayList<Square>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private int dice1 = 0;
	private int dice2 = 0;
	private int curPlayerTurn = 0;	
	// Constructor
	public Game() throws IOException
	{
	  	// Set data as attributes
		  this.fillBoard();
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

	public boolean buyProperty(int player, String propertyName) 
	{
		boolean done = false;
		boolean has_owner = false;
		boolean found = false;

		int location = 0;

		// Look for what property they wanna buy
		while(found == false)
		{
			if(location == this.board.size())
			{
				found = true;
				location = -1;
			}
			else if(this.board.get(location).equals(propertyName))
			{
				found = true;
			}
			else
			{
				location++;
			}
		}

		if(location != -1 && this.board.get(location) instanceof Property)
		{

			if(((Property)this.board.get(location)).getOwner().equals(null))
			{
				int cost = ((Property)this.board.get(location)).getCost();
				if(this.players.get(player).withdraw(cost) == true)
				   {
					   ((Property)this.board.get(location)).setOwner(this.players.get(player));
					   this.players.get(player).addProperty(((Property)this.board.get(location)));
					   done = true;
				   }
			}
		}
		else
		{
			done = false;
		}

		return done;
	}	

	// Parsing the data from sqaures.txt
	public void fillBoard() throws IOException
	{
		Scanner file = new Scanner(new FileReader("squares.txt"));
		int i = 0;



		while(file.hasNextLine())
			{
				i++;
				System.out.println(i);
				String line = file.nextLine();
				String[] split_line = line.split(",");

				if(split_line[1].equals("property"))
					{
						int[] rent_temp = new int[7];
						System.out.println(split_line.length);
						rent_temp[0] = Integer.parseInt(split_line[4]);
						rent_temp[1] = Integer.parseInt(split_line[5]);
						rent_temp[2] = Integer.parseInt(split_line[6]);
						rent_temp[3] = Integer.parseInt(split_line[7]);
						rent_temp[4] = Integer.parseInt(split_line[8]);
						rent_temp[5] = Integer.parseInt(split_line[9]);
						rent_temp[6] = Integer.parseInt(split_line[10]);

						Property temp = new Property(split_line[0],Integer.parseInt(split_line[3]), rent_temp, 50,split_line[2]);

						board.add(new Property(split_line[0],Integer.parseInt(split_line[3]), rent_temp, 50,split_line[2]));
					}
			}
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
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
