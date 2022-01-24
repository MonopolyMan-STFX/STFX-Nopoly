import java.util.Arrays;

/**
 * Property Child class for Monopoly Square
 *
 * @author Mr. Monopoly
 * @course ICS4UC
 * @date 2022/01/22
 */

public class Property extends Square {
    // Attributes
    private int cost = 0;
    private int[] rent;
    private int housesOwned = 0;
    private int housesCost = 0;
    private String colour = "";
    private Player owner = null;

    /**
     * Constructor
     *
     * @param propertyName name of property and square
     * @param propertyCost cost of property
     * @param rentPrices   a list of rent prices from no house to hotel
     * @param housesCost   how much a house costs to build
     * @param colour       the colour group of property
     */
    public Property(String propertyName, int propertyCost, int[] rentPrices, int housesCost, String colour) {
        super(propertyName);
        this.cost = propertyCost;
        this.rent = rentPrices;
        this.housesOwned = 0;
        this.housesCost = housesCost;
        this.colour = colour;
    }

    // Methods for price
    public int getCost() {
        return cost;
    }

    /**
     * getRent - Based on how many houses owned, 0 = no houses, 1-4 - houses , 5 -
     * hotel
     *
     * @return int rent
     */
    public int getRent() {
        return rent[this.housesOwned];
    }

    public int getHousesCost() {
        return housesCost;
    }

    // Methods for ownership
    public int getHousesOwned() {
        return housesOwned;
    }

    public void addHouse() {
        this.housesOwned++;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void removeOwner() {
        this.owner = null;
    }

    // Methods for colour groups
    public String getColour() {
        return colour;
    }

    /**
     * toString function
     * 
     * @return String
     */
    // To whoever keeps adding @Override, STOP WE DONT NEED IT IT DOESNT MAKE A DIFFERNCE
    public String toString() {
        return super.toString() + " " + "Property{" + "owner=" + owner + ", cost=" + cost + ", rent=" + Arrays.toString(rent) + ", housesOwned="
                + housesOwned + ", housesCost=" + housesCost + ", colour='" + colour + '\'' + "} ";
    }
}
