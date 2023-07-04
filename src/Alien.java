import javax.swing.*;
import java.awt.*;

public class Alien {
    private static int moveAlienBulletSpeed;
    private static int moveAlienBulletTimer;
    private JLabel alienLabel;

    public JLabel getAlienLabel() {
        return alienLabel;
    }

    public void setAlienLabel(JLabel alienLabel) {
        this.alienLabel = alienLabel;
    }



   Alien(){
       initializeAlienLabel();
   }
    private void initializeAlienLabel() {
        ImageIcon alienImage = new ImageIcon("src/alliens.png");
        Image img2 = alienImage.getImage();
        Image alien2 = img2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        alienImage.setImage(alien2);
        alienLabel = new JLabel(alienImage);

    }

    public static int getMoveAlienBulletSpeed() {
        return moveAlienBulletSpeed;
    }

    public static void setMoveAlienBulletSpeed(int moveAlienBulletSpeed1) {
        moveAlienBulletSpeed = moveAlienBulletSpeed1;
    }

    public static int getMoveAlienBulletTimer() {
        return moveAlienBulletTimer;
    }

    public static void setMoveAlienBulletTimer(int moveAlienBulletTimer1) {
        moveAlienBulletTimer = moveAlienBulletTimer1;
    }
}
