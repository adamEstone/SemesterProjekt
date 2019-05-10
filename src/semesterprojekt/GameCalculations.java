package semesterprojekt;
import java.util.Random;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Mads
 */
public class GameCalculations {
    
    //These ArrayLists keeps track of the enemies, thire shots, and the players shots
    public java.util.List<Enemy> enemies = new ArrayList<>();
    public java.util.List<EnemyShot> enemyShots = new ArrayList<>();
    public java.util.List<PlayerShot> playerShots = new ArrayList<>();
    
    public enum enemyTypes { //mulige spil stadier
        Ghost,
        Moon,
    }
        
        long oldMillisShoot = 0;
        long oldMillisMove = 0;
        
    //GameObjects functions
    private void checkEnemyCollision() {
        for (int i = 0; i < enemies.size(); i++) {

            for (int j = 0; j < enemies.size(); j++) {

                if (enemies.get(i).bounds().intersects(enemies.get(j).bounds())) {

                   // if (enemies.get(i).bounds().x != enemies.get(j).bounds().x && enemies.get(i).bounds().y != enemies.get(j).bounds().y) {
                    //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOF");
                    enemies.get(i).changeDirection();
                    enemies.get(j).changeDirection();
                    //}
                }
            }
        }
    }

    private void checkEnemyPlayerShotCollision() {

        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < playerShots.size(); j++) {

                try {

                    if (enemies.get(i).bounds().intersects(playerShots.get(j).bounds())) {
                        enemies.remove(i);
                        playerShots.remove(j);

                    }
                    //enemiesRemove.add(i);
                    //shotsRemove.add(j);

                } catch (Exception e) {
                    System.out.println("Et skud ramte 2 fjender, eller en fjende ramte 2 skud. Out of bounds bliver ignoreret.");
                }
            }
        }
    }

    private void removeDeadObjects(PlayerShip myPlayerShip) {
        
        for (int i = 0; i < playerShots.size(); i++) {
                    
            if (playerShots.get(i).yPos < 0) {
                playerShots.remove(i);
            }
        }

        for (int i = 0; i < enemyShots.size(); i++) {
            try {
                
            if (myPlayerShip.bounds().intersects(enemyShots.get(i).bounds())) {
                myPlayerShip.loseLife();
                enemyShots.remove(i);
            }
            
                if (enemyShots.get(i).yPos > 700) {
                    enemyShots.remove(i);
                }
                
            } catch (Exception e) {
                                System.out.println("Out of bounds");
            }

        }
       
    }

    private void enemyShot() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).shoot() == true) {
                enemyShots.add(new EnemyShot(enemies.get(i).xPos, enemies.get(i).yPos));
            }
        }
    }
    
    public void spawnEnemies(int i, int j){
        generateEnemies(i, enemyTypes.Ghost);
        generateEnemies(j, enemyTypes.Moon);
    }

    public void generateEnemies(int numberOfEnemies, enemyTypes typeOfEnemy) {

        int minX = 2;
        int maxX = 538;
        int minY = 28;
        int maxY = 298;
        int randX;
        int randY;

        switch (typeOfEnemy) {
            case Ghost:
                for (int i = 0; i < numberOfEnemies; i++) {
                    Random r = new Random();

                    randX = r.nextInt(maxX - minX) + minX;
                    randY = r.nextInt(maxY - minY) + minY;

                    enemies.add(new EnemyGhostShooting(randX, randY));//add the enemy to the array of enemies
                }

                break;
            case Moon:

                for (int i = 0; i < numberOfEnemies; i++) {
                    Random r = new Random();

                    randX = r.nextInt(maxX - minX) + minX;
                    randY = r.nextInt(maxY - minY) + minY;

                    enemies.add(new EnemyMoonShooting(randX, randY));//add the enemy to the array of enemies
                }

                break;
        }
    }

    private void playerShot(int delayms, long millis, int xPos, int yPos) {

        if (MultiMuselytter.leftButtonDown == true) {

            if ((oldMillisShoot + delayms) <= millis) {
                oldMillisShoot = millis;
                playerShots.add(new PlayerShot(xPos, yPos));
            }
        }
    }
    
    
    
    void update(long millis, PlayerShip player){
        if ((oldMillisMove + 10) <= millis) {
            
            enemies.forEach((enemy) -> enemy.move());
            enemyShots.forEach((EnemyShot) -> EnemyShot.move());
            playerShots.forEach((playerShot) -> playerShot.move());

            
            playerShot(500, millis, player.xPos, player.yPos);
            checkEnemyCollision();
            enemyShot();
            checkEnemyPlayerShotCollision();
            removeDeadObjects(player);

        }
    }    
}
