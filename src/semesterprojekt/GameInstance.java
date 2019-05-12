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

    private long millis = 0;

    GameCalculations GC = new GameCalculations();

    Graphics2D g2;

    private PlayerShip myPlayerShip = new PlayerShip(300, 600,60,70);

    private AktivVisning gameWindow;

    private Backgrounds theBackground = new Backgrounds();
    
    private SoundPlayer winSound = new SoundPlayer("WinLevel.wav");

    GameInstance(AktivVisning theWindow) {//constructor  or new game

        gameWindow = theWindow;

        GC.spawnEnemies(6, 0);

        System.out.println(GC.enemies.size());

    }

    long tempMillis = 0;

    public void tickGame() {

        millis = System.currentTimeMillis();

        GC.update(millis, myPlayerShip);

        //animation?
        
        if (GC.enemies.isEmpty()) { //if no more enemies is left
            
            theBackground.moveStartsFast(true);
            winSound.playOnce(0);
            
            
            if (tempMillis + 3000 < millis) {
                tempMillis = millis;
                winSound.playOnceReset();
                
                System.out.println("NEXT LEVEL BEGINS");
                
                Random r = new Random();
                
                theBackground.moveStartsFast(false);
                //GC.generateEnemies(2, GameCalculations.enemyTypes.Moon);
                GC.spawnEnemies(r.nextInt(8), r.nextInt(8));
                
            }
            
            //GC.generateEnemies(2, GameCalculations.enemyTypes.Moon);
        }else{tempMillis = millis;} //next level

    }

    /////////////////////////  Drawing Calls///////////////////////////////
    void drawObj(BaseObject b) {                                                //tegner det objekt som er parameter i drawObj på g2    
        b.draw(g2);                                                             //(g2 er grafik objektet til vores Jframe. Dette variablen g2 følger med constructoren)
    }

    //DRAW GAME
    public void drawGame(Graphics2D bufferedGraphics) {
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2

        theBackground.draw(g2);

        myPlayerShip.setXpos(MultiMuselytter.mouseX-(myPlayerShip.width/2));//opdatere rumskibet 

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
