package semesterprojekt;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;

import java.util.Iterator;
import java.util.Random;

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

    private PlayerShip myPlayerShip = new PlayerShip(300, 600, 60, 70);

    private AktivVisning gameWindow;

    private Backgrounds theBackground = new Backgrounds();

    private Random r = new Random();
    
    private Stats stats = new Stats();
    
    
    
    private boolean levelHasChanged = false;//

    
    GameInstance (AktivVisning theWindow) {//constructor  or new game

        myPlayerShip.statsRef = stats;
        gameWindow = theWindow;
        GC.myPlayerShipRef = myPlayerShip;
        theBackground.stats = stats;
        
        GC.spawnEnemies(6, 0);

        System.out.println(GC.enemies.size());

    }

    long tempMillis = 0;

    public void tickGame() {

        myPlayerShip.setXpos(MultiMuselytter.mouseX - 
                            (myPlayerShip.width / 2));//opdatere rumskibet
        
        millis = System.currentTimeMillis();

        GC.update(millis, myPlayerShip);

        //animation?
        if (GC.enemies.isEmpty()) { //if no more enemies is left

            if (!levelHasChanged) { //only run once due to levelHasChanged variable
                levelHasChanged = true;//level just changed

                theBackground.moveStarsFast(true);
                SoundPlayer winSound = new SoundPlayer("WinLevel.wav");
                winSound.play(0);

            }

            if (tempMillis + 3000 < millis) {
                tempMillis = millis;
                levelHasChanged = false;
                //winSound.playOnceReset();
                
                stats.nextLevel();
                
                System.out.println("NEXT LEVEL BEGINS");           
                
                theBackground.moveStarsFast(false);
                //GC.generateEnemies(2, GameCalculations.enemyTypes.Moon);
                GC.spawnEnemies(1 + r.nextInt(7), r.nextInt(8));

            }

            //GC.generateEnemies(2, GameCalculations.enemyTypes.Moon);
        } else {
            tempMillis = millis;
        } //next level

    }

    /////////////////////////  Drawing Calls///////////////////////////////
    void drawObj(BaseObject b) {     //tegner det objekt som er parameter i drawObj på g2    
        b.draw(g2);                  //(g2 er grafik objektet til vores Jframe. Dette variablen g2 følger med constructoren)
    }

    //DRAW GAME
    public void drawGame(Graphics2D bufferedGraphics) {
        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2
        
        drawObj(theBackground);

        myPlayerShip.setXpos(MultiMuselytter.mouseX  - (myPlayerShip.width / 2));//opdatere rumskibet 

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

        Iterator<Animation> iterator = GC.explotions.iterator();
        while (iterator.hasNext()) {
            Animation next = iterator.next();
            if (!next.hasEnded()) {
                drawObj(next);
            } else {
                iterator.remove();
            }
        }
        //enemies.forEach((enemy) -> enemy.draw(tempG2D));
    }

    //DRAW MENU
    private final int menuButtonWidth
            = AreaCoordinates.AC.getMenuButtonWidth();

    private final int menuButtonHeight
            = AreaCoordinates.AC.getMenuButtonHeight();

    private final MenuButton btnResume = new MenuButton(
            AreaCoordinates.AC.getA(),
            AreaCoordinates.AC.getB(50),
            menuButtonWidth,
            menuButtonHeight,
            "Resume",
            38);

    private final MenuButton btnNewGame = new MenuButton(
            AreaCoordinates.AC.getA(),
            AreaCoordinates.AC.getB(120),
            menuButtonWidth,
            menuButtonHeight,
            "New Game",
            48);

    private final MenuButton btnQuit = new MenuButton(
            AreaCoordinates.AC.getA(),
            AreaCoordinates.AC.getB(190),
            menuButtonWidth,
            menuButtonHeight,
            "Quit",
            20);

    private void drawMenuBackground(Graphics2D bufferedGraphics) {

        g2 = bufferedGraphics;//vigtig! overføre bufferen til g2

        g2.setColor(Color.darkGray);
        g2.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight()); //tegn overlay så man ikke kan se spillet

        g2.setColor(Color.GRAY);
        g2.fillRect(
                AreaCoordinates.AC.getMenuLocX(),
                AreaCoordinates.AC.getMenuLocY(),
                AreaCoordinates.AC.getMenuWidth(),
                AreaCoordinates.AC.getMenuHeight());

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("default", Font.BOLD, 50));
        g2.drawString(
                "Menu",
                //TODO fix dynamicly - see AreaCoordinates.java
                //menuLocation.x + (menuWidth / 2) - 65,
                AreaCoordinates.AC.getStrLocX("Menu"),
                AreaCoordinates.AC.getStrLocY(90));         //Menu Tekst

        //int t = g2.getFontMetrics().stringWidth("Menu") / 2;
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
