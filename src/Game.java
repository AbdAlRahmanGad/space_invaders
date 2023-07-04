import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements ActionListener {
    JFrame frame;
    int WIDTH = 500;
    int HEIGHT = 500;

    JLabel scoreLabel;
    JLabel livesLabel;
    int score =0;
    int lives =3;
    int start = 0;








    int enemyBulletNumber=0;
    int enemyBulletNumber1=0;
    int bulletNumber = 0;
    int bulletNumber1 =0;


    int direction = 1;
    Timer moveAlien;
    Timer moveBullet;
    Timer setBullet;
    Timer moveEnemyBullet;
    Timer setEnemyBullet;

    ArrayList<Alien> aliens = new ArrayList<>();
    SpaceShip ship = new SpaceShip(HEIGHT,WIDTH);

    Game(int myBulletSpeed , int enemyBulletSpeed, int screenMode,int bulletMode){
        /// place aliens
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                Alien alien  =new Alien();
                alien.getAlienLabel().setBounds(i*40, j*40, 30, 30);
                aliens.add(alien);
            }
        }

        if(myBulletSpeed == 1 ){
             ship.setMoveShipBulletSpeed(25);
             ship.setMoveShipBulletTimer(2000);
        }else{
            ship.setMoveShipBulletSpeed(50);
            ship.setMoveShipBulletTimer(3000);
        }
        if(enemyBulletSpeed == 1 ){
            Alien.setMoveAlienBulletSpeed(25);
            Alien.setMoveAlienBulletTimer(2000);
        }else{
            Alien.setMoveAlienBulletSpeed(50);
            Alien.setMoveAlienBulletTimer(3000);
        }
        /// score Label
        scoreLabel = new JLabel("Score : "+score);
        scoreLabel.setBounds(0,200,100,30);
        /// score Label
        livesLabel = new JLabel("lives left : "+lives);
        livesLabel.setBounds(0,350,100,30);

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
//        ship.setBounds(WIDTH-(WIDTH/2), HEIGHT-75, 30, 30);
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

        frame.add(ship.getShip());
        frame.add(bullet);
        for (int i = 0; i < aliens.size(); i++) {
            frame.add(aliens.get(i).getAlienLabel());
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

         setEnemyBullet = new Timer(Alien.getMoveAlienBulletTimer(),new AbstractAction("bullet begin enemy") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = enemyBullets.get(enemyBulletNumber);

                int randomNum = (int) (Math.random() * ((aliens.size())));

                JLabel enemyBullet = aliens.get(randomNum).getAlienLabel();
                b.setBounds(enemyBullet.getX(),enemyBullet.getY(),50,50);
                enemyBulletNumber1 = enemyBulletNumber;
                frame.add(b);
                enemyBulletNumber = (enemyBulletNumber+1)%10;
            }
        });
        setEnemyBullet.start();
        moveEnemyBullet = new Timer(Alien.getMoveAlienBulletSpeed(),new AbstractAction("enemy bullet move") {
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
                if(b.getY()>= ship.getShip().getY()-20&& b.getY()<= ship.getShip().getY()){
                    if(b.getX()>= ship.getShip().getX()-27&& b.getX()<= ship.getShip().getX()+5){
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
         setBullet = new Timer(ship.getMoveShipBulletTimer(),new AbstractAction("bullet begin") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = bullets.get(bulletNumber);
                b.setBounds(ship.getShip().getX()-10,ship.getShip().getY()-15,50,50);
                bulletNumber1 = bulletNumber;
                frame.add(b);
                bulletNumber = (bulletNumber+1)%3;
            }
        });
        setBullet.start();
         moveBullet = new Timer(ship.getMoveShipBulletSpeed(),new AbstractAction("bullet move") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = bullets.get(bulletNumber1);
                start++;
                if(start <= 50){
                    return;
                }
                for (int i = 0; i <aliens.size() ; i++) {
                    JLabel enemy = aliens.get(i).getAlienLabel();
                    if(b.getY()>= enemy.getY()&& b.getY()<= enemy.getY()+20){
                        if(b.getX()+20>= enemy.getX()-10&& b.getX()+20<= enemy.getX()+30){
                                frame.remove(enemy);
                                aliens.remove(i);
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
        int enemiesLeft = aliens.size();
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

        if(aliens.get(aliens.size()-1).getAlienLabel().getX()+30 >= 500 || aliens.get(0).getAlienLabel().getX() <= 0){
            direction *=-1;
        }
        for (int i = 0; i < aliens.size(); i++) {
            JLabel enemy = aliens.get(i).getAlienLabel();
            enemy.setLocation(enemy.getX()-direction,enemy.getY());
        }
    }

}
