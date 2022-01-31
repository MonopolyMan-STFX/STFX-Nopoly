import java.io.*;
import java.util.*;

/**
 * Test cards in conjunction with Game class and CardSquare class
 **/

class TestCard {
    public static void main(String[] args) throws Exception {
        // Set up user input 
        Scanner sc = new Scanner(System.in);

        // Create test game
        Game game = new Game("squares.txt", "cards.txt");

        // Create a test player
        game.createPlayer("Car");

        // Declare variable
        boolean done = false;

        // Does playing turn return DrawCard string?
        if (game.playTurn(2).equals("DrawCard")) {
            
            // Draw a card
            Card cardDrawn = game.drawCard();

            // Display card drawn message
            System.out.println(cardDrawn.getMessage());

            // Wait until done
            while (done!= true) {

                // Take user input
                String userInput = sc.nextLine();

                // User confirms
                if (userInput.equals("ok")) {
                    
                    // Play the card
                    game.playCard(cardDrawn);
                    done = true;
                }
                else {
                    System.out.println("Please enter ok to confirm.");
                }
            }
        }

        // Player stats
        System.out.println("Balance: " + game.getPlayers().get(game.getCurPlayerTurn()).getBalance());
        System.out.println("Position: " + game.getPlayers().get(game.getCurPlayerTurn()).getPosition());
        System.out.println("Jail Status: " + game.getPlayers().get(game.getCurPlayerTurn()).checkIfInJail());
    }
}