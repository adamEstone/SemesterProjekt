/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Adam
 */
public class Star {

    private Point location = new Point(0, 0);

    private int speed = 1;

    private int maxSpeed = 25;
    private int normalSpeed = 1;

    private boolean speedUp = false;

    private int brightness = 255; // min 0 to 255 max

    private long oldMillisMove = 0;

    private Color starColor;


    Star() {

        Random r = new Random();

        setBrightness(r.nextInt(255));//random value
        setStarColor(new Color(255, 200, 255, brightness));

        location.x = r.nextInt(AreaCoordinates.AC.getPlayableAreaX() +
                               AreaCoordinates.AC.getInfoAreaX());
        location.y = r.nextInt(AreaCoordinates.AC.getPlayableAreaY());

    }

    public void move() {

        long millis = (System.currentTimeMillis());
    
        //kør med en standart hastiged på 10 millis .
        //Jo mere stjernerne lyser desto færre millis går der (255 - brightness) .
        //Divider (255 - brightness) med 2 så hastighedsforskellen ikke er så stor på stærke- og svagt-lysende stjerner
        //divider hele udtrykket med speed så Jo højre speed bliver desto mindre bliver resultatet af hele udtrykket:
        //altså dermed (10 + (255 - brightness) / 2) / speed
        if ((oldMillisMove + (10 + (255 - brightness) / 2) / speed) <= millis) {
            oldMillisMove = millis;
            if (speedUp == true && speed < maxSpeed) { //speed up
                speed++;
            }
            if (speedUp == false && speed > normalSpeed) {//slow down
                speed--;
            }

            
            location.y += 1 + speed / 5;//opærksom på float!

        }

        if (location.y > AreaCoordinates.AC.getPlayableAreaY()) {
            location.y = 0;
        }

    }

    public void setStarColor(Color starColor) {
        this.starColor = starColor;
    }

    public Color getStarColor() {
        return starColor;
    }

    public void setSpeedUp(boolean state) {
        speedUp = state;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public Point getLocation() {
        return location;
    }

    public double getSpeed() {
        return speed;
    }

}
