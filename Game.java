import java.util.*;
import java.io.*;

/**
 * Main Game Class
 * @author Kyle, Basel, Dan. Mr. MonopolyMan
 * @course ICS4UC
 * @date 2022/02/01
 */

class Game {
    // Attributes
    private int MONEY_TO_WIN = 2000;
    private ArrayList<Square> board = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> deck = new ArrayList<Card>();
    private int dice1 = 0;
    private int dice2 = 0;
    private int curPlayerTurn = 0;
    private String monopolyWinSituation = "";

    // Discuss changing buy house rule to one per turn?... offial rule is too complex, and buying unlimited in one turn is too OP
//    private boolean houseBuiltThisTurn

    // Constructor - default for testing
    public Game() throws IOException {
        // Populate default data - 40 properties
        int[] rent = {10, 20, 30, 40};
        for (int i=0; i<40; i++) {
            board.add(new Property("Prop "+i,100,rent, 50,"GREEN"));
        }
    }

    // Constructor - real one
    public Game(String boardFile, String cardFile) {
        try {
            // Set data as attributes
            this.fillBoard(boardFile);
            this.fillDeck(cardFile);
        }
        catch (Exception e) {
            System.out.println("Failed to read file");
        }
    }

    /**
     * Print property data
     */
    public void printPropertyData(int index) {
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
     * Buy Property
     * @return result of transaction
     */
    public boolean buyProperty() {
        boolean transactionResult = false;

        // handle to check if square is a property
        if(this.board.get(players.get(curPlayerTurn).getPosition()) instanceof Property) {
            // tempProperty points to property in board
            Property tempProperty = (Property) this.board.get(players.get(curPlayerTurn).getPosition());

            // If it has no owner
            if(tempProperty.getOwner() == null) {
                // withdraw the money from player, if successful it will return true and buy
                if(players.get(curPlayerTurn).withdraw(tempProperty.getCost())) {
                    players.get(curPlayerTurn).addProperty(tempProperty);
                    transactionResult = true;
                }
            }
        }
        return transactionResult;
    }

    /**
     * Sell Property
     * @param propertyName name of property to be sold
     * @return result of sale
     */
    public boolean sellProperty(String propertyName) {
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
                    if(tempProperty.getOwner().getName().equals(players.get(curPlayerTurn).getName())) {
                        //Remove property from player
                        players.get(curPlayerTurn).removeProperty(tempProperty);

                        //Give player money
                        players.get(curPlayerTurn).deposit(tempProperty.getCost() / 2);
                        saleResult = true;
                    }
                }
            } 
            else {
                location++;
            }
        }
        return saleResult;
    }

    /**
     * Buy house
     * @param property
     * @return result of sale
     */
    public boolean buyHouse(Property property) {
        // Initialize variables
        boolean result = false;
        int location = 0;
        String searchColour = property.getColour();
        int numColourInSet = 0;
        int numColourOwned = 0;

        // Loop through board
        while(location < this.board.size()) {

            // Check if board square is property
            if(this.board.get(location) instanceof Property) {
                // Increment by one if same colour as search
                if (((Property) this.board.get(location)).getColour().equals(searchColour)) {
                    numColourInSet++;
                }
            }
            location++;
        }

        // Loop through players owned properties
        for (Square tempProperty : players.get(curPlayerTurn).getAllProperties()) {
            // Check if board square is property
            if (tempProperty instanceof Property) {
                // Increment by one if same colour as search
                if (((Property)tempProperty).getColour().equals(searchColour)) {
                    numColourOwned++;
                }
            }
        }
        
        // Check if player owns full set
        if (numColourInSet == numColourOwned) {
            // Try to purchase house
            if (players.get(curPlayerTurn).withdraw(property.getHousesCost())) {
                // Add house
                property.addHouse();
                result = true;
            }
        }
        return result;
    }

    /*
     * Fill board - parsing squares.txt to fill board
     * @param filename
     */
    public void fillBoard(String filename) throws IOException {
        // Set up file input
        Scanner fileIn = new Scanner(new FileReader(filename));

        // Skip the first line because it talks about how to "interpret the data"
        fileIn.nextLine();

        // Loop through file
        while(fileIn.hasNextLine()) {

            String lineToBeParsed = fileIn.nextLine();
            String[] splitLine = lineToBeParsed.split(",");

            if(splitLine[1].equals("property")) {

                int[] rentTemp = new int[7];
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
            else if(splitLine[1].equals("Railroad")) {
                int[] railroadRent = {25,50,100,200};
                board.add(new Property(splitLine[0],200,railroadRent,9999,"black"));
            }
            else if(splitLine[1].equals("Chance") || splitLine[1].equals("Community Chest")) {
                board.add(new CardSquare(splitLine[0]));
            }
            else {
                board.add(new Square(splitLine[0]));
            }
        }
    }

    /*
     * Fill deck - parsing cards.txt to fill deck
     * @param filename
     */
    public void fillDeck(String filename) throws IOException {
        // Connect scanner
        Scanner sc = new Scanner(new FileReader(filename));

        // Loop through file
        while(sc.hasNextLine()) {
            // Read and parse the line of data
            String[] cardSplitEntry = sc.nextLine().split(",");

            // Creating the card object with the parsed data
            Card cardInstance = new Card(Integer.parseInt(cardSplitEntry[0]), Integer.parseInt(cardSplitEntry[1]), cardSplitEntry[2], Boolean.parseBoolean(cardSplitEntry[3]), cardSplitEntry[4]);

            // Adding it to the arrayList of cards
            deck.add(cardInstance);
        }
    }

    /*
     * Play the card
     * @param Player currPlayer
     */
    public void playCard(Card cardDrawn) {

        // If selected card is changing money
        if (cardDrawn.getChangeMoney() != 0) {
            // Depositing money?
            if (cardDrawn.getChangeMoney() > 0) {
                players.get(curPlayerTurn).deposit(cardDrawn.getChangeMoney());
            }

            // Withdrawing money?
            else if (cardDrawn.getChangeMoney() < 0) {
                players.get(curPlayerTurn).withdraw(Math.abs(cardDrawn.getChangeMoney()));
            }
        }

        // If selected card is moving player position
        if (cardDrawn.getMovePosition() != 0) {
            players.get(curPlayerTurn).moveUp(cardDrawn.getMovePosition());
        }

        // If selected card is setting player position
        if (cardDrawn.getSetPosition().equals("N/A") == false) {
            players.get(curPlayerTurn).changePositionDirect(Integer.parseInt(cardDrawn.getSetPosition()));
        }

        // If selected card is sending player to jail
        if (cardDrawn.getGoToJail() != false) {
            setJailStatus(true);
        }
    }

    /*
     * Draw a card
     * @return Card selected
     */
    public Card drawCard() {
        // Create random object
        Random rand = new Random();

        // Randomize value of index
        int selectedCard = rand.nextInt(deck.size());

        return deck.get(selectedCard);
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



        // Calculate and return total
        int total = dice1 + dice2;
        System.out.print("Roll: " + total);
        return total;
    }

    /**
     * Overloaded for testing - fixed dice numbers
     * 
     * @param d1
     * @param d2
     * @return
     */
    public int rollDie(int d1, int d2) {

        // Loaded die for testing
        dice1 = d1;
        dice2 = d2;

        // Calculate and return total
        int total = dice1 + dice2;
        System.out.print("Roll: " + total);
        return total;
    }

    /*
     * Check if rolled doubles
     * @return if double
     */
    public boolean checkDouble() {
        boolean isDouble = false;
        // Check if dice are equal to each other
        if (dice1 == dice2) {
            isDouble = true;
        }
        return isDouble;
    }

    /*
     * Set jail staus
     * @param in jail or not
     */
    public void setJailStatus(boolean status) {
        if (status == true) {
            players.get(curPlayerTurn).setIfInJail(true);
            players.get(curPlayerTurn).changePositionDirect(10);
        }
        else {
            players.get(curPlayerTurn).setIfInJail(false);
        }
    }

    /** 
     * Pay
     * @return amount of money paid
     */
    public int pay() {
        // Set variables
        int num = 0;
        int currentPosition = players.get(curPlayerTurn).getPosition();
        // Check if on property
        if (board.get(currentPosition) instanceof Property) {
            if (players.get(curPlayerTurn).withdraw(((Property)board.get(currentPosition)).getRent())) {
                // Pay owner of property
                ((Property)board.get(currentPosition)).getOwner().deposit(((Property)board.get(currentPosition)).getRent());
                num = ((Property) board.get(currentPosition)).getRent();
            }            
        }
        else {
            // Pay luxury tax
            if (board.get(currentPosition).getName().equals("Luxury Tax")) {
                if (players.get(curPlayerTurn).withdraw(100)) {
                    num = 100;
                }  
            }
            // Pay income tax
            else if (board.get(currentPosition).getName().equals("Income Tax")) {
                if (players.get(curPlayerTurn).withdraw(200)) {
                    num = 200;
                }  
            }
        }
        // Return amount paid (if any)
        return num;
    }

    /*
     * Play turn
     */
    public String playTurn(int roll) {
        String returnString = "";
        
        // Move player if they are not in jail
        if (!players.get(curPlayerTurn).checkIfInJail()) {

            // Move player based on roll
            players.get(curPlayerTurn).moveUp(roll);

            // Testing - output player values
            System.out.println("Money: "+ players.get(curPlayerTurn).getBalance());
            System.out.println("Position: "+ players.get(curPlayerTurn).getPosition());
            System.out.println("Square Name: " + getBoard().get(players.get(curPlayerTurn).getPosition()).getName());

            int currentPosition = players.get(curPlayerTurn).getPosition();
            
            // Player landed on property
            if (board.get(currentPosition) instanceof Property) {

                // Property has no owner
                if(((Property)board.get(currentPosition)).getOwner() == null) {
                    returnString = "NoOwner";
                }

                // Player owns the property
                else if(((Property)board.get(currentPosition)).getOwner() == players.get(curPlayerTurn)) {
                    returnString = "YouAreOwner";
                }

                // Other player owns the property, pay rent
                else {
                    returnString = "PayRent";
                }
            }

            else if (board.get(currentPosition) instanceof Square) {
                
                // If they land on go to jail, send to jail
                if(board.get(currentPosition).getName().equals("Go To Jail")) {
                    returnString = "inJail";
                    setJailStatus(true);
                }

                // Land on chance or community chest
                else if (board.get(currentPosition) instanceof CardSquare) {
                    returnString = "DrawCard";
                }

                // Land on income or luxury tax
                else if (board.get(currentPosition).getName().equals("Luxury Tax") || board.get(currentPosition).getName().equals("Income Tax")) {
                    returnString = "PayTax";
                }

                else {
                    //System.out.println("Free Parking, Just Visiting or Go");
                    returnString = "Nothing";
                }
            }
            // Error
            else {
                //System.out.println("Error");
                returnString = "Error";
            }
        }
        
        // Player is in jail
        else {
            // If it has not been 3 days, increment the amount of days in jail
            if(players.get(curPlayerTurn).getNumDaysInJail() < 2) {
                returnString = "InJail";
                players.get(curPlayerTurn).incrNumDaysInJail();
                
            }
            // Otherwise, reset the amount days in jail, and now they're free
            else {
                returnString = "Free";
                setJailStatus(false);
            }
        }
        
        return returnString;
    }



    public void declareBankruptcy() {
        players.get(curPlayerTurn).declaredBankruptcy();
    }

    /**
     * if a player reaches an amount of money end
     * @return is the gameover
     */
    public boolean gameEndMoney() {
            boolean gameEnd = false;

            for(Player player : players) {
                if(player.getBalance() >= MONEY_TO_WIN) {
                    monopolyWinSituation = "money";
                    gameEnd = true;
                }
            }
            return gameEnd;
    }


    /**
     * check if all but one player is bankrupt
     * @return is the game over
     */
    public boolean gameEndBankrupt() {
            boolean gameEnd = false;

            int bankruptPlayers = 0;
            
            for(Player player : players) {
                if(player.isBankrupt()) {
                    bankruptPlayers++;
                }
            }

            if(bankruptPlayers == players.size()-1) {
                monopolyWinSituation = "bankrupt";
                gameEnd = true;
            }

            return gameEnd;
    }

    /**
     * currentPlayer before game over
     * @return winner of the game
     */
    public Player getWinner() {
        Player winner = null;

        if(monopolyWinSituation.equals("bankrupt")) {
            for (Player player:players) {
                if (!player.isBankrupt()){
                    winner = player;
                }
            }
        }

        else if (monopolyWinSituation.equals("money")) {
            for (Player player:players) {
                if (player.getBalance() >= MONEY_TO_WIN) {
                    winner = player;
                }
            }
        }

        return winner;
    }

    /*
     * End turn
     */
    public String endTurn() {
        System.out.println("New Money: "+ players.get(curPlayerTurn).getBalance());
        String returnString = "EndOfTurn";


        System.out.println("New Money: "+ players.get(curPlayerTurn).getBalance());
        
        if(gameEndBankrupt() || gameEndMoney()) {
            returnString = "GameOver";
        }

        // Rolled a double
        else if (checkDouble() == true) {
            returnString = "Double";
            // Increment number of doubles
            players.get(curPlayerTurn).incrNumDoubles();

            // If player is in jail, reset number of doubles, they are now out and get to roll again
            if (players.get(curPlayerTurn).checkIfInJail() == true) {
                players.get(curPlayerTurn).resetNumDoubles();
                returnString = "Free";
                setJailStatus(false);
            }

            // If player rolls 3 doubles, go to jail
            else if (players.get(curPlayerTurn).getNumDoubles() == 3) {
                returnString = "inJail";
                setJailStatus(true);

                // Next player's turn
                nextPlayerTurn();
            }
        }
        // Next player's turn
        else {
            nextPlayerTurn();
        }
        return returnString;
    }

    /*
     * Next player's turn
     */
    public void nextPlayerTurn() {
        boolean nextPlayerFound = false;

        // Reset number of doubles
        players.get(curPlayerTurn).resetNumDoubles();

        while(!nextPlayerFound) {
            curPlayerTurn++;
            // Reset to first player
            if (curPlayerTurn > players.size()-1) {
            curPlayerTurn = 0;
            }
            if(!players.get(curPlayerTurn).isBankrupt()) {
                nextPlayerFound = true;
            }
        }  
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
    public void createPlayer(String name) {
        players.add(new Player(name, 1500));
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

    public Player getCurrentPlayer() {
        return players.get(curPlayerTurn);
    }


}
