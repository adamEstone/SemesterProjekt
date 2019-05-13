package semesterprojekt;

import java.util.Random;
import java.util.ArrayList;

/**
 * Denne klasse står for beregninger af Collision detection mellem fjender og
 * skud, samt deres bevægelse
 *
 */
public class GameCalculations {

    //These ArrayLists keeps track of the enemies, thire shots, and the players shots
    public java.util.List<Enemy> enemies = new ArrayList<>();
    public java.util.List<EnemyShot> enemyShots = new ArrayList<>();
    public java.util.List<PlayerShot> playerShots = new ArrayList<>();
    public java.util.List<Animation> explotions = new ArrayList<>();

    public PlayerShip myPlayerShipRef;
    
    public enum enemyTypes { //mulige typer af fjender
        Ghost,
        Moon,
    }

    long oldMillisShoot = 0;
    long oldMillisMove = 0;

    //Collision detection mellem fjender
    private void checkEnemyCollision() {
        for (int i = 0; i < enemies.size(); i++) {

            for (int j = 0; j < enemies.size(); j++) {

                if (enemies.get(i).bounds().intersects(enemies.get(j).bounds())) {

                    if (enemies.get(i).getSpriteID() != 4) {
                        enemies.get(i).changeDirection();
                    }

                    if (enemies.get(j).getSpriteID() != 4) {
                        enemies.get(j).changeDirection();
                    }

                    if (enemies.get(i).getXpos() < enemies.get(j).getXpos()) {
                        enemies.get(i).setXpos(enemies.get(i).getXpos() - 2);
                        enemies.get(j).setXpos(enemies.get(j).getXpos() + 2);
                    } else {
                        enemies.get(i).setXpos(enemies.get(i).getXpos() + 2);
                        enemies.get(j).setXpos(enemies.get(j).getXpos() - 2);
                    }

                    if (enemies.get(i).getYpos() < enemies.get(j).getYpos()) {
                        enemies.get(i).setYpos(enemies.get(i).getYpos() - 2);
                        enemies.get(j).setYpos(enemies.get(j).getYpos() + 2);
                    } else {
                        enemies.get(i).setYpos(enemies.get(i).getYpos() + 2);
                        enemies.get(j).setYpos(enemies.get(j).getYpos() - 2);
                    }
                }
            }
        }
    }

    //Collision detection mellem fjender og spillerens skud
    private void checkEnemyPlayerShotCollision() {

        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < playerShots.size(); j++) {

                try {

                    if (enemies.get(i).bounds().intersects(playerShots.get(j).bounds())) {
                        
                        explotions.add(enemies.get(i).explode());
                        enemies.remove(i);
                        playerShots.remove(j);

                        // semi random score
                        myPlayerShipRef.statsRef.addScore(75 * (i + j + 1) + 15);

                    }

                } catch (Exception e) {
                    System.out.println("Et skud ramte 2 fjender, eller en fjende ramte 2 skud. Out of bounds bliver ignoreret.");
                }
            }
        }
    }

    //Objekter der er "døde" bliver fjernet
    private void removeDeadObjects() {

        for (int i = 0; i < playerShots.size(); i++) {

            if (playerShots.get(i).getYpos() < 0) {
                playerShots.get(i).shotSound.remove();
                playerShots.remove(i);
            }
        }

        for (int i = 0; i < enemyShots.size(); i++) {
            try {

                if (myPlayerShipRef.bounds().intersects(enemyShots.get(i).bounds())) {
                    if (myPlayerShipRef.statsRef.getLives() == 0){
                        System.out.println("Game Over");
                        //TODO: game over scene.
                        //gameOver();
                    } else {
                        myPlayerShipRef.statsRef.loseLife();
                        enemyShots.get(i).shotSound.remove();
                        enemyShots.remove(i);
                    }
                }

                if (enemyShots.get(i).getYpos() > AreaCoordinates.AC.getPlayableAreaY()) {
                    enemyShots.get(i).shotSound.remove();
                    enemyShots.remove(i);
                }

            } catch (Exception e) {
                System.out.println("Out of bounds");
                e.printStackTrace();
            }

        }

    }

    //Tilføjelse af skud fra en fjende
    private void enemyShoot() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).shoot() == true) {
                enemyShots.add(new EnemyShot(enemies.get(i).getXpos() + (25 / 2), enemies.get(i).getYpos(), 25, 40));
            }
        }
    }

    //Spawn fjender ind i start af en level
    public void spawnEnemies(int i, int j) {
        generateEnemies(i, enemyTypes.Ghost);
        generateEnemies(j, enemyTypes.Moon);
    }

    //Generere fjender tilfældigt på skærmen
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
                    enemies.add(new EnemyGhostShooting(randX, randY, 30 + size, 30 + size));//add the enemy to the array of enemies
                }

                break;
            case Moon:

                for (int i = 0; i < numberOfEnemies; i++) {
                    Random r = new Random();

                    randX = r.nextInt(maxX - minX) + minX;
                    randY = r.nextInt(maxY - minY) + minY;

                    int size = r.nextInt(20);

                    enemies.add(new EnemyMoonShooting(randX, randY, 30 + size, 30 + size));//add the enemy to the array of enemies
                }

                break;
        }
    }

    //Generere spillerens skud
    private void playerShoot(int delayms, long millis, int xPos, int yPos) {

        if (MultiMuselytter.leftButtonDown == true) {

            if ((oldMillisShoot + delayms) <= millis) {
                oldMillisShoot = millis;
                playerShots.add(new PlayerShot(xPos - (30 / 2), yPos, 30, 40));
            }
        }
    }

    //Kalder divers funktioner hvert game tick
    void update(long millis, PlayerShip player) {
        if ((oldMillisMove + 8) <= millis) {
            oldMillisMove = millis;
            enemies.forEach((enemy) -> enemy.move());
            enemyShots.forEach((EnemyShot) -> EnemyShot.move());
            playerShots.forEach((playerShot) -> playerShot.move());

            playerShoot(250, millis, player.getXpos() + (player.width / 2), player.getYpos());
            checkEnemyCollision();
            enemyShoot();
            checkEnemyPlayerShotCollision();
            removeDeadObjects();
            
            
        }
    }
}
