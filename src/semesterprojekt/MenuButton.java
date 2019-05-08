package semesterprojekt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Adam
 */
public class MenuButton extends BaseObject {

    private String text = "EmptyString";
    private int textOffset = 0;

    private boolean mouseAlreadyEntered = false;

    SoundPlayer hoverSound = new SoundPlayer("ButtonSound.wav");

    //simpel
    public MenuButton(int posX_in, int posY_in, int Width_in, int Height_in, String text_in) {
        xPos = posX_in;
        yPos = posY_in;
        width = Width_in;
        height = Height_in;
        text = text_in;
    }

    //Med textOffset (flytter strenges x position i knappen)
    public MenuButton(int posX_in, int posY_in, int Width_in, int Height_in, String text_in, int textOffset_in) {
        xPos = posX_in;
        yPos = posY_in;
        width = Width_in;
        height = Height_in;
        text = text_in;
        textOffset = textOffset_in;
    }

    public GameInstance.gameState buttonPressedAction(GameInstance.gameState currentGameState, GameInstance.gameState newGameState) {//retuner gamestate når der er trykket

        if (MultiMuselytter.mouseX > xPos && MultiMuselytter.mouseX < xPos + width
                && MultiMuselytter.mouseY > yPos && MultiMuselytter.mouseY < yPos + height) {//hvis musen rammer

            if (MultiMuselytter.leftButtonDown) {
                return newGameState;
            }//hvis vestre mus trykkes

        }

        return currentGameState;
    }

    @Override
    public void draw(Graphics2D g2) {

        if (MultiMuselytter.mouseX > xPos && MultiMuselytter.mouseX < xPos + width
                && MultiMuselytter.mouseY > yPos && MultiMuselytter.mouseY < yPos + height) { //hvis musen rammer

            if (!mouseAlreadyEntered) {  //if the mouse has not aleready entered play sound.
                hoverSound.play(0);
                mouseAlreadyEntered = true;
            }

            g2.setColor(Color.gray);
            g2.fill3DRect(xPos, yPos, width, height, false);

            g2.setColor(Color.RED);
            g2.setFont(new Font("default", Font.BOLD, 27));
            g2.drawString(text, xPos + width / 2 - text.length() - textOffset - 6, yPos + height / 2 + 10);

        } else {//hvis musen IKKE rammer

            mouseAlreadyEntered = false;

            g2.setColor(Color.gray);
            g2.fill3DRect(xPos, yPos, width, height, true);

            g2.setColor(Color.white);
            g2.setFont(new Font("default", Font.BOLD, 25));
            g2.drawString(text, xPos + width / 2 - text.length() - textOffset, yPos + height / 2 + 7);

        }

    }

}
