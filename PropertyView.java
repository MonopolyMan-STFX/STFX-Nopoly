import javax.swing.*;
import java.awt.event.*;

public class PropertyView extends JFrame implements ActionListener {

    MainBoard parent = null;
    JButton okButton = null;
    JButton sellButton = null;
    JButton houseButton = null;
    JLabel msgLabel = null;
    Game game = null;
    Property property = null;
    Player player = null;

    public PropertyView(MainBoard parent, Game game, Property property, Player player) {
        this.parent = parent;
        this.parent.setEnabled(false);
        this.game = game;
        this.property = property;
        this.player = player;
        this.setSize(300, 300);
                
        // Name
        JLabel label = new JLabel(""+property.getName());
        label.setBounds(50,10,200,50);
        this.add(label);

        // Owner
        Player owner = property.getOwner();
        if (owner == null) {
            label = new JLabel("Owner: None");
        }
        else {
            label = new JLabel("Owner: "+owner.getName());
        }
        label.setBounds(50,50,200,50);
        this.add(label);

        // Messages
        msgLabel = new JLabel("");
        msgLabel.setBounds(50,160,200,50);
        this.add(msgLabel);


        okButton = new JButton("Done");
        okButton.setBounds(5,200,80,50);
        okButton.addActionListener(this);
        this.add(okButton);

        // Sell button only if it's the owner
        sellButton = new JButton("Sell");
        sellButton.setBounds(90,200,80,50);
        if (owner == null || player != owner) {
            sellButton.setEnabled(false);
        }
        sellButton.addActionListener(this);
        this.add(sellButton);

        // Add house button only if it's the owner
        houseButton = new JButton("Add House");
        houseButton.setBounds(175,200,80,50);
        if (owner == null || player != owner) {
            houseButton.setEnabled(false);
        }
        houseButton.addActionListener(this);
        this.add(houseButton);

        // Settings for the frame
        this.setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sellButton) {
            if (game.sellProperty(this.property.getName())) {
                msgLabel.setText("Sold");
                parent.updatePlayerPanel();
                parent.repaint();
                sellButton.setEnabled(false);
            } else {
                msgLabel.setText("Error: Cannot sell");
            }
            this.repaint();
        }
        else if (e.getSource() == houseButton) {
            /* TODO call game to add house
            if (game.sellProperty(this.property.getName())) {
                msgLabel.setText("Sold");
                parent.updatePlayerPanel();
                parent.repaint();
                sellButton.setEnabled(false);
            } else {
                msgLabel.setText("Error: Cannot sell");
            }
            */
            this.repaint();
        }
        else {
            this.parent.setEnabled(true);
            this.setVisible(false); 
            this.dispose();
        }
    }

}
