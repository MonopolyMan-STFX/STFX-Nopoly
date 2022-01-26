import java.io.*;
import java.util.*;

/**
 * Test cards program
 **/

class TestCard {
    public static void main(String[] args) throws Exception {

        // Create a test player
        Player testPlayer = new Player("Car", 1500);

        // Create arrayList of cards
        ArrayList<Card> deck = new ArrayList<Card>();

        // Connect scanner
        Scanner sc = new Scanner(new FileReader("cards.txt"));

        // Loop through file
        while(sc.hasNextLine()) {
            // Read and parse the line of data
            String[] cardSplitEntry = sc.nextLine().split(",");

            // Creating the card object with the parsed data
            Card cardInstance = new Card(Integer.parseInt(cardSplitEntry[0]), Integer.parseInt(cardSplitEntry[1]), cardSplitEntry[2], Boolean.parseBoolean(cardSplitEntry[3]), cardSplitEntry[4]);

            // Adding it to the arrayList of cards
            deck.add(cardInstance);
        }
       
        // Create random object
        Random rand = new Random();

        // Randomize value of index
        int selectedCard = rand.nextInt(deck.size());

        // Select specific cards in deck for testing
        // selectedCard = 5;

        // Card index selected
        System.out.println("Card Selected: " + selectedCard);

        // If selected card is changing money
        if (deck.get(selectedCard).getChangeMoney() != 0) {
            
            // Depositing money?
            if (deck.get(selectedCard).getChangeMoney() > 0) {
                testPlayer.deposit(deck.get(selectedCard).getChangeMoney());
            }

            // Withdrawing money?
            else if (deck.get(selectedCard).getChangeMoney() < 0) {
                testPlayer.withdraw(Math.abs(deck.get(selectedCard).getChangeMoney()));
            }
        }

        // If selected card is moving player position
        if (deck.get(selectedCard).getMovePosition() != 0) {
            testPlayer.moveUp(deck.get(selectedCard).getMovePosition());
        }

        // If selected card is setting player position
        if (deck.get(selectedCard).getSetPosition().equals("N/A") == false) {
            testPlayer.changePositionDirect(Integer.parseInt(deck.get(selectedCard).getSetPosition()));
        }

        // If selected card is sending player to jail
        if (deck.get(selectedCard).getGoToJail() != false) {
            testPlayer.setIfInJail(true);
            testPlayer.changePositionDirect(10); // Jail position can be determined by dividing board size into 4 pieces
        }

        // Get message and display
        System.out.println(deck.get(selectedCard).getMessage());

        // Check player stats to see changes
        System.out.println("Jail Status: " + testPlayer.checkIfInJail());
        System.out.println("Current Position: " + testPlayer.getPosition());
        System.out.println("Current Balance: " + testPlayer.getBalance());
    }
}