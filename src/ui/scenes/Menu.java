package ui.scenes;

import ui.Application;
import ui.components.Button;
import ui.components.Text;
import ui.components.TransparentPanel;
import utilities.resources.Backgrounds;

import javax.swing.*;
import java.awt.*;

public class Menu extends BaseScene {

    public Menu(Application application) {

        super(application, Backgrounds.load("menu.png"));
    }

    @Override
    protected void scaffold(JPanel panel) {

        Button startButton = new Button("Start", (e) -> {

            this.application.loadNextLevel();
            this.application.requestFocusInWindow();
        });

        Button exitButton = new Button("Quit", (e) -> {

            this.application.close();
        });

        panel.setLayout(new BorderLayout());

        Text title = new Text("Commando", "bold", 140);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(title, BorderLayout.PAGE_START);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        TransparentPanel buttons = new TransparentPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.add(Box.createVerticalGlue());

        this.addButton(startButton, buttons);
        this.addButton(exitButton, buttons);

        buttons.add(Box.createVerticalGlue());

        panel.add(buttons, BorderLayout.CENTER);
    }

    private void addButton(Button button, TransparentPanel panel) {

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
    }
}
