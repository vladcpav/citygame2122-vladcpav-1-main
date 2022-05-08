package utilities.resources;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Sounds {

    public static final SoundClip BACKGROUND_MUSIC = Sounds.load("resources/sounds/background-music.wav");

    private static SoundClip load(String soundPath) {

        try {
            return new SoundClip(soundPath);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            System.out.println(exception);
            return null;
        }
    }
}
