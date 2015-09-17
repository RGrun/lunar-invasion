package r3software.org.lunarinvasion.engine.framework;

import r3software.org.lunarinvasion.engine.math.C2DMatrix;
import r3software.org.lunarinvasion.engine.math.Vector2;

import static r3software.org.lunarinvasion.engine.math.Vector2.sub;
import static r3software.org.lunarinvasion.engine.math.Vector2.vec2Normalize;

@SuppressWarnings("unused")
public class DynamicGameObject extends GameObject {

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

    public Vector2 steeringForce;

	
	public DynamicGameObject(float x, float y, float width,
                             float height, float mass, float maxSpeed,
                             float maxForce) {
		super(x, y, width, height);
		velocity = new Vector2();
		accel = new Vector2();
        this.mass = mass;
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
        this.steeringForce = new Vector2(0,0);
	}


    //this MUST be implemented by a subclass
    public void update(float deltatime) {

    }

    //accessors
    public Vector2 velocity() {
        return velocity;
    }

    public void setVelocity(Vector2 NewVel) {
        velocity = NewVel;
    }

    public float mass() {
        return mass;
    }

    public Vector2 side() {
        return side;
    }

    public float maxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float new_speed) {
        maxSpeed = new_speed;
    }

    public float maxForce() {
        return maxForce;
    }

    public void setMaxForce(float mf) {
        maxForce = mf;
    }

    public boolean isSpeedMaxedOut() {
        return maxSpeed * maxSpeed >= velocity.lenSq();
    }

    public float speed() {
        return velocity.len();
    }

    public float speedSq() {
        return velocity.lenSq();
    }

    public Vector2 heading() {
        return heading;
    }

    /**
     *  given a target position, this method rotates the entity's heading and
     *  side vectors by an amount not greater than m_dMaxTurnRate until it
     *  directly faces the target.
     *
     *  @return true when the heading is facing in the desired direction
     */
    public boolean rotateHeadingToFacePosition(Vector2 target) {
        Vector2 toTarget = vec2Normalize(sub(target, position));

        //first determine the angle between the heading vector and the target
        float angle = (float) Math.acos(heading.dot(toTarget));
        if(Float.isNaN(angle))  {
            angle = 0;
        }

        //return true if the player is facing the target
        if (angle < 0.00001f) {
            return true;
        }


        //The next few lines use a rotation matrix to rotate the player's heading
        //vector accordingly
        C2DMatrix RotationMatrix = new C2DMatrix();

        //notice how the direction of rotation has to be determined when creating
        //the rotation matrix
        RotationMatrix.rotate(angle * heading.sign(toTarget));
        RotationMatrix.transformVector2s(heading);
        RotationMatrix.transformVector2s(velocity);

        //finally recreate m_vSide
        side = heading.perp();


        return false;
    }

    /**
     *  first checks that the given heading is not a vector of zero length. If the
     *  new heading is valid this function sets the entity's heading and side
     *  vectors accordingly
     */
    public void setHeading(Vector2 new_heading) {
        //assert ((new_heading.lenSq() - 1.0) < 0.00001);

        heading = new_heading;

        //the side vector must always be perpendicular to the heading
        side = heading.perp();
    }

    private Vector2 calculateHeading (Vector2.Cardinals facing) {

        switch(facing) {

            case EAST:
                return new Vector2(1, 0);
            case NORTH:
                return new Vector2(0, 1);
            case SOUTH:
                return new Vector2(0, -1);
            case WEST:
                return new Vector2(-1, 0);
            case NORTHEAST:
                return new Vector2(0.5f, 0.5f);
            case NORTHWEST:
                return new Vector2(-0.5f, 0.5f);
            case SOUTHEAST:
                return new Vector2(0.5f, -0.5f);
            case SOUTHWEST:
                return new Vector2(-0.5f, -0.5f);
        }
        return new Vector2(1, 0);
    }

}
