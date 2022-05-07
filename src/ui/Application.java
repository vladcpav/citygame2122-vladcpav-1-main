package ui;

import ui.scenes.BaseScene;
import ui.scenes.Menu;
import ui.scenes.levels.BaseLevel;
import ui.scenes.levels.Level1;
import ui.scenes.levels.Level2;
import utilities.resources.Fonts;

import javax.swing.*;

public class Application extends JFrame {

    private int level = -1;
    private BaseLevel[] levels = new BaseLevel[]{new Level1(this), new Level2(this)};

    private BaseScene currentScene;

    public void start() {

        System.out.println("Starting application...");

        // Load resources

        System.out.println("Setting up resources...");
        Fonts.setup();

        // Setup window

        System.out.println("Setting up window...");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Commando");
        this.setResizable(false);
        this.loadScene(new Menu(this));
    }

    public void close() {

        System.out.println("Exiting application...");
        System.exit(0);
    }

    public void loadNextLevel() {

        this.level++;

        if (this.level > this.levels.length - 1) {
            System.out.println("Game over!");
            this.close();
            return;
        }

        this.loadScene(this.levels[this.level]);
    }

    public void loadScene(BaseScene scene) {

        if (this.currentScene != null) {
            System.out.println("Cleaning up old scene: " + this.currentScene.getClass().getName());
            this.currentScene.cleanup();
        }

        System.out.println("Scaffolding new scene: " + scene.getClass().getName());
        this.currentScene = scene;
        this.setContentPane(scene.build());
        this.revalidate();
    }
}
