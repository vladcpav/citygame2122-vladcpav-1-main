package game.objects;

import game.characters.Player;
import city.cs.engine.*;
import game.levels.Level;

public class Portal extends StaticBody {

    private Sensor sensor;

    public Portal(Level level) {

        super(level);

        this.addImage(new BodyImage("resources/sprites/grunt-portal.png", 4));
        this.sensor = new Sensor(this, new BoxShape(2, 2));

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();
                if (otherBody instanceof Player) {
                    ((Level) Portal.this.getWorld()).finish();
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }
}
