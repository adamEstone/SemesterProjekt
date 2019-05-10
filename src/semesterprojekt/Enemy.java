/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author Adam
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends BaseObject {

    public int spriteID = 0;
    private BufferedImage TheImage;

    private double lives = 1.0;
    private int weapon = 0;

    public int moveSpeed = 1;

    public void move() {//ryk fjende

    }
    
    public Rectangle bounds(){ //Bruges til Collision detection
        
        return null;
    }

    public Boolean shoot() {
        
        return false;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public double getLives() {
        return lives;
    }
    
        public void changeDirection(){
    }

    public void setLives(double lives) {
        this.lives = lives;
    }

}
