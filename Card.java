
/**
 * Community/Chance card class
 **/

public class Card {

    // Attributes
    private int changeMoney;
    private int movePosition;
    private String setPosition;
    private boolean goToJail;
    private String message;

    /**
     * Constructor
     *
     * @param 
     */
    public Card(int changeMoney, int movePosition, String setPosition, boolean goToJail, String message) {
        
        // Set data as attributes
        this.changeMoney = changeMoney;
        this.movePosition = movePosition;
        this.setPosition = setPosition;
        this.goToJail = goToJail;
        this.message = message;
    }

    // Getters
    public int getChangeMoney() {
        return this.changeMoney;
    }

    public int getMovePosition() {
        return this.movePosition;
    }

    public boolean getGoToJail() {
        return this.goToJail;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSetPosition() {
        return this.setPosition;
    }
}