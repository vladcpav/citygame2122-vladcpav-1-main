package game.characters;

import city.cs.engine.*;
import game.objects.Bullet;
import org.jbox2d.common.Vec2;
import game.objects.Ground;

import java.lang.reflect.Field;
import java.util.Random;

public class Player extends DynamicBody {

    // Sprite definitions

    private static final BodyImage IDLE_IMAGE = new BodyImage("resources/sprites/player-idle.gif", 6);
    private static final BodyImage JUMP_IMAGE = new BodyImage("resources/sprites/player-jump.png", 6);
    private static final BodyImage RUN_IMAGE = new BodyImage("resources/sprites/player-run.gif", 6);
    private static final BodyImage RUN_SHOOT_IMAGE = new BodyImage("resources/sprites/player-run-shoot.gif", 6);
    private static final BodyImage STAND_SHOOT_IMAGE = new BodyImage("resources/sprites/player-stand-shoot.gif", 6);

    // States

    private AttachedImage currentImage;
    private float baseVSpeed = 70;
    private float baseHSpeed = 40;
    private int direction = 1;                  // 1 = forward, -1 = backwards
    private boolean isGrounded = true;
    private boolean isJumping = false;
    private boolean isMoving = false;
    private boolean isShooting = false;
    private int baseAmmo = 50;
    private int baseCooldown = 10;
    private int ammo = this.baseAmmo;
    private int cooldown = 0;
    private float hitpoint = 100;

    private Random rng = new Random();

    public Player(World world) {

        super(world, new BoxShape(3, 3));

        // Hack to set body rotation

        try {
            Field b2bodyField = this.getClass().getSuperclass().getSuperclass().getDeclaredField("b2body");
            b2bodyField.setAccessible(true);
            ((org.jbox2d.dynamics.Body) b2bodyField.get(this)).setFixedRotation(true);
        }
        catch (IllegalAccessException | NoSuchFieldException exception) {
            System.out.println(exception);
        }

        this.switchCurrentImage(Player.IDLE_IMAGE);
        this.setGravityScale(15);

        this.addCollisionListener(e -> {

            Body otherBody = e.getOtherBody();
            if (otherBody instanceof Enemy) {
                Player.this.hitpoint -= ((Enemy) otherBody).getDamage();
                return;
            }

            if (otherBody instanceof Ground &&
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

        if (this.isShooting &&
                this.ammo > 0) {

            if (this.cooldown == 0) {
                this.cooldown = this.baseCooldown;

                this.ammo--;
                if (this.ammo < 0) {
                    this.ammo = 0;
                }

                Bullet bullet = new Bullet(this.getWorld(), this.direction);
                Vec2 currentPosition = this.getPosition();
                bullet.setPosition(new Vec2(currentPosition.x + 3 * this.direction, currentPosition.y));
            }
            else {
                this.cooldown--;
            }
        }
    }
}