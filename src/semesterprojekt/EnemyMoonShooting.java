/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Adam
 */
public class EnemyMoonShooting extends Enemy {

    int spriteID = 4;

    private int weapon = 1;
    private double lives = 1;

    private int speedX;
    private int speedY;//not used
    private int imageDirection = -1; //changes between: 1 and -1

    private Random r = new Random();
    
    EnemyMoonShooting(int Xpos_in, int Ypos_in) {
        System.out.println("EnemyMoonShooting constructor run");
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
    }

    EnemyMoonShooting(int Xpos_in, int Ypos_in, int width_in, int height_in) {
        System.out.println("EnemyMoonShooting constructor run");
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        this.width = width_in;
        this.height = height_in;

    }

    EnemyMoonShooting(int Xpos_in, int Ypos_in, double Lives_in, int Weapon_in) {
        System.out.println("EnemyMoonShooting constructor run");
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        this.lives = Lives_in;
        this.weapon = Weapon_in;
    }

    @Override
    public void explode() {
        SoundPlayer ExplosionSound = new SoundPlayer("Explosion.wav");
        ExplosionSound.play(0);
    }

    @Override
    public void move() {//ryk fjende

        int minSped = 1;
        int maxSped = 5;
        int rand = r.nextInt(maxSped - minSped) + minSped;

        if (this.speedX > 0) {
            this.speedX = rand;
        } else {
            this.speedX = -rand;
        }

        rand = r.nextInt(maxSped - minSped) + minSped;

        //X constrain
        if (this.xPos <= 0) {
            this.speedX = -this.speedX;
            xPos = 2;
            imageDirection = imageDirection * -1;
        }
        if (this.xPos + this.width >= AreaCoordinates.AC.getPlayableAreaX()) {
            this.speedX = -this.speedX;
            xPos = AreaCoordinates.AC.getPlayableAreaX() - width;
            imageDirection = imageDirection * -1;
        }

        this.xPos = this.xPos + this.speedX;

    }

    @Override
    public Boolean shoot() {//ryk fjende

        int minShot = 1;
        int maxShot = 500;
        int rand = r.nextInt(maxShot - minShot) + minShot;

        if (rand == 2) {
            return true;
        }
        return false;
    }

    @Override
    public void changeDirection() {
        this.speedX = -this.speedX;
        imageDirection = imageDirection * -1; //change
    }

    @Override
    public Rectangle bounds() {
        return new Rectangle(this.xPos, this.yPos, this.width, this.height);
    }

    @Override
    public void draw(Graphics2D a) {//ryk fjende
        //AffineTransform orgTrans = a.getTransform();
        //a.scale(2,1);          // flyt, skaler og roté
        Image theImage = ResourceClass.LoadedSprites.get(spriteID);
        if (imageDirection == 1) {
            a.drawImage(theImage, this.xPos, this.yPos, this.width, this.height, null);
        } else {
            a.drawImage(theImage, this.width + this.xPos, this.yPos, -this.width, this.height, null);
        }
    }
}
