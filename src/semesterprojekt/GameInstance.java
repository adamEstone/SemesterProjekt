package semesterprojekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
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

    public  java.util.List<Enemy> enemies = new ArrayList<>();
    public java.util.List<EnemyShot> enemyShots = new ArrayList<>();
    public  java.util.List<PlayerShot> playerShots = new ArrayList<>();
    boolean ResartGame = false;

    
    private java.util.List<Integer> enemiesRemove = new ArrayList<>();
    private java.util.List<Integer> shotsRemove = new ArrayList<>();
    
            int test = 1;
    
    Graphics2D g2;

    private PlayerShip myPlayerShip = new PlayerShip(300, 500, 100, 100);

    private AktivVisning gameWindow;

    GameInstance(AktivVisning theWindow) {//constructor  or new game
        
        gameWindow=theWindow;

        //Enemy someEnemy = new EnemyGhostShooting(200,100);
        
        
        generateEnemies(20);
        

        System.out.println(enemies.size());

        // Enemy someOtherEnemy = new EnemyGhostShooting(20,10);
        //enemies.add(someOtherEnemy);   
    }

    private static int a = 0;
    private static long oldMillis = 0;
    private long millis = 0;

    public void tickGame() {        
        
        var today = new Date();

        millis = System.currentTimeMillis();
        System.out.println(millis);

        /*
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
       
        
            playerShot(500);
           


        
        for (PlayerShot playerShot : playerShots){
            playerShot.move();
        }
           
        //animation?
        
        if (enemies.isEmpty()) {
            System.out.println("NEXT LEVEL");
        } //next level

    }

    
    /////////////////////////  Drawing Calls///////////////////////////////
    
    void drawObj(BaseObject b) {                                                //tegner det objekt som er parameter i drawObj på g2    
        b.draw(g2);                                                             //(g2 er grafik objektet til vores Jframe. Dette variablen g2 følger med constructoren)
    }

    
    //DRAW GAME
    public Graphics2D drawGame(Graphics2D bufferedGraphics) {
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2
        Graphics2D tempG2D = bufferedGraphics;

        myPlayerShip.setXpos(MultiMuselytter.mouseX);//opdatere rumskibet 

        drawObj(myPlayerShip);

        for (Enemy enemy : enemies) {
            drawObj(enemy);
        }

        for (EnemyShot shot : enemyShots) {
            drawObj(shot);
        }

        //enemies.forEach((enemy) -> enemy.draw(tempG2D));
        return tempG2D;
    }

    
    
    //DRAW MENU
    private int menuWidth=300;
    private int menuHeight=400;
    
    private Point menuLocation = new Point(150 , 100);
    
    private int menuButtonWidth=200;
    private int menuButtonHeight=50;

    private MenuButton btnResume = new MenuButton(menuLocation.x + (menuWidth/2)-(menuButtonWidth/2), menuLocation.y    +100 + 50, menuButtonWidth, menuButtonHeight, "Resume", 38);

    private MenuButton btnNewGame = new MenuButton(menuLocation.x + (menuWidth/2)-(menuButtonWidth/2), menuLocation.y   +100 + 120, menuButtonWidth, menuButtonHeight, "New Game", 48);

    private MenuButton btnQuit = new MenuButton(menuLocation.x + (menuWidth/2)-(menuButtonWidth/2), menuLocation.y      +100 + 190, menuButtonWidth, menuButtonHeight, "Quit", 20);

    public Graphics2D drawMenu(Graphics2D bufferedGraphics) {
    
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2
        Graphics2D tempG2D = bufferedGraphics;
        
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight()); //tegn overlay så man ikke kan se spillet

        g2.setColor(Color.GRAY);
        g2.fillRect(menuLocation.x, menuLocation.y,  menuWidth, menuHeight);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("default", Font.BOLD, 50));
        g2.drawString("Menu",menuLocation.x + (menuWidth/2) - 65,menuLocation.y + 90);//Menu Tekst
        
        drawObj(btnResume);
        drawObj(btnNewGame);
        drawObj(btnQuit);

        mygamestate = btnResume.buttonPressedAction(mygamestate, gameState.Ingame);
        mygamestate = btnNewGame.buttonPressedAction(mygamestate, gameState.RestartgameState);

        return tempG2D;

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
    
    private void playerShot(int delayms){
        
                if (MultiMuselytter.leftButtonDown == true) {
            
            if ((oldMillis + delayms) <= millis) {
                oldMillis = millis;
                playerShots.add(new PlayerShot(myPlayerShip.xPos, myPlayerShip.yPos));
            }   
        }
    }
    
    
    
}
