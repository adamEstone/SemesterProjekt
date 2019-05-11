/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


/**
 *
 * @author Adam
 */
public class Backgrounds {

    ArrayList<Star> StarPosition = new ArrayList<Star>();

    private int numberOfStart = 75;

    Backgrounds() {//cons

        for (int i = 0; i < numberOfStart; i++) { //make star objects
            StarPosition.add(new Star());
        }

    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.BLACK);//tegn sort skærm
        g2.fillRect(0, 0, AreaCoordinates.AC.getPlayableAreaX(), AreaCoordinates.AC.getPlayableAreaY());

        for (Star thisStar : StarPosition) {//tegn og flyt stjerner

            g2.setColor(thisStar.getStarColor());
            g2.fillRect(thisStar.getLocation().x, thisStar.getLocation().y, 2, 2);

            thisStar.move();

        }

    }

    public void moveStartsFast(boolean highspeed) {
        for (Star thisStar : StarPosition) {
        thisStar.setSpeedUp(highspeed);
        }
    }

}
