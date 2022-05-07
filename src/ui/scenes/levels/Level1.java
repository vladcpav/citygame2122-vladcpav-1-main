package ui.scenes.levels;

import ui.Application;
import utilities.resources.Backgrounds;

import javax.swing.*;

public class Level1 extends BaseLevel {

    public Level1(Application application) {

        super(application, new game.levels.Level1());
    }

    @Override
    protected void scaffold(JPanel panel) {

        this.setBackgroundImage(Backgrounds.load("background-sky.png"));

        super.scaffold(panel);
    }
}
