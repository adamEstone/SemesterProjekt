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
    
    

    private PlayerShip myPlayerShip = new PlayerShip(300, 500, 100, 100);

    GameInstance() {//constructor  or new game
        //Enemy someEnemy = new EnemyGhostShooting(200,100);
        
        enemies.add(new EnemyGhostShooting(200,100));
        enemies.add(new EnemyGhostShooting(100,100));
        /*enemies.add(new EnemyGhostShooting(100,200));
        enemies.add(new EnemyGhostShooting(300,200));
        enemies.add(new EnemyGhostShooting(50,250));
        enemies.add(new EnemyGhostShooting(300,150));
        */

        System.out.println(enemies.size());
   
        
        // Enemy someOtherEnemy = new EnemyGhostShooting(20,10);
        //enemies.add(someOtherEnemy);   
      
         
        
    }

    private static int a = 0;
    private static long oldMillis = 0;

    public static void tickGame() {

        var today = new Date();

        long millis = System.currentTimeMillis() % 1000;

        if (millis != oldMillis) {
            System.out.println("millis changed in tickGame(): " + a);
            a++;
        }

        
            enemies.forEach((enemy) -> enemy.move());        

            //check enemy for collision()
            
            for (int i = 0; i < enemies.size(); i++) {
            
                for (int j = 0; j < enemies.size(); j++) {
                    
                    if (enemies.get(i).bounds().intersects(enemies.get(j).bounds())) {
                        
                        if (enemies.get(i).bounds().x != enemies.get(j).bounds().x &&
                            enemies.get(i).bounds().y != enemies.get(j).bounds().y) {
                            
                            
                            
                            
                            System.out.println("Av! " + i);
                            
                        }
                        
                        
                        
                    }
                    
                }
                
        }
            
            
            
            //make enemy shoot() 
            //animation?
            


        if (enemies.isEmpty()) {
            System.out.println("NEXT LEVEL");
        } //next level

    }

    public static boolean restartGame(boolean ResartGame) {

        return ResartGame;

    }

    public Graphics2D drawGame(Graphics2D bufferedGraphics) {
        
        Graphics2D tempG2D = bufferedGraphics;
        
        myPlayerShip.setXpos(MultiMuselytter.mouseX);
         
        tempG2D = myPlayerShip.draw(tempG2D);
        
        for (Enemy enemy : enemies) {
            

        
        tempG2D = enemy.draw(tempG2D);
        }

        
         //enemies.forEach((enemy) -> enemy.draw(tempG2D));

        return tempG2D;
    }

}
