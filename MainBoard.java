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

    JPanel buyPanel = null;
    JButton buyButton = null;
    JButton passButton = null;
    Timer buyTimer = null;

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
        tempLabel.setBounds(22,2,tileWidth-4,20);
        tempPanel.add(tempLabel);

        //add the cost side
        Label templabel = new Label(""+sqr.getCost());
        tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
        templabel.setBounds(22,40,tileWidth-4,20);
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
    
            // Add the Name
            // Label tempLabel = new Label("Property");
            // tempLabel.setFont(new Font("Arial", Font.BOLD, 10));
            // tempLabel.setBounds(20,1,tileWidth-20,20);
            // tempPanel.add(tempLabel);

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

    public JPanel makeBuyPanel() {
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(null);
            tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            tempPanel.setBackground(Color.WHITE);

            buyButton = new JButton("Buy");
            buyButton.setBounds(33,100,80, 40); 
            buyButton.addActionListener(this);
            tempPanel.add(buyButton);

            passButton = new JButton("Pass");
            passButton.setBounds(123,100,80, 40); 
            passButton.addActionListener(this);
            tempPanel.add(passButton);

            //tempPanel.addMouseListener(this);
            // Add the Name
            JLabel tempLabel = new JLabel("Buy Decision");
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
        monopoly.createPlayer("Joe");
        monopoly.createPlayer("Bob");
        board = monopoly.getBoard(); 
        players = monopoly.getPlayers();
        Iterator boardIter = board.iterator();

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
        Square sqr = (Square)boardIter.next();
        JPanel pPanel = makeCornerPanel("Go");
        pPanel.setBounds(640,640,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Bottom
        for (int i=8; i>=0; i--) {            
            sqr = (Square)boardIter.next();
            pPanel = makePropertyPanelAcross((Property)sqr);
            pPanel.setBounds(tileHeight+(tileWidth*i),640,tileWidth,tileHeight);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Jail corner
        sqr = (Square)boardIter.next();
        pPanel = makeCornerPanel("Jail");
        pPanel.setBounds(0,640,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Left side
        for (int i=8; i>=0; i--) {            
            sqr = (Square)boardIter.next();
            pPanel = makePropertyPanelSide((Property)sqr);
            pPanel.setBounds(0,tileHeight+(tileWidth*i),tileHeight,tileWidth);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Free Parking        
        sqr = (Square)boardIter.next();
        pPanel = makeCornerPanel("Parking");
        pPanel.setBounds(0,0,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Top
        for (int i=0; i<9; i++) {            
            sqr = (Square)boardIter.next();
            pPanel = makePropertyPanelAcross((Property)sqr);
            pPanel.setBounds(tileHeight+(tileWidth*i),0,tileWidth,tileHeight);
            gamePositions.add(pPanel);
            this.add(pPanel);         
        }

        // Go to Jail
        sqr = (Square)boardIter.next();
        pPanel = makeCornerPanel("<html>Go to <br> Jail</html>");
        pPanel.setBounds(640,0,tileHeight,tileHeight);
        gamePositions.add(pPanel);
        this.add(pPanel);         

        // Right side
        for (int i=0; i<9; i++) {            
            sqr = (Square)boardIter.next();
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

        buyPanel = makeBuyPanel();
        buyPanel.setBounds(380,380,250,250);
        buyPanel.setVisible(false);
        //this.enabled();
        this.add(buyPanel);         

        // Draw the players in locations
        ImageIcon[] listOfIcons = new ImageIcon[4];
        listOfIcons[0] = new ImageIcon("hatpiece.png");
        listOfIcons[1] = new ImageIcon("carpiece.png");
        listOfIcons[2] = new ImageIcon("thimblepiece.png");
        listOfIcons[3] = new ImageIcon("dogpiece.png");


        //label1 = new JLabel(img1);

        for (int i=0; i<players.size(); i++) {
            Player p = players.get(i);
            playerIcons[i] = new JLabel(listOfIcons[i]);
            playerIcons[i].setFont(new Font("Arial", Font.BOLD, 10));
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
        System.out.println("Action!!!");
        if (e.getSource() == rollButton) {
            int num = monopoly.getCurPlayerTurn();
            int pos = players.get(num).getPosition();
            System.out.println("Player "+num+" rolled from "+pos);

            // Remove from the board
            gamePositions.get(pos).remove(playerIcons[num]);

            // Roll the dice
            int roll = monopoly.rollDie();   
            rollLabel.setText(""+roll);
            monopoly.playTurn(roll);

            // Place on the board
            int newPos = players.get(num).getPosition();
            System.out.println("Now At "+newPos);
            gamePositions.get(newPos).add(playerIcons[num]);
            this.repaint();

            /// Delay before next decision
            rollTimer = new Timer(2000, this);
            rollTimer.start();
        }
        else if (e.getSource() == rollTimer) {
            rollTimer.stop();
            rollLabel.setText("");
            rollPanel.setVisible(false);
            buyPanel.setVisible(true);
            this.repaint();
        }
        else if (e.getSource() == buyButton) {
            System.out.println("Buy!!");
            buyPanel.setVisible(false);
            rollPanel.setVisible(true);
        }

    }

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
        System.out.println("Click "+pos);
        Property prop = (Property)board.get(pos);
        PropertyView thisProperty = new PropertyView(this, prop);
        thisProperty.setVisible(true);

    }

}