

/**
 * Test property program
 **/
class TestSquareAndProperty {
    // Attributes
    private static Square[] boardSquares = new Square[3];

    public static void setupBoard() {
        boardSquares[0] = new Square("Go");
        boardSquares[1] = new Property("Old Kent Road", 60, new int[] { 2, 4, 10, 30, 90, 160, 250 }, 50, "brown");
        boardSquares[2] = new Property("White Chapel Road", 60, new int[] { 4, 8, 20, 60, 180, 320, 450 }, 50, "brown");
       
    }

    public static void main(String[] args) {



    }


}