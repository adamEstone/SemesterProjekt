package semesterprojekt;

import java.util.Random;

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

    public enum enemyTypes { //mulige typer af fjender
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

                   //if (enemies.get(i).bounds().x != enemies.get(j).bounds().x && enemies.get(i).bounds().y != enemies.get(j).bounds().y) {
                    //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOF");
                    
                    if (enemies.get(i).spriteID != 4) {
                        enemies.get(i).changeDirection();    
                    }
                    
                    if (enemies.get(j).spriteID != 4) {
                        enemies.get(j).changeDirection();    
                    }
                    
                    if ( enemies.get(i).xPos < enemies.get(j).xPos) {
                        enemies.get(i).xPos = enemies.get(i).xPos -2;
                        enemies.get(j).xPos = enemies.get(j).xPos +2;
                    } else{
                        enemies.get(i).xPos = enemies.get(i).xPos +2;
                        enemies.get(j).xPos = enemies.get(j).xPos -2;
                    }
                    
                    if ( enemies.get(i).yPos < enemies.get(j).yPos) {
                        enemies.get(i).yPos = enemies.get(i).yPos -2;
                        enemies.get(j).yPos = enemies.get(j).yPos +2;
                    } else{
                        enemies.get(i).yPos = enemies.get(i).yPos +2;
                        enemies.get(j).yPos = enemies.get(j).yPos -2;
                    }
                    
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
                        
                        enemies.get(i).explode();
                        enemies.remove(i);
                        
                        playerShots.remove(j);
                        
                        // semi random score
                        Stats.stats.addScore(75 * (i + j + 1) + 15);

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
               playerShots.get(i).shotSound.remove();
               playerShots.remove(i);
            }
        }

        for (int i = 0; i < enemyShots.size(); i++) {
            try {

                if (myPlayerShip.bounds().intersects(enemyShots.get(i).bounds())) {
                    if (Stats.stats.getLives() == 0){
                        System.out.println("Game Over");
                        //TODO: game over scene.
                        //gameOver();
                    }
                    else{
                        Stats.stats.loseLife();
                        enemyShots.get(i).shotSound.remove();
                        enemyShots.remove(i);
                    }
                }

                if (enemyShots.get(i).yPos > AreaCoordinates.AC.getPlayableAreaY()) {
                    enemyShots.get(i).shotSound.remove();
                    enemyShots.remove(i);
                }

            } catch (Exception e) {
                System.out.println("Out of bounds");
            }

        }

    }

    private void enemyShoot() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).shoot() == true) {
                enemyShots.add(new EnemyShot(enemies.get(i).xPos+(25/2), enemies.get(i).yPos,25,40));
            }
        }
    }

    public void spawnEnemies(int i, int j) {
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
                    int size = r.nextInt(20);           
                    enemies.add(new EnemyGhostShooting(randX, randY,30+size,30+size));//add the enemy to the array of enemies
                }

                break;
            case Moon:

                for (int i = 0; i < numberOfEnemies; i++) {
                    Random r = new Random();

                    randX = r.nextInt(maxX - minX) + minX;
                    randY = r.nextInt(maxY - minY) + minY;
                    
                    int size = r.nextInt(20);

                    enemies.add(new EnemyMoonShooting(randX, randY,30+size,30+size));//add the enemy to the array of enemies
                }

                break;
        }
    }

    private void playerShoot(int delayms, long millis, int xPos, int yPos) {

        if (MultiMuselytter.leftButtonDown == true) {

            if ((oldMillisShoot + delayms) <= millis) {
                oldMillisShoot = millis;
                playerShots.add(new PlayerShot(xPos-(30/2), yPos,30,40));
            }
        }
    }
        
    void update(long millis, PlayerShip player) {
        if ((oldMillisMove + 8) <= millis) {
            oldMillisMove=millis;
            enemies.forEach((enemy) -> enemy.move());
            enemyShots.forEach((EnemyShot) -> EnemyShot.move());
            playerShots.forEach((playerShot) -> playerShot.move());

            playerShoot(250, millis, player.xPos+(player.width/2), player.yPos);
            checkEnemyCollision();
            enemyShoot();
            checkEnemyPlayerShotCollision();
            removeDeadObjects(player);

        }
    }
}
