/**
 * Test property program
 **/
class TestProperty {
    public static void main(String[] args) {

        // Create a test player (testing ownership setter)
        Player testPlayer = new Player("Car", 1500);

        // Create test property
        Property testProperty = new Property("Test Property", 200, new int[] { 50, 100, 150, 200, 250, 300 }, 50,
                "Blue");

        // Get cost
        System.out.println(testProperty.getCost());

        // Get rent
        System.out.println(testProperty.getRent());

        // Get cost to add house
        System.out.println(testProperty.getHousesCost());

        // Add a house and get houses on property
        testProperty.addHouse();
        System.out.println(testProperty.getHousesOwned());

        // Set the owner and get owner
        testProperty.setOwner(testPlayer);
        System.out.println(testProperty.getOwner().getName());

        // Remove owner amd get owner
        testProperty.removeOwner();
        System.out.println(testProperty.getOwner());
    }
}