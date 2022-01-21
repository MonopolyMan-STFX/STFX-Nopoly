import java.util.*;

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
     * @param position
     * @param name
     */
    public Property(int position, String propertyName, int propertyCost, int[] rentPrices, int housesOwned, boolean hotelOwned, int housesCost, int hotelCost, String colour) {
        super(position, propertyName);
        this.cost = propertyCost;
        this.rent = rentPrices;
        this.housesOwned = 0;
        this.housesCost = housesCost;
        this.colour = colour;
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
    /**
     * Getters
     */






}
