package semesterprojekt;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Date;

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

    boolean ResartGame = false;

    Graphics2D g2;

    private PlayerShip myPlayerShip = new PlayerShip(300, 500, 100, 100);
    
    /////////////////////////  knapper ///////////////////////////////
    MenuButton btnResume =  new MenuButton(50, 50, 200, 50, "Resume",38);
    
    MenuButton btnNewGame = new MenuButton(50, 110, 200, 50, "New Game",48);
    
    MenuButton btnQuit =    new MenuButton(50, 170, 200, 50, "Quit",20);
    //////////////////////////////////////////////////////////////
    
    GameInstance(AktivVisning theWindow) {//constructor  or new game

        //Enemy someEnemy = new EnemyGhostShooting(200,100);
        enemies.add(new EnemyGhostShooting(200, 100));
        enemies.add(new EnemyGhostShooting(100, 100));
        enemies.add(new EnemyGhostShooting(100,200));
        
        
        
        //enemyShots.add(new EnemyShot(100, 100));
        enemies.add(new EnemyGhostShooting(300,200));
        enemies.add(new EnemyGhostShooting(50,250));
        enemies.add(new EnemyGhostShooting(300,150));
        enemies.add(new EnemyGhostShooting(400,100));
        enemies.add(new EnemyGhostShooting(100,150));
        enemies.add(new EnemyGhostShooting(200,250));
        enemies.add(new EnemyGhostShooting(300,200));
        

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

        //check enemy for collision()
        for (int i = 0; i < enemies.size(); i++) {

            for (int j = 0; j < enemies.size(); j++) {

                if (enemies.get(i).bounds().intersects(enemies.get(j).bounds())) {

                    if (enemies.get(i).bounds().x != enemies.get(j).bounds().x
                            && enemies.get(i).bounds().y != enemies.get(j).bounds().y) {
                            
                            enemies.get(i).changeDirection();
                            //enemies.get(j).changeDirection();
                        System.out.println("Av! " + i);

                    }

                }

            }
                if (enemies.get(i).shoot() == true) {
                enemyShots.add(new EnemyShot(enemies.get(i).xPos, enemies.get(i).yPos));
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

}
