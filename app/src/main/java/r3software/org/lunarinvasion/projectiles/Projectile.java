package r3software.org.lunarinvasion.projectiles;

import r3software.org.lunarinvasion.engine.framework.DynamicGameObject;
import r3software.org.lunarinvasion.engine.math.Circle;
import r3software.org.lunarinvasion.engine.math.Vector2;

/**
 * Created by Jeff on 1/29/2015.
 *
 * Base class for all weapons.
 */
public abstract class Projectile extends DynamicGameObject {

    /*
        This class inherits:

        From GO:

        public Vector2 position;
	    public Rectangle bounds;
        public boolean tagged;

        From DGO:

        //current velocity
        protected Vector2 velocity;
        //normalized velocity vector
        protected Vector2 heading;
        //current acceleration
        protected Vector2 accel;
        //a vector perpendicular to the heading vector
        protected Vector2 side;
        //the maximum speed this entity may travel at.
        public float maxSpeed;
        //this entity's mass. Used for calculating force (F = MA)
        public float mass;
        //the maximum force this entity can produce to power itself
        //(think rockets and thrust)
        public float maxForce;
     */

    // projectile types
    public enum TYPE {
        ORANGE,
        BLUE,
        GREEN,
        RED,
        MISSILE
    }

    public TYPE projType;

    public final float projectileBaseSpeed = 5f;

    public float idOfLastPlatformHit = -99;

    public static final float PROJECTILE_HEIGHT = 1f;
    public static final float PROJECTILE_WIDTH = 1f;

    public Vector2 lastNonCollidingPosition = new Vector2();

    public Circle boundingCircle;

    public float existedTime;

    //these are for drawing the projectile's tail
    public Vector2 tail1;
    public Vector2 tail2;
    public Vector2 tail3;
    public Vector2 tail4;
    public Vector2 tail5;

    public int tailCounter;

    public Projectile(float x, float y, float width, float height,
                      float mass, float maxSpeed, float maxForce,
                      float radius) {
        super(x, y, width, height, mass, maxSpeed, maxForce);


        this.boundingCircle = new Circle(pos().x, pos().y, radius);
        this.existedTime = 0f;

        this.tail1 = new Vector2(-1, -1);
        this.tail2 = new Vector2(-1, -1);
        this.tail3 = new Vector2(-1, -1);
        this.tail4 = new Vector2(-1, -1);
        this.tail5 = new Vector2(-1, -1);

        this.tailCounter = 0;

    }

    public void update(float deltaTime)  {

       position.add(velocity.x * deltaTime, velocity.y * deltaTime);
       bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
        boundingCircle.center.set(position);

        this.existedTime += deltaTime;

        this.tailCounter++;

        if(tailCounter >= 5) {
            addPosToTail(pos());
            tailCounter = 0;
        }
    }

    protected void addPosToTail(Vector2 newPos) {

        Vector2 tail1Old = tail1.cpy();
        this.tail1.set(newPos);

        Vector2 tail2Old = tail2.cpy();
        this.tail2.set(tail1Old);

        Vector2 tail3Old = tail3.cpy();
        this.tail3.set(tail2Old);

        Vector2 tail4Old = tail4.cpy();
        this.tail4.set(tail3Old);

        this.tail5.set(tail4Old);

    }

    public void setLastNonCollidingPosition(Vector2 pos) {
        this.lastNonCollidingPosition = pos;
    }

    public void fire(float scalarForce) {
        this.velocity.set(heading).mul(scalarForce);
    }

    public void reflectX() {

        Vector2 copyCurrentVel = new Vector2(-this.velocity.x, this.velocity.y);

        this.velocity.zero();

        this.velocity.set(copyCurrentVel);

        //this.velocity.x = -this.velocity.x;
    }

    public void reflectY() {

        Vector2 copyCurrentVel = new Vector2(this.velocity.x, -this.velocity.y);

        this.velocity.zero();

        this.velocity.set(copyCurrentVel);

        //this.velocity.y = -this.velocity.y;
    }

    public void reflectBoth() {
        Vector2 copyCurrentVel = new Vector2(-this.velocity.x, -this.velocity.y);

        this.velocity.zero();

        this.velocity.set(copyCurrentVel);
    }

    public void setIdOfLastPlatformHit(float id) {

        this.idOfLastPlatformHit = id;

    }

}
