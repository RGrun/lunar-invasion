package r3software.org.lunarinvasion;

import java.util.List;

import r3software.org.lunarinvasion.engine.framework.GameObject;
import r3software.org.lunarinvasion.engine.math.Circle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.powerups.WeaponPU;
import r3software.org.lunarinvasion.projectiles.Projectile;

import static r3software.org.lunarinvasion.engine.math.Vector2.add;
import static r3software.org.lunarinvasion.engine.math.Vector2.sub;

/**
 * Created by Jeff on 1/29/2015.
 *
 * Base class for the two player ships.
 *
 * Should probibly be named "ship", but current name refers to
 * the demo game that this game was based on.
 */

@SuppressWarnings("AccessStaticViaInstance")
public class Cannon extends GameObject {

    /*
        This class inherits:

        From GameObject:

        public Vector2 position;
	    public Rectangle bounds;
        public boolean tagged;
     */

    //is the cannon dead?
    public enum CANNON_STATE {
        ALIVE,
        DEAD
    }

    public CANNON_STATE curState;
    public float stateTime;

    public float cannonAngle;
    public Vector2 target;
    public final Vector2 POINT_NORTH;

    //used to check for teleport safety
    public Circle cannonCircle;
    public final float CANNON_RADIUS = 1f;

    //used to identify cannon
    public static int ID;

    public final float TELEPORT_DISTANCE_MAX = 14;
    public float currentTeleportEnergy;
    public float energyRatio;
    public final float FIFTEEN_PERCENT_OF_ENERGY = TELEPORT_DISTANCE_MAX * 0.15f;
    public final float TWENTYFIVE_PERCENT_OF_ENERGY = TELEPORT_DISTANCE_MAX * 0.25f;

    public float health;

    public static final float CANNON_WIDTH = 2f;
    public static final float CANNON_HEIGHT = 2f;

    //for rendering the shield animation
    public float existedTime;

    //reference to world
    private World world;

    //current weapon
    public Projectile.TYPE curWeapon;

    // ammo of each weapon
    // orange type has unlimited ammo
    public int blueAmmo;
    public int greenAmmo;
    public int redAmmo;
    public int missileAmmo;

    public boolean shieldOn;

    public Cannon(float x, float y, float width,
                  float height, float startingAngle,
                  int ID, World world) {
        super(x, y, width, height);

        this.cannonCircle = new Circle(position.x, position.y, CANNON_RADIUS);

        this.world = world;

        this.health = 60f;

        this.target = new Vector2();

        this.ID = ID;

        //this will need to change once I get things working better
        //does not currently work with alien cannon
        this.POINT_NORTH = add(position, new Vector2(3, 0));

        //this is used to rotate (in radians) the cannon's texture
        //this should always be recalculated when the cannon's target is changed
        //0 == NORTH, PI == SOUTH, PI/2 == WEST, 3PI/2 == EAST
        cannonAngle = startingAngle;

        existedTime = 0;

        this.currentTeleportEnergy = TELEPORT_DISTANCE_MAX;
        this.energyRatio = (currentTeleportEnergy / TELEPORT_DISTANCE_MAX);

        // cannon starts with orange weapon type selected by default
        this.curWeapon = Projectile.TYPE.ORANGE;

        // starting ammo
        this.blueAmmo = 0;
        this.greenAmmo = 0;
        this.redAmmo = 0;
        this.missileAmmo = 0;

        this.shieldOn = false;

        this.curState = CANNON_STATE.ALIVE;
        this.stateTime = 0f;
    }

    public void setTarget(Vector2 targetPos) {

        //replace target
        this.target = targetPos;

        //angle is in radians
        float angle = (float)
                Math.atan2(target.y - position.y,
                        target.x - position.x);

        angle *= Vector2.TO_DEGREES; //convert to degrees for ease of use

        if (angle < 0) {
            angle = 360 - (-angle);
        }


        if (ID == World.HUMAN_CANNON) this.cannonAngle = angle + 90;
        else if (ID == World.ALIEN_CANNON) this.cannonAngle = angle + 270;

    }

    public void update(float deltaTime) {
        this.existedTime += deltaTime;
        this.stateTime += deltaTime;
    }

    public boolean teleport(Vector2 newPos, List<TeleportEffect> teleportEffects) {
        //calculate new position after checking distance to newPos
        //and checking against teleportEnergy

        float distToNewPos = sub(newPos, pos()).len();

        if (currentTeleportEnergy - distToNewPos > 0) {
            //allow teleport

            this.currentTeleportEnergy = currentTeleportEnergy - distToNewPos;
            this.energyRatio = currentTeleportEnergy / TELEPORT_DISTANCE_MAX;

            teleportEffects.add(new TeleportEffect(this.position));
            teleportEffects.add(new TeleportEffect(newPos, true));

            this.position = newPos;
            this.bounds.move(newPos);
            this.cannonCircle.center.set(newPos);

            world.checkPowerUpCollisionsCannons();

            if(world.state == World.H_MOVE ||
                    world.state == World.H_CANNON_AIM) {
                world.humanTargetOn = false;
            } else if (world.state == World.A_MOVE ||
                    world.state == World.A_CANNON_AIM) {
                world.alienTargetOn = false;
            }
            Assets.playSound(Assets.warp);
            return true;
        } else {
            //teleport only as far as allowed
            Vector2 truncatedPos = calculateOvershootPoint(pos(), newPos,
                    currentTeleportEnergy);

            if(!world.safeToTeleport(truncatedPos, this)) {
                return false;
            }

            this.currentTeleportEnergy = 0;
            this.energyRatio = currentTeleportEnergy / TELEPORT_DISTANCE_MAX;

            teleportEffects.add(new TeleportEffect(this.position));
            teleportEffects.add(new TeleportEffect(truncatedPos, true));

            this.position = truncatedPos;
            this.bounds.move(truncatedPos);
            this.cannonCircle.center.set(truncatedPos);

            world.checkPowerUpCollisionsCannons();

            if(world.state == World.H_MOVE ||
            world.state == World.H_CANNON_AIM) {
                world.humanTargetOn = false;
            } else if (world.state == World.A_MOVE ||
                    world.state == World.A_CANNON_AIM) {
                world.alienTargetOn = false;
            }
            Assets.playSound(Assets.warp);
            return true;
        }


    }

    private Vector2 calculateOvershootPoint(Vector2 A, Vector2 C, float maxDist) {

        float topHalfX = maxDist * (C.x - A.x);
        float bottomHalfX = (float) Math.sqrt(((A.x - C.x) * (A.x - C.x)) +
                (A.y - C.y) * (A.y - C.y));

        float Bx = A.x + (topHalfX / bottomHalfX);

        float topHalfY = maxDist * (C.y - A.y);
        float bottomHalfY = (float) Math.sqrt(((A.x - C.x) * (A.x - C.x)) +
                (A.y - C.y) * (A.y - C.y));

        float By = A.y + (topHalfY / bottomHalfY);

        return new Vector2(Bx, By);

    }

    public boolean setWeapon(Projectile.TYPE newType) {
        if(checkAmmo(newType)) {
            this.curWeapon = newType;
            return true;
        } else {
            return false;
        }
    }

    //returns true on successful weapon change
    //returns false if ship has no ammo for weapon
    @SuppressWarnings("RedundantIfStatement")
    private boolean checkAmmo(Projectile.TYPE newType) {

        switch(newType) {
            case ORANGE:
                return true;

            case BLUE:
                if(blueAmmo > 0) {
                    return true;
                } else {
                    return false;
                }
            case GREEN:
                if(greenAmmo > 0) {
                    return true;
                } else {
                    return false;
                }
            case RED:
                if(redAmmo > 0) {
                    return true;
                } else {
                    return false;
                }
            case MISSILE:
                if(missileAmmo > 0) {
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    public void damage(float damage) {

        if(shieldOn) {
            disableShield();
        } else {
            this.health -= damage;
        }

        if (health >= 10) {
            world.shieldEffects.add(new ShieldEffect(pos()));

            if (currentTeleportEnergy < TELEPORT_DISTANCE_MAX) {
                //give 15% of total energy back each time ship is hit
                currentTeleportEnergy += TWENTYFIVE_PERCENT_OF_ENERGY;


                //do not allow > 100%
                if (currentTeleportEnergy > TELEPORT_DISTANCE_MAX) {
                    currentTeleportEnergy = TELEPORT_DISTANCE_MAX;
                }
                energyRatio = currentTeleportEnergy /
                        TELEPORT_DISTANCE_MAX;
            }

            Assets.playSound(Assets.take_damage);
        }

        // cannon is dead
        if(health <= 0) {
            this.curState = CANNON_STATE.DEAD;
            this.stateTime = 0f;

            Assets.playSound(Assets.player_death);
        }
    }

    public void heal(float health) {

        Assets.playSound(Assets.powerup);

        if(this.health < 60f) {
            this.health += health;

            if(this.health > 60f) {
                this.health = 60f;
            }
        }
    }

    public int getCurrentAmmo() {
        switch(curWeapon) {
            case ORANGE:
                return 99;
            case BLUE:
                return blueAmmo;
            case GREEN:
                return greenAmmo;
            case RED:
                return redAmmo;
            case MISSILE:
                return missileAmmo;
        }

        return -1;
    }

    public void addAmmo(WeaponPU.CONTENTS type, int amount) {

        Assets.playSound(Assets.pickup);

        switch(type) {
            case BLUE:
                this.blueAmmo += amount;
                if(this.blueAmmo > 9) this.blueAmmo = 9;
                break;
            case GREEN:
                this.greenAmmo += amount;
                if(this.greenAmmo > 9) this.greenAmmo = 9;
                break;
            case RED:
                this.redAmmo += amount;
                if(this.redAmmo > 9) this.redAmmo = 9;
                break;
            case MISSILE:
                this.missileAmmo += amount;
                if(this.missileAmmo > 9) this.missileAmmo = 9;
                break;
        }

    }

    public void enableShield() {
        Assets.playSound(Assets.pickup);
        shieldOn = true;
    }

    public void disableShield() {
        shieldOn = false;
    }

}
