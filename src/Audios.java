import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audios {
    Clip end;
    Audios() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("src/audio/end.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        end = clip;
    }


    public void playEnd(){
         end.start();
    }

}