package ui.scenes;

import ui.Application;
import ui.components.Button;
import ui.components.Text;
import ui.components.TransparentPanel;
import utilities.resources.Images;

import javax.swing.*;
import java.awt.*;

public class Menu extends BaseScene {

    public Menu(Application application) {

        super(application);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(Images.load("/backgrounds/menu.png"), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void scaffold() {

        Button startButton = new Button("Start", (e) -> {

            this.application.loadNextLevel();
            this.application.requestFocusInWindow();
        });

        Button exitButton = new Button("Quit", (e) -> {

            this.application.close();
        });

        this.setLayout(new BorderLayout());

        Text title = new Text("Commando", "bold", 140);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(title, BorderLayout.PAGE_START);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        TransparentPanel buttons = new TransparentPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.add(Box.createVerticalGlue());

        this.addButton(startButton, buttons);
        this.addButton(exitButton, buttons);

        buttons.add(Box.createVerticalGlue());

        this.add(buttons, BorderLayout.CENTER);
    }

    private void addButton(Button button, TransparentPanel panel) {

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
}
