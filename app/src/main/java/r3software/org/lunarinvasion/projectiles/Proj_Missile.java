package r3software.org.lunarinvasion.projectiles;

import java.util.List;

import r3software.org.lunarinvasion.engine.math.Vector2;

import static r3software.org.lunarinvasion.engine.math.Vector2.div;
import static r3software.org.lunarinvasion.engine.math.Vector2.mul;
import static r3software.org.lunarinvasion.engine.math.Vector2.sub;
import static r3software.org.lunarinvasion.engine.math.Vector2.vec2Normalize;

/**
 * Created by Jeff on 5/17/2015.
 *
 * This is the most complex projectile. The missile has two states:
 * ARRIVE and SEEK.
 *
 * ARRIVE:
 *
 * The missile fires at a high initial velocity, and uses the Arrive behavior to
 * slow down and stop at the cannon's target's location.
 *
 * When the missile finally stops, proceeds to SEEK state.
 *
 * SEEK:
 *
 * The missile uses the Seek behavior to move directly toward the enemy cannon.
 *
 * In any state:
 *
 * The missile detonates upon contact with the first thing it hits, cannon
 * or platform.
 *
 */

public class Proj_Missile extends Projectile {

    private List<Projectile> projectiles;

    public Vector2 target;

    public Vector2 enemyShipLocation;

    public Vector2 accel;

    public Vector2 steeringForce;

    public float stateTime;

    public int wallsHit;

    public static final int MAX_WALLS_HIT = 25;

    public enum STATE {
        ARRIVE,
        SEEK
    }

    public STATE curState;

    public Proj_Missile(float x, float y, float width, float height,
                     float mass, float maxSpeed, float maxForce,
                     float radius, Vector2 target, Vector2 enemyShipLocation,
                        List<Projectile> projectiles) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projectiles = projectiles;

        this.projType = TYPE.MISSILE;
        this.curState = STATE.ARRIVE;
        this.stateTime = 0f;

        this.target = target;
        this.enemyShipLocation = enemyShipLocation;

        this.accel = new Vector2(0,0);
        this.steeringForce = new Vector2(0,0);

        this.wallsHit = 0;

    }


    @Override
    public void update(float deltaTime)  {
        //handled below

    }

    public void update(float deltaTime, int index) {

        if(this.fizzleState == FIZZLE_STATE.FIZZLING) {
            this.fizzleTime += deltaTime;
        } else {

            steeringForce = calculate();

            accel = div(steeringForce, mass);

            velocity.add(accel.x * deltaTime, accel.y * deltaTime);

            velocity.truncate(maxForce);

            position.add(velocity.x * deltaTime, velocity.y * deltaTime);

            bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
            boundingCircle.center.set(position);


            this.existedTime += deltaTime;
            this.stateTime += deltaTime;

            if(this.existedTime >= 5) {
                this.toggleFizzleState();
            }

            if (wallsHit >= MAX_WALLS_HIT) {
                projectiles.remove(index);
            }

            //check for switch to SEEK
            if (this.curState == STATE.ARRIVE) {
                float distToTarget = target.dist(position);

                //change state
                if (distToTarget <= 0.2f) {
                    this.curState = STATE.SEEK;
                    this.stateTime = 0;
                    this.target = enemyShipLocation;
                }
            }

            this.tailCounter++;

            if (tailCounter >= 5) {
                addPosToTail(pos());
                tailCounter = 0;
            }
        }

    }



    /**
     * This behavior is similar to seek but it attempts to arrive at the
     * target with a zero velocity
     */
    private Vector2 Arrive(Vector2 Targetpos, Deceleration deceleration) {
        Vector2 ToTarget = sub(Targetpos, position);

        //calculate the distance to the target
        float dist = ToTarget.len();


        final float slowdownRadius = 0f;

        if (dist > slowdownRadius) {

            //because Deceleration is enumerated as an int, this value is required
            //to provide fine tweaking of the deceleration..
            final float DecelerationTweaker = 0.3f;

            //calculate the speed required to reach the target given the desired
            //deceleration

            float speed = dist / ((float) deceleration.value() * DecelerationTweaker);

            //make sure the velocity does not exceed the max
            speed = Math.min(speed, maxSpeed);

            //from here proceed just like Seek except we don't need to normalize
            //the ToTarget vector because we have already gone to the trouble
            //of calculating its length: dist.
            Vector2 DesiredVelocity = mul(ToTarget, speed  / dist);

            return sub(DesiredVelocity, velocity);


        }

        return new Vector2(0, 0);
    }

    /* Arrive makes use of these to determine how quickly a vehicle
    should decelerate to its target */
    public enum Deceleration {

        slow(3), normal(2), fast(1);
        private int dec;

        Deceleration(int d) {
            this.dec = d;
        }

        public int value() {
            return dec;
        }
    }


    float ARRIVE_SCALE = 50f;
    float SEEK_SCALE = 0.0009f;

    /**
     * Given a target, this behavior returns a steering force which will
     *  direct the agent towards the target
     */
    private Vector2 Seek(Vector2 Targetpos) {
        Vector2 DesiredVelocity = mul(vec2Normalize(sub(Targetpos, position)),
                maxSpeed);

        return sub(DesiredVelocity, velocity).mul(ARRIVE_SCALE);
    }

    private Vector2 calculate() {
        steeringForce.zero();

        if(this.curState == STATE.ARRIVE) {
            steeringForce = Arrive(target, Deceleration.fast).mul(ARRIVE_SCALE);
        }

        if(this.curState == STATE.SEEK) {
            steeringForce = Seek(target).mul(SEEK_SCALE);
        }

        return steeringForce;

    }


}
