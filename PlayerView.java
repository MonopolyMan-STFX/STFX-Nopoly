import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PlayerView extends JFrame implements ActionListener {

	JFrame parent = null;
	MainBoard parentMain = null;
	JComboBox<String> jComboBox = new JComboBox<>();
	JButton button = null;
	Player currentPlayer = null;
	ArrayList<Square> board = null;
	Game monopoly = null;	
	
	public PlayerView(MainBoard parent, Game game, Player player)
	{
		// Setting all the parents and related things
		parentMain = parent;
	    this.parent = parent;
	    this.parent.setEnabled(false);
	    this.setSize(300, 300);
	    this.currentPlayer = player;
	    monopoly = game;
	    board = game.getBoard();
		
	    // Add a label
	    JLabel label = new JLabel("Player Info Popup");
	    label.setBounds(50,20,200,50);
	    this.add(label);

	    // Shows their name
	    label = new JLabel(""+player.getName());
	    label.setBounds(50,50,200,50);
	    this.add(label);

	 	// Shows balance
	    label = new JLabel("$"+player.getBalance());
	    label.setBounds(50,65,200,50);
	    this.add(label);	

		// Make an arraylist which contains their properties
	    ArrayList<Square> playerProperties = player.getAllProperties();	

		// Add them to the combo box
	    for(int i = 0; i < playerProperties.size(); i++)
	    {
	        jComboBox.addItem(playerProperties.get(i).getName());
	    }

		// Give it some personal space
	    jComboBox.setBounds(50, 100, 140, 20);

		// Give it the sense of touch, and hearing?
	    jComboBox.addActionListener(this);
	    this.add(jComboBox);	


	    // An OK button
	    button = new JButton("Ok");
	    button.setBounds(100,200,100,50);
	    button.addActionListener(this);
	    this.add(button);
	
	    // Settings for the frame
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLayout(null);
	}	

	/**
	 * Please google what actionPerformed is
	 */
	public void actionPerformed(ActionEvent e) {	
		// if the combobox had any interactions
		if(e.getSource() == jComboBox)
	    {
			// Print out the selected item
			System.out.println("GUI: "+jComboBox.getSelectedItem().toString());



			// Get the property
			int position = 0;
			boolean found = false;
			while(found == false)
			{
				if(this.currentPlayer.getAllProperties().get(position).getName().equals(jComboBox.getSelectedItem().toString()))
				{
				//	System.out.println(jComboBox.getSelectedItem.toString());
					found = true;
				}
				else if(this.currentPlayer.getAllProperties().size() == position)
				{
					found = true;
					position = -1;
				}
				else
				{
					position++;
				}

				Square prop = board.get(position);
				System.out.println("Looking at " + prop.getName());
			}	

			// open a viewProperty window on it
			if(position != -1)
			{
				// get ppoint to the property
				Property prop = (Property)currentPlayer.getAllProperties().get(position);
	         	Player curPlayer = monopoly.getPlayers().get(monopoly.getCurPlayerTurn());
				System.out.println("Property " + prop.getName());
				PropertyView thisProperty = new PropertyView(parentMain, monopoly, prop, curPlayer);
	         	thisProperty.setVisible(true);
			}

	    }
	    
		else if(e.getSource() == button)
	    {
	       System.out.println("Wat");
	       parent.setEnabled(true);
	       this.setVisible(false);
	       this.dispose();
	    }
	}	
}
