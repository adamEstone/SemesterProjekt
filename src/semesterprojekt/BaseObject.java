/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.image.BufferedImage;

/**
 *
 * @author Adam
 */
public class BaseObject {
    
    public String NameOfSprite = "SpaceShip.png";
    private BufferedImage TheImage;

    private int weapon = 0;

    protected int xPos = 0;
    protected int yPos = 0;
    protected int width = 50;
    protected int heigth = 50;
    
}
