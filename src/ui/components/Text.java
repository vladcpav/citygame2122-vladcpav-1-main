package ui.components;

import utilities.resources.Fonts;

import javax.swing.*;

public class Text extends JLabel {

    public Text(String text, String weight, float size) {

        super(text);

        if (weight.equals("bold")) {
            this.setFont(Fonts.GAME_FONT_BOLD.deriveFont(size));
        }
        else if (weight.equals("regular")) {
            this.setFont(Fonts.GAME_FONT_REGULAR.deriveFont(size));
        }
        else {
            throw new IllegalArgumentException("Invalid weight argument, must be bold or regular");
        }
    }
}
