/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.geom.*;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.image.*;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Adam
 */
public class GameLogic {

    public static enum gameState {
        Loading,
        Menu,
        Ingame,
        Pause,
        Settings,
        RestartgameState
    }

    public static gameState mygamestate;

    private static AktivVisning theWindow = null; //pointer til

    public static java.util.List<Enemy> enemies = new ArrayList<>();

    public static java.util.List<BufferedImage> LoadedSprites = new ArrayList<>();

    private long fpsCount = 0;
    private long oldsecond = 0;
    private static boolean mouseState = true;

    private Graphics2D g2D;

    GameLogic(AktivVisning vindue) { //Constructor

        theWindow = vindue;

        vindue.createBufferStrategy(2);   // opret 2 buffere
        BufferStrategy bufferStrategy = vindue.getBufferStrategy();

        //vindue.init();
        //
        Enemy someEnemy = new Enemy();
        enemies.add(someEnemy);

        PlayerShip PlayerSpaceship = new PlayerShip(50, 50, 50, 50);
        LoadedSprites.add(findOrLoadSprite(PlayerSpaceship.NameOfSprite));

        Calendar calendar = Calendar.getInstance(); //bruges til at aflæse tid

        GameInstance myGameInstance = new GameInstance(); //create new game

        //----------------  Game state section -----------------
        mygamestate = gameState.RestartgameState; //start i stadiet

        while (true) { ///////  GameStateLoop /////////////

            if (MultiMuselytter.leftButtonDown) {
                mygamestate = gameState.Menu;
            }
            if (MultiMuselytter.rightButtonDown) {
                mygamestate = gameState.Ingame;
            }

            switch (mygamestate) {
                case Menu:
                    setCursor(true);
                    vindue.g2 = (Graphics2D) bufferStrategy.getDrawGraphics(); // få buffer
                    vindue.tegnMenu();            // tegn på bufferens (med dens Graphics-objekt)
                    bufferStrategy.show();    // vis grafikken EFTER at der er tegnet færdigt
                    vindue.g2.dispose();      // frigiv bufferen så den er klar til genbrug
                    break;
                case Ingame:
                    setCursor(false);
                    vindue.g2 = (Graphics2D) bufferStrategy.getDrawGraphics(); // få buffer
                    vindue.tegnSpil(LoadedSprites); // tegn på bufferens (med dens Graphics-objekt) og brug de loaded billeder fra parameter variablen
                    g2D = vindue.g2;

                    g2D.fillRect(400, 400, 50, 50);

                    vindue.g2 = g2D;
                    bufferStrategy.show();    // vis grafikken EFTER at der er tegnet færdigt
                    vindue.g2.dispose();      // frigiv bufferen så den er klar til genbrug

                    myGameInstance.tickGame();//checks if enemys left,
                    //checkes for collision,
                    //moves enemies,makes enemies shoot

                    break;

                case Pause:

                    break;

                case Loading:
                    vindue.g2 = (Graphics2D) bufferStrategy.getDrawGraphics(); // få buffer
                    vindue.tegnLoading();            // tegn på bufferens (med dens Graphics-objekt)
                    bufferStrategy.show();    // vis grafikken EFTER at der er tegnet færdigt
                    vindue.g2.dispose();      // frigiv bufferen så den er klar til genbrug

                    break;

                case RestartgameState:

                    myGameInstance = new GameInstance();//new bame
                    System.out.println("-- New Game started --");

                    mygamestate = gameState.Ingame;

                    break;
            }

            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }// vent lidt

            //FPS counting
            fpsCount++;

            if (myGameInstance.ResartGame) {
               // mygamestate = gameState.RestartgameState;
            }

            if (oldsecond != calendar.getInstance().get(Calendar.SECOND)) {
                oldsecond = calendar.getInstance().get(Calendar.SECOND);
                // System.out.println(fpsCount + " fps");
                fpsCount = 0;
            }

        }

    }

    private static void hideCursor() {
        // http://www.java2s.com/Code/Java/2D-Graphics-GUI/HidethemousecursoruseatransparentGIFasthecursor.htm
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                image, new Point(0, 0), "invisibleCursor");
        theWindow.setCursor(transparentCursor);

    }

    private static void showCursor() {
        theWindow.setCursor(Cursor.getDefaultCursor());
    }

    private static void setCursor(boolean state) {
        if (state != mouseState) {
            mouseState = state;
            if (mouseState) {
                showCursor();
            } else {
                hideCursor();
            }
        }

    }

    public static BufferedImage findOrLoadSprite(String spriteString) {//læg

        for (int i = 0; i < LoadedSprites.size(); i++) {

            if (LoadedSprites.get(i).toString().equals(spriteString)) {

                return LoadedSprites.get(i);
            }

        }

        //hvis spriten ikke blev fundet i den loaded hukommelse-array
        try {

            BufferedImage TempImg = ImageIO.read(new File("SpaceShip.png"));
            LoadedSprites.add(TempImg);

            return TempImg;

        } catch (IOException e) {
            System.out.println("ERROR LOADING IMAGE");
            return null;
        }

    }

}
