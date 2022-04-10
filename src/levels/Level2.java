package levels;

import characters.Enemy;
import characters.Guard;
import game.Game;
import objects.Floor;
import org.jbox2d.common.Vec2;
import powerups.Health;
import powerups.Powerup;

public class Level2 extends Level {

    public Level2(Game game) {

        super(game);

        // Ground

        Floor ground = new Floor(this, 60, 1); ;
        ground.setPosition(new Vec2(2, -14.5f));

        // Enemies

        Enemy guard = new Guard(this);
        guard.setPosition(new Vec2(30, 0));
        guard.setLinearVelocity(new Vec2(-10, 0));

        // Power up

        Powerup health = new Health(this);
        health.setPosition(new Vec2(-20, -10));
        this.portal.setPosition(new Vec2(30, -10));

    }
}

