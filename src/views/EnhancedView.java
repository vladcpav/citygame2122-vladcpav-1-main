package views;

import characters.Player;
import city.cs.engine.UserView;
import levels.Level;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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

        Player player = ((Level) this.getWorld()).getPlayer();
        float hitpoint = player.getHitpoint();

        g.fillRect(50, 30, Math.round((hitpoint / 100) * 240), 30);

        g.setColor(Color.BLACK);


        // Ammo

        int ammo = player.getAmmo();

        g.drawString("Ammo", 300, 20);
        g.drawString(Integer.toString(ammo), 300, 50);
    }

    protected void paintBackground(Graphics2D g) {

        Level level = (Level) this.getWorld();
        BufferedImage bgImage = level.getBackgroundImage();

        if (bgImage == null) {
            return;
        }

        double sy = this.getHeight() / (float) bgImage.getHeight();
        double sx = this.getWidth() / (float) bgImage.getWidth();

        AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
        g.drawImage(bgImage, transform, null);
    }
}
