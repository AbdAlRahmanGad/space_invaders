import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SpaceShip {

   private JLabel ship;
    private Action upAction;
    private Action downAction;
    private Action leftAction;
    private Action rightAction;

    private Action upActionR;
    private Action downActionR;
    private Action leftActionR;
    private Action rightActionR;



    private boolean superBullet;


    private final int movement = 3;
    private int panelHeight;
    private int panelWidth;
    private int moveShipBulletSpeed ;
    private int moveShipBulletTimer ;

    private boolean isRight;
    private boolean isLeft;
    private boolean isUp;
    private boolean isDown;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
    public boolean isSuperBullet() {
        return superBullet;
    }

    public void setSuperBullet(boolean superBullet) {
        this.superBullet = superBullet;
    }

    public int getMovement() {
        return movement;
    }
    public boolean isRight() {
        return isRight;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean isDown() {
        return isDown;
    }

    public JLabel getShip() {
        return ship;
    }

    public void setShip(JLabel ship) {
        this.ship = ship;
    }

    public int getMoveShipBulletSpeed() {
        return moveShipBulletSpeed;
    }


    public void setMoveShipBulletSpeed(int moveShipBulletSpeed) {
        this.moveShipBulletSpeed = moveShipBulletSpeed;
    }

    public int getMoveShipBulletTimer() {
        return moveShipBulletTimer;
    }

    public void setMoveShipBulletTimer(int moveShipBulletTimer) {
        this.moveShipBulletTimer = moveShipBulletTimer;
    }

    SpaceShip(int panelHeight, int panelWidth){
            this.panelHeight = panelHeight;
            this.panelWidth = panelWidth;
        initializeShipLabel();
        initializekeyBindings();


        }

    private void initializekeyBindings() {
        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        upActionR = new UpActionR();
        downActionR = new DownActionR();
        leftActionR = new LeftActionR();
        rightActionR = new RightActionR();
        ship.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        ship.getActionMap().put("upAction", upAction);
        ship.getActionMap().put("leftAction", leftAction);
        ship.getActionMap().put("rightAction", rightAction);
        ship.getActionMap().put("downAction", downAction);

        ship.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "upActionR");
        ship.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "downActionR");
        ship.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "leftActionR");
        ship.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "rightActionR");
        ship.getActionMap().put("upActionR", upActionR);
        ship.getActionMap().put("leftActionR", leftActionR);
        ship.getActionMap().put("rightActionR", rightActionR);
        ship.getActionMap().put("downActionR", downActionR);
    }

    private void initializeShipLabel() {
        ImageIcon spaceShip = new ImageIcon("src/spaceship.png");
        Image img = spaceShip.getImage();
        Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        spaceShip.setImage(newimg);
        ship = new JLabel(spaceShip);
        ship.setBounds(panelWidth-(panelHeight/2), panelHeight-75, 30, 30);
    }


    public class UpAction extends AbstractAction   {
        @Override
        public void actionPerformed(ActionEvent e) {
           isUp = true;
        }
    }
    public class UpActionR extends AbstractAction   {
        @Override
        public void actionPerformed(ActionEvent e) {
            isUp = false;
        }
    }
    public class DownAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            isDown = true;
        }
    }
    public class DownActionR extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            isDown = false;
        }
    }
    public class LeftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
           isLeft = true;
        }
    }
    public class LeftActionR extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            isLeft = false;
        }
    }
    public class RightAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            isRight=true;
        }
    }

    public class RightActionR extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            isRight=false;
        }
    }


}
