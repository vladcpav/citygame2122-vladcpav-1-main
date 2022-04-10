package misc;

import characters.Player;
import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;

public class EnhancedView extends UserView {

    Player player;

    public EnhancedView(World w, Player player, int width, int height) {

        super(w, width, height);

        this.player = player;
    }

    protected void paintForeground(Graphics2D g) {

        g.drawString("Hp", 50, 20);

        // Health bar

        g.setColor(Color.RED);
        g.fillRect(50, 30, 240, 30);
        g.setColor(Color.GREEN);
        g.fillRect(50, 30, Math.round((this.player.getHitpoint() / 100) * 240), 30);

        g.setColor(Color.BLACK);
    }
}
