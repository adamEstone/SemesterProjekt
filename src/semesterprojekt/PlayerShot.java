/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.*;
import java.util.Random;

/**
 *
 * @author Mads
 */
public class PlayerShot extends Enemy {

    int spriteID = 3;

    private int speedY;
    SoundPlayer shotSound = new SoundPlayer("LaserGun1.wav");

    PlayerShot(int Xpos_in, int Ypos_in) {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;

        shotSound.play(0);

    }

    PlayerShot(int Xpos_in, int Ypos_in, int width_in, int height_in) {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        this.width = width_in;
        this.height = height_in;
        shotSound.play(0);

    }

    @Override
    public void move() {
        this.yPos = this.yPos - 5;
    }

    @Override
    public Rectangle bounds() {
        return new Rectangle(this.xPos, this.yPos, 30, 30);
    }

    @Override
    public void draw(Graphics2D a) {//tegn 

        a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos,this.width,this.height, null);

    }

}
