package game.powerups;

import game.characters.Player;
import city.cs.engine.*;

import java.util.Random;

public class Ammo extends Powerup {

    public Ammo(World world) {

        super(world, "resources/sprites/ammo.png");

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();
                if (otherBody instanceof Player) {
                    Random rng = new Random();
                    ((Player) otherBody).replenishAmmo(rng.nextInt(10) + 10);
                    Ammo.this.destroy();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
