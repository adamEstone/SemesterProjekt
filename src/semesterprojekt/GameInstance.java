package semesterprojekt;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adam
 */
public class GameInstance {

    
    
    public static java.util.List<Enemy> enemies = new ArrayList<>();

    boolean ResartGame = false;
    
    private PlayerShip myPlayerShip = new PlayerShip(300, 300, 100, 100);

    GameInstance() {//constructor  or new game

    }

    private static int a = 0;
    private static long oldMillis = 0;

    public static void tickGame() {

        var today = new Date();

        long millis = System.currentTimeMillis() % 1000;

        if (millis != oldMillis) {
            System.out.println(a);
            a++;
        }

        for (Enemy enemy : enemies) {
            //move enemy()
            //check enemy for collision()
            //make enemy shoot() 
            //animation?
        }

        if (enemies.isEmpty()) {
            System.out.println("NEXT LEVEL");
        } //next level

    }

    public static boolean restartGame(boolean ResartGame) {

        return ResartGame;

    }

    public static Graphics2D drawGame(Graphics2D bufferedGraphics) {
        Graphics2D tempG2D = bufferedGraphics;
        
        tempG2D.drawImage(
                ResourceClass.LoadedSprites.get(0), 
                MultiMuselytter.mouseX-(ResourceClass.LoadedSprites.get(0).getWidth()/2),
                600, null);
        
        return tempG2D ;
    }

}
