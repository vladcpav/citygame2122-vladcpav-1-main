package game.levels;

import game.characters.Player;
import city.cs.engine.*;
import game.events.LevelAdapter;
import game.objects.Portal;

public class Level extends World {

    protected Portal portal;
    protected Player player;

    private LevelAdapter adapter;
    private StepListener listener;

    Level() {

        this.portal = new Portal(this);
        this.player = new Player(this);
        this.listener = new Level.WorldStepListener();

        this.addStepListener(this.listener);
    }

    public void setLevelListener(LevelAdapter adapter) {

        this.adapter = adapter;
    }

    public void finish() {

        if (this.adapter != null) {
            this.adapter.finished();
        }
    }

    public void cleanup() {

        this.stop();
        this.removeStepListener(this.listener);
        this.adapter = null;

        for (StaticBody body: this.getStaticBodies()) {
            body.destroy();
        }

        for (DynamicBody body: this.getDynamicBodies()) {
            body.destroy();
        }
    }

    public Player getPlayer() {

        return this.player;
    }

    private class WorldStepListener implements StepListener {

        @Override
        public void preStep(StepEvent stepEvent) {
        }

        @Override
        public void postStep(StepEvent stepEvent) {

            // Move player

            Level.this.player.update();

            if (Level.this.adapter != null) {
                Level.this.adapter.stepped();
            }
        }
    }
}
