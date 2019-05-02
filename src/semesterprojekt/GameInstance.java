package semesterprojekt;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adam
 */
public class GameInstance {
    
    public static java.util.List<Enemy> enemies = new ArrayList<>();

    private PlayerShip myPlayerShip = new PlayerShip(300, 300, 100, 100);
    boolean ResartGame = false;

    GameInstance() {//constructor  or new game

    }
    
    private static int a=0;
    private static long oldMillis=0;
    
    public static void tickGame() {

        var today = new Date();

        long millis = System.currentTimeMillis() % 1000;
        
        if(millis!=oldMillis){System.out.println(a);a++;}      

        for (Enemy enemy : enemies) {
            //move enemy()
            //check enemy for collision()
            //make enemy shoot() 
        }
        
                if (enemies.isEmpty()) {
            System.out.println("NEXT LEVEL");
        } //next level

    }

    public static boolean restartGame(boolean ResartGame) {

        return ResartGame;

    }

}
