
package semesterprojekt;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class PlayerShip extends BaseObject {

    /**
     *
     * @author Adam Aron Edelsten
     */
    
    public String NameOfSprite = "SpaceShip.png";
    private BufferedImage TheImage;

    private double lives = 0;
    private int weapon = 0;


    public PlayerShip(int x, int y,int width,int heigth) {

        xPos = x;
        yPos = y;

       // NameOfSprite = "";

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
