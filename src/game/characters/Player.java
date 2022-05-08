package game.characters;

import city.cs.engine.*;
import events.GameOverListener;
import game.objects.PlayerBullet;
import org.jbox2d.common.Vec2;
import game.objects.Ground;

public class Player extends Character {

    // Sprite definitions

    private static final BodyImage IDLE_IMAGE = new BodyImage("resources/sprites/player-idle.gif", 6);
    private static final BodyImage JUMP_IMAGE = new BodyImage("resources/sprites/player-jump.png", 6);
    private static final BodyImage RUN_IMAGE = new BodyImage("resources/sprites/player-run.gif", 6);
    private static final BodyImage RUN_SHOOT_IMAGE = new BodyImage("resources/sprites/player-run-shoot.gif", 6);
    private static final BodyImage STAND_SHOOT_IMAGE = new BodyImage("resources/sprites/player-stand-shoot.gif", 6);

    // States

    private float baseVSpeed = 70;
    private float baseHSpeed = 40;
    private float baseDamage = 20;
    private float maxHitpoint = 100;
    private boolean isGrounded = true;
    private boolean isJumping = false;
    private boolean isMoving = false;
    private boolean isShooting = false;
    private int baseAmmo = 50;
    private int baseCooldown = 10;
    private int ammo = this.baseAmmo;
    private int cooldown = 0;
    private float hitpoint = 100;

    private GameOverListener gameOverListener;

    public Player(World world) {

        super(world);

        this.switchCurrentImage(Player.IDLE_IMAGE);

        this.addCollisionListener(e -> {

            Body otherBody = e.getOtherBody();

            if (otherBody instanceof Ground
                && !Player.this.isGrounded) {

                Player.this.isGrounded = true;
            }
        });
    }

    public float getHitpoint() {

        return this.hitpoint;
    }

    public float getMaxHitpoint() {

        return this.maxHitpoint;
    }

    public int getAmmo() {

        return this.ammo;
    }

    @Override
    public void destroy() {

        super.destroy();
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

    public void hit(float damage) {

        this.hitpoint -= damage;

        if (this.hitpoint <= 0) {
            if (this.gameOverListener != null) {
                this.gameOverListener.gameEnded();
            }
        }
    }

    public void setGameOverListener(GameOverListener listener) {

        this.gameOverListener = listener;
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

        if (this.isShooting
            && this.ammo > 0) {

            if (this.cooldown == 0) {
                this.cooldown = this.baseCooldown;

                this.ammo--;
                if (this.ammo < 0) {
                    this.ammo = 0;
                }

                PlayerBullet playerBullet = new PlayerBullet(this.getWorld(), this.direction, this.baseDamage);
                Vec2 currentPosition = this.getPosition();
                playerBullet.setPosition(new Vec2(currentPosition.x + 3 * this.direction, currentPosition.y));
            }
            else {
                this.cooldown--;
            }
        }
    }
}