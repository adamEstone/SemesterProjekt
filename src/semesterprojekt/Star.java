/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Adam
 */
public class Star {

    private Point location = new Point(0,0) ;

    private double speed = 1.0;

    Star() {

        Random r = new Random();
        location.x = r.nextInt(600);
        location.y = r.nextInt(700);

    }

    public void move() {
        location.y += (int) speed;//opærksom på float!
        
        if (location.y > 700) {
            location.y = 0;
        }
        
    }

    public Point getLocation() {
        return location;
    }

    public double getSpeed() {
        return speed;
    }

}
