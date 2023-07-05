import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements Runnable {
    private JFrame frame;
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JLabel scoreLabel;
    private JLabel livesLabel;
    private int score =0;
    private int lives =3;
    private int start = 0;
    private final int FPS = 60;



    private final int bulletNumber = 0;


    private int direction = 1;

    private Thread gameThread;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private SpaceShip ship = new SpaceShip(HEIGHT,WIDTH);
    private  ArrayList<Bullet> spaceBullets = new ArrayList<>();
    private  ArrayList<Bullet> alienBullets = new ArrayList<>();
    private Audios audios ;
    Game(int myBulletSpeed , int enemyBulletSpeed, int screenMode,int bulletMode) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        audios = new Audios();
        if(bulletMode == 2)
        ship.setSuperBullet(true);
      else
        ship.setSuperBullet(false);

        gameThread = new Thread(this);
      gameThread.start();
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
        moveAlienTimer();
//        for (int i = 0; i < 1; i++) {
            Bullet bulletShip = new Bullet();
            spaceBullets.add(bulletShip);
//        }
        ship.setBullets(spaceBullets);

        for (int i = 0; i < 3; i++) {
            Bullet bulletEnemy = new Bullet();
            alienBullets.add(bulletEnemy);
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
        for (int i = 0; i < aliens.size(); i++) {
            frame.add(aliens.get(i).getAlienLabel());
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        setAlienBullet();
        startMoveAlienBullet();
        setShipBullet();
       startMoveShipBullet();

        frame.add(livesLabel);
        frame.add(scoreLabel);
    }

    private void moveAlienTimer() {
        moveAlien = new Timer(50, new ActionListener() {
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
        });
        moveAlien.start();
    }
    private void startMoveShipBullet() {
        moveBullet = new Timer(ship.getMoveShipBulletSpeed(),new AbstractAction("bullet move") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                JLabel b = ship.getBullets().get(bulletNumber).getBulletLabel();
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
                            if(!ship.isSuperBullet()){
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
    }

    private void setShipBullet() {
        setBullet = new Timer(ship.getMoveShipBulletTimer(),new AbstractAction("bullet begin") {
            @Override
            public void actionPerformed( ActionEvent e ) {

                JLabel b = ship.getBullets().get(bulletNumber).getBulletLabel();
                b.setBounds(ship.getShip().getX()-10,ship.getShip().getY()-15,50,50);
                frame.add(b);
            }
        });
        setBullet.start();
    }

    private void startMoveAlienBullet() {
        moveAlienBullet = new Timer(Alien.getMoveAlienBulletSpeed(),new AbstractAction("enemy bullet move") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                int size = 1;
                if(aliens.size()>=3){
                    size = 3;
                } else if (aliens.size() == 2) {
                    size = 2;
                }
                for (int j = 0; j < size; j++) {
                    JLabel b = alienBullets.get(j).getBulletLabel();
                    for (int i = 0; i <ship.getBullets().size(); i++) {
                        JLabel shipBullet = ship.getBullets().get(i).getBulletLabel();
                        if(shipBullet.getY()
                                >= b.getY()
                                && shipBullet.getY()
                                <= b.getY()+20){
                            if(shipBullet.getX()+10>= b.getX()&& shipBullet.getX()+20<= b.getX()+30){
                                shipBullet.setLocation(-50, -50);
                                b.setLocation(-50, -50);
                                break;
                            }
                        }
                    }
                }

                for (int j = 0; j < size; j++) {
                    JLabel b = alienBullets.get(j).getBulletLabel();
                    if (b.getY() >= ship.getShip().getY() - 20 && b.getY() <= ship.getShip().getY()) {
                        if (b.getX() >= ship.getShip().getX() - 27 && b.getX() <= ship.getShip().getX() + 5) {
                            b.setLocation(-50, -50);
                            lives--;
                            livesLabel.setText("lives left : " + lives);
                            if (lives == 0) {
                                endGame();
                            }
                        }
                    }
                }
                for (int j = 0; j < size; j++) {
                    JLabel b = alienBullets.get(j).getBulletLabel();
                    if (b.getY() <= 530) {
                        b.setLocation(b.getX(), b.getY() + 10);
                    }
                }
                if(size == 2){
                    JLabel b = alienBullets.get(2).getBulletLabel();
                    if (b.getY() <= 530) {
                        b.setLocation(b.getX(), b.getY() + 10);
                    }
                } else if (size == 1 ) {
                    JLabel b = alienBullets.get(1).getBulletLabel();
                    if (b.getY() <= 530) {
                        b.setLocation(b.getX(), b.getY() + 10);
                    }
                }
            }
        });
        moveAlienBullet.start();
    }

    private Timer moveAlien;
    private Timer moveBullet;
    private Timer setBullet;
    private Timer moveAlienBullet;
    private Timer setAlienBullet;
    public void setAlienBullet() {
        setAlienBullet = new Timer(Alien.getMoveAlienBulletTimer(),new AbstractAction("bullet begin enemy") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                int randomNum = (int) (Math.random() * ((aliens.size())));
                int randomNum2 = (int) (Math.random() * ((aliens.size())));
                int randomNum3 = (int) (Math.random() * ((aliens.size())));
                if(aliens.size() >= 3){
                    while(randomNum == randomNum3 || randomNum2 == randomNum3 || randomNum2 == randomNum){
                         randomNum = (int) (Math.random() * ((aliens.size())));
                         randomNum2 = (int) (Math.random() * ((aliens.size())));
                         randomNum3 = (int) (Math.random() * ((aliens.size())));
                    }
                    JLabel enemyPos = aliens.get(randomNum).getAlienLabel();
                    alienBullets.get(0).getBulletLabel().setBounds(enemyPos.getX(),enemyPos.getY(),50,50);
                    frame.add(alienBullets.get(0).getBulletLabel());

                    JLabel enemyPos2 = aliens.get(randomNum2).getAlienLabel();
                    alienBullets.get(1).getBulletLabel().setBounds(enemyPos2.getX(),enemyPos2.getY(),50,50);
                    frame.add(alienBullets.get(1).getBulletLabel());
//
                    JLabel enemyPos3 = aliens.get(randomNum3).getAlienLabel();
                    alienBullets.get(2).getBulletLabel().setBounds(enemyPos3.getX(),enemyPos3.getY(),50,50);
                    frame.add(alienBullets.get(2).getBulletLabel());

                } else if (aliens.size() == 2) {
                    JLabel enemyPos = aliens.get(0).getAlienLabel();
                    alienBullets.get(0).getBulletLabel().setBounds(enemyPos.getX(),enemyPos.getY(),50,50);
                    frame.add(alienBullets.get(0).getBulletLabel());

                    JLabel enemyPos2 = aliens.get(1).getAlienLabel();
                    alienBullets.get(1).getBulletLabel().setBounds(enemyPos2.getX(),enemyPos2.getY(),50,50);
                    frame.add(alienBullets.get(1).getBulletLabel());

                }else if (aliens.size() == 1){
                    JLabel enemyPos = aliens.get(0).getAlienLabel();
                    alienBullets.get(0).getBulletLabel().setBounds(enemyPos.getX(),enemyPos.getY(),50,50);
                    frame.add(alienBullets.get(0).getBulletLabel());

                }
            }
        });
        setAlienBullet.start();
    }
    private void setScore(){
        int enemiesLeft = aliens.size();
        int newScore =  20 * (50-enemiesLeft);
        scoreLabel.setText("Score : "+newScore );
        score = newScore;
        if(score == 1000){
            endGame();
        }
    }
    private void endGame(){
          frame.setVisible(false);
          audios.playEnd();
          moveBullet.stop();
          setBullet.stop();
          moveAlien.stop();
          moveAlienBullet.stop();
         setAlienBullet.stop();
          gameThread = null;
          new Frame(score);
          frame.dispose();
    }


    @Override
    public void run() {

        double drawiInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawiInterval;

            lastTime = currentTime;
            if (delta >= 1) {
                    update();
                    delta--;
            }
        }
    }private void update(){

        if(ship.isRight()&&ship.getShip().getX()+ship.getShip().getWidth()<WIDTH){
            ship.getShip().setLocation(ship.getShip().getX()+ship.getMovement(),ship.getShip().getY());
        } else if (ship.isLeft()&&ship.getShip().getX()>0) {
            ship.getShip().setLocation(ship.getShip().getX()-ship.getMovement(),ship.getShip().getY());
        }
        if (ship.isUp() && ship.getShip().getY() > HEIGHT-120) {
            ship.getShip().setLocation(ship.getShip().getX(),ship.getShip().getY()-ship.getMovement());
        } else if (ship.isDown()&&ship.getShip().getY()+60<HEIGHT) {
            ship.getShip().setLocation(ship.getShip().getX(),ship.getShip().getY()+ship.getMovement());
        }

    }
}
