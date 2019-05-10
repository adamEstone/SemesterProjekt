
package semesterprojekt;

import java.awt.*;
import javax.swing.WindowConstants;

/**
 *
 * @author Adam
 */
public class SemesterProjekt {
    

    public static void main(String[] args) {

        //--------- OPRETTELSE AF SPILVINDUE ----------------
//        SpilVindue panel = new SpilVindue();        // opret panelet
        AktivVisning vindue = new AktivVisning();    // opret et vindue på skærmen
        //vindue.add(panel);                          // vis panelet i vinduet
        vindue.setSize(AreaCoordinates.AC.getPlayableAreaX() + 
                       AreaCoordinates.AC.getInfoAreaX(),
                       AreaCoordinates.AC.getPlayableAreaY());
        vindue.setResizable(false);
        vindue.init();
       // vindue.setDefaultCloseOperation(vindue.EXIT_ON_CLOSE);
       
        //-------------- INPUT FRA BRUGER -------------------
        MultiMuselytter lytter = new MultiMuselytter();
        vindue.addMouseListener(lytter);
        vindue.addMouseMotionListener(lytter);

        Tastetryk tast = new Tastetryk();
        vindue.addKeyListener(tast);
        vindue.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //--------------------------------------------------

        //opretter selve spillet i det vindue som er parrameter 
        GameLogic myGame = new GameLogic(vindue); 
        
        //TODO:
        //myGame.runthegame();
        
    }

    public static void initFuldskærm(Frame vindue) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice dev = env.getDefaultScreenDevice();
        vindue.setUndecorated(true); // vis ikke vinduesramme, titel, luk-knap etc.
        dev.setFullScreenWindow(vindue);
    }

}
