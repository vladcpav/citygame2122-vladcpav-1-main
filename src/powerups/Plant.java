package powerups;

import characters.Player;
import city.cs.engine.*;

import java.util.Random;

public class Plant extends Powerup {

    public Plant(World world) {

        super(world, "resources/sprites/plant.png");

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();
                if (otherBody instanceof Player) {
                    Random ran = new Random();
                    ((Player) otherBody).increaseHitpoint(ran.nextInt(5) + 5);
                    Plant.this.destroy();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
