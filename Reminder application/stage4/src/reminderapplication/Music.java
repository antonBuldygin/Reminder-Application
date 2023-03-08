package reminderapplication;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class Music {
    Clip clip;
    AudioInputStream sound;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);


            System.out.println("moe " +file.getPath().toString());
            System.out.println("abs" + file.getAbsolutePath().toString());
            sound = AudioSystem.getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.open(sound);
            System.out.println("Музыка "+clip);
        }
        catch (Exception e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}
