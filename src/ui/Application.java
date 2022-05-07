package ui;

import ui.scenes.BaseScene;
import ui.scenes.Menu;
import utilities.resources.Fonts;

import javax.swing.*;

public class Application extends JFrame {

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
        this.loadScene(new Menu());
    }

    public void close() {

        System.out.println("Exiting application...");
        System.exit(0);
    }

    public void loadScene(BaseScene scene) {

        String name = scene.getClass().getName();

        if (this.currentScene != null) {
            System.out.println("Cleaning up old scene: " + name);
            this.currentScene.cleanup();
        }

        System.out.println("Scaffolding new scene: " + name);
        this.currentScene = scene;
        this.setContentPane(scene.build(this));
        this.revalidate();
    }
}
