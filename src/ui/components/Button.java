package ui.components;

import utilities.resources.Fonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {

    private static final Color DEFAULT_TEXT_COLOR = Color.decode("#5e5e5e");
    private static final Color HOVERED_TEXT_COLOR = Color.WHITE;

    public Button(String text, ActionListener listener) {

        super(text);

        this.addActionListener(listener);

        this.style();
    }

    private void style() {

        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setFont(Fonts.GAME_FONT_BOLD.deriveFont(60f));

        this.setForeground(Button.DEFAULT_TEXT_COLOR);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                Button.this.setForeground(Button.HOVERED_TEXT_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                Button.this.setForeground(Button.DEFAULT_TEXT_COLOR);
            }
        });
    }
}
