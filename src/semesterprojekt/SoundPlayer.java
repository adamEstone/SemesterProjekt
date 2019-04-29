/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Adam
 */
public class SoundPlayer {
    

	public static void play(String SoundName) {
		try {
			
			File m = new File(SoundName).getAbsoluteFile();
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(m);

			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
		} catch (Exception e) {
	
			e.printStackTrace();
			
		}
	}
    
    
}
