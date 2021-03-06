package semesterprojekt;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {

    private Clip soundClip;
    private long currFramePos;
    private boolean waitingForResume = false;

    public SoundPlayer(String filename) {

        try {

            //AudioInputStream sourceStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(filename));
            File audioFile = new File(filename).getAbsoluteFile();
            AudioInputStream sourceStream = AudioSystem.getAudioInputStream(audioFile);

            soundClip = AudioSystem.getClip();
            soundClip.open(sourceStream);
            soundClip = this.soundClip;
            soundClip.addLineListener(audioListener);

        } catch (Exception sp) {
            sp.printStackTrace();
        }
    }

    LineListener audioListener = new LineListener() {

        public void update(LineEvent event) {

            if (event.getType() == LineEvent.Type.START) {

            }

            if (event.getType() == LineEvent.Type.STOP) {
                //System.out.println("Lyd er slut!");
                if (!waitingForResume) {
                    remove();
                }
            }
        }
    };

    public void remove() {
        this.soundClip.close();
    }

    public void play(int framePos) {

        if (soundClip == null) {
            while (true) {
                System.out.println("STOOOP!");
            }
            //return;
        }
        if (soundClip.isRunning()) {
            return;
        }//dont play

        soundClip.setFramePosition(framePos);
        soundClip.start();

    }

    public void pause() {
        waitingForResume = true;//soundclip will not be removed by remove()

        if (soundClip == null) {
            currFramePos = 0;
        }

        currFramePos = soundClip.getMicrosecondPosition();
        stop();

    }

    public void resume() {
        if (!soundClip.isRunning()) {
            soundClip.setMicrosecondPosition(currFramePos);
            soundClip.start();
        }

    }

    public void stop() {
        if (soundClip == null) {
            return;
        }

        if (soundClip.isRunning() == true) {

            soundClip.stop();
            //soundClip.flush();
            //soundClip.close();
        }

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
