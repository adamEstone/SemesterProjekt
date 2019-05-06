/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Adam
 */
public class EnemyGhostShooting extends Enemy {

    int spriteID = 1;

    private int weapon = 1;
    private double lives = 1;
    
    private int speedX;
    private int speedY;

    EnemyGhostShooting(int Xpos_in, int Ypos_in) {
        System.out.println("WOWOWOOWOWOWOWOW");
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;

    }

    EnemyGhostShooting(int Xpos_in, int Ypos_in, double Lives_in, int Weapon_in) {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        this.lives=Lives_in;
        this.weapon = Weapon_in;
    }

    @Override
    public void move() {//ryk fjende
          //System.out.println("HALLO!!!!!");
          
          Random r = new Random();
          
          int minSped = 1;
          int maxSped = 5;
          int rand = r.nextInt(maxSped-minSped) + minSped;
          
          if (speedX > 0){
              speedX = rand;
          } else{
              speedX = -rand;
          }
          
          rand = r.nextInt(maxSped-minSped) + minSped;
          
          
          if (speedY > 0){
              speedY = rand;
          } else{
              speedY = -rand;
          }
          
          
          if (xPos < 0 || xPos > 600){
              speedX = -speedX;
              
          }
          
          xPos = xPos + speedX;
          
          
          if (yPos < 0 || yPos >= 300){
              speedY = -speedY;
              
          }
          
          yPos = yPos + speedY;
          
          System.out.println(yPos);
        
    }

    @Override
    public void shoot() {//ryk fjende
        
        
        
    }

    @Override
    public Graphics2D draw(Graphics2D a) {//ryk fjende
        AffineTransform orgTrans;

        a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos, null);
        //a.scale(0.3,0.3);          // flyt, skaler og rotér
        return a;
    }

}
