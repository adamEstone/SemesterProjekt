

package semesterprojekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

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
    
    /*Stats(int startX, int startY, int endX, int endY){
        
    }*/
    
    
    
    public void drawText(Graphics2D g2) {
        
        // baggrund
        g2.setColor(Color.LIGHT_GRAY);//tegn grå baggrund
        g2.fillRect(AreaCoordinates.AC.getPlayableAreaX(),
                    0,
                    AreaCoordinates.AC.getInfoAreaX(),
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
                      575 );
        
    }
    
    public void drawLives(Graphics2D g2){
        switch (getLives())
        {
            case 0:
            break;
            
            case 1:
                g2.setColor(Color.BLUE);
                g2.fillOval(AreaCoordinates.AC.getPlayableAreaX() + 10,
                            AreaCoordinates.AC.getInfoAreaY() - 100,
                            60,
                            70);
                
            break;
            
            case 2:
                g2.setColor(Color.BLUE);
                g2.fillOval(AreaCoordinates.AC.getPlayableAreaX() + 10,
                            AreaCoordinates.AC.getInfoAreaY() - 100,
                            60,
                            70);
                
                g2.fillOval(AreaCoordinates.AC.getPlayableAreaX() + 80,
                            AreaCoordinates.AC.getInfoAreaY() - 100,
                            60,
                            70);
            break;
            
            case 3:
                g2.setColor(Color.BLUE);
                g2.fillOval(AreaCoordinates.AC.getPlayableAreaX() + 10,
                            AreaCoordinates.AC.getInfoAreaY() - 100,
                            60,
                            70);
                
                g2.fillOval(AreaCoordinates.AC.getPlayableAreaX() + 80,
                            AreaCoordinates.AC.getInfoAreaY() - 100,
                            60,
                            70);
                
                g2.fillOval(AreaCoordinates.AC.getPlayableAreaX() + 150,
                            AreaCoordinates.AC.getInfoAreaY() - 100,
                            60,
                            70);
            break;
            
            default:
            break;
                
        }
    }
    
    public void drawScore(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(getScore()),
                      AreaCoordinates.AC.getPlayableAreaX() + 10,
                      AreaCoordinates.AC.getWindowTopOffset() + 70);
    }
    
        public void drawLevel(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Algerian", Font.PLAIN, 25));
        g2.drawString(String.valueOf(getLevel()),
                      AreaCoordinates.AC.getPlayableAreaX() + 10,
                      AreaCoordinates.AC.getWindowTopOffset() + 175);
    }
    
    public void setLives(int value) {
        lives = value;
    }
    
    public void addScore(int score){
        this.score += score;
    }
    
    public int getScore(){
        return score;
    }
    
    public void resetLives() {
        setLives(3);
    }
    
    public void loseLife(){
        lives--;
    }

    public int getLives() {
        return lives;
    }
    
    public int getLevel(){
        return level;
    }
    
    public void nextLevel(){
        level++;
    }
}
