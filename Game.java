/**
 * This is the main game class
 *
 * @author JM
 */


class Game {
    public static void main(String[] args) {
        System.out.println("Hello");
        Square test = new Square(12, "test", "test");
        System.out.println(test.toString());
        System.out.println("\n");


        int[] rent = {50,200, 600, 1400, 1700, 2000};

        // https://monopoly.fandom.com/wiki/Boardwalk
        //
        // Board walk property
        Property boardWalk = new Property(4, "Board Walk", 400, rent, 0, false, 200, 200, "blue" );

        System.out.println(boardWalk.toString());
        // System.out.println(boardWalk.getPosition());



    }
}