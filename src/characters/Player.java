package characters;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import platforms.Floor;

public class Player extends DynamicBody {

    // Sprite definitions

    private static final BodyImage IDLE_IMAGE = new BodyImage("resources/sprites/player-idle.gif", 4);
    private static final BodyImage JUMP_IMAGE = new BodyImage("resources/sprites/player-jump.png", 4);
    private static final BodyImage RUN_IMAGE = new BodyImage("resources/sprites/player-run.gif", 4);

    // States

    private AttachedImage currentImage;
    private float baseVSpeed = 8;
    private float baseHSpeed = 10;
    private int direction = 1;                  // 1 = forward, -1 = backwards
    private boolean isGrounded = true;
    private boolean isJumping = false;
    private boolean isMoving = false;
    private float hitpoint = 100;

    public Player(World world) {

        super(world, new BoxShape(2, 2));

        this.switchCurrentImage(Player.IDLE_IMAGE);

        this.addCollisionListener(e -> {

            Body otherBody = e.getOtherBody();
            if (otherBody instanceof Enemy) {
                Player.this.hitpoint -= ((Enemy) otherBody).getDamage();
                return;
            }

            if (otherBody instanceof Floor &&
                    !Player.this.isGrounded) {

                Player.this.isGrounded = true;
            }
        });
    }

    private void switchCurrentImage(BodyImage image) {

        if (this.currentImage == null ||
                this.currentImage.getBodyImage() != image) {

            this.removeAllImages();
            this.currentImage = this.addImage(image);
        }

        if ((this.direction == -1 && !this.currentImage.isFlippedHorizontal())
                || (this.direction == 1 && this.currentImage.isFlippedHorizontal())) {

            this.currentImage.flipHorizontal();
        }
    }

    public float getHitpoint() {

        return this.hitpoint;
    }

    public void increaseHitpoint(int percentage) {

        this.hitpoint = Math.min(100, (float) (100 + percentage) / 100 * this.hitpoint);
    }

    public void moveForward() {

        this.isMoving = true;
        this.direction = 1;
    }

    public void moveBackwards() {

        this.isMoving = true;
        this.direction = -1;
    }

    public void stopMoving() {

        this.isMoving = false;
    }

    public boolean isMovingForward() {

        return this.direction == 1;
    }

    public boolean isMovingBackwards() {

        return this.direction == -1;
    }

    public void jump() {

        this.isJumping = true;
    }

    public void stopJumping() {

        this.isJumping = false;
    }

    public void update() {

        Vec2 currentVelocity = this.getLinearVelocity();

        float actualVSpeed = currentVelocity.y;
        float actualHSpeed = this.isMoving ? this.baseHSpeed * this.direction : 0;

        if (this.isJumping) {
            if (this.isGrounded) {
                this.isGrounded = false;
                actualVSpeed = this.baseVSpeed;
            }
        }

        if (!this.isGrounded) {
            this.switchCurrentImage(Player.JUMP_IMAGE);
        }
        else {
            this.switchCurrentImage(this.isMoving ? Player.RUN_IMAGE : Player.IDLE_IMAGE);
        }

        this.setLinearVelocity(new Vec2(actualHSpeed, actualVSpeed));
    }
}