import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PropertyView extends JFrame implements ActionListener {

    JFrame parent = null;

    public PropertyView(JFrame parent, Property sqr) {
        this.parent = parent;
        this.setSize(300, 300);
                
        // Add a label
        JLabel label = new JLabel("This is a popup");
        label.setBounds(50,50,200,50);
        this.add(label);

        label = new JLabel(""+sqr.getName());
        label.setBounds(50,150,200,50);
        this.add(label);

        JButton button = new JButton("Ok");
        button.setBounds(100,200,100,50);
        button.addActionListener(this);
        this.add(button);
    
        // Settings for the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    public void actionPerformed(ActionEvent e) {
        //this.parent.enabled();
        System.out.println("Button");
        this.setVisible(false); 
        this.dispose();
    }

}
