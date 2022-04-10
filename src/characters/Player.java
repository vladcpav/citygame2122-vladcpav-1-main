package characters;

import city.cs.engine.*;
import objects.Bullet;
import org.jbox2d.common.Vec2;
import objects.Floor;

public class Player extends DynamicBody {

    // Sprite definitions

    private static final BodyImage IDLE_IMAGE = new BodyImage("resources/sprites/player-idle.gif", 4);
    private static final BodyImage JUMP_IMAGE = new BodyImage("resources/sprites/player-jump.png", 4);
    private static final BodyImage RUN_IMAGE = new BodyImage("resources/sprites/player-run.gif", 4);
    private static final BodyImage RUN_SHOOT_IMAGE = new BodyImage("resources/sprites/player-run-shoot.gif", 4);
    private static final BodyImage STAND_SHOOT_IMAGE = new BodyImage("resources/sprites/player-stand-shoot.gif", 4);

    // States

    private AttachedImage currentImage;
    private float baseVSpeed = 8;
    private float baseHSpeed = 10;
    private int direction = 1;                  // 1 = forward, -1 = backwards
    private boolean isGrounded = true;
    private boolean isJumping = false;
    private boolean isMoving = false;
    private boolean isShooting = false;
    private int baseAmmo = 50;
    private int baseCooldown = 20;
    private int ammo = this.baseAmmo;
    private int cooldown = 0;
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

    public int getAmmo() {

        return this.ammo;
    }

    public void replenishAmmo(int amount) {

        this.ammo += amount;
        if (this.ammo > this.baseAmmo) {
            this.ammo = this.baseAmmo;
        }
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

    public void shoot() {

        this.isShooting = true;
    }

    public void stopShooting() {

        this.isShooting = false;
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
            if (this.isMoving) {
                this.switchCurrentImage(this.isShooting ? Player.RUN_SHOOT_IMAGE : Player.RUN_IMAGE);
            }
            else {
                this.switchCurrentImage(this.isShooting ? Player.STAND_SHOOT_IMAGE : Player.IDLE_IMAGE);
            }
        }

        this.setLinearVelocity(new Vec2(actualHSpeed, actualVSpeed));

        if (this.isShooting) {
            if (this.cooldown == 0) {
                this.cooldown = this.baseCooldown;

                this.ammo--;
                if (this.ammo < 0) {
                    this.ammo = 0;
                }

                Bullet bullet = new Bullet(this.getWorld(), this.direction);
                Vec2 currentPosition = this.getPosition();
                bullet.setPosition(new Vec2(currentPosition.x + 2 * this.direction, currentPosition.y));
            }
            else {
                this.cooldown--;
            }
        }
    }
}