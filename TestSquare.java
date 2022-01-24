/**
 * Test square program
 * tests square and property class inconjunction with one another
 **/
class TestSquare {
    public static void main(String[] args) {

        // Declare array of board squares
        Square[] boardSquares = new Square[3];

        // Create a test player (testing ownership setter)
        Player testPlayer = new Player("Car", 1500);

        // Insert elements of square/property
        boardSquares[0] = new Square("Go");
        boardSquares[1] = new Property("Old Kent Road", 60, new int[] { 2, 4, 10, 30, 90, 160, 250 }, 50, "brown");
        boardSquares[2] = new Property("White Chapel Road", 60, new int[] { 4, 8, 20, 60, 180, 320, 450 }, 50, "brown");

        // Check set owner for property squares
        for (int i = 0; i <= boardSquares.length-1; i++) {
			if (boardSquares[i] instanceof Property) {
				((Property)boardSquares[i]).setOwner(testPlayer);
				System.out.println(((Property)boardSquares[i]).getOwner());
			}
        }

        // Check remove owner for property squares
        for (int i = 0; i <= boardSquares.length-1; i++) {
			if (boardSquares[i] instanceof Property) {
				((Property)boardSquares[i]).removeOwner();
				System.out.println(((Property)boardSquares[i]).getOwner());
			}
        }

        // Get the name for each one of the squares
        for (int i = 0; i <= boardSquares.length-1; i++) {
            System.out.println(boardSquares[i].getName());
        }
    }
}