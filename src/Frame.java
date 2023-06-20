import javax.swing.*;

public class Frame extends JFrame {
    Panel p = new Panel();
    Frame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(p);
        this.pack();
//        this.setSize(600,6 00);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
