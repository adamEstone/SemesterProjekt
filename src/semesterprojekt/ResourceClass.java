package semesterprojekt;

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
public class ResourceClass {

    public static java.util.List<BufferedImage> LoadedSprites = new ArrayList<>();
    public static java.util.List<Clip> LoadedSounds = new ArrayList<>();

    ResourceClass() {
        System.out.println("LOADING:");
        LoadSprite("shipA1.png");
        LoadSprite("EnemyShip1.png",48,48);
        LoadSprite("Beam1.png",30,60);
        LoadSprite("Beam2.png",30,60);
        LoadSprite("moon_look_rightV2.png",48,48);
    }
    
    public static void LoadSprite(String spriteString) {//læg

        try {//prøv at hente billede fra disken

            BufferedImage TempImg = ImageIO.read(new File(spriteString));

            //LoadedSprites.add(resize(spriteWidth, spriteHeight, TempImg));
            LoadedSprites.add( TempImg);
            System.out.println("LOADED: " + spriteString);

        } catch (IOException e) {
            System.out.println("ERROR WHILE TYRING LOADING IMAGE: " + spriteString);
            e.printStackTrace();
        }

    }

    public static void LoadSprite(String spriteString,int spriteWidth,int spriteHeight) {//læg

        
        try {//prøv at hente billede fra disken

            BufferedImage TempImg = ImageIO.read(new File(spriteString));

            LoadedSprites.add(resize(spriteWidth, spriteHeight, TempImg));
            //LoadedSprites.add( TempImg);
            System.out.println("LOADED: " + spriteString);

        } catch (IOException e) {
            System.out.println("ERROR WHILE TYRING LOADING IMAGE: " + spriteString);
            e.printStackTrace();
        }

    }

    public static void LoadSound(String soundString) {//læg

    }
    
    
    //http://www.java2s.com/Tutorials/Java/Graphics_How_to/Image/Resize_image.htm
    public static BufferedImage resize(int targetWidth, int targetHeight,
            BufferedImage src) {
        double scaleW = (double) targetWidth / (double) src.getWidth();
        double scaleH = (double) targetHeight / (double) src.getHeight();

        double scale = scaleW < scaleH ? scaleW : scaleH;

        BufferedImage result = new BufferedImage((int) (src.getWidth() * scale),
                (int) (src.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(src, 0, 0, result.getWidth(), result.getHeight(), null);
        g2d.dispose();

        return result;
    }

}
