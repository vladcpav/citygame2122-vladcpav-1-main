package ui.scenes.levels;

import ui.Application;
import utilities.resources.Backgrounds;

import javax.swing.*;

public class Level2 extends BaseLevel {

    public Level2(Application application) {

        super(application, new game.levels.Level2());
    }

    @Override
    protected void scaffold(JPanel panel) {

        this.setBackgroundImage(Backgrounds.load("background-cave.png"));

        super.scaffold(panel);
    }
}
