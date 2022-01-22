
/**
 * MonopolyBoard class for Board - Created MonopolyBoard Class, added documentation and getters to Property. Added txt file of first row of square in the board game.
 * @author Mr. Monopoly
 * @course ICS4UC
 * @date 2022/01/22
 */

public class MonopolyBoard {

    // change to 40, once yohello is done copying board, and parsing
//    int numOfSquares = 40;
    int numOfSquares = 4;

    // Attributes
    private Square[] boardSquare = new Square[numOfSquares];


    public MonopolyBoard() {

        // to be parsed by hello
        boardSquare[0] = new Square(0,"Go");
        boardSquare[1] = new Property(1, "Old Kent Road",60, new int[] {2,4,10,30,90,160,250}, 50, "brown" );
        boardSquare[2] = new Property(2, "White Chapel Road",60, new int[] {4,8,20,60,180,320,450}, 50, "brown" );
        boardSquare[3] = new Property(3, "The Angel Islington",100, new int[] {6,12,30,90,270,400,550}, 50, "light_blue" );
//        boardSquare[40] = new Property(40, "Boardwalk",400, new int[] {50,200, 600, 1400, 1700, 2000}, 200, "dark_Blue");
    }

    /*
     * Getter
     */

    /**
     * get Property object from name
     * @param name name of property
     * @return property object, use .getRent to do stuff etc.
     */
    public Property getProperty (String name) {
        Property foundProperty = null;
        for(Square square : boardSquare) {
            if(square instanceof Property) {
                Property currentProperty = (Property) square;
                if(currentProperty.getName().equals(name)) {
                    foundProperty = currentProperty;
                }
            }
        }
        return foundProperty;
    }

    public Property getProperty(int index) {
        return (Property) boardSquare[index];
    }


    public boolean isProperty(int index) {
        boolean squareIsProperty = false;
        if (boardSquare[index] instanceof Property) {
            squareIsProperty = true;
        }
        return squareIsProperty;
    }
}
