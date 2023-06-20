import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game implements ActionListener {
    JFrame frame;

    JLabel ship;
    JLabel enemy;
    JLabel scoreLabel;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    int bulletNumber1 =0;
    int score =0;
    int movement= 10;
    int WIDTH = 500;
    int HEIGHT = 500;
    int direction = 1;
    int bulletNumber = 0;
    int start = 0;
    ArrayList<JLabel> enemies = new ArrayList<JLabel>();
    Timer moveAlien;
//    int enemiesSize;

    Game(){
        /// score Label
        scoreLabel = new JLabel("Score : "+score);
        scoreLabel.setBounds(0,200,100,30);
        /// spaceship icon
        ImageIcon spaceShip = new ImageIcon("src/spaceship.png");
        Image img = spaceShip.getImage();
        Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        /// aliens icon
        ImageIcon alien = new ImageIcon("src/alliens.png");
        Image img2 = alien.getImage();
        Image alien2 = img2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        alien.setImage(alien2);
        /// place aliens
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                enemy = new JLabel(alien);
                enemy.setBounds(i*40, j*40, 30, 30);
                enemies.add(enemy);

            }
        }
        /// moving aliens timer
        moveAlien = new Timer(50,this);
        moveAlien.start();

        ImageIcon bullet1 = new ImageIcon("src/bulletFinal.png");
        Image bullet2 = bullet1.getImage();
        Image bullet3 = bullet2.getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH);
        bullet1.setImage(bullet3);
        ArrayList<JLabel> bullets = new ArrayList<>();
        JLabel bullet = new JLabel(bullet1);
        JLabel bullet30 = new JLabel(bullet1);
        JLabel bullet50 = new JLabel(bullet1);
        bullets.add(bullet);
        bullets.add(bullet30);
        bullets.add(bullet50);
        ///set spaceShip
        spaceShip.setImage(newimg);
        ship = new JLabel(spaceShip);
        ship.setBounds(WIDTH-(WIDTH/2), HEIGHT-75, 30, 30);
        /// key bindings
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        ship.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        ship.getActionMap().put("upAction", upAction);
        ship.getActionMap().put("leftAction", leftAction);
        ship.getActionMap().put("rightAction", rightAction);
        ship.getActionMap().put("downAction", downAction);
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
//        panel.setBackground(Color.black);
//        panel.setVisible(true);

        /// Frame stuff
        frame = new JFrame("Snake Demo");
        frame.add(scoreLabel);
        frame.add(ship);
        frame.add(bullet);
        for (int i = 0; i < enemies.size(); i++) {
            frame.add(enemies.get(i));
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
//       frame.add(enemy);
//       frame.pack();
//       frame.add(panel);
//       frame.remove();
        Timer setBullet = new Timer(3000,new AbstractAction("bullet begin") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = bullets.get(bulletNumber);
                b.setBounds(ship.getX()-10,ship.getY()-15,50,50);
                bulletNumber1 = bulletNumber;
                frame.add(b);
                bulletNumber = (bulletNumber+1)%3;
            }
        });
        setBullet.start();
        Timer moveBullet = new Timer(50,new AbstractAction("bullet move") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = bullets.get(bulletNumber1);
                start++;
                if(start <= 50){
                    return;
                }
                for (int i = 0; i <enemies.size() ; i++) {
                    JLabel enemy = enemies.get(i);
                    if(b.getY()>= enemy.getY()&& b.getY()<= enemy.getY()+20){
                        if(b.getX()+20>= enemy.getX()-10&& b.getX()+20<= enemy.getX()+30){
                                frame.remove(enemy);
                            enemies.remove(i);
                        }
                    }
                }
                setScore();
//                JLabel b  =  bullets.get(bulletNumber1);
                if(b.getY()>=-30) {
                    b.setLocation(b.getX(), b.getY() - 10);
                }
            }
        });
        moveBullet.start();
    }
    public void setScore(){
        int enemiesLeft = enemies.size();
        int newScore =   50-enemiesLeft;
        scoreLabel.setText("Score : "+20*newScore );
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(enemies.get(enemies.size()-1).getX()+30 >= 500 || enemies.get(0).getX() <= 0){
            direction *=-1;
        }
        for (int i = 0; i < enemies.size(); i++) {
            JLabel enemy = enemies.get(i);
            enemy.setLocation(enemy.getX()-direction,enemy.getY());
        }
    }

    public class UpAction extends AbstractAction   {
        @Override
        public void actionPerformed(ActionEvent e) {
                    if(ship.getY() > HEIGHT-100)
                 ship.setLocation(ship.getX(), ship.getY()-movement);
        }
    }
    public class DownAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ship.getY() <HEIGHT-70 )
                ship.setLocation(ship.getX(), ship.getY() + movement);
        }
    }
    public class LeftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ship.getX() > 10 )
                ship.setLocation(ship.getX() - movement, ship.getY());
        }
    }
    public class RightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ship.getX() < WIDTH-40 )
                ship.setLocation(ship.getX()+movement, ship.getY());
        }
    }
}