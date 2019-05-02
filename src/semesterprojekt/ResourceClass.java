package semesterprojekt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
        LoadSprite("SpaceShip.png");
    }

    public static void LoadSprite(String spriteString) {//læg
/*
        for (int i = 0; i < LoadedSprites.size(); i++) {

            if (LoadedSprites.get(i).toString().equals(spriteString)) {

                return LoadedSprites.get(i);
            }

        }
*/
        //hvis spriten ikke blev fundet i den loaded hukommelse-array
        try {

            BufferedImage TempImg = ImageIO.read(new File(spriteString));
            LoadedSprites.add(TempImg);
            System.out.println("LOADED: "+ spriteString);
            

        } catch (IOException e) {
            System.out.println("ERROR LOADING IMAGE");
            
        }

    }

}
