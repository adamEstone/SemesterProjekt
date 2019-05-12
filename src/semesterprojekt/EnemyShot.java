/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.*;

/**
 *
 * @author Mads
 */
public class EnemyShot extends Enemy
{

    int spriteID = 2;

    private int speedY;

    public SoundPlayer shotSound = new SoundPlayer("EnemyShot1.wav");

    EnemyShot(int Xpos_in, int Ypos_in, int width_in, int height_in)
    {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        width = width_in;
        height = height_in;
        shotSound.play(0);

    }

    @Override
    public void move()
    {
        this.yPos = this.yPos + 2;
    }

    @Override
    public Rectangle bounds()
    {
        return new Rectangle(this.xPos, this.yPos, width, height);
    }

    @Override
    public void draw(Graphics2D a)
    {//ryk fjende
        //AffineTransform orgTrans;

        a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos, width, height, null);
        //a.scale(0.3,0.3);          // flyt, skaler og rotér

    }

}
