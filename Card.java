/**
 * Community/Chance Card Class
 * @author Dan
 * @course ICS4UC
 * @date 2022/02/01
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
     * @param changeMoney
     * @param movePosition
     * @param setPosition
     * @param goToJail
     * @param message
     */
    public Card(int changeMoney, int movePosition, String setPosition, boolean goToJail, String message) {
        
        // Set data as attributes
        this.changeMoney = changeMoney;
        this.movePosition = movePosition;
        this.setPosition = setPosition;
        this.goToJail = goToJail;
        this.message = message;
    }

    /**
     * Get change in money
     * @return change in money
     */
    public int getChangeMoney() {
        return this.changeMoney;
    }

    /**
     * Get move position
     * @return move position
     */
    public int getMovePosition() {
        return this.movePosition;
    }

    /**
     * Get go to jail
     * @return go to jail
     */
    public boolean getGoToJail() {
        return this.goToJail;
    }

    /**
     * Get message
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Get set position
     * @return set position
     */
    public String getSetPosition() {
        return this.setPosition;
    }
}