package game.levels;

import game.characters.Enemy;
import game.objects.Tree;
import game.powerups.Plant;
import ui.scenes.Game;
import game.objects.Floor;
import org.jbox2d.common.Vec2;
import game.powerups.Ammo;
import game.powerups.Powerup;

import java.util.Random;

public class Level1 extends Level {

    public Level1(Game game) {

        super(game, "resources/backgrounds/background-sky.png");

        // PLayer positioning

        this.player.setPosition(new Vec2(0, 8));

        // Ground

        Floor ground = new Floor(this, 200, 12);
        ground.setPosition(new Vec2(0, 0));

        // Trees

        Random ran = new Random();

        for (int i = 0; i < 6; i++) {
            Tree tree = new Tree(this);
            tree.setPosition(new Vec2(ran.nextFloat(80) - 20, 12));
        }


        // Enemies

        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(ran.nextFloat(50) + 30, 8));
        }

        // Power up

        for (int i = 0; i < 3; i++) {
            int powerUpId = ran.nextInt(2);

            Powerup powerup;
            if (powerUpId == 0) {
                powerup = new Ammo(this);
            }
            else {
                powerup = new Plant(this);
            }

            powerup.setPosition(new Vec2(ran.nextFloat(40) + 40, 8));
        }

        Powerup health = new Ammo(this);
        health.setPosition(new Vec2(20, 8));

        // Setup portal

        this.portal.setPosition(new Vec2(100, 8));
    }
}