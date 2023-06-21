import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements ActionListener {
    JFrame frame;

    JLabel ship;
    JLabel enemy;
    JLabel scoreLabel;
    JLabel livesLabel;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    int enemyBulletNumber=0;
    int enemyBulletNumber1=0;

    int bulletNumber1 =0;
    int score =0;
    int lives =3;
    int movement= 15;
    int WIDTH = 500;
    int HEIGHT = 500;
    int direction = 1;
    int bulletNumber = 0;
    int start = 0;
    ArrayList<JLabel> enemies = new ArrayList<>();
    Timer moveAlien;
    Timer moveBullet;
    Timer setBullet;
    Timer  moveEnemyBullet;
    Timer setEnemyBullet;
    int moveShipBulletSpeed;
    int moveShipBulletTimer;
    int moveEnemyBulletSpeed;
    int moveEnemyBulletTimer;
    Game(int myBulletSpeed , int enemyBulletSpeed, int screenMode,int bulletMode){
        if(myBulletSpeed == 1 ){
            moveShipBulletSpeed = 25;
                    moveShipBulletTimer = 2000;
        }else{
            moveShipBulletSpeed = 50;
            moveShipBulletTimer = 3000;
        }
        if(enemyBulletSpeed == 1 ){
            moveEnemyBulletSpeed = 25;
                    moveEnemyBulletTimer = 2000;
        }else{
            moveEnemyBulletSpeed = 50;
            moveEnemyBulletTimer = 3000;
        }
        /// score Label
        scoreLabel = new JLabel("Score : "+score);
        scoreLabel.setBounds(0,200,100,30);
        /// score Label
        livesLabel = new JLabel("lives left : "+lives);
        livesLabel.setBounds(0,350,100,30);


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
        ArrayList<JLabel> enemyBullets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JLabel bulletEnem = new JLabel(bullet1);
            enemyBullets.add(bulletEnem);
        }
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


        /// Frame stuff
        frame = new JFrame("Space Invaders");
        if(screenMode == 1){
            livesLabel.setForeground(Color.white);
            scoreLabel.setForeground(Color.white);
            frame.getContentPane().setBackground( Color.BLACK );
        }else {
            livesLabel.setForeground(Color.BLACK);
            scoreLabel.setForeground(Color.BLACK);
            frame.getContentPane().setBackground( Color.WHITE );
        }
        frame.setBackground(Color.gray);

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

         setEnemyBullet = new Timer(moveEnemyBulletTimer,new AbstractAction("bullet begin enemy") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = enemyBullets.get(enemyBulletNumber);

                int randomNum = (int) (Math.random() * ((enemies.size())));

                JLabel enemyBullet = enemies.get(randomNum);
                b.setBounds(enemyBullet.getX(),enemyBullet.getY(),50,50);
                enemyBulletNumber1 = enemyBulletNumber;
                frame.add(b);
                enemyBulletNumber = (enemyBulletNumber+1)%10;
            }
        });
        setEnemyBullet.start();
        moveEnemyBullet = new Timer(moveEnemyBulletSpeed,new AbstractAction("enemy bullet move") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = enemyBullets.get(enemyBulletNumber1);

                for (int i = 0; i <bullets.size() ; i++) {
                    JLabel shipBullet = bullets.get(i);
                    if(shipBullet.getY()>= b.getY()&& shipBullet.getY()<= b.getY()+20){
                        if(shipBullet.getX()+10>= b.getX()&& shipBullet.getX()+20<= b.getX()+30){
//                            frame.remove(shipBullet);
                            shipBullet.setLocation(-50, -50);
                            b.setLocation(-50, -50);
                                break;
                        }
                    }
                }
                if(b.getY()>= ship.getY()-20&& b.getY()<= ship.getY()){
                    if(b.getX()>= ship.getX()-27&& b.getX()<= ship.getX()+5){
//                            frame.remove(shipBullet);
//                        ship.setLocation(ship.getX()+10, ship.getY()+10);
                        b.setLocation(-50,-50);
                        lives--;
                        livesLabel.setText("lives left : "+lives);
                        if(lives == 0 ){
                            endGame();
                        }
                    }
                }

                if(b.getY()<=530) {
                    b.setLocation(b.getX(), b.getY() + 10);
                }
            }
        });
        moveEnemyBullet.start();
         setBullet = new Timer(moveShipBulletTimer,new AbstractAction("bullet begin") {
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
         moveBullet = new Timer(moveShipBulletSpeed,new AbstractAction("bullet move") {
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
                                //  super bullet
                            if(bulletMode == 1){
                                b.setLocation(-50, -50);
                                break;
                            }

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
        frame.add(livesLabel);
        frame.add(scoreLabel);
    }
    public void setScore(){
        int enemiesLeft = enemies.size();
        int newScore =  20 * (50-enemiesLeft);
        scoreLabel.setText("Score : "+newScore );
        score = newScore;
        if(score == 1000){
            endGame();
        }
    }
    public void endGame(){
          frame.setVisible(false);
          moveBullet.stop();
          setBullet.stop();
          moveAlien.stop();
          moveEnemyBullet.stop();
          setEnemyBullet.stop();
          new Frame(score);
          frame.dispose();
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