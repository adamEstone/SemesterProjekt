/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Adam
 */
public class BaseObject {
    
    public String NameOfSprite = "";
    private BufferedImage TheImage;

    public int xPos = 0;
    public int yPos = 0;
    public int width = 50;
    public int height = 50;
    
    public void draw(Graphics2D g2){
    
    }
    
}
