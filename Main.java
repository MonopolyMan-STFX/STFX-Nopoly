import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class Main {
  public static void main(String[] args) 
  {
    // A change
    JFrame frame = new JFrame("ICS4UC Quad 2");
    frame.setSize(300, 300);
    frame.setLocation(5, 5);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel label = new JLabel("Hello world", SwingConstants.CENTER);
    frame.add(label);

    frame.show();
  }
}