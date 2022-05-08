package game.objects;

import city.cs.engine.*;
import events.ShelterReachedListener;
import game.characters.Player;

public class Shelter extends StaticBody {

    private Sensor sensor;

    private ShelterReachedListener shelterReachedListener;

    public Shelter(World level) {

        super(level);

        this.addImage(new BodyImage("resources/sprites/shelter.png", 16));
        this.sensor = new Sensor(this, new BoxShape(8, 8));

        this.sensor.addSensorListener(new SensorListener() {

            @Override
            public void beginContact(SensorEvent e) {

                Body otherBody = e.getContactBody();

                if (otherBody instanceof Player) {
                    if (Shelter.this.shelterReachedListener != null) {
                        Shelter.this.shelterReachedListener.shelterReached();
                    }
                }
            }

            @Override
            public void endContact(SensorEvent e) {}
        });
    }

    public void setShelterReachedListener(ShelterReachedListener listener) {

        this.shelterReachedListener = listener;
    }
}
