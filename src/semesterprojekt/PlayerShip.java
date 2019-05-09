package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlayerShip extends BaseObject {

    public int spriteID = 0;
    private BufferedImage TheImage;

    private double lives = 0;
    private int weapon = 0;

    
    public PlayerShip(int x, int y) {

        xPos = x;
        yPos = y;

    }
    /*
    public PlayerShip(int x, int y, int width, int heigth) {

        xPos = x;
        yPos = y;

    }*/

    @Override
    public void draw(Graphics2D G2D) {
        
        //G2D.drawOval(MultiMuselytter.mouseX - (50 / 2), MultiMuselytter.mouseY - (50 / 2), 50, 50);

        G2D.drawImage(ResourceClass.LoadedSprites.get(spriteID), xPos, yPos, null);
        
    }

    public void loseLife() {

        lives--;

    }
    
    
    public Rectangle bounds(){
        return new Rectangle(this.xPos, this.yPos,250,250);
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
