package views;

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

        float hitpoint = ((Level) this.getWorld()).getPlayer().getHitpoint();

        g.fillRect(50, 30, Math.round((hitpoint / 100) * 240), 30);

        g.setColor(Color.BLACK);
    }

    protected void paintBackground(Graphics2D g) {

        BufferedImage image = ((Level) this.getWorld()).getBackgroundImage();
        if (image == null) {
            return;
        }

        double sy = this.getHeight() / (float) image.getHeight();
        double sx = this.getWidth() / (float) image.getWidth();
        AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);

        g.drawImage(image, transform, null);
    }
}
