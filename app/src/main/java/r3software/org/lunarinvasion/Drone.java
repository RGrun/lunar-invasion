package r3software.org.lunarinvasion;

import java.util.List;

import r3software.org.lunarinvasion.engine.framework.DynamicGameObject;
import r3software.org.lunarinvasion.engine.math.Circle;
import r3software.org.lunarinvasion.engine.math.Vector2;

import static r3software.org.lunarinvasion.engine.math.Vector2.div;
import static r3software.org.lunarinvasion.engine.math.Vector2.mul;
import static r3software.org.lunarinvasion.engine.math.Vector2.sub;

/**
 * Created by Jeff on 6/6/2015.
 *
 * This class is a drone. It moves along a list of Vector2s in a patrol fashion.
 * The drone is destroyed when it's shot or hits a player.
 *
 * The Drone uses the Arrive behavior to move fron point to point.
 *
 * There's a chance the drone will drop a power up when it's destroyed.
 */
public class Drone extends DynamicGameObject {

    public enum STATE {
        NORMAL,
        EXPLODING
    }

    public STATE curState;
    public float stateTime;

    public static final float DRONE_HEIGHT = 2f;
    public static final float DRONE_WIDTH = 2f;
    public static final float DRONE_MASS = 1f;
    public static final float DRONE_MAX_SPEED = 10f;
    public static final float DRONE_MAX_FORCE = 3f;
    public static final float DRONE_CIRCLE_RAD = 1f;

    public Circle boundingCircle;

    //use alien drone sprite?
    public boolean isAlienDrone;

    //the list of waypoints that make up the drone's patrol path.
    //the first waypoint should always be the drone's starting position.
    public List<Vector2> waypoints;

    //the point the Drone is arriving at
    public Vector2 curPoint;

    //holds the index of the current waypoint that the drone is Arriving at
    private int curPointIndex;

    //this is used to rotate the drone's sprite.
    public float rotationAngle;


    public Drone(float x, float y, List<Vector2> points, boolean isAlienDrone) {

        super(x, y, DRONE_WIDTH, DRONE_HEIGHT,
                DRONE_MASS, DRONE_MAX_SPEED, DRONE_MAX_FORCE);

        this.waypoints = points;
        this.curState = STATE.NORMAL;
        this.stateTime = 0f;
        this.curPointIndex = 0;
        this.rotationAngle = 0f;
        this.curPoint = waypoints.get(curPointIndex);

        this.isAlienDrone = isAlienDrone;

        this.boundingCircle = new Circle(position.x, position.y,
                DRONE_CIRCLE_RAD);
    }


    @Override
    public void update(float deltaTime) {

        if(curState == STATE.NORMAL) {

            this.curPoint = waypoints.get(curPointIndex);

            steeringForce = calculate();

            accel = div(steeringForce, mass);

            velocity.add(accel.x * deltaTime, accel.y * deltaTime);

            velocity.truncate(maxForce);

            position.add(velocity.x * deltaTime, velocity.y * deltaTime);

            bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
            boundingCircle.center.set(position);


            float distToTarget = curPoint.dist(position);

            //change target to next point
            if (distToTarget <= 0.2f) {
                this.curPointIndex++;

                //wrap around index if too big
                if(curPointIndex >= waypoints.size()) {
                    this.curPointIndex = 0;
                }
            }

            this.stateTime += deltaTime;

            //handle rotation
            this.rotationAngle += 5f;

            if (rotationAngle > 360) {
                rotationAngle -= 360;
            }

        } else if(curState == STATE.EXPLODING) {
            this.stateTime += deltaTime;

        }

    }

    float ARRIVE_SCALE = 50f;

    private Vector2 calculate() {
        steeringForce.zero();

        steeringForce = Arrive(curPoint, Deceleration.fast).mul(ARRIVE_SCALE);

        return steeringForce;

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


    public void explode() {
        if(curState == STATE.NORMAL) {
            curState = STATE.EXPLODING;
            stateTime = 0;
        }
    }
}
