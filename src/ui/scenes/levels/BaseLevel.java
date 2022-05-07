package ui.scenes.levels;

import game.characters.Player;

import city.cs.engine.*;
import game.events.LevelAdapter;
import game.levels.Level;
import ui.Application;
import ui.scenes.BaseScene;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BaseLevel extends BaseScene {

    private Level level;
    private UserView view;

    private KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {

            Player player = BaseLevel.this.level.getPlayer();

            System.out.println("Test");

            if (e.getKeyCode() == 68) {
                player.moveForward();
                return;
            }

            if (e.getKeyCode() == 65) {
                player.moveBackwards();
                return;
            }

            if (e.getKeyCode() == 87) {
                player.jump();
                return;
            }

            if (e.getKeyCode() == 32) {
                player.shoot();
                return;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            Player player = BaseLevel.this.level.getPlayer();

            if ((e.getKeyCode() == 68 && player.isMovingForward())
                    || (e.getKeyCode() == 65 && player.isMovingBackwards())) {

                player.stopMoving();
                return;
            }

            if (e.getKeyCode() == 87) {
                player.stopJumping();
                return;
            }

            if (e.getKeyCode() == 32) {
                player.stopShooting();
                return;
            }
        }
    };

    BaseLevel(Application application, Level level) {

        super(application);
        this.level = level;
    }

    @Override
    public void cleanup() {

        this.level.cleanup();
        this.application.removeKeyListener(this.keyListener);
    }

    @Override
    protected void scaffold(JPanel panel) {

        this.view = new UserView(this.level, this.application.getWidth(), this.application.getHeight());

        // Listeners

        this.level.setLevelListener(new LevelAdapter() {

            @Override
            public void stepped() {

                BaseLevel.this.view.setCentre(BaseLevel.this.level.getPlayer().getPosition());
            }

            @Override
            public void finished() {

                System.out.println("Level finished. Cleaning up...");

                BaseLevel.this.cleanup();
                application.loadNextLevel();
            }
        });

        this.application.addKeyListener(this.keyListener);

        panel.add(this.view);
        this.view.setOpaque(false);

        this.level.start();
    }
}

