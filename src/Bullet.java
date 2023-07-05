import javax.swing.*;
import java.awt.*;

public class Bullet {
    public JLabel getBulletLabel() {
        return bulletLabel;
    }

    public void setBulletLabel(JLabel bulletLabel) {
        this.bulletLabel = bulletLabel;
    }

    private JLabel bulletLabel = new JLabel();
    Bullet(){
        ImageIcon bulletIcon = new ImageIcon("src/bulletFinal.png");
        Image bullet2 = bulletIcon.getImage();
        Image bullet3 = bullet2.getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH);
        bulletIcon.setImage(bullet3);
        bulletLabel = new JLabel(bulletIcon);
    }

}
