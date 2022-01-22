import java.util.Arrays;

/**
 * Property Child class for Monopoly Square
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
     * @param position position of property on board
     * @param propertyName name of property and square
     * @param propertyCost cost of property
     * @param rentPrices a list of rent prices from no house to hotel
     * @param housesCost how much a house costs to build
     * @param colour the colour group of property
     */
    public Property(int position, String propertyName, int propertyCost, int[] rentPrices, int housesCost, String colour) {
        super(position, propertyName);
        this.cost = propertyCost;
        this.rent = rentPrices;
        this.housesOwned = 0;
        this.housesCost = housesCost;
        this.colour = colour;
    }

    /*
     * Getters
     */
    // Methods for price

    public int getCost() {
        return cost;
    }

    /**
     * get rent based on how many houses owned, 0 = no houses, 1-4 - houses , 5- hotel
     * @return rent cost
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

    /**
     * owner sells property
     */
    public void removeOwner() {
        this.owner = null;
    }

    // Methods for colour groups
    public String getColour() {
        return colour;
    }

    /**
     * toString function
     */
    @Override
    public String toString() {
        return super.toString() + " " + "Property{" +
                "cost=" + cost +
                ", rent=" + Arrays.toString(rent) +
                ", housesOwned=" + housesOwned +
                ", housesCost=" + housesCost +
                ", colour='" + colour + '\'' +
                "} ";
    }


}
