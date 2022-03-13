package powerups;

import characters.Player;
import city.cs.engine.*;

public class Health extends Powerup {

    public Health(World world) {

        super(world, "resources/sprites/heart.png");

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();
                if (otherBody instanceof Player) {
                    System.out.println(((Player) otherBody).getHitpoint());
                    ((Player) otherBody).increaseHitpoint(10);
                    System.out.println(((Player) otherBody).getHitpoint());
                    Health.this.destroy();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
