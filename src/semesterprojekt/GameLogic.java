/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;

import java.awt.image.*;

import java.awt.Toolkit;

import java.awt.*;

import java.awt.image.BufferStrategy;

import java.util.Calendar;
import javax.swing.WindowConstants;

/**
 *
 * @author Adam
 */
public class GameLogic {

    private SoundPlayer backgroundMusic;

    private AktivVisning theWindow = null; //pointer til vindue
    private GameInstance myGameInstance = null;
    private Calendar calendar;
    //public static java.util.List<Enemy> enemies = new ArrayList<>();
    //public static java.util.List<BufferedImage> LoadedSprites = new ArrayList<>();
    private long fpsCount = 0;
    private long oldsecond = 0;
    private boolean mouseState = true;

    private Graphics2D g2D;

    ResourceClass Res = new ResourceClass(); //use the resource 

    //private int
    GameLogic(AktivVisning vindue) { //Constructor

        theWindow = vindue;

        theWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Calendar calendar = Calendar.getInstance(); //bruges til at aflæse tid

        theGame();

    }

    private void theGame() {

        //----------------  Game state section -----------------
        GameInstance myGameInstance = new GameInstance(theWindow); //create new game

        myGameInstance.mygamestate = myGameInstance.mygamestate.Ingame; //start i stadiet

        backgroundMusic = new SoundPlayer("BackgroundMusic.wav");
        backgroundMusic.play(0);

        theWindow.createBufferStrategy(2);   // opret 2 buffere
        BufferStrategy bufferStrategy = theWindow.getBufferStrategy();

        /////////////////////////////////////  GameStateLoop ///////////////////////////////
        while (myGameInstance.mygamestate != myGameInstance.mygamestate.QuitGame) {

            switch (myGameInstance.mygamestate) {
                case Ingame:

                    if (Tastetryk.escapeTast) {

                        myGameInstance.mygamestate = myGameInstance.mygamestate.Menu;
                        pause(200);

                        backgroundMusic.pause();//pause musik
                    }

                    setCursor(false);

                    theWindow.g2 = (Graphics2D) bufferStrategy.getDrawGraphics(); // få buffer
                    // tegn på bufferens (med dens Graphics-objekt) og brug de loaded billeder fra parameter variablen

                    myGameInstance.drawGame(theWindow.g2); //TEGN SPILLET

                    bufferStrategy.show();    // vis grafikken EFTER at der er tegnet færdigt
                    theWindow.g2.dispose();      // frigiv bufferen så den er klar til genbrug

                    myGameInstance.tickGame();
                    //-checks if enemys left,
                    //-checkes for collision,
                    //-moves enemies,makes enemies shoot

                    break;

                case Menu:

                    if (Tastetryk.escapeTast) {
                        myGameInstance.mygamestate = myGameInstance.mygamestate.Ingame;
                        pause(200);
                        backgroundMusic.resume();
                    }

                    setCursor(true);

                    theWindow.g2 = (Graphics2D) bufferStrategy.getDrawGraphics(); // få buffer
                    myGameInstance.drawMenu(theWindow.g2);            // tegn på bufferens (med dens Graphics-objekt) 
                    bufferStrategy.show();    // vis grafikken EFTER at der er tegnet færdigt
                    theWindow.g2.dispose();      // frigiv bufferen så den er klar til genbrug

                    break;

                case Loading:

                    theWindow.g2 = (Graphics2D) bufferStrategy.getDrawGraphics(); // få buffer

                    //theWindow.tegnLoading();            // tegn på bufferens (med dens Graphics-objekt)
                    bufferStrategy.show();    // vis grafikken EFTER at der er tegnet færdigt
                    theWindow.g2.dispose();      // frigiv bufferen så den er klar til genbrug

                    break;

                case RestartGameState:
                    myGameInstance = null;
                    myGameInstance = new GameInstance(theWindow);//new bame

                    backgroundMusic.play(0);

                    System.out.println("-- New Game started --");

                    myGameInstance.mygamestate = myGameInstance.mygamestate.Ingame;

                    break;
            }

            pause(5);
            
            CalculateFPS();

        }//end off gameloop

        theWindow.dispose();

        System.out.println("Game has quit");

    }

    private void CalculateFPS() {
        
        //FPS counting
        fpsCount++;

        if (oldsecond != calendar.getInstance().get(Calendar.SECOND)) {
            oldsecond = calendar.getInstance().get(Calendar.SECOND);
            System.out.println(fpsCount + " fps");
            fpsCount = 0;
        }

    }

    private void pause(int waitTime) {
        try {/////vent 10ms
            Thread.sleep(waitTime);
        } catch (Exception e) {
        }// vent lidt

    }

    private void hideCursor() {
        // http://www.java2s.com/Code/Java/2D-Graphics-GUI/HidethemousecursoruseatransparentGIFasthecursor.htm
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                image, new Point(0, 0), "invisibleCursor");
        theWindow.setCursor(transparentCursor);

    }

    private void showCursor() {
        theWindow.setCursor(Cursor.getDefaultCursor());
    }

    private void setCursor(boolean state) {
        if (state != mouseState) {
            mouseState = state;
            if (mouseState) {
                showCursor();
            } else {
                hideCursor();
            }
        }

    }

}
