import java.util.*;
/**
 * Utility Child class for Monopoly Square
 *
 * @author Craig, Aaron
 * @course ICS4UC
 * @date 2022/01/26
 */

public class Utility extends Square {
    // Attributes
    private int cost = 0;
    private int rentMultiplier1 = 0;
    private int rentMultiplier2 = 0;
    private int utilitiesOwned = 0;
    private Player owner = null;
    private ArrayList<Property> ownerProperties = new ArrayList<Property>();

    /**
     * Constructor
     *
     * @param utilityName name of utility and square
     * @param utilityCost cost of utility
     * @param rentMultiplier a list of rent multipler based on properties eyes
     */
    public Utility (String utilityName, int utilityCost, int rentMultiplier1, int rentMultiplier2) {
        super(utilityName);
        this.cost = utilityCost;
        this.rentMultiplier1 = rentMultiplier1;
        this.rentMultiplier2 = rentMultiplier2;
    }

    // Methods for price
    public int getCost() {
        return cost;
    }

    /**
     * getRent 
     * Calculates rent based on dice number & Utilities Owned
     * @return int rent
     */
    public int getRent(int diceNum) {

      //Get list of the owner of this utilites properties
      ownerProperties = owner.getAllProperties();
      //Reset 
      utilitiesOwned = 0;
      //Look through owner's properties and find out how many utilities are owned
       for (int i = 0; i < ownerProperties.size(); i++) {
        if (ownerProperties.get(i).getName().equals("Electric Company")||ownerProperties.get(i).getName().equals("Water Company")){
          utilitiesOwned++;
        }
       }

        int total = 0;

        //If one utility is owned
        if (utilitiesOwned == 1) {
            total = diceNum * rentMultiplier1;
        }

        //If two utilites are owned 
        else if (utilitiesOwned == 2){
            total = diceNum * rentMultiplier2;        
          }

        return total;
    }


    //Methods for ownership
    public int getUtilitiesOwned() {
        return utilitiesOwned;
    }

    public void addUtility() {
        this.utilitiesOwned++;
    }

    //Get utility owner
    public Player getOwner() {
        return owner;
    }

    //Set Utility Owner
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    //Remove Utility Owner
    public void removeOwner() {
        this.owner = null;
    }


}