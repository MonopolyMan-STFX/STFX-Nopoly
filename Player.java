import java.util.*;

/**
 * Player Class
 * @author Sourojeet, Kyle
 * @course ICS4UC
 * @date 2022/02/01
 */

class Player {
    
    // Attributes
    private int STARTING_MONEY = 1500;
    private String name = "";
    private int money = 0;
    private ArrayList<Square> properties = new ArrayList<Square>();
    private int position = 0;
    private boolean inJail = false;
    private int numDoubles = 0;
    private int numDaysInJail = 0;
    private boolean isBankrupt = false;


    /**
     * Constructor
     * @param tokenIn
     * @param moneyIn
     */
    public Player(String tokenIn, int moneyIn)
    {
        this.name = tokenIn;
        this.money = STARTING_MONEY;
        this.position = 0;
        this.inJail = false;
        this.numDoubles = 0;
        this.numDaysInJail = 0;
    }

    /**
     * Deposit money into the account
     * @param moneyIn
     */
    public void deposit(int moneyIn)
    {
        this.money += moneyIn;
    }

    /**
     * Withdraws money, if false it failed
     * @param moneyOut
     * @return result
     */
    public boolean withdraw(int moneyOut) {
        boolean result;
        if(moneyOut > this.money) {
            result = false;
            this.isBankrupt = true;
            this.money = 0;
        }
        else {
            result = true;
            this.money -= moneyOut;
        }
        return result;
    }

    /**
     * Is the player bankrupt
     */
    public boolean isBankrupt() {
        return this.isBankrupt;

    }

    /**
     * player decided to declare Bankruptcy
     */
    public void declaredBankruptcy() {
        this.money = 0;
        this.isBankrupt = true;
    }

    /**
     * Returns money
     * @return money
     */
    public int getBalance()
    {
        return this.money;
    }

    /**
     * Moves directly to that tile
     * @param newTile tile to move to
     */
    public void changePositionDirect(int newTile)
    {
        this.position = newTile;
    }

    /**
     * Moves up
     * @param moveUp num of squares to move
     */
    public void moveUp(int moveUp) {
        // Checks if it passed go, and adds funds accordingly
        if((this.position + moveUp) > 39) {
            this.position = (this.position + moveUp) - 40;
            this.money += 200;
        }
        else {
            this.position += moveUp;
        }
    }

    /**
     * Adds the index of a certain property to the ArrayList
     * @param property property object
     */
    public void addProperty(Property property) {
        // set self as owner of property
        property.setOwner(this);
        // Setting owner is done in player class
        properties.add(property);
    }

    /**
     * Searches for the property in a linear fashion, if not found it returns false
     * @param indexOfProperty
     * @return found
     * */
    public boolean removeProperty(Property property) {
        boolean removed = false;

        //Look for property being removed
        for (int i = 0; i < properties.size(); i++) {
          if (properties.get(i).equals(property)) {
            //Remove owner from property
            property.removeOwner();
            // remove property from player owned
            properties.remove(i);
            removed = true;
          }
        }
        return removed;

    }

    /**
     * Get position
     * @return position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Get name
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Increments the number of days in jail
     */
    public void incrNumDaysInJail() {
        this.numDaysInJail++;
    }

    /**
     * Get number of days in jail
     * @return number of days in jail
     */
    public int getNumDaysInJail() {
        return this.numDaysInJail;
    }

    /**
     * Check if the player is in jail
     * @return in jail or not
     */
    public boolean checkIfInJail() {
        return this.inJail;
    }

    /**
     * Set if the player is in jail
     */
    public void setIfInJail(boolean jail) {
        this.inJail = jail;
    }

    /**
     * Increments the number of doubles
     */
    public void incrNumDoubles() {
        this.numDoubles++;
    }

    /**
     * Resets the number of days in jail
     */
    public void resetNumDaysInJail() {
        this.numDaysInJail = 0;
    }

    /**
     * Resets the number of doubles
     */
    public void resetNumDoubles() {
        this.numDoubles = 0;
    }

    /**
     * Get number of doubles
     * @return number of doubles
     */
    public int getNumDoubles() {
        return this.numDoubles;
    }

    /*
    * Get list of all owned properties 
    * @return properties
    */
    public ArrayList<Square> getAllProperties() {
        return properties;
    }
}
