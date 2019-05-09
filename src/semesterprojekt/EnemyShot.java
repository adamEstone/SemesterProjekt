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
public class EnemyShot extends Enemy {
    
    int spriteID = 2;

    
    private int speedY;

    EnemyShot(int Xpos_in, int Ypos_in) {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        

    }
        
        
    @Override
        public void move(){
            this.yPos = this.yPos + 2;
        }
        
            @Override   
    public Rectangle bounds(){
        return new Rectangle(this.xPos, this.yPos,30,30);
    }
        
    
     @Override
    public void draw(Graphics2D a) {//ryk fjende
    //    AffineTransform orgTrans;

        a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos, null);
        //a.scale(0.3,0.3);          // flyt, skaler og rotér
        
    }
    
}
