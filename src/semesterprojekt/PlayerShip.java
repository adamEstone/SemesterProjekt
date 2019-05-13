package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlayerShip extends BaseObject {

    public int spriteID = 0;
    private BufferedImage TheImage;
    Stats statsRef;

    
    public PlayerShip(int x, int y, int w, int h) {

        xPos = x;
        yPos = y;
        width = w;
        height = h;

    }

    @Override
    public void draw(Graphics2D G2D) {

        G2D.drawImage(ResourceClass.LoadedSprites.get(spriteID), xPos, yPos,width,height, null);
        
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
}
