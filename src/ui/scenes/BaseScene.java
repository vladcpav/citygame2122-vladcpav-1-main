package ui.scenes;

import ui.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseScene {

    private BufferedImage backgroundImage;

    protected Application application;

    protected BaseScene(Application application, BufferedImage backgroundImage) {

        this.application = application;
        this.backgroundImage = backgroundImage;
    }

    protected BaseScene(Application application) {

        this.application = application;
    }

    public JPanel build() {

        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                BufferedImage image = BaseScene.this.backgroundImage;

                if (image == null) {
                    return;
                }

                Application application = BaseScene.this.application;
                g.drawImage(image, 0, 0, application.getWidth(), application.getHeight(), null);
            }
        };

        this.scaffold(panel);

        return panel;
    }

    public void setBackgroundImage(BufferedImage image) {

        this.backgroundImage = image;
    }

    public void cleanup() {}

    protected abstract void scaffold(JPanel panel);
}
