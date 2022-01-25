/**
 * Test player program
 **/
class TestPlayer {
    public static void main(String[] args) {

        // Create a test player
        Player testPlayer = new Player("Car", 1500);

        // Create a test property
        Property testProperty = new Property("Test Property", 200, new int[] { 50, 100, 150, 200, 250, 300 }, 50,
                "Blue");

        // Deposit money and get balance
        testPlayer.deposit(200);
        System.out.println(testPlayer.getBalance());

        // Withdraw money and get balance
        testPlayer.withdraw(100);
        System.out.println(testPlayer.getBalance());

        // Set position and get position
        testPlayer.changePositionDirect(6);
        System.out.println(testPlayer.getPosition());

        // Move player up, get position, and check balance
        testPlayer.moveUp(3);
        System.out.println(testPlayer.getPosition());
        System.out.println(testPlayer.getBalance());

        // Add player property owned
        testPlayer.addProperty(testProperty);

        // Remove player property owned
        // testPlayer.removeProperty();

        // Get name
        System.out.println(testPlayer.getName());

        // Increase number of doubles and get number of doubles
        testPlayer.incrNumDoubles();
        System.out.println(testPlayer.getNumDoubles());

        // Reset number of doubles and get number of doubles
        testPlayer.resetNumDoubles();
        System.out.println(testPlayer.getNumDoubles());

        // Check if in jail
        System.out.println(testPlayer.checkIfInJail());

        // Change attribute if in jail
        testPlayer.setIfInJail(true);
        System.out.println(testPlayer.checkIfInJail());
    }
}