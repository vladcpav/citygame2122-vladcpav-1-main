package game.objects;

import game.characters.Enemy;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bullet extends DynamicBody {

    private Sensor sensor;
    private float baseHSpeed = 80;

    public Bullet(World world, int direction) {

        super(world, new CircleShape(1));

        AttachedImage image = this.addImage(new BodyImage("resources/sprites/player-bullet.gif", 2));
        this.setLinearVelocity(new Vec2(this.baseHSpeed  * direction, 0));

        this.sensor = new Sensor(this, new CircleShape(1));
        this.setGravityScale(0);

        if (direction == -1) {
            image.flipHorizontal();
        }

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();
                if (otherBody instanceof Enemy) {
                    ((Enemy) otherBody).kill();
                }

                if (otherBody instanceof Enemy || otherBody instanceof Ground) {
                    Bullet.this.destroy();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
