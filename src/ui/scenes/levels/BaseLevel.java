package ui.scenes.levels;

import game.characters.Enemy;
import game.characters.Player;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import ui.Application;
import ui.scenes.BaseScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public abstract class BaseLevel extends BaseScene {

    protected Player player;
    protected World world;
    private UserView view;
    private KeyListener keyListener;
    private StepListener stepListener;
    protected ArrayList<Enemy> enemies;

    BaseLevel(Application application) {

        super(application);
    }

    @Override
    public void cleanup() {

        // Cleanup world

        this.world.stop();
        this.world.removeStepListener(this.stepListener);

        for (StaticBody body: this.world.getStaticBodies()) {
            body.destroy();
        }

        for (DynamicBody body: this.world.getDynamicBodies()) {
            body.destroy();
        }

        this.application.removeKeyListener(this.keyListener);

        // Nullify fields

        this.world = null;
        this.player = null;
        this.view = null;
        this.keyListener = null;
        this.stepListener = null;
        this.enemies = null;
    }

    @Override
    public void scaffold() {

        // Setup

        this.world = new World();
        this.player = new Player(this.world);
        this.view = new UserView(this.world, this.application.getWidth(), this.application.getHeight());
        this.view.setOpaque(false);
        this.enemies = new ArrayList();
        new DebugViewer(this.world, 500, 500);

        // Build world

        this.build();

        // Listeners

        this.keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                Player player = BaseLevel.this.player;

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

                Player player = BaseLevel.this.player;

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

        this.application.addKeyListener(this.keyListener);

        this.stepListener = new StepListener() {

            @Override
            public void preStep(StepEvent stepEvent) {}

            @Override
            public void postStep(StepEvent stepEvent) {

                BaseLevel.this.player.update();
                BaseLevel.this.view.setCentre(new Vec2(BaseLevel.this.player.getPosition().x + 16, BaseLevel.this.view.getCentre().y));

                for (Enemy enemy: BaseLevel.this.enemies) {
                    enemy.update();
                }
            }
        };

        this.world.addStepListener(this.stepListener);

        this.add(this.view);
        this.world.start();
    }

    protected Enemy addEnemy(Enemy enemy) {

        this.enemies.add(enemy);

        enemy.addDestructionListener((e) -> {

            this.enemies.remove(enemy);
        });

        return enemy;
    }

    protected abstract void build();
}

