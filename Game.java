import java.util.*;

/**
 * This is the main game class
 *
 * @author Aaron, Craig, Kyle, Basel
 */

class Game {

    // Attributes
	private ArrayList<String> board = new ArrayList<String>();
    private ArrayList<String> players = new ArrayList<String>();
	private int[] tileInGame;
    private int dice1 = 0;
    private int dice2 = 0;

	// Constructor
    public Game() {
		// Set data as attributes
    } 
    
    /**
     *  toString function
     */
 	public String toString( ) {
 	   String result = "";
 		return result;
   }
  
    /*
    * Roll Die
    * @return total
    */
    public int rollDie(){
        Random rand = new Random();
        // Randomize value of two dice
        dice1 = rand.nextInt(6)+1;
        dice2 = rand.nextInt(6)+1;
        // Calculate and return total
        int total = dice1 + dice2;
        return total;
    }

    /*
    * Check if rolled doubles
    * @return if double
    */
    public boolean checkDouble(){
        boolean isDouble = false;
        if (dice1 == dice2) {
            isDouble = true;
        }
        return isDouble;
    }

    public static void main(String[] args) {

        Game game = new Game();

        // Test roll
        System.out.println("Roll: "+game.rollDie());
        System.out.println("Double: "+game.checkDouble());

        Square test = new Square(12, "test", "test");
        System.out.println(test.toString());
        System.out.println("\n");


        int[] rent = {50,200, 600, 1400, 1700, 2000};

        // https://monopoly.fandom.com/wiki/Boardwalk
        
        // Board walk property
        Property boardWalk = new Property(4, "Board Walk", 400, rent, 0, false, 200, 200, "blue" );

        System.out.println(boardWalk.toString());
        // System.out.println(boardWalk.getPosition());



    }
}