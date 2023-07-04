import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpaceShip {

    JLabel ship;
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    int movement= 15;
    int panelHeight;
    int panelWidth;
    int moveShipBulletSpeed ;
    int moveShipBulletTimer ;

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
        ship.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        ship.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        ship.getActionMap().put("upAction", upAction);
        ship.getActionMap().put("leftAction", leftAction);
        ship.getActionMap().put("rightAction", rightAction);
        ship.getActionMap().put("downAction", downAction);
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
            if(ship.getY() > panelHeight-100)
                ship.setLocation(ship.getX(), ship.getY()-movement);
        }
    }
    public class DownAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ship.getY() <panelHeight-70 )
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
            if(ship.getX() < panelWidth-40 )
                ship.setLocation(ship.getX()+movement, ship.getY());
        }
    }
}
