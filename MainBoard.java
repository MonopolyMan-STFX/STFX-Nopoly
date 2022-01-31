                                                  import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
/**
 * Overall GUI for the Monopoly board
 **/
public class MainBoard extends JFrame implements ActionListener, MouseListener {

    JLabel label1 = null;

    int tileWidth = 60;
    int tileHeight = 100;
    int houseLocation = 0; 

    JPanel playerStatPanel = null;
    JPanel[] playerStats = new JPanel[4];
    JLabel[] playerIcons = new JLabel[4];
    ImageIcon[] listOfIcons = new ImageIcon[4];
    ImageIcon[] diceIcons = new ImageIcon[7];

    JPanel rollPanel = null;
    JButton rollButton = null;
    Timer rollTimer = null;
    JLabel rollLabel1 = null;
    JLabel rollLabel2 = null;

    JPanel buyPropertyPanel = null;
    JButton buyPropertyButton = null;
    JButton passButton = null;
    Timer buyPropertyTimer = null;

    JPanel rentPanel = null;
    JButton rentButton = null;
    JButton bankruptButton = null;
    JLabel oweLabel = null;

    JPanel endTurnPanel = null;
    JButton endTurnButton = null;
    JButton addHouseButton = null;

    JPanel gameOverPanel = null;
    JLabel winnerLabel = null;

    // All the squares on the board
    ArrayList<JPanel> gamePositions = null;


    // Attributes linking to the game
    Game monopoly = null;
    ArrayList<Square> board = null;
    ArrayList<Player> players = null;

    /**
     * These functions create the panels for the board pieces
     **/
    public JPanel makePropertyPanelAcross(Property sqr) {
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(null);
            tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tempPanel.setBackground(Color.WHITE);
            tempPanel.addMouseListener(this);

            JPanel housePanel = new JPanel();
            housePanel.setLayout(null);
            housePanel.setBorder(BorderFactory.createLineBorder(Color.black));
            if(sqr.getColour().equals("brown"))
            {
                housePanel.setBackground(Color.lightGray);
            }
            else if(sqr.getColour().equals("light_blue"))
            {
                housePanel.setBackground(Color.BLUE);
            }
            else if(sqr.getColour().equals("red"))
            {
                housePanel.setBackground(Color.RED);
            }
            else if(sqr.getColour().equals("yellow"))
            {
                housePanel.setBackground(Color.YELLOW);
            }
            else 
            {
                 housePanel.setBackground(Color.BLACK);
            }
            // else
            // {
            //     housePanel.setBackground(Color.GREEN);
            // }           
            housePanel.setBounds(0,0,tileWidth, 20);
            tempPanel.add(housePanel);
            // Nick's code
            if (sqr.getHousesOwned() < 5)  { 
            for (int i = 0;  i < sqr.getHousesOwned(); i++) { 

               ImageIcon img1 = new ImageIcon("graphics/House.png");
               JLabel label1 = new JLabel(img1);
               int width1 = img1.getIconWidth();
               int height1 = img1.getIconHeight();
               label1.setBounds(i * 15, 0, width1 , height1 );
               housePanel.add(label1);
                houseLocation += 20;
            }
            } else  {

                ImageIcon img2 = new ImageIcon("graphics/Hotel.png");
                JLabel label2 = new JLabel(img2);
                int width2 = img2.getIconWidth();
                int height2 = img2.getIconHeight();
                label2.setBounds(0, 0, width2, height2);
                housePanel.add(label2);
            }

            // Add the Name across
            Label tempLabel = new Label(sqr.getName());
            tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
            tempLabel.setBounds(2,20,tileWidth-4,20);
            tempPanel.add(tempLabel);

            // Add the cost across
            Label templabel = new Label(""+sqr.getCost());
            tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
            templabel.setBounds(2,80,tileWidth-4,20);
            tempPanel.add(templabel);

            
            return tempPanel;
    }



    public JPanel makeSquarePanelAcross(Square sqr) {
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(null);
            tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tempPanel.setBackground(Color.WHITE);
            //tempPanel.addMouseListener(this);

            JPanel housePanel = new JPanel();
            housePanel.setLayout(null);
            housePanel.setBorder(BorderFactory.createLineBorder(Color.black));

            // Add the Name across
            Label tempLabel = new Label(sqr.getName());
            tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
            tempLabel.setBounds(2,20,tileWidth-4,20);
            tempPanel.add(tempLabel);
            
            return tempPanel;
    }

    public JPanel makePropertyPanelSide(Property sqr) {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder (BorderFactory.createLineBorder (Color.black));
        tempPanel.setBackground(Color.WHITE);
        tempPanel.addMouseListener(this);

        // add the name side
        Label tempLabel = new Label(sqr.getName());
        tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
        tempLabel.setBounds(22,1,tileWidth-4,20);
        tempPanel.add(tempLabel);

        //add the cost side
        Label templabel = new Label(""+sqr.getCost());
        tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
        templabel.setBounds(22,49,tileWidth,10);
        tempPanel.add(templabel);

            
        JPanel housePanel = new JPanel();
        housePanel.setLayout(null);
        housePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        if(sqr.getColour().equals("pink"))
        {
            housePanel.setBackground(Color.PINK);
        }
        else if(sqr.getColour().equals("orange"))
        {
            housePanel.setBackground(Color.ORANGE);
        }
        else if(sqr.getColour().equals("green"))
        {
            housePanel.setBackground(Color.GREEN);
        }
        else if(sqr.getColour().equals("blue"))
        {
            housePanel.setBackground(Color.BLUE);
        }
        else 
        {
            housePanel.setBackground(Color.BLACK);
        }
        housePanel.setBounds(0,0,20,tileWidth);
        tempPanel.add(housePanel);

        //Nick's code   
        if (sqr.getHousesOwned() < 5)  {    
            for (int i = 0;  i < sqr.getHousesOwned(); i++) { 
                
            
                ImageIcon img1 = new ImageIcon("graphics/House.png");
                JLabel label1 = new JLabel(img1);
                int width1 = img1.getIconWidth();
                int height1 = img1.getIconHeight();
                label1.setBounds(0, i * 15, width1 , height1 );
                housePanel.add(label1); 
                houseLocation += 20;
            }
        }  else {
            ImageIcon img2 = new ImageIcon("graphics/Hotel.png");
            JLabel label2 = new JLabel(img2);
            int width2 = img2.getIconWidth();
            int height2 = img2.getIconHeight();
            label2.setBounds(0, 0, width2, height2);
            housePanel.add(label2);
        } 
        return tempPanel;
    }


    public JPanel makeSquarePanelSide(Square sqr) {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder (BorderFactory.createLineBorder (Color.black));
        tempPanel.setBackground(Color.WHITE);
        //tempPanel.addMouseListener(this);

        // add the name side
        Label tempLabel = new Label(sqr.getName());
        tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
        tempLabel.setBounds(22,1,tileWidth-4,20);
        tempPanel.add(tempLabel);

        return tempPanel;
    }


    /**
     * Make the corner panel
     * @param text
     * @param imagefile
     * @return
     */
    public JPanel makeCornerPanel(String text, String imagefile) {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempPanel.setBackground(Color.WHITE);
        tempPanel.addMouseListener(this);
        // Add the Name
        JLabel tempLabel = new JLabel(text);
        tempLabel.setFont(new Font("Arial", Font.BOLD, 15));
        tempLabel.setBounds(18,0,tileHeight-2,15);
        tempPanel.add(tempLabel);
         
         ImageIcon img3 = new ImageIcon(imagefile);
         JLabel label3 = new JLabel  (img3);
         int width3 =  img3.getIconWidth();
         int height3 = img3.getIconHeight();
         label3.setBounds(28,20, width3, height3);
         tempPanel.add(label3);


        return tempPanel;
}

/**
     * This is for the Player Stats in the middle
     **/
    public JPanel makePlayerPanel() {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempPanel.setBackground(Color.WHITE);

        // Add the Title
        JLabel tempLabel = new JLabel("Player Panel", SwingConstants.CENTER);
        tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tempLabel.setBounds(2,0,250,25);
        tempPanel.add(tempLabel);

        // player panel name and info
        for (int i=0; i<players.size(); i++) {                
            // JPanel for the player Stats
            playerStats[i] = new JPanel();
            playerStats[i].setLayout(null);
            if (monopoly.getCurPlayerTurn() == i) {
                playerStats[i].setBorder(BorderFactory.createLineBorder(Color.black));
            }
            playerStats[i].addMouseListener(this);
            playerStats[i].setBackground(Color.WHITE);

            // Add Player information
            Player p = players.get(i);
            tempLabel = new JLabel(""+p.getName()+"     $"+p.getBalance(), SwingConstants.LEFT);
            tempLabel.setFont(new Font("Arial", Font.BOLD, 15));
            tempLabel.setBounds(0,0,200,25);
            playerStats[i].add(tempLabel);

            // Add the player icons
            JLabel labelName = new JLabel(listOfIcons[i]);
            labelName.setBounds(200,0,20,20);
            playerStats[i].add(labelName);

            // Add player stats
            playerStats[i].setBounds(5,30*(i+1),240,25);
            tempPanel.add(playerStats[i]);
        }         
        return tempPanel;
    }


    /** 
     * These will be decision panels that appear in the same spot on the 
     * right and will be used to control the game
     **/
    public JPanel makeRollPanel() {
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(null);
            tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tempPanel.setBackground(Color.WHITE);

            rollButton = new JButton("Roll");
            rollButton.setBounds(63,100,100, 40); 
            rollButton.addActionListener(this);
            tempPanel.add(rollButton);

            // Add the Name
            JLabel tempLabel = new JLabel("Roll Decision", SwingConstants.CENTER);
            tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
            tempLabel.setBounds(2,0,250,30);
            tempPanel.add(tempLabel);

            // Labels for the dice
            rollLabel1 = new JLabel();
            rollLabel1.setBounds(50,40,50,50);
            tempPanel.add(rollLabel1);
            
            rollLabel2 = new JLabel();
            rollLabel2.setBounds(125,40,50,50);
            tempPanel.add(rollLabel2);

            return tempPanel;
    }

    public JPanel makeBuyPropertyPanel() {
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(null);
            tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tempPanel.setBackground(Color.WHITE);

            buyPropertyButton = new JButton("Buy");
            buyPropertyButton.setBounds(33,100,80, 40); 
            buyPropertyButton.addActionListener(this);
            tempPanel.add(buyPropertyButton);

            passButton = new JButton("Pass");
            passButton.setBounds(123,100,80, 40); 
            passButton.addActionListener(this);
            tempPanel.add(passButton);

            //tempPanel.addMouseListener(this);
            // Add the Name
            JLabel tempLabel = new JLabel("Buy Property?");
            tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
            tempLabel.setBounds(2,2,200,20);
            tempPanel.add(tempLabel);

            return tempPanel;
    }

    public JPanel makeRentPropertyPanel() {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempPanel.setBackground(Color.WHITE);

        rentButton = new JButton("Pay");
        rentButton.setBounds(33,100,80, 40); 
        rentButton.addActionListener(this);
        tempPanel.add(rentButton);

        bankruptButton = new JButton("Bankrupt");
        bankruptButton.setBounds(123,100,80, 40); 
        bankruptButton.addActionListener(this);
        tempPanel.add(bankruptButton);

        // Add the Name
        JLabel tempLabel = new JLabel("You Owe:");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tempLabel.setBounds(2,2,200,20);
        tempPanel.add(tempLabel);

        oweLabel = new JLabel("");
        oweLabel.setFont(new Font("Arial", Font.BOLD, 20));
        oweLabel.setBounds(2,22,200,20);
        tempPanel.add(oweLabel);


        return tempPanel;
}


    /**
     * Decisions at the end of players turn
     * @return 
     */
    public JPanel makeEndTurnPanel() {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempPanel.setBackground(Color.WHITE);

        // End turn option
        endTurnButton = new JButton("Finish Turn");
        endTurnButton.setBounds(50,100,150,40); 
        endTurnButton.addActionListener(this);
        tempPanel.add(endTurnButton);

        // Add house option (not ready yet)
        addHouseButton = new JButton("Add House");
        addHouseButton.setBounds(50,140,150,40); 
        addHouseButton.setEnabled(false);
        //addHouseButton.addActionListener(this);
        tempPanel.add(addHouseButton);
        //tempPanel.addMouseListener(this);

        // Add the Name
        JLabel tempLabel = new JLabel("Decision Options");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tempLabel.setBounds(2,2,200,20);
        tempPanel.add(tempLabel);
        
        tempPanel.setVisible(false);

        return tempPanel;
    }

    /**
     * Decisions at the end of players turn
     * @return 
     */
    public JPanel makeGameOverPanel() {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempPanel.setBackground(Color.WHITE);

        // Game over!!!
        JLabel tempLabel = new JLabel("GAME", SwingConstants.CENTER);
        tempLabel.setFont(new Font("Arial", Font.BOLD, 40));
        tempLabel.setBounds(10,2,200,40);
        tempPanel.add(tempLabel);

        tempLabel = new JLabel("OVER", SwingConstants.CENTER);
        tempLabel.setFont(new Font("Arial", Font.BOLD, 40));
        tempLabel.setBounds(10,52,200,40);
        tempPanel.add(tempLabel);

        tempLabel = new JLabel("Winner is:", SwingConstants.CENTER);
        tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tempLabel.setBounds(10,92,200,40);
        tempPanel.add(tempLabel);

        winnerLabel = new JLabel("", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        winnerLabel.setBounds(10,112,200,40);
        tempPanel.add(winnerLabel);

        tempPanel.setVisible(false);

        return tempPanel;
    }



    /**
     * Constructor
    **/
    public MainBoard() throws IOException{

        // Links to the game
        monopoly = new Game("squares.txt", "cards.txt");
        monopoly.createPlayer("Aman");
        monopoly.createPlayer("Vyshnavi");
        board = monopoly.getBoard(); 
        players = monopoly.getPlayers();
        Iterator<Square> boardIter = board.iterator();

        // Setup the board information
        gamePositions = new ArrayList<JPanel>();
        this.setSize(740, 770);

        // Title
        Label titleLabel = new Label("X-NOPOLY");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(200,150,400,60);
        this.add(titleLabel);

        // Board setup
        // Start
        Square sqr = boardIter.next();
        JPanel pPanel = makeCornerPanel("", "graphics/Go.png");
        pPanel.setBounds(640,640,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Bottom
        for (int i=8; i>=0; i--) {            
            sqr = boardIter.next();
            if(sqr instanceof Property)
            {
                pPanel = makePropertyPanelAcross((Property)sqr);
            }
            else
            {
                pPanel = makeSquarePanelAcross(sqr);
            }
            pPanel.setBounds(tileHeight+(tileWidth*i),640,tileWidth,tileHeight);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Jail corner
        sqr = boardIter.next();
        pPanel = makeCornerPanel("In Jail", "graphics/InJail.png");
        pPanel.setBounds(0,640,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Left side
        for (int i=8; i>=0; i--) {            
            sqr = boardIter.next();
            if(sqr instanceof Property)
            {
            pPanel = makePropertyPanelSide((Property)sqr);
            }
            else
            {
              pPanel = makeSquarePanelSide(sqr);
            }
            pPanel.setBounds(0,tileHeight+(tileWidth*i),tileHeight,tileWidth);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Free Parking        
        sqr = boardIter.next();
        pPanel = makeCornerPanel("Parking", "graphics/Parking.png");
        pPanel.setBounds(0,0,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Top
        for (int i=0; i<9; i++) {            
            sqr = boardIter.next();
            if(sqr instanceof Property)
            {
                pPanel = makePropertyPanelAcross((Property)sqr);
            }
            else
            {
                pPanel = makeSquarePanelAcross(sqr);
            }            
            pPanel.setBounds(tileHeight+(tileWidth*i),0,tileWidth,tileHeight);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Go to Jail
        sqr = boardIter.next();
        pPanel = makeCornerPanel("Go to Jail","graphics/Tojail.png");
        pPanel.setBounds(640,0,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Right side
        for (int i=0; i<9; i++) {            
            sqr = boardIter.next();
            if(sqr instanceof Property)
            {
                pPanel = makePropertyPanelSide((Property)sqr);
            }
            else
            {
                pPanel = makeSquarePanelSide(sqr);
            }
            pPanel.setBounds(640,tileHeight+(tileWidth*i),tileHeight,tileWidth);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Decision Area
        rollPanel = makeRollPanel();
        rollPanel.setBounds(380,380,250,250);
        rollPanel.setVisible(true);
        this.add(rollPanel);         

        buyPropertyPanel = makeBuyPropertyPanel();
        buyPropertyPanel.setBounds(380,380,250,250);
        buyPropertyPanel.setVisible(false);
        this.add(buyPropertyPanel);         

        rentPanel = makeRentPropertyPanel();
        rentPanel.setBounds(380,380,250,250);
        rentPanel.setVisible(false);
        this.add(rentPanel);         

        endTurnPanel = makeEndTurnPanel();
        endTurnPanel.setBounds(380,380,250,250);
        endTurnPanel.setVisible(false);
        this.add(endTurnPanel);         

        gameOverPanel = makeGameOverPanel();
        gameOverPanel.setBounds(380,380,250,250);
        gameOverPanel.setVisible(false);
        this.add(gameOverPanel);         

        // Load the players in locations
        listOfIcons[0] = new ImageIcon("graphics/hatpiece.png");
        listOfIcons[1] = new ImageIcon("graphics/carpiece.png");
        listOfIcons[2] = new ImageIcon("graphics/himblepiece.png");
        listOfIcons[3] = new ImageIcon("graphics/dogpiece.png");

        // Add the players to the board on Go Square
        for (int i=0; i<players.size(); i++) {
            playerIcons[i] = new JLabel(listOfIcons[i]);
            playerIcons[i].setBounds(2+25*i,60,20,20);
            gamePositions.get(0).add(playerIcons[i]);
        }

        // Load the Dice Icons
        diceIcons[0] = new ImageIcon("graphics/dice0.png");
        diceIcons[1] = new ImageIcon("graphics/dice1.png");
        diceIcons[2] = new ImageIcon("graphics/dice2.png");
        diceIcons[3] = new ImageIcon("graphics/dice3.png");
        diceIcons[4] = new ImageIcon("graphics/dice4.png");
        diceIcons[5] = new ImageIcon("graphics/dice5.png");
        diceIcons[6] = new ImageIcon("graphics/dice6.png");

        // Player Area
        playerStatPanel = makePlayerPanel();
        playerStatPanel.setBounds(110,380,250,250);
        this.add(playerStatPanel);         

        // Settings for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    /**
     * These are for controlling the actions on the GUI
     **/
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e);
        int num = monopoly.getCurPlayerTurn();
        int pos = players.get(num).getPosition();

        // Handle different actions
        if (e.getSource() == rollButton) {
            rollButton.setEnabled(false);
            System.out.println("GUI: Player "+num+" rolled from "+pos);

            // Remove from the board
            gamePositions.get(pos).remove(playerIcons[num]);

            // Roll the dice
            int roll = monopoly.rollDie(0,1);   // args to test rolls - no args for random
            int[] rollVals = monopoly.getDiceNumbers();            
            rollLabel1.setIcon(diceIcons[rollVals[0]]);
            rollLabel2.setIcon(diceIcons[rollVals[1]]);
            this.repaint();

            // Play the turn
            String resp = monopoly.playTurn(roll);
            
            // Place on the board
            int newPos = players.get(num).getPosition();
            System.out.println("GUI: Now At "+newPos);
            if ( (newPos >= 1 && newPos <= 9) 
                || (newPos >= 21 && newPos <= 29) ) {
                playerIcons[num].setBounds(2+25*num,40,20,20);
                gamePositions.get(newPos).add(playerIcons[num]);
            } else if ( (newPos >= 11 && newPos <= 19) 
                || (newPos >= 31 && newPos <= 39) ) {
                playerIcons[num].setBounds(22+25*num,15,20,20);
                gamePositions.get(newPos).add(playerIcons[num]);
            } else {
                playerIcons[num].setBounds(2+25*num,50,20,20);
                gamePositions.get(newPos).add(playerIcons[num]);
            }

            // Delay before next decision
            rollTimer = new Timer(1000, this);
            rollTimer.start();
        }
        else if (e.getSource() == rollTimer) {
            rollTimer.stop();
            rollLabel1.setIcon(null);
            rollLabel2.setIcon(null);
            rollButton.setEnabled(true);
            rollPanel.setVisible(false);

            // What happens now?
            Square curSqr = board.get(pos);

            if (curSqr instanceof Property) {
                Property curProp = (Property)curSqr;

                if (curProp.getOwner() == null) {
                    System.out.println("GUI: Buy");
                    buyPropertyPanel.setVisible(true);
                }
                else {
                    System.out.println("GUI: Already owned");
                    oweLabel.setText("$"+curProp.getRent());
                    rentPanel.setVisible(true);
                }
            }
            else {
                System.out.println("GUI: Not a property - end turn?");
                endTurnPanel.setVisible(true);
            }
            updatePlayerPanel();

            this.repaint();
        }
        else if (e.getSource() == buyPropertyButton) {
            System.out.println("GUI: Buy Property");

            // Get the player information
            System.out.println("GUI: player "+num+" pos "+pos);

            // Replace the space
            Square mySpace = board.get(pos);
            if (mySpace instanceof Property) {
                if (monopoly.buyProperty()) {
                    System.out.println("GUI: Purchased "+((Property)mySpace).getName());
                } else {
                    System.out.println("GUI: Failed Purchase "+((Property)mySpace).getName());
                }               
            }

            // Update player stats                     
            updatePlayerPanel();

            // Head to end turn
            buyPropertyPanel.setVisible(false);
            endTurnPanel.setVisible(true);
            this.repaint();
        }
        else if (e.getSource() == rentButton) {
            System.out.println("GUI: Rent");

            // Get the player information
            System.out.println("GUI: player "+num+" pos "+pos);

            // Replace the space
            Square mySpace = board.get(pos);
            if (mySpace instanceof Property) {
                    int amt = monopoly.pay();
                    System.out.println("GUI: Pay: "+amt);

                if (amt != 0) {
                    System.out.println("GUI: Paid rent on "+((Property)mySpace).getName());
                    rentPanel.setVisible(false);
                    endTurnPanel.setVisible(true);        
                } else {
                    // If it fails, stay on the decision (need to buy)
                    System.out.println("GUI: Failed rent "+((Property)mySpace).getName());
                }               
            }
            
            // Update player stats                     
            updatePlayerPanel();

            // Head to end turn
            this.repaint();
        }

        else if (e.getSource() == passButton) {
            // Head to end turn
            buyPropertyPanel.setVisible(false);
            endTurnPanel.setVisible(true);

            // Update player stats                     
            updatePlayerPanel();
            
            this.repaint();
        }
        else if (e.getSource() == endTurnButton) {
            // Switch panels
            endTurnPanel.setVisible(false);
            
            // Move on to next turn
            String resp = monopoly.endTurn();
            
            if (resp.equals("GameOver")) {
                winnerLabel.setText("Reid");
                gameOverPanel.setVisible(true);
            }
            else {
                rollPanel.setVisible(true);
            }

            // Update player stats                     
            updatePlayerPanel();

            this.repaint();
        }
    }


    /**
     * Here is some code to use later - replacing panel to add house
            if (mySpace instanceof Property) {
                ((Property)mySpace).addHouse();

                // Need to generate new panel and replace it
                JPanel oldPanel = gamePositions.get(pos); 
                JPanel newPanel = makePropertyPanelAcross((Property)mySpace);
                newPanel.setBounds(oldPanel.getBounds());
                this.remove(oldPanel);
                this.add(newPanel);
            }
     */


    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        JPanel src = (JPanel)e.getSource();

        // Check if it's a game position
        int pos = gamePositions.indexOf(src);
        if (pos >=0) {
            Property prop = (Property)board.get(pos);
            Player curPlayer = monopoly.getPlayers().get(monopoly.getCurPlayerTurn());
            PropertyView thisProperty = new PropertyView(this, monopoly, prop, curPlayer);
            thisProperty.setVisible(true);
        }
        else {
            // Check if it's a playerStat
            pos = -1;
            for (int i=0; i<playerStats.length; i++) {
                if (src == playerStats[i]) {
                    pos = i;
                }
            }
            if (pos >=0) {
                Player player = (Player)players.get(pos);
                PlayerView thisPlayer = new PlayerView(this, player);
                thisPlayer.setVisible(true);
            }
        }

    }

    /**
     * Update player panel
     */
    public void updatePlayerPanel() {
        JPanel newPanel = makePlayerPanel();
        newPanel.setBounds(playerStatPanel.getBounds());
        this.remove(playerStatPanel);
        this.add(newPanel);
        playerStatPanel = newPanel;
    }
}