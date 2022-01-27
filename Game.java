import java.util.*;
import java.io.*;

/**
 * Main Game Class
 */

class Game {
    // Attributes
    private ArrayList<Square> board = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
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

    /**
     * buy Property
     * @param player Player buying property
     * @return result of transaction
     */
    public boolean buyProperty(Player player) {
        boolean transactionResult = false;

        // handle to check if square is a property
        if(this.board.get(player.getPosition()) instanceof Property) {
            // tempProperty points to property in board
            Property tempProperty = (Property) this.board.get(player.getPosition());

            // If it has no owner
            if(tempProperty.getOwner() == null) {
                // withdraw the money from player, if successful it will return true and buy
                if(player.withdraw(tempProperty.getCost())) {
                    player.addProperty(tempProperty);
                    transactionResult = true;
                }
            }
        }
        return transactionResult;
    }

    /**
     * sell Property
     * @param player player selling property
     * @param propertyName name of property to be sold
     * @return result of sale
     */
    public boolean sellProperty(Player player, String propertyName) {
        boolean found = false;
        boolean saleResult = false;
        int location = 0;

        // Look for what property they want to sell
        while(!found && location < this.board.size()) {
            // Find the property
            if(this.board.get(location).getName().equals(propertyName)) {
                found = true;
                // handle to check if square is a property
                if(this.board.get(location) instanceof Property) {
                    // tempProperty points to property in board
                    Property tempProperty = (Property) this.board.get(location);

                    // Check if player owns property
                    if(tempProperty.getOwner().getName().equals(player.getName())) {
                        //Remove property from player - AWAITING FUNCTION IN PLAYER
                        player.removeProperty(tempProperty);

                        //Give player money
                        player.deposit(tempProperty.getCost());
                        saleResult = true;
                    }
                }
            } else location++;
        }
        return saleResult;
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

        // Move up 1 space, no double
        //dice1 = 0;
        //dice2 = 1;

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
    public String playTurn(int roll)
        {

        String returnString = "";

        // Check if their in jail
        if(players.get(curPlayerTurn).checkIfInJail() == true)
        {
            // If they've been in there for 3> days, it just increments the amount of days in jail
            if(players.get(curPlayerTurn).getNumDaysInJail() < 3)
            {
                returnString = "InJail";
                players.get(curPlayerTurn).incrNumDaysInJail();
            }
            // Otherwise reset the amount days in jail, and now they're free
            else
            {
                players.get(curPlayerTurn).resetNumDaysInJail();
                players.get(curPlayerTurn).setIfInJail(false);
                returnString = "Free";
            }
        }
        else
        {
            // Roll the dice
            players.get(curPlayerTurn).moveUp(roll);

            // Get the current position
            int currentPosition = players.get(curPlayerTurn).getPosition();

            // check if the position we're on is a property
            if (board.get(currentPosition) instanceof Property)
            {

                //If there's no owner, tell the gui that
                if(((Property)board.get(currentPosition)).getOwner() == null)
                {
                    System.out.println("NoOwner");
                    returnString = "NoOwner";
                }

                // If the player is the owner, tell the player that
                else if(((Property)board.get(currentPosition)).getOwner() == players.get(curPlayerTurn))
                {
                    System.out.println("YouAreOwner");
                    returnString = "YouAreOwner";
                }

                // Otherwise someone else gotta be the owner so tell the gui that
                else
                {
                    System.out.println("PayRent");
                    returnString = "PayRent";
                }
            }

            // If we are on a Square, return that we are on a Square
            else if (board.get(currentPosition) instanceof Square)
            {
                System.out.println("Square");
                returnString = "Square";
            }
            // else if (board.get(currentPosition) instanceof Jail)
            // {
            //     System.out.println("Roll die, or Pay");
            // }
            // else if(board.get(currentPosition) instanceof Chance)
            // {
            //     System.out.println("You are on a Chance square");
            // }
            else
            {
                // There should be nothing else on the board, so it's probably an error
                System.out.println("ERROR");
                returnString = "ERROR";
            }
        }

        // Return it
        return returnString;
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

            // Check if they have been in jail for 0 to 3 days inclusive
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

    public void printBoard() {
        for(int i = 0; i < board.size(); i++)
        {
            System.out.println("I: " + i + " " + board.get(i).getName() );
        }
    }
}
