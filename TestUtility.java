  /**
 * Test Utility
 **/
class TestUtility {
    public static void main(String[] args) {

      //Test Properties(Temporary just to test getRent until player properties are allowed to take squares)
      Property testProperty = new Property("Water Company", 200, new int[] { 50, 100, 150, 200, 250, 300 }, 50,
                "Blue");

      Property testProperty2 = new Property("Electric Company", 200, new int[] { 50, 100, 150, 200, 250, 300 }, 50,
                "Blue");

        // Create a test players
        Player testPlayer = new Player("player", 1500);

        // Create test utilities
        Utility testUtility = new Utility("Water Company",150,4,10);

        // Get cost
        System.out.println("Utility Cost "+testUtility.getCost());

        // Set the owner and get owner
        testUtility.setOwner(testPlayer);
        //testPlayer.addProperty((Property)(Square)(testUtility));
        System.out.println("Utility Owner: "+testUtility.getOwner());

        // Get rent if Utility owner owns 1 Utility
        testPlayer.addProperty(testProperty);
        System.out.println("Utility Rent when one Utility is owned: "+testUtility.getRent(5));

        //Get rent if Utility owner owns 2 utilities
        testPlayer.addProperty(testProperty2);
        System.out.println("Utility Rent when 2 Utilities are owned: "+testUtility.getRent(5));

        // Remove owner amd get owner
        testUtility.removeOwner();
        System.out.println("Utility Owner: "+testUtility.getOwner());
    }
}