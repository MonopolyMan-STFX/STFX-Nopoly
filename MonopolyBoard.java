import java.util.Arrays;

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
    private Square[] boardSquares = new Square[numOfSquares];

    public MonopolyBoard() {

        // to be parsed by hello
        boardSquares[0] = new Square("Go");
        boardSquares[1] = new Property("Old Kent Road",60, new int[] {2,4,10,30,90,160,250}, 50, "brown" );
        boardSquares[2] = new Property("White Chapel Road",60, new int[] {4,8,20,60,180,320,450}, 50, "brown" );
        boardSquares[3] = new Property("The Angel Islington",100, new int[] {6,12,30,90,270,400,550}, 50, "light_blue" );
//        boardSquare[40] = new Property(40, "Boardwalk",400, new int[] {50,200, 600, 1400, 1700, 2000}, 200, "dark_Blue");
    }

    /**
     * get Property object from name
     * @param name name of property
     * @return property object, use .getRent to do stuff etc.
     */
    public Property getProperty (String name) {
        Property foundProperty = null;
        for(Square square : boardSquares) {
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
        return (Property) boardSquares[index];
    }

    public boolean isProperty(int index) {
        return boardSquares[index] instanceof Property;
    }

    /**
     * toString Function
     */
    @Override
    public String toString() {
        return "MonopolyBoard{" +
                "boardSquares=" + Arrays.toString(boardSquares) +
                '}';
    }
}
