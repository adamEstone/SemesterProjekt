/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Adam
 */
public class Animation extends BaseObject {

    Graphics2D g2;
    private long millisPrFrame;
    private long currentMillis;

    private Image spriteSheet;

    private int currentFrame;
    private int currentFrameXpos;
    private int currentFrameYpos;

    private int framesX;
    private int framesY;

    boolean donePlaying = false;

    public Animation(int x_in, int y_in, int framWidth_in, int framHeight_in, int framesX_in, int framesY_in, int millisPrFrame_in) {

        xPos = x_in;
        yPos = y_in;
        width = framWidth_in;
        height = framHeight_in;

        framesX = framesX_in;
        framesY = framesY_in;

        currentFrame = 0;

        currentMillis = System.currentTimeMillis();
        millisPrFrame = millisPrFrame_in;

        try {

            spriteSheet = ImageIO.read(new File("src/semesterprojekt/sprites/exp0.png"));
            spriteSheet = spriteSheet.getScaledInstance(width * framesX, width * framesY, 0);

        } catch (Exception e) {
            System.out.println("EEEEEERRRRRRROOOOOOORRRRRRR!!!!!");
        }

    }

    public boolean hasEnded() {
        if (currentFrame == (framesX * framesY)) {
             System.out.println("DOOONEE!!");
            return true;
        }
        return false;
    }

    private void run() {

        long millis = System.currentTimeMillis();

        if (millis > millisPrFrame + currentMillis) {
            currentMillis = millis;
                        currentFrame++;
            currentFrameXpos++;

            if (currentFrameXpos % 4 == 0) {
                currentFrameXpos = 0;
                currentFrameYpos++;
            }


            
        }

    }

    public void draw(Graphics2D g2) {
        
        run();
        
        g2.drawImage(spriteSheet,
                xPos, yPos,
                xPos + width, yPos + height,
                currentFrameXpos * width, currentFrameYpos * height,
                (currentFrameXpos * width) + width, (currentFrameYpos * height) + height,
                null);

    }

}
