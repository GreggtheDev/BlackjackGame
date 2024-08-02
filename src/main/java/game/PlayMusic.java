package blackjack.game;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class is used to play background music during the game.
 */
public class PlayMusic {
    public static void playMusic(String filePath) {
        try {
            // Get the URL of the file from the resources directory
            URL musicPath = PlayMusic.class.getClassLoader().getResource(filePath);
            if (musicPath != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
            } else {
                System.out.println("Can't find file: " + filePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
