package semesterprojekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author FK
 */
public class Stats
{
    private int lives = 3;
    private int weapon = 0;
    private int level = 1;
    private int score = 0;

    static Stats stats = new Stats();

    public void drawText(Graphics2D g2)
    {
        // baggrund
        g2.setColor(Color.BLACK);//tegn grå baggrund
        g2.fillRect(AreaCoordinates.AC.getPlayableAreaX(),
                0,
                AreaCoordinates.AC.getInfoAreaX(),
                AreaCoordinates.AC.getInfoAreaY());
        
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(AreaCoordinates.AC.getPlayableAreaX(),
                    0,
                    5,
                    AreaCoordinates.AC.getInfoAreaY());
        
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

    public void drawLives(Graphics2D g2)
    {
        final int L1 = 20;
        final int L2 = 90;
        final int L3 = 160;
        
        switch (getLives())
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

    public void drawScore(Graphics2D g2)
    {
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(getScore()),
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 70);
    }

    public void drawLevel(Graphics2D g2)
    {
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(getLevel()),
                AreaCoordinates.AC.getPlayableAreaX() + 10,
                AreaCoordinates.AC.getWindowTopOffset() + 175);
    }

    public void setLives(int value)
    {
        lives = value;
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public int getScore()
    {
        return score;
    }

    public void resetLives()
    {
        setLives(3);
    }

    public void loseLife()
    {
        lives--;
    }

    public int getLives()
    {
        return lives;
    }

    public int getLevel()
    {
        return level;
    }

    public void nextLevel()
    {
        level++;
    }
}
