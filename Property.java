import java.util.*;

/**
 * Property Child class for Monopoly Square
 * @author JM
 * @course ICS4UC
 * @date 2022/01/22
 */
public class Property extends Square {
    // Attributes
    private String propertyName = "";
    private int cost = 0;
    private int[] rent;
    private int housesOwned = 0;
    private boolean hotelOwned = false;
    private int housesCost = 0;
    private int hotelCost = 0;
    private String colour = "";

    /**
     * Constructor
     * @param position
     * @param name
     */
    public Property(int position, String name, String propertyName, int propertyCost, int[] rentPrices, int housesOwned, boolean hotelOwned, int housesCost, int hotelCost, String colour) {
        super(position, name, "property");
        this.propertyName = propertyName;
        this.cost = propertyCost;
        this.rent = rentPrices;
        this.housesOwned = housesOwned;
        this.hotelOwned = hotelOwned;
        this.housesCost = housesCost;
        this.hotelCost = hotelCost;
        this.colour = colour;
    }

    /**
     * toString function
     */
    @Override
    public String toString() {
        return super.toString() + " " + "Property{" +
                "propertyName='" + propertyName + '\'' +
                ", cost=" + cost +
                ", rent=" + Arrays.toString(rent) +
                ", housesOwned=" + housesOwned +
                ", hotelOwned=" + hotelOwned +
                ", housesCost=" + housesCost +
                ", hotelCost=" + hotelCost +
                ", colour='" + colour + '\'' +
                ", position=" + position +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                "} ";
    }
/**
     * Getters
     */



}