package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PlayerShip extends BaseObject {

    public int spriteID = 0;
    private BufferedImage TheImage;

    private double lives = 0;
    private int weapon = 0;

    public PlayerShip(int x, int y, int width, int heigth) {

        xPos = x;
        yPos = y;

    }

    public Graphics2D draw(Graphics2D G2D) {
        
        G2D.drawImage(ResourceClass.LoadedSprites.get(spriteID), xPos, yPos, null);
        return G2D;
    }

    public void loseLife() {

        lives--;

    }

    public void gainLife() {

        lives++;

    }

    public BufferedImage getImage() {
        return TheImage;
    }

    public void setXpos(int x) {
        xPos = x;
        System.out.println("set");
    }

    public void setYpos(int y) {
        yPos = y;
    }

    public int getXpos() {
        return xPos;
    }

    public int getYpos() {
        return yPos;
    }

    public void setLives(double value) {
        lives = value;
    }

    public double getLives() {
        return lives;
    }

}
