package semesterprojekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


public class PlayerShip extends BaseObject {

    public int spriteID = 0;
    private Image theImage=ResourceClass.LoadedSprites.get(spriteID);
    public Stats statsRef;
    
    public PlayerShip(int x, int y, int w, int h) {

        xPos = x;
        yPos = y;
        width = w;
        height = h;

    }
    
    public void draw(Graphics2D G2D) {                        
        G2D.drawImage(theImage, xPos, yPos, width, height, null);
    }

    public void loseLife() {

        //lives--;

    }
    
    
    public Rectangle bounds(){//bounds is smaller than the player ship which feels nicer
        return new Rectangle(this.xPos+(width/3), this.yPos+(height/3), width-((width/3)*2), height-((height/3)*2));
    }

    public void gainLife() {

        //lives++;

    }

    public Image getImage() {
        return theImage;
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
}
