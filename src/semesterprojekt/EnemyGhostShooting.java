
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.*;
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
    

    EnemyGhostShooting(int xpos_in, int ypos_in) {
        System.out.println("EnemyGhostShooting constructor run");
        this.xPos = xpos_in;
        this.yPos = ypos_in;
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
          int maxSped = 4;
          int rand = r.nextInt(maxSped-minSped) + minSped;
          
          if (this.speedX > 0){
              this.speedX = rand;
          } else{
              this.speedX = -rand;
          }
          
          
          rand = r.nextInt(maxSped-minSped) + minSped;
          
          if (this.speedY > 0){
              this.speedY = rand;
          } else{
              this.speedY = -rand;
          }
          
          //X constrain
          if (this.xPos <= 0){
              this.speedX = -this.speedX;
              xPos = 2;
          }
          if (this.xPos >= 540) {
              this.speedX = -this.speedX;  
              xPos = 538;
        }
        
          
          this.xPos = this.xPos + this.speedX;
          //////
          
          //Y constrain
          if (this.yPos <= 30){
              this.speedY = -this.speedY;
              yPos = 28;
          }
          
          if (this.yPos >= 300) {
            this.speedY = -this.speedY;
            yPos = 298;
        }
          
          this.yPos = this.yPos + this.speedY;
        
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
         this.speedY = -this.speedY;
       
    }
    
    @Override   
    public Rectangle bounds(){
        return new Rectangle(this.xPos, this.yPos,48,48);
    }

    @Override
    public void draw(Graphics2D a) {//ryk fjende
    //    AffineTransform orgTrans;

        a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos, null);
        //a.scale(0.3,0.3);          // flyt, skaler og rotér
        
    }

}
