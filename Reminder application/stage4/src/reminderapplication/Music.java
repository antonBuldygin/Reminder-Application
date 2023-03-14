package reminderapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class Music {
    Clip clip;
    AudioInputStream sound;
    private static final Logger LOG = LoggerFactory.getLogger(Music.class);

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            LOG.info("relative path {}", file.getPath().toString());
            LOG.info("absolute path {}", file.getAbsolutePath().toString());
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            LOG.info("Clip instance {}", clip);
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
