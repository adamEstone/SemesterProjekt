/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
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
    private int imageDirection = 1; //changes between: 1 and -1
    
    EnemyMoonShooting(int Xpos_in, int Ypos_in) {
        System.out.println("EnemyMoonShooting constructor run");
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
    }
  
    EnemyMoonShooting(int Xpos_in, int Ypos_in, double Lives_in, int Weapon_in) {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        this.lives=Lives_in;
        this.weapon = Weapon_in;
    }

    @Override
    public void move() {//ryk fjende

          Random r = new Random();
          
          int minSped = 1;
          int maxSped = 5;
          int rand = r.nextInt(maxSped-minSped) + minSped;
          
          if (this.speedX > 0){
              this.speedX = rand;
          } else{
              this.speedX = -rand;
          }
          
          rand = r.nextInt(maxSped-minSped) + minSped;
          
          //X constrain
          if (this.xPos <= 0){
              this.speedX = -this.speedX;
              xPos = 2;
              imageDirection=imageDirection*-1;
          }
          if (this.xPos >= 540) {
              this.speedX = -this.speedX;  
              xPos = 538;
              imageDirection=imageDirection*-1;
        }
        
          this.xPos = this.xPos + this.speedX;

        
    }

    @Override
    public Boolean shoot() {//ryk fjende
        Random r = new Random();
        int minShot = 1;
        int maxShot = 500;
        int rand = r.nextInt(maxShot-minShot) + minShot;
        
        if (rand == 2){
            return true;
        }
        return false;
    }
    
    @Override
    public void changeDirection(){
        this.speedX = -this.speedX;
        imageDirection=imageDirection*-1; //change
    }
    
    @Override   
    public Rectangle bounds(){
        return new Rectangle(this.xPos, this.yPos,48,48);
    }

    @Override
    public void draw(Graphics2D a) {//ryk fjende
        //AffineTransform orgTrans = a.getTransform();
        //a.scale(2,1);          // flyt, skaler og roté
        BufferedImage theImage=ResourceClass.LoadedSprites.get(spriteID);
        
        a.drawImage(theImage, this.xPos, this.yPos, imageDirection*theImage.getWidth(),theImage.getHeight(), null);
 
        //a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos, orgTrans, null);
    }
}
