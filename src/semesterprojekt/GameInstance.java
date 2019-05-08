package semesterprojekt;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Adam
 */
public class GameInstance {

    public enum gameState { //mulige spil stadier
        Loading,
        Menu,
        Ingame,
        Pause,
        Settings,
        RestartgameState
    }

    public gameState mygamestate;

    public static java.util.List<Enemy> enemies = new ArrayList<>();
    public static java.util.List<EnemyShot> enemyShots = new ArrayList<>();
    public static java.util.List<PlayerShot> playerShots = new ArrayList<>();
    boolean ResartGame = false;

    
    private java.util.List<Integer> enemiesRemove = new ArrayList<>();
    private java.util.List<Integer> shotsRemove = new ArrayList<>();
    
            int test = 1;
    
    Graphics2D g2;

    private PlayerShip myPlayerShip = new PlayerShip(300, 500, 100, 100);
    
    /////////////////////////  knapper ///////////////////////////////
    MenuButton btnResume =  new MenuButton(50, 50, 200, 50, "Resume",38);
    
    MenuButton btnNewGame = new MenuButton(50, 110, 200, 50, "New Game",48);
    
    MenuButton btnQuit =    new MenuButton(50, 170, 200, 50, "Quit",20);
    //////////////////////////////////////////////////////////////
    
    GameInstance(AktivVisning theWindow) {//constructor  or new game

        //Enemy someEnemy = new EnemyGhostShooting(200,100);
        
        
        generateEnemies(100);
        

        System.out.println(enemies.size());

        // Enemy someOtherEnemy = new EnemyGhostShooting(20,10);
        //enemies.add(someOtherEnemy);   
    }

    private static int a = 0;
    private static long oldMillis = 0;

    public void tickGame() {        
        /*
        var today = new Date();

        long millis = System.currentTimeMillis() % 1000;

        if (millis != oldMillis) {
            System.out.println("millis changed in tickGame(): " + a);
            a++;
        }
         */
        
        enemies.forEach((enemy) -> enemy.move());
        enemyShots.forEach((EnemyShot) -> EnemyShot.move());
        
        
            enemyShot(); //Enemy shoot at random
            checkEnemyCollision(); //Checks if enemies collide with eachother, and changes thire direction
            checkEnemyPlayerShotCollision(); //Check if shots collide with enemies/player
            removeDeadObjects(); //Remove dead shots and enemies
       
        
        if (MultiMuselytter.leftButtonDown == true && test == 1) {
            playerShots.add(new PlayerShot(myPlayerShip.xPos, myPlayerShip.yPos));
            test = 0;
        }
        
        if (MultiMuselytter.rightButtonDown == true) {
            test = 1;
        }
        
        for (PlayerShot playerShot : playerShots){
            playerShot.move();
        }
           
        //animation?
        if (enemies.isEmpty()) {
            System.out.println("NEXT LEVEL");
        } //next level

    }

    public static boolean restartGame(boolean ResartGame) {

        return ResartGame;

    }

    public Graphics2D drawGame(Graphics2D bufferedGraphics) {
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2
        Graphics2D tempG2D = bufferedGraphics;

        myPlayerShip.setXpos(MultiMuselytter.mouseX);//opdatere rumskibet 

        drawObj(myPlayerShip);

        for (Enemy enemy : enemies) {
            drawObj(enemy);
        }
        
        for (EnemyShot shot : enemyShots){
            drawObj(shot);
        }
        for (PlayerShot playerShot : playerShots){
            drawObj(playerShot);
        }
        
        ///////////// tegn knapper //////////////////
        
        drawObj(btnResume);
        drawObj(btnNewGame);
        drawObj(btnQuit);
        
        
        mygamestate = btnResume.buttonPressedAction(mygamestate, gameState.Menu);
        
        ////////////////////////////////////////////
        //enemies.forEach((enemy) -> enemy.draw(tempG2D));


              
        return tempG2D;
        

    }
    
    

    void drawObj(BaseObject b) {                                                //tegner det objekt som er parameter i drawObj på g2    
        b.draw(g2);                                                             //(g2 er grafik objektet til vores Jframe. Dette variablen g2 følger med constructoren)
    }
    
    
    private void checkEnemyCollision(){
                for (int i = 0; i < enemies.size(); i++) {

            for (int j = 0; j < enemies.size(); j++) {

                if (enemies.get(i).bounds().intersects(enemies.get(j).bounds())) {

                    if (enemies.get(i).bounds().x != enemies.get(j).bounds().x && enemies.get(i).bounds().y != enemies.get(j).bounds().y) {
                         
                            enemies.get(i).changeDirection();
                            enemies.get(j).changeDirection();
                    }
                }
            }   
        }    
    }
    
    private void checkEnemyPlayerShotCollision(){
        
                        enemiesRemove.clear();
                shotsRemove.clear();
        
        for (int i = 0; i < enemies.size(); i++) {
            for (int j = 0; j < playerShots.size(); j++) {      
                 if (enemies.get(i).bounds().intersects(playerShots.get(j).bounds())) {
                enemiesRemove.add(i);
                shotsRemove.add(j);
                 }
            }
        }    
    }
    
    private void removeDeadObjects(){
        
        try {
           
        for (int i = 0; i < enemiesRemove.size(); i++) {  
        int k = enemiesRemove.get(i);
        enemies.remove(k);
        }
        
        
        for (int i = 0; i < shotsRemove.size(); i++) { 
            int k = shotsRemove.get(i);
            playerShots.remove(k);
        }
        
        
                for (int i = 0; i < enemyShots.size(); i++) {
            
            if (myPlayerShip.bounds().intersects(enemyShots.get(i).bounds())) {
                
                myPlayerShip.loseLife();
                enemyShots.remove(i);
                
            }  
        }   
                
         } catch (Exception e) {
                            
             System.out.println("Et skud ramte 2 fjender, eller en fjende ramte 2 skud. Out of bounds bliver ignoreret.");
        }
    }
    
    private void enemyShot(){
                for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).shoot() == true) {
                enemyShots.add(new EnemyShot(enemies.get(i).xPos, enemies.get(i).yPos));
            }
        }
    }
    
    private void generateEnemies(int numberOfEnemies){
        
        Random r = new Random();
        
        int minX = 2;
        int maxX = 538;
        int minY = 28;
        int maxY = 298;
        int randX;
        int randY;
        
        for (int i = 0; i < numberOfEnemies; i++) {
            
         randX = r.nextInt(maxX-minX) + minX;
         randY = r.nextInt(maxY-minY) + minY;
        
         enemies.add(new EnemyGhostShooting(randX,randY));
            
        }
        
    }
    
    
    
}
