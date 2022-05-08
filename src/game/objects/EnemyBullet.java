package game.objects;

import city.cs.engine.Body;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.World;
import game.characters.Player;

public class EnemyBullet extends Bullet {

    public EnemyBullet(World world, int direction, float damage) {

        super(world, direction, damage, "resources/sprites/enemy-bullet.gif");

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();

                if (otherBody instanceof Player) {
                    ((Player) otherBody).hit(damage);
                }

                if (otherBody instanceof Player
                        || otherBody instanceof Ground) {

                    EnemyBullet.this.destroy();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
