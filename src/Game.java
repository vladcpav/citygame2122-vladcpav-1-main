import characters.Enemy;
import characters.Guard;
import characters.Player;
import characters.Bruce;

import city.cs.engine.*;
import city.cs.engine.Shape;
import misc.EnhancedView;
import org.jbox2d.common.Vec2;
import powerups.Health;
import powerups.Powerup;
import powerups.Health;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {

    public Game() {

        // Initialise world, camera, user view and frame

        World world = new World();
        Player player = new Bruce(world, "resources/sprites/ak47.gif");

        EnhancedView view = new EnhancedView(world, player, 1000, 500);

        JFrame frame = new JFrame("My game");
        frame.add(view);

        // Frame settings

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // enable the frame to quit the application when the x button is pressed
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();                                                       // size the frame to fit the world view
        frame.setVisible(true);

        // Listeners

        world.addStepListener(new StepListener() {

            @Override
            public void preStep(StepEvent stepEvent) {
            }

            @Override
            public void postStep(StepEvent stepEvent) {

                // Move player

                player.update();

                // Follow player position

                view.setCentre(player.getPosition());
            }
        });

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

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
                }

                if (e.getKeyCode() == 38) {
                    player.elevateWeapon();
                }

                if (e.getKeyCode() == 40) {
                    player.lowerWeapon();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if ((e.getKeyCode() == 68 && player.isMovingForward())
                    || (e.getKeyCode() == 65 && player.isMovingBackwards())) {

                    player.stopMoving();
                    return;
                }

                if (e.getKeyCode() == 87) {
                    player.stopJumping();
                }
            }
        });


        // Debug frame

        new DebugViewer(world, 500, 500);

        // Start
        world.start();

        // Ground

        Shape shape = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0f, -11.5F));

        // Enemies

        Enemy guard = new Guard(world);
        guard.setPosition(new Vec2(30, 0));
        guard.setLinearVelocity(new Vec2(-10, 0));

        // Power up

        Powerup health = new Health(world);
        health.setPosition(new Vec2(-20, -10));
    }

    public static void main(String[] args) {

        new Game();
    }
}
