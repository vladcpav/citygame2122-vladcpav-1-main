package game.characters;

import city.cs.engine.*;

import java.lang.reflect.Field;

public class Character extends DynamicBody {

    private AttachedImage currentImage;
    protected int direction = 1;

    Character(World world) {

        super(world, new BoxShape(3, 3));

        // Hack to set body rotation

        try {
            Field b2bodyField = Body.class.getDeclaredField("b2body");
            b2bodyField.setAccessible(true);
            ((org.jbox2d.dynamics.Body) b2bodyField.get(this)).setFixedRotation(true);
        }
        catch (IllegalAccessException | NoSuchFieldException exception) {
            System.out.println(exception);
        }

        this.setGravityScale(15);
    }

    protected void switchCurrentImage(BodyImage image) {

        if (this.currentImage == null
            || this.currentImage.getBodyImage() != image) {

            this.removeAllImages();
            this.currentImage = this.addImage(image);
        }

        if ((this.direction == -1 && !this.currentImage.isFlippedHorizontal())
            || (this.direction == 1 && this.currentImage.isFlippedHorizontal())) {

            this.currentImage.flipHorizontal();
        }
    }
}
