
import java.util.*;
import java.io.*;

/**
 * Main Game Class
 * @author Kyle, Basel, Craig, Aaron
 */

class Game {	
	// Attributes
	private ArrayList<Square> board = new ArrayList<Square>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private int dice1 = 0;
	private int dice2 = 0;
	private int curPlayerTurn = 0;	

	// Constructor
	public Game()
	{
        // Populate default data - 40 properties
        int[] rent = {10, 20, 30, 40};
        for (int i=0; i<40; i++) {            
            board.add(new Property("Prop "+i,100,rent, 50,"GREEN"));
        }
    }	

    	// Constructor
	public Game(String filename)
	{
        try {
            // Set data as attributes
            this.fillBoard(filename);
        } 
        catch (Exception e) {
            System.out.println("Failed to read file");
        }
	}	

    public void printPropertyData(int index)
    {
        System.out.println(((Property)this.board.get(index)));
    }

    /**
    * toString function
    */
    public String toString() {
        String result = "";
        return result;
    }

    // Buy current property
    // Do you want to sell?
    // Buy house

    public boolean buyProperty(int player, String propertyName) 
	{
		boolean done = false;
		boolean has_owner = false;
		boolean found = false;

		int location = 0;

		// Look for what property they wanna buy
		while(found == false)
		{
            // If it gets to the end and it doesnt find it
			if(location == this.board.size())
			{
				found = true;
				location = -1;
			}
            // Once it's found end it
			else if(this.board.get(location).getName().equals(propertyName))
			{
				found = true;
			}
            // Otherwise keep looking through it
			else
			{
				location++;
			}
		}

        // If it does actually exist, and the location is an actual property
		if(location != -1 && this.board.get(location) instanceof Property)
		{

            // If it has no owner
			if(((Property)this.board.get(location)).getOwner() == null)
			{
                // Get the cost, and put it into the variable ``cost``
                int cost = ((Property)this.board.get(location)).getCost();

                // withdraw the money from player, if successful it will return true
                if(this.players.get(player).withdraw(cost) == true)
                {
                    // Set the owner of the property
                    ((Property)this.board.get(location)).setOwner(this.players.get(player));
                    // Adding the Property to player
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
	public void fillBoard(String filename) throws IOException {
		// Set up file input
        Scanner fileIn = new Scanner(new FileReader(filename));
		int i = 0;
        // Skip the first line because it talks about how to "interpret the data"
        fileIn.nextLine();
        // Loop through file
		while(fileIn.hasNextLine()) {
				i++;
				//System.out.println(i);
				String lineToBeParsed = fileIn.nextLine();
				String[] splitLine = lineToBeParsed.split(",");

				if(splitLine[1].equals("property")) {
						int[] rentTemp = new int[7];
						//System.out.println(splitLine.length);
						rentTemp[0] = Integer.parseInt(splitLine[4]);
						rentTemp[1] = Integer.parseInt(splitLine[5]);
						rentTemp[2] = Integer.parseInt(splitLine[6]);
						rentTemp[3] = Integer.parseInt(splitLine[7]);
						rentTemp[4] = Integer.parseInt(splitLine[8]);
						rentTemp[5] = Integer.parseInt(splitLine[9]);
						rentTemp[6] = Integer.parseInt(splitLine[10]);

						// Property temp = new Property(splitLine[0],Integer.parseInt(splitLine[3]), rent_temp, 50,splitLine[2]);

						board.add(new Property(splitLine[0],Integer.parseInt(splitLine[3]), rentTemp, 50,splitLine[2]));
				}
                else 
                {
                    board.add(new Square(splitLine[0]));
                }
		}
    }

    /*
    * Roll Die
    * @return total
    */
    public int rollDie() {
        Random rand = new Random();
        // Randomize value of two six-sided dice
        dice1 = rand.nextInt(6)+1;
        dice2 = rand.nextInt(6)+1;
        // Two-sided dice to test doubles 
        //dice1 = rand.nextInt(2)+1;
        //dice2 = rand.nextInt(2)+1;
        // Calculate and return total
        int total = dice1 + dice2;
        System.out.println("Roll: " + total);
        return total;
    }

    /*
    * Check if rolled doubles
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
        // Move player if they are not in jail
        if (players.get(curPlayerTurn).checkIfInJail() == false) {
            // Move player based on roll, output position
            players.get(curPlayerTurn).moveUp(roll);
            
            // System.out.println("Position: "+ (players.get(curPlayerTurn).getPosition() + 1));

            System.out.println("Position: "+ players.get(curPlayerTurn).getPosition());
            System.out.println("Square Name: " + getBoard().get(players.get(curPlayerTurn).getPosition()).getName());

            // If they land on go to jail, send to jail
            if (players.get(curPlayerTurn).getPosition() == 30) {
                System.out.println("(!) JAIL");
                players.get(curPlayerTurn).setIfInJail(true);
                players.get(curPlayerTurn).changePositionDirect(10);
            }
            // TO-DO Buy the property here
        }
        // End of turn
        endTurn();
    }
    
    /*
    * End turn
    */
    public void endTurn() {
        // Rolled a double
        if (checkDouble() == true) {
            System.out.println("DOUBLE");       
            // Increment number of doubles
            players.get(curPlayerTurn).incrNumDoubles();
            
            // If player is in jail, reset number of doubles, they are now out and get to roll again
            if (players.get(curPlayerTurn).checkIfInJail() == true) {
                System.out.println("OUT OF JAIL");
                players.get(curPlayerTurn).setIfInJail(false);
                players.get(curPlayerTurn).resetNumDoubles();
            }

            // If player rolls 3 doubles, go to jail
            else if (players.get(curPlayerTurn).getNumDoubles() == 3) {
                System.out.println("(!) JAIL");
                players.get(curPlayerTurn).setIfInJail(true);
                players.get(curPlayerTurn).changePositionDirect(10);
                
                // Next player's turn
                nextPlayerTurn();
            }
        }
        // Check if player is currently in jail
        else if (players.get(curPlayerTurn).checkIfInJail() == true){
            players.get(curPlayerTurn).incrNumDaysInJail();
            
            // Check if they have been in jail for 4 days (technically 3) 
            if ((players.get(curPlayerTurn).getNumDaysInJail() == 4)) {
                System.out.println("OUT OF JAIL");
                players.get(curPlayerTurn).setIfInJail(false);
                players.get(curPlayerTurn).resetNumDaysInJail();
            }
            // Next player's turn
            nextPlayerTurn();
        }

        // Next player's turn
        else {
            nextPlayerTurn();
        }
    }

    /*
    * Next player's turn
    */
    public void nextPlayerTurn() {
        // Next player's turn
        curPlayerTurn++;
        // Reset to first player
        if (curPlayerTurn > players.size()-1) {
            curPlayerTurn = 0;
        }
        // Reset number of doubles
        players.get(curPlayerTurn).resetNumDoubles();
    }

    /*
    * Get current player turn
    * @return player
    */
    public int getCurPlayerTurn() {
        return curPlayerTurn;
    }

    /*
    * Create new player
    * @param player name
    */
    public void createPlayer(String token) {
        players.add(new Player(token, 1500));
    }

    /*
    * Get players
    * @return players
    */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /*
     * Get dice numbers
     * @return dice1 and dice2
     */
    public int[] getDiceNumbers() {
        return new int[] {dice1, dice2};
    }
    
    /*
    * Get board
    * @return board
    */
    public ArrayList<Square> getBoard() {
        return board;
    }	

    public void printBoard()
        {
            for(int i = 0; i < board.size(); i++)
                {
                    System.out.println("I: " + i + " " + board.get(i).getName() );
                }
        }
}
