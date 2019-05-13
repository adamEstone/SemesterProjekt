/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Adam & Farzad
 */
public class Backgrounds extends BaseObject {

    ArrayList<Star> StarPosition = new ArrayList<Star>();

    private int numberOfStart = 100;
    Stats stats;

    Backgrounds() {//cons

        for (int i = 0; i < numberOfStart; i++) //opret stjerne objekter
        { //make star objects
            StarPosition.add(new Star());
        }

    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.BLACK);//tegn sort skærm
        g2.fillRect(0, 0, AreaCoordinates.AC.getPlayableAreaX()
                + AreaCoordinates.AC.getInfoAreaX(),
                AreaCoordinates.AC.getPlayableAreaY());

        for (Star thisStar : StarPosition)//tegn og flyt stjerner
        {

            g2.setColor(thisStar.getStarColor());
            g2.fillRect(thisStar.getLocation().x, thisStar.getLocation().y, 1, 2);

            thisStar.move();

        }
        
        /////////////don't change the order/////////////
        //draw(g2);
        drawLives(g2, stats);
        drawScore(g2);
        drawLevel(g2);
        drawDivider(g2);
        drawText(g2);
        /////////////don't change the order/////////////

    }

    public void moveStarsFast(boolean highspeed) {
        for (Star thisStar : StarPosition) {
            thisStar.setSpeedUp(highspeed);
        }
    }

    public void drawDivider(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(AreaCoordinates.AC.getPlayableAreaX(),
                0,
                5,
                AreaCoordinates.AC.getInfoAreaY());
    }

    public void drawText(Graphics2D g2) {
        // score
        g2.setColor(Color.RED);
        g2.setFont(new Font("Algerian", Font.PLAIN, 40));
        g2.drawString("SCORE",
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 40);

        // level
        g2.setColor(Color.RED);
        g2.setFont(new Font("Algerian", Font.PLAIN, 40));
        g2.drawString("LEVEL",
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                175);

        //Weapon
        g2.setColor(Color.RED);
        g2.setFont(new Font("Algerian", Font.PLAIN, 40));
        g2.drawString("WEAPON",
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                280);

        // lives tekst
        g2.setColor(Color.RED);
        g2.setFont(new Font("Algerian", Font.PLAIN, 40));
        g2.drawString("LIVES",
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                575);

    }

    public void drawScore(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(stats.getScore()),
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 70);
    }

    public void drawLevel(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(stats.getLevel()),
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 175);
    }

    public void drawLives(Graphics2D g2, Stats stats)
    {
        final int L1 = 20;
        final int L2 = 90;
        final int L3 = 160;

        switch (stats.getLives())
        {
            case 1:
                drawShipLives(g2, L1);
                break;

            case 2:
                drawShipLives(g2, L1);
                drawShipLives(g2, L2);
                break;

            case 3:
                drawShipLives(g2, L1);
                drawShipLives(g2, L2);
                drawShipLives(g2, L3);
                break;
        }
    }

    protected void drawShipLives(Graphics2D g2, int offset) {

        Image image = (BufferedImage) ResourceClass.LoadedSprites.get(0);
        //image = ResourceClass.resize(60, 70, image);

        g2.drawImage(image,
                AreaCoordinates.AC.getPlayableAreaX() + offset,
                AreaCoordinates.AC.getInfoAreaY() - 100,
                60/2,70/2, //half size as original
                null);

    }

}
