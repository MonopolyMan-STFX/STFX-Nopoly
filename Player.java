//import com.sun.tools.javac.Main;

import java.util.*;

/**
 * @author Mr. Monopoly
 * @Date 2022/01/20
 * @Course ICS4UC
 */

class Player
{
    // This could identify the thing on the board, or the name
    // Attributes
    private String name = "";
    private int money = 0;
    private ArrayList<Property> properties = new ArrayList<Property>();
    private int position = 0;
    private boolean inJail = false;
    private int numDoubles = 0;

    /**
     * Constructor
     * @param tokenIn
     * @param moneyIn
     */
    public Player(String tokenIn, int moneyIn)
    {
        this.name = tokenIn;
        this.money = moneyIn;
    }

    /**
     * deposit money into the account
     * @param moneyIn
     */
    public void deposit(int moneyIn)
    {
        money += moneyIn;
    }

    /**
     * Withdraws money, if false it failed
     * @param moneyOut
     * @return result
     */
    public boolean withdraw(int moneyOut)
    {
        boolean result;
        if(moneyOut > money)
        {
            result = false;
        }
        else
        {
            result = true;
            money -= moneyOut;
        }

        return result;
    }

    /**
     * Returns money
     * @return money
     */
    public int getBalance()
    {
        return money;
    }

    /**
     * Moves directly to that tile
     * @param newTile tile to move to
     */
    public void changePositionDirect(int newTile)
    {
        position = newTile;
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
            money += 200;
            this.position = (this.position + moveUp) - 39;
        }
        else
        {
            this.position += moveUp;
        }
    }

    /**
     * Adds the index of a certain property to the ArrayList
     * @param indexOfProperty
     * */
    public void addProperty(Property indexOfProperty)
    {
        properties.add(indexOfProperty);
    }

    /**
     * Searches for the property in a linear fashion, if not found it returns false
     * @param indexOfProperty
     * @return found
     * */
    public boolean removeProperty(int indexOfProperty)
    {
        // Search through the array in a linear fashion, and remove the value
        boolean found = false;
        // int i = 0;
        // while(found == false)
        //     {

        //         // When found, stop searching
        //         if(properties.get(i) == indexOfProperty)
        //             {
        //                 found = true;
        //             }
        //         else
        //             {
        //                 i++;
        //             }

        //         // If not found, exit and give up
        //         if(i == properties.size())
        //             {
        //                 found = true;
        //                 i = -1;
        //             }
        //     }

        // // Checks if it was not found by the end, and sets found to false to be returned
        // if(i == -1)
        //     {
        //         found = false;
        //     }
        // // Otherwise remove that property
        // else
        //     {
        //         properties.remove(i);
        //     }

        return found;
    }

    public int getPosition() {
        return position;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * Increments the number of doubles
     */
    public void incrNumDoubles() {
        this.numDoubles++;
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
}
