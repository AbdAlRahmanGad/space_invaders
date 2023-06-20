import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {
//        JFrame frame;
        JLabel ship;
        JLabel enemy;
        Action upAction;
        Action downAction;
        Action leftAction;
        Action rightAction;
        Timer t;
        ArrayList<ImageIcon> enemies = new ArrayList<ImageIcon>();
    int x=0;
    int y=0;
        int movement= 15;
        int WIDTH = 500;
        int HEIGHT = 500;
    ImageIcon alien = new ImageIcon("src/alliens.png");

        Panel(){
            this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

//        java.net.URL imgUrl = getClass().getResource("spaceship.png");
//        ImageIcon icon = new ImageIcon(imgUrl);

            ImageIcon spaceShip = new ImageIcon("src/spaceship.png");
            Image img = spaceShip.getImage();
            Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
            // scale it the smooth way

            Image img2 = alien.getImage();
            Image alien2 = img2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it th
            alien.setImage(alien2);
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 5; j++) {
//                    enemy = new JLabel(alien);
//                    enemy.setBounds(i*40, j*40, 30, 30);
//                    enemies.add(enemy);
//
//                }
//            }
            t = new Timer(5,this);
            t.start();

            spaceShip.setImage(newimg);
            ship = new JLabel(spaceShip);
//        ship.setBackground(Color.green);
            ship.setBounds(WIDTH-(WIDTH/2), HEIGHT-50, 30, 30);
//        ship.setBounds(200, 250, 30, 30);
//        ship.setOpaque(true);
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

//        t= new Timer(10,rightAction);
//        Timer x = new Timer(5);
//        t.start();

//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
//        panel.setBackground(Color.black);
//        panel.setVisible(true);
//            frame = new JFrame("Snake Demo");
            this.add(ship);
//
//            for (int i = 0; i < enemies.size(); i++) {
//                this.add(enemies.get(i));
//            }
//        frame.add(enemy);
//            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//        frame.pack();
//            this.setResizable(false);
//            this.setLayout(null);
//            this.setVisible(true);
//            this.s,/etLocationRelativeTo(null);
        }

    public void paint(Graphics g) {

        super.paint(g); // paint background

        Graphics2D g2D = (Graphics2D) g;

        //g2D.drawImage(backgroundImage, 0, 0, null);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
//                enemy = new JLabel(alien);
                g2D.drawImage(alien.getImage(),x+i*30,y+j*30, null);

//                enemy.setBounds(i*40, j*40, 30, 30);
//                enemies.add(alien);

            }
        }

    }

        @Override
        public void actionPerformed(ActionEvent e) {
            int direction = 5;
            if(x+9*40>=WIDTH-alien.getIconWidth()) {
                x = x + direction;

            }else{
                x = x - direction;

            }
            x = x + direction;

//            if(y>=PANEL_HEIGHT-enemy.getHeight(null) || y<0) {
//                yVelocity = yVelocity * -1;
//            }
//            y = y + yVelocity;
            repaint();
        }

        public class UpAction extends AbstractAction   {
            @Override
            public void actionPerformed(ActionEvent e) {
//            for (int i = 0; i < 10; i++)
                ship.setLocation(ship.getX(), ship.getY()-movement);

            }
        }
        public class DownAction extends AbstractAction{
            @Override
            public void actionPerformed(ActionEvent e) {
//            for (int i = 0; i < 10; i++)
                ship.setLocation(ship.getX(), ship.getY() + movement);
            }
        }
        public class LeftAction extends AbstractAction{
            @Override
            public void actionPerformed(ActionEvent e) {
//            for (int i = 0; i < 10; i++)

                ship.setLocation(ship.getX() - movement, ship.getY());

            }
        }
        public class RightAction extends AbstractAction{

            @Override
            public void actionPerformed(ActionEvent e) {
//            for (int i = 0; i < 100; i++)
                ship.setLocation(ship.getX()+movement, ship.getY());
            }
        }

}
