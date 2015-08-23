package r3software.org.lunarinvasion.projectiles;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.engine.math.Circle;
import r3software.org.lunarinvasion.engine.math.OverlapTester;

/**
 * Created by Jeff on 5/17/2015.
 *
 * The blue projectile checks to see whether it's within a certain distance
 * to the enemy ship. If it is, it explodes in a 3x3 blast.
 */
public class Proj_Blue extends Projectile {

    public static final float EXPLOSION_RADIUS = 2f;

    public enum BLUE_STATE {
        NORMAL,
        EXPLODING
    }
    public BLUE_STATE curState;

    public float stateTime;

    public Circle explosionCheck;

    //target for explosion
    public Cannon enemyCannon;

    //ref to world for explosion damage
    private World world;


    public Proj_Blue(float x, float y, float width, float height,
                       float mass, float maxSpeed, float maxForce,
                       float radius, Cannon enemyCannon,
                        World world) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.BLUE;
        this.curState = BLUE_STATE.NORMAL;
        this.stateTime = 0f;

        this.explosionCheck = new Circle(x, y, EXPLOSION_RADIUS);
        this.enemyCannon = enemyCannon;

        this.world = world;
    }


    @Override
    public void update(float deltaTime)  {

        if(fizzleState == FIZZLE_STATE.FIZZLING) {
            this.fizzleTime += deltaTime;

        } else {

            //only move if we're in the normal state
            if (curState == BLUE_STATE.NORMAL) {
                position.add(velocity.x * deltaTime, velocity.y * deltaTime);
                bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
                boundingCircle.center.set(position);
                explosionCheck.center.set(position);

                //check to see if we should explode
                checkRadius(enemyCannon.cannonCircle);

                this.tailCounter++;

                if (tailCounter >= 5) {
                    addPosToTail(pos());
                    tailCounter = 0;
                }


            }

            this.existedTime += deltaTime;
            this.stateTime += deltaTime;

            if(existedTime > 5 && curState != BLUE_STATE.EXPLODING) {
                this.toggleFizzleState();
            }


        }


    }

    private void checkRadius(Circle enemyBounds) {
        if(OverlapTester.overlapCircles(explosionCheck,
                enemyBounds)) {

            //BOOM!
            explode(true);
        }
    }

    //cause blue projectile to explode
    public void explode(boolean hurtShip) {
        this.curState = BLUE_STATE.EXPLODING;
        this.stateTime = 0f;

        if(hurtShip) {
            enemyCannon.damage(10);
        }

        world.checkBlueShotExplosionRadius(this, true);

        Assets.playSound(Assets.blue_activate);


    }
}
