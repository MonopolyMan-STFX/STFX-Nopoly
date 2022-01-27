                                              import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Overall GUI for the Monopoly board
 **/
public class MainBoard extends JFrame implements ActionListener, MouseListener {

    JLabel label1 = null;

    int tileWidth = 60;
    int tileHeight = 100;
    int houseLocation = 0; 
    JLabel[] playerIcons = new JLabel[4];

    JPanel rollPanel = null;
    JButton rollButton = null;
    Timer rollTimer = null;
    JLabel rollLabel = null;

    JPanel buyPropertyPanel = null;
    JButton buyPropertyButton = null;
    JButton passButton = null;
    Timer buyPropertyTimer = null;

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
            housePanel.setBackground(Color.GREEN);
            housePanel.setBounds(0,0,tileWidth, 20);
            tempPanel.add(housePanel);
            // Nick's code
            if (sqr.getHousesOwned() < 5)  { 
            for (int i = 0;  i < sqr.getHousesOwned(); i++) { 

               ImageIcon img1 = new ImageIcon("House.png");
               JLabel label1 = new JLabel(img1);
               int width1 = img1.getIconWidth();
               int height1 = img1.getIconHeight();
               label1.setBounds(i * 15, 0, width1 , height1 );
               housePanel.add(label1);
                houseLocation += 20;
            }
            } else  {

                ImageIcon img2 = new ImageIcon("Hotel.png");
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
        housePanel.setBackground(Color.GREEN);
        housePanel.setBounds(0,0,20,tileWidth);
        tempPanel.add(housePanel);

        //Nick's code   
        if (sqr.getHousesOwned() < 5)  {    
            for (int i = 0;  i < sqr.getHousesOwned(); i++) { 
                
            
                ImageIcon img1 = new ImageIcon("House.png");
                JLabel label1 = new JLabel(img1);
                int width1 = img1.getIconWidth();
                int height1 = img1.getIconHeight();
                label1.setBounds(0, i * 15, width1 , height1 );
                housePanel.add(label1); 
                houseLocation += 20;
            }
        }  else {
            ImageIcon img2 = new ImageIcon("Hotel.png");
            JLabel label2 = new JLabel(img2);
            int width2 = img2.getIconWidth();
            int height2 = img2.getIconHeight();
            label2.setBounds(0, 0, width2, height2);
            housePanel.add(label2);
        } 
        return tempPanel;
    }


    public JPanel makeCornerPanel(String text) {
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(null);
            tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tempPanel.setBackground(Color.RED);
            tempPanel.addMouseListener(this);
            // Add the Name
            JLabel tempLabel = new JLabel(text);
            tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
            tempLabel.setBounds(2,2,tileHeight-2,40);
            tempPanel.add(tempLabel);

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

            for (int i=0; i<players.size(); i++) {
                Player p = players.get(i);
                tempLabel = new JLabel(""+p.getName(), SwingConstants.LEFT);
                tempLabel.setFont(new Font("Arial", Font.BOLD, 20));
                tempLabel.setBounds(2,20*(i+1),250,25);
                tempPanel.add(tempLabel);
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

            rollLabel = new JLabel("", SwingConstants.CENTER);
            rollLabel.setFont(new Font("Arial", Font.BOLD, 20));
            rollLabel.setBounds(2,40,250,30);
            tempPanel.add(rollLabel);

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


    /**
     * Constructor
    **/
    public MainBoard() {

        // Links to the game
        monopoly = new Game();
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
        JPanel pPanel = makeCornerPanel("Go");
        pPanel.setBounds(640,640,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Bottom
        for (int i=8; i>=0; i--) {            
            sqr = boardIter.next();
            pPanel = makePropertyPanelAcross((Property)sqr);
            pPanel.setBounds(tileHeight+(tileWidth*i),640,tileWidth,tileHeight);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Jail corner
        sqr = boardIter.next();
        pPanel = makeCornerPanel("Jail");
        pPanel.setBounds(0,640,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Left side
        for (int i=8; i>=0; i--) {            
            sqr = boardIter.next();
            pPanel = makePropertyPanelSide((Property)sqr);
            pPanel.setBounds(0,tileHeight+(tileWidth*i),tileHeight,tileWidth);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Free Parking        
        sqr = boardIter.next();
        pPanel = makeCornerPanel("Parking");
        pPanel.setBounds(0,0,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Top
        for (int i=0; i<9; i++) {            
            sqr = boardIter.next();
            pPanel = makePropertyPanelAcross((Property)sqr);
            pPanel.setBounds(tileHeight+(tileWidth*i),0,tileWidth,tileHeight);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Go to Jail
        sqr = boardIter.next();
        pPanel = makeCornerPanel("<html>Go to <br>&nbsp; Jail</html>");
        pPanel.setBounds(640,0,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Right side
        for (int i=0; i<9; i++) {            
            sqr = boardIter.next();
            pPanel = makePropertyPanelSide((Property)sqr);
            pPanel.setBounds(640,tileHeight+(tileWidth*i),tileHeight,tileWidth);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Player Area
        pPanel = makePlayerPanel();
        pPanel.setBounds(110,380,250,250);
        this.add(pPanel);         

        // Decision Area
        rollPanel = makeRollPanel();
        rollPanel.setBounds(380,380,250,250);
        rollPanel.setVisible(true);
        this.add(rollPanel);         

        buyPropertyPanel = makeBuyPropertyPanel();
        buyPropertyPanel.setBounds(380,380,250,250);
        buyPropertyPanel.setVisible(false);
        //this.enabled();
        this.add(buyPropertyPanel);         

        // Draw the players in locations
        ImageIcon[] listOfIcons = new ImageIcon[4];
        listOfIcons[0] = new ImageIcon("hatpiece.png");
        listOfIcons[1] = new ImageIcon("carpiece.png");
        listOfIcons[2] = new ImageIcon("thimblepiece.png");
        listOfIcons[3] = new ImageIcon("dogpiece.png");


        // Add the players to the board on Go Square
        for (int i=0; i<players.size(); i++) {
            playerIcons[i] = new JLabel(listOfIcons[i]);
            playerIcons[i].setBounds(2+25*i,40,20,20);
            gamePositions.get(0).add(playerIcons[i]);
        }

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
        if (e.getSource() == rollButton) {
            rollButton.setEnabled(false);
            int num = monopoly.getCurPlayerTurn();
            int pos = players.get(num).getPosition();
            System.out.println("GUI: Player "+num+" rolled from "+pos);

            // Remove from the board
            gamePositions.get(pos).remove(playerIcons[num]);

            // Roll the dice
            int roll = monopoly.rollDie();   
            rollLabel.setText(""+roll);
            monopoly.playTurn(roll);

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
            this.repaint();

            // Delay before next decision
            rollTimer = new Timer(1000, this);
            rollTimer.start();
        }
        else if (e.getSource() == rollTimer) {
            rollTimer.stop();
            rollLabel.setText("");
            rollButton.setEnabled(true);
            rollPanel.setVisible(false);
            buyPropertyPanel.setVisible(true);
            this.repaint();
        }
        else if (e.getSource() == buyPropertyButton) {
            System.out.println("GUI: Buy Property");

            // Get the player information
            int num = monopoly.getCurPlayerTurn();
            int pos = players.get(num).getPosition();
            System.out.println("GUI: player "+num+" pos "+pos);

            // Replace the space
            Square mySpace = board.get(pos);
            if (mySpace instanceof Property) {
                if (monopoly.buyProperty(players.get(num))) {
                    System.out.println("GUI: Purchased "+((Property)mySpace).getName());
                } else {
                    System.out.println("GUI: Failed Purchase "+((Property)mySpace).getName());
                }               
            }

            // Delay before next decision
            buyPropertyTimer = new Timer(1000, this);
            buyPropertyTimer.start();
        }
        else if (e.getSource() == buyPropertyTimer) {
            // Switch panels
            buyPropertyTimer.stop();
            buyPropertyPanel.setVisible(false);
            rollPanel.setVisible(true);
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
        //System.out.println(e);
        JPanel src = (JPanel)e.getSource();
        int pos = gamePositions.indexOf(src);
        System.out.println("GUI: Click "+pos);
        Property prop = (Property)board.get(pos);
        PropertyView thisProperty = new PropertyView(this, prop);
        thisProperty.setVisible(true);

    }

}