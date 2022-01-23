import com.sun.tools.javac.Main;

import java.util.*;

/**
 * @author SA, Mr. Monopoly
 * @Date 2022/01/20
 * @Course ICS4UC
 */


class Player
{
    // This could identify the thing on the board, or the name
    // Attributes
    private String playerToken = "";
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
        this.playerToken = tokenIn;
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
        if((position + moveUp) > (Board.BOARD_SIZE - 1))
        {
            money += 200;
            position = (position + moveUp) - Board.BOARD_SIZE;
        }
        else
        {
            position += moveUp;
        }
    }

    /**
     * Adds the index of a certain property to the ArrayList
     * @param indexOfProperty
     * */
    public void addProperty(int indexOfProperty)
    {
        // properties.add(indexOfProperty);
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

    public String getToken()
    {
        return this.playerToken;
    }
}
