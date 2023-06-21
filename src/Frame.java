import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    JButton myButton = new JButton("Play Again");
    int WIDTH = 600;
    int HEIGHT = 300;

    //    Panel p = new Panel();
    Frame(int score){
//        myButton.setBounds(WIDTH/2,HEIGHT/2,100,50 );
//        myButton.setFocusable(false);
        myButton.addActionListener(this);
        JLabel scoreLabel = new JLabel("score = "+score);
//        scoreLabel.setBounds(100,100,100,20);
        scoreLabel.setForeground(Color.BLACK);
//        this.setSize(WIDTH,HEIGHT);
        this.setLayout(new FlowLayout());
        this.add(scoreLabel);
        this.add(myButton);


        this.pack();

//        this.setLayout(new BorderLayout());
//        this.add(score, BorderLayout.CENTER);  // b2 will be placed in the Center
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        this.();
        this.dispose();
        new Game();

    }
}
