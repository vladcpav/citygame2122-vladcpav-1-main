package objects;

import characters.Player;
import city.cs.engine.*;
import levels.Level;

public class Portal extends StaticBody {

    private Sensor sensor;

    public Portal(Level level) {

        super(level);

        this.addImage(new BodyImage("resources/sprites/grunt-portal.png", 8));
        this.sensor = new Sensor(this, new BoxShape(4, 4));

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
