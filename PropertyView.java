import javax.swing.*;
import java.awt.event.*;

public class PropertyView extends JFrame implements ActionListener {

    MainBoard parent = null;
    JButton okButton = null;
    JButton sellButton = null;
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
        okButton.setBounds(50,200,100,50);
        okButton.addActionListener(this);
        this.add(okButton);

        // Sell button only if it's the owner
        sellButton = new JButton("Sell");
        sellButton.setBounds(150,200,100,50);
        if (owner == null || player != owner) {
            sellButton.setEnabled(false);
        }
        sellButton.addActionListener(this);
        this.add(sellButton);

        // Settings for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sellButton) {
            if (game.sellProperty(this.player, this.property.getName())) {
                msgLabel.setText("Sold");
                parent.updatePlayerPanel();
                parent.repaint();
                sellButton.setEnabled(false);
            } else {
                msgLabel.setText("Error: Cannot sell");
            }
            this.repaint();
        }
        else {
            this.parent.setEnabled(true);
            this.setVisible(false); 
            this.dispose();
        }
    }

}
