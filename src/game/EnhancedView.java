package game;

import characters.Player;
import city.cs.engine.UserView;
import city.cs.engine.World;
import levels.Level;

import java.awt.*;

public class EnhancedView extends UserView {

    public EnhancedView(Level w, int width, int height) {

        super(w, width, height);
    }

    protected void paintForeground(Graphics2D g) {

        g.drawString("Hp", 50, 20);

        // Health bar

        g.setColor(Color.RED);
        g.fillRect(50, 30, 240, 30);
        g.setColor(Color.GREEN);

        float hitpoint = ((Level) this.getWorld()).getPlayer().getHitpoint();

        g.fillRect(50, 30, Math.round((hitpoint / 100) * 240), 30);

        g.setColor(Color.BLACK);
    }
}
