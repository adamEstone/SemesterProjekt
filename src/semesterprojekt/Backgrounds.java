/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Adam
 */
public class Backgrounds {

    ArrayList<Star> StarPosition = new ArrayList<Star>();

    private int numberOfStart = 50;
            
    Backgrounds() {
       
        for (int i = 0; i < numberOfStart; i++) {
            StarPosition.add(new Star());
        }

    }

    public void draw(Graphics2D g2) {
        
        g2.setColor(Color.BLACK);                  // rens skærmen selv
        g2.fillRect(0, 0, 600, 700);
        
        for (Star thisStar : StarPosition) {
            
            int alpha = 150; // 50% transparent
            Color myColour = new Color(255, 200, 255, alpha);
            g2.setColor(myColour);
            
            g2.fillRect(thisStar.getLocation().x, thisStar.getLocation().y, 2, 2);  
            
            thisStar.move();
             
        }

    }

}
