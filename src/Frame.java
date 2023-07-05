import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frame extends JFrame implements ActionListener {
    JButton myButton = new JButton("Play Again");
    int WIDTH = 400;
    int HEIGHT = 300;
    JRadioButton enemyBulletNormal;
    JRadioButton enemyBulletFast;
    JRadioButton shipBulletNormal;
    JRadioButton shipBulletFast;
    JRadioButton darkMode;
    JRadioButton lightMode;
    JRadioButton shipBulletSuper;
    JRadioButton shipBulletNotSuper;
    Frame(int score){
        this.setTitle("Main Menu");
        this.setSize(WIDTH,HEIGHT);
        myButton.setSize(WIDTH,50 );
//        myButton.setFocusable(false);
        //radio BUTTON
        enemyBulletNormal = new JRadioButton("Normal");
        enemyBulletFast = new JRadioButton("Fast");
        darkMode = new JRadioButton("Dark Mode");
        lightMode = new JRadioButton("Light Mode");
        shipBulletNotSuper = new JRadioButton("Normal");
        shipBulletSuper = new JRadioButton("Super");
        ButtonGroup bulletmode = new ButtonGroup();
        bulletmode.add(shipBulletNotSuper);
        bulletmode.add(shipBulletSuper);
        ButtonGroup mode = new ButtonGroup();
        mode.add(darkMode);
        mode.add(lightMode);
        ButtonGroup enemyBullet = new ButtonGroup();
        enemyBullet.add(enemyBulletNormal);
        enemyBullet.add(enemyBulletFast);
        shipBulletNormal = new JRadioButton("Normal");
        shipBulletFast = new JRadioButton("Fast");
        ButtonGroup shipBullet = new ButtonGroup();
        shipBullet.add(shipBulletNormal);
        shipBullet.add(shipBulletFast);
        myButton.addActionListener(this);
        enemyBulletNormal.setSelected(true);
        shipBulletNotSuper.setSelected(true);
        shipBulletNormal.setSelected(true);
        darkMode.setSelected(true);
        JLabel scoreLabel = new JLabel("your score = "+score);
        if(score == -1){
            myButton.setText("Play");
        }
        scoreLabel.setForeground(Color.BLACK);
        this.setLayout(new GridLayout(5,1));
        if(score != -1){
            this.setLayout(new GridLayout(6,1));

            this.add(new JLabel("")).setVisible(false);
            this.add(scoreLabel);
            this.add(new JLabel("")).setVisible(false);
        }

        this.add(new JLabel("Enemy Bullet Speed"));
        this.add(enemyBulletNormal);
        this.add(enemyBulletFast);

        this.add(new JLabel("Ship Bullet Speed"));
        this.add(shipBulletNormal);
        this.add(shipBulletFast);

        this.add(new JLabel("Ship Bullet Mode"));
        this.add(shipBulletNotSuper);
        this.add(shipBulletSuper);

        this.add(new JLabel("Mode"));
        this.add(darkMode);
        this.add(lightMode);

        this.add(new JLabel("")).setVisible(false);
        this.add(myButton);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        int shipBulletSpeed = shipBulletFast.isSelected()?1:2;
        int enemyBulletSpeed = enemyBulletFast.isSelected()?1:2;
        int gameMode = darkMode.isSelected()?1:2;
        int bulletMode = shipBulletNotSuper.isSelected()?1:2;
        try {
            new Game(shipBulletSpeed,enemyBulletSpeed,gameMode,bulletMode );
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }

    }
}
