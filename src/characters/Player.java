package characters;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends DynamicBody {

    private float vSpeed = 7;
    private float hSpeed = 5;
    private int direction = 0;                  // 0 = still, 1 = forward, -1 = backwards
    private boolean isJumping = false;

    private float hitpoint = 100;
    private float stamina = 100;

    private AttachedImage characterImage;
    private AttachedImage weaponImage;
    private float weaponElevation = 0;

    Player(World world, String weaponSpritePath, String spritePath) {

        super(world, new BoxShape(4, 4));

        this.characterImage = this.addImage(new BodyImage(spritePath, 8));
        this.weaponImage = this.addImage(new BodyImage(weaponSpritePath, 8));

        this.addCollisionListener(new CollisionListener() {

            @Override
            public void collide(CollisionEvent e) {

                Body otherBody = e.getOtherBody();
                if (otherBody instanceof Enemy) {
                    Player.this.hitpoint -= ((Enemy) otherBody).getDamage();
                }
            }
        });
    }

    public float getHitpoint() {

        return this.hitpoint;
    }

    public void increaseHitpoint(int percentage) {

        this.hitpoint = Math.min(100, (float) (100 + percentage) / 100 * this.hitpoint);
    }

    public float getStamina() {

        return this.stamina;
    }

    public void moveForward() {

        this.stamina -= 3;
        this.direction = 1;
    }

    public void moveBackwards() {

        this.stamina -= 3;
        this.direction = -1;
    }

    public void stopMoving() {

        this.direction = 0;
    }

    public boolean isMovingForward() {

        return this.direction == 1;
    }

    public boolean isMovingBackwards() {

        return this.direction == -1;
    }

    public void jump() {

        this.stamina -= 5;
        this.isJumping = true;
    }

    public void stopJumping() {

        this.isJumping = false;
    }

    public void elevateWeapon() {

        this.weaponElevation = Math.min(5, this.weaponElevation + 1);
    }

    public void lowerWeapon() {

        this.weaponElevation = Math.max(-5, this.weaponElevation - 1);
    }

    public void update() {

        for (AttachedImage image: this.getImages()) {
            if ((direction == -1 && !image.isFlippedHorizontal())
                || (direction == 1 && image.isFlippedHorizontal())) {

                image.flipHorizontal();
            }
        }

        weaponImage.setRotation((float)0.174533 * this.weaponElevation);

        Vec2 currentVelocity = this.getLinearVelocity();

        if (this.isJumping) {
            if (Math.abs(currentVelocity.y) <= 0.01F) {
                this.setLinearVelocity(new Vec2(currentVelocity.x, vSpeed));
                return;
            }
        }

        this.setLinearVelocity(new Vec2(this.hSpeed * this.direction, currentVelocity.y));
    }
}
