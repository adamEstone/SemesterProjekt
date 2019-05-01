
package semesterprojekt;

import java.io.Console;
import java.io.File;
import javax.sound.sampled.*;


public class SoundPlayer {
    
    private Clip soundClip;
    private int currFramePos;
    
    public SoundPlayer(String filename){
        
        try {
            
            //AudioInputStream sourceStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(filename));
            File audioFile = new File(filename).getAbsoluteFile();
            AudioInputStream sourceStream = AudioSystem.getAudioInputStream(audioFile);
            
            soundClip = AudioSystem.getClip();
            soundClip.open(sourceStream);
            soundClip = this.soundClip;
            soundClip.addLineListener(audioListener);
        }
     
        catch (Exception sp){
        sp.printStackTrace();
        }
    }
    
    
        LineListener audioListener = new LineListener() {

            public void update(LineEvent event) {
                 if (event.getType() == LineEvent.Type.START) {

                }
            }
        };
        
    
    
    public void play(int framePos){
    
        if (soundClip == null){
            while(true){
                System.out.println("STOOOP!");
            }
            //return;
        }
        
        soundClip.setFramePosition(framePos);
        soundClip.start();
    }
    
    
    public void pause(){
        
       if (soundClip == null){
            currFramePos = 0;
        }
       
       currFramePos = soundClip.getFramePosition();
       stop();
       
        
    }
    
    public void resume(){
        if (soundClip == null){
            return;
        }
        
        play(currFramePos);
        
    }
    
    public void stop(){
        if (soundClip == null){
            return;
        }
        
        //if (soundClip.isRunning() == true){
       
                    
        soundClip.stop();
        //soundClip.flush();
        //soundClip.close();
        System.out.println("ØVVVVVVVVVVVVVVVVVVVVVVVVVVVV!");
        //}
        
    }
    
    
    
    

/*	public static void play(String SoundName) {
		try {
			
			File m = new File(SoundName).getAbsoluteFile();
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(m);

			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
		} catch (Exception e) {
	
			e.printStackTrace();
			
		}
	} */
    
    
}
