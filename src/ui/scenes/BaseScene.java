package ui.scenes;

import ui.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseScene {

    private BufferedImage backgroundImage;
    private JPanel panel;

    public JPanel build(Application application) {

        this.panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                BufferedImage image = BaseScene.this.backgroundImage;

                if (image == null) {
                    return;
                }

                g.drawImage(image, 0, 0, application.getWidth(), application.getHeight(), null);
            }
        };

        this.scaffold(this.panel, application);

        return this.panel;
    }

    protected void setBackgroundImage(BufferedImage image) {

        this.backgroundImage = image;
        this.panel.repaint();
    }

    public void cleanup() {}

    protected abstract void scaffold(JPanel panel, Application application);
}
