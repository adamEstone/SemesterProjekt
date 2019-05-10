package semesterprojekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.WindowConstants;

/**
 *
 * @author Adam
 */
public class GameInstance {

    public enum gameState { //mulige spil stadier
        Ingame,
        Loading,
        Menu,
        QuitGame,
        RestartGameState
    }

    private enum enemyTypes { //mulige spil stadier
        Ghost,
        Moon,
    }

    public gameState mygamestate;


    boolean ResartGame = false;

    GameCalculations GC = new GameCalculations();


    Graphics2D g2;

    private PlayerShip myPlayerShip = new PlayerShip(300, 600);

    private AktivVisning gameWindow;

    GameInstance(AktivVisning theWindow) {//constructor  or new game

        gameWindow = theWindow;
        
        
        


        //generateEnemies(10, enemyTypes.Ghost);
        //generateEnemies(3, enemyTypes.Moon);
        //generateEnemies(2, enemyTypes.Ghost);

        GC.spawnEnemies(10,2);
        
        System.out.println(GC.enemies.size());

        // Enemy someOtherEnemy = new EnemyGhostShooting(20,10);
        //enemies.add(someOtherEnemy);   
    }

    private int a = 0;
    private long oldMillisShoot = 0;
    private long oldMillisMove = 0;

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
        if ((oldMillisMove + 10) <= millis) {
          /*  oldMillisMove = millis;
            enemyShots.forEach((EnemyShot) -> EnemyShot.move());
            enemies.forEach((enemy) -> enemy.move());
            checkEnemyCollision();
            enemyShot(); //Enemy shoot at random
            checkEnemyPlayerShotCollision(); //Check if shots collide with enemies/player
            removeDeadObjects(); //Remove dead shots and enemies
            enemyShots.forEach((EnemyShot) -> EnemyShot.move());
            enemies.forEach((enemy) -> enemy.move());
            */
        }

        GC.update(millis, myPlayerShip);


       // playerShot(500);



        //animation?
        if (GC.enemies.isEmpty()) {
            System.out.println("NEXT LEVEL");
        } //next level

    }

    /////////////////////////  Drawing Calls///////////////////////////////
    void drawObj(BaseObject b) {                                                //tegner det objekt som er parameter i drawObj på g2    
        b.draw(g2);                                                             //(g2 er grafik objektet til vores Jframe. Dette variablen g2 følger med constructoren)
    }

    //DRAW GAME
    public void drawGame(Graphics2D bufferedGraphics) {
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2

        DrawBackground(g2);//temporary solution

        myPlayerShip.setXpos(MultiMuselytter.mouseX);//opdatere rumskibet 

        drawObj(myPlayerShip);

        for (Enemy enemy : GC.enemies) {
            drawObj(enemy);
        }

        for (EnemyShot shot : GC.enemyShots) {
            drawObj(shot);
        }

        for (PlayerShot shot : GC.playerShots) {
            drawObj(shot);
        }

        
        //enemies.forEach((enemy) -> enemy.draw(tempG2D));
    }

    private void DrawBackground(Graphics2D g2) {
        g2.setColor(Color.BLACK);                  // rens skærmen selv
        g2.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());
    }

    //DRAW MENU
    private int menuWidth = 300;
    private int menuHeight = 400;

    private Point menuLocation = new Point(150, 100);

    private int menuButtonWidth = 200;
    private int menuButtonHeight = 50;

    private MenuButton btnResume = new MenuButton(menuLocation.x + (menuWidth / 2) - (menuButtonWidth / 2), menuLocation.y + 100 + 50, menuButtonWidth, menuButtonHeight, "Resume", 38);

    private MenuButton btnNewGame = new MenuButton(menuLocation.x + (menuWidth / 2) - (menuButtonWidth / 2), menuLocation.y + 100 + 120, menuButtonWidth, menuButtonHeight, "New Game", 48);

    private MenuButton btnQuit = new MenuButton(menuLocation.x + (menuWidth / 2) - (menuButtonWidth / 2), menuLocation.y + 100 + 190, menuButtonWidth, menuButtonHeight, "Quit", 20);

    private void drawMenuBackground(Graphics2D bufferedGraphics) {

        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight()); //tegn overlay så man ikke kan se spillet

        g2.setColor(Color.GRAY);
        g2.fillRect(menuLocation.x, menuLocation.y, menuWidth, menuHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("default", Font.BOLD, 50));
        g2.drawString("Menu", menuLocation.x + (menuWidth / 2) - 65, menuLocation.y + 90);//Menu Tekst

    }

    public void drawMenu(Graphics2D bufferedGraphics) {
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2
        drawMenuBackground(g2);
        drawObj(btnResume);
        drawObj(btnNewGame);
        drawObj(btnQuit);

        mygamestate = btnResume.buttonPressedAction(mygamestate, gameState.Ingame);
        mygamestate = btnNewGame.buttonPressedAction(mygamestate, gameState.RestartGameState);
        mygamestate = btnQuit.buttonPressedAction(mygamestate, gameState.QuitGame);

    }
  
}
