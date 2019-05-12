/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Adam
 */
public class Backgrounds
{

    ArrayList<Star> StarPosition = new ArrayList<Star>();

    private int numberOfStart = 75;

    Backgrounds()
    {//cons

        for (int i = 0; i < numberOfStart; i++)
        { //make star objects
            StarPosition.add(new Star());
        }

    }

    public void drawDivider(Graphics2D g2)
    {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(AreaCoordinates.AC.getPlayableAreaX(),
                0,
                5,
                AreaCoordinates.AC.getInfoAreaY());
    }

    public void drawText(Graphics2D g2)
    {
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

    public void drawScore(Graphics2D g2)
    {
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(Stats.stats.getScore()),
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 70);
    }

    public void drawLevel(Graphics2D g2)
    {
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(Stats.stats.getLevel()),
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 175);
    }

    public void drawLives(Graphics2D g2)
    {
        final int L1 = 20;
        final int L2 = 90;
        final int L3 = 160;

        switch (Stats.stats.getLives())
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

    protected void drawShipLives(Graphics2D g2, int offset)
    {
        BufferedImage image = new BufferedImage(80, 60, BufferedImage.TYPE_INT_ARGB);

        try
        {
            image = ResourceClass.LoadedSprites.get(0);
            image = ResourceClass.resize(60, 70, image);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        g2.drawImage(image,
                AreaCoordinates.AC.getPlayableAreaX() + offset,
                AreaCoordinates.AC.getInfoAreaY() - 100,
                null);

    }

    public void draw(Graphics2D g2)
    {

        g2.setColor(Color.BLACK);//tegn sort skærm
        g2.fillRect(0, 0, AreaCoordinates.AC.getPlayableAreaX()
                + AreaCoordinates.AC.getInfoAreaX(),
                AreaCoordinates.AC.getPlayableAreaY());

        for (Star thisStar : StarPosition)
        {//tegn og flyt stjerner

            g2.setColor(thisStar.getStarColor());
            g2.fillRect(thisStar.getLocation().x, thisStar.getLocation().y, 2, 2);

            thisStar.move();

        }

    }

    public void moveStartsFast(boolean highspeed)
    {
        for (Star thisStar : StarPosition)
        {
            thisStar.setSpeedUp(highspeed);
        }
    }

}
