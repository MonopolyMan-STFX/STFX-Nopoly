
import java.util.*;

/**
 * @Date 2022/01/20
 * @Course ICS4UC
 */

class Player
{
    // This could identify the thing on the board, or the name
    // Attributes
    private String name = "";
    private int money = 0;
    private ArrayList<Square> properties = new ArrayList<Square>();
    private int position = 0;
    private boolean inJail = false;
    private int numDoubles = 0;
    private int numDaysInJail = 0;

    /**
     * Constructor
     * @param tokenIn
     * @param moneyIn
     */
    public Player(String tokenIn, int moneyIn)
    {
        this.name = tokenIn;
        this.money = moneyIn;
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
    public boolean withdraw(int moneyOut)
    {
        boolean result;
        if(moneyOut > this.money)
        {
            result = false;
        }
        else
        {
            result = true;
            this.money -= moneyOut;
        }

        return result;
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
    public void moveUp(int moveUp)
    {
        // Checks if it passed go, and adds funds accordingly
        if((this.position + moveUp) > 39)
        {
            this.money += 200;
            this.position = (this.position + moveUp) - 40;
        }
        else
        {
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
    public boolean removeProperty(Square property)
    {
        boolean removed = false;
        Property temporaryProperty = (Property)property;

        //Look for property being removed
        for (int i = 0; i < properties.size(); i++)
        {
          if (properties.get(i).equals(temporaryProperty))
          {
            //Remove Property
            properties.remove(i);
            removed = true;
          }
        }
        return removed;

    }

    public int getPosition() {
        return this.position;
    }

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

    public ArrayList<Square> getAllProperties()
    {
        return properties;
    }
}
