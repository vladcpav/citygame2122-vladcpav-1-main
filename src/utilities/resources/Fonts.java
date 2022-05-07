package utilities.resources;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {

    public static Font GAME_FONT_REGULAR;
    public static Font GAME_FONT_BOLD;

    public static void setup() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            Fonts.GAME_FONT_BOLD = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/IBMPlexMono-Bold.ttf"));
            Fonts.GAME_FONT_REGULAR = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/IBMPlexMono-Medium.ttf"));
        } catch (IOException | FontFormatException exception) {
            System.out.println("Failed to load font: " + exception);
        }

        ge.registerFont(Fonts.GAME_FONT_BOLD);
        ge.registerFont(Fonts.GAME_FONT_REGULAR);
    }
}
