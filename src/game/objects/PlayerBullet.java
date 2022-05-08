package game.objects;

import game.characters.Enemy;
import city.cs.engine.*;

public class PlayerBullet extends Bullet {

    public PlayerBullet(World world, int direction, float damage) {

        super(world, direction, damage, "resources/sprites/player-bullet.gif");

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();

                if (otherBody instanceof Enemy) {
                    ((Enemy) otherBody).hit(damage, direction);
                }

                if (otherBody instanceof Enemy
                    || otherBody instanceof Ground) {

                    PlayerBullet.this.destroy();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
