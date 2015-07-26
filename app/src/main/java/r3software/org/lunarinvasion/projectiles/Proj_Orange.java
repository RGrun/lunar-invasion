package r3software.org.lunarinvasion.projectiles;

/**
 * Created by Jeff on 5/17/2015.
 *
 * The orange projectile is the standard, default one.
 * It simply flies forward and bounces off walls.
 *
 */

public class Proj_Orange extends Projectile {

    public Proj_Orange(float x, float y, float width, float height,
                      float mass, float maxSpeed, float maxForce,
                      float radius) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.ORANGE;

    }


    @Override
    public void update(float deltaTime)  {

        if(this.fizzleState == FIZZLE_STATE.FIZZLING) {
            this.fizzleTime += deltaTime;
        } else {
            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
            bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
            boundingCircle.center.set(position);

            this.existedTime += deltaTime;

            if(this.existedTime > 5) {
                this.toggleFizzleState();
            }

            this.tailCounter++;

            if(tailCounter >= 5) {
                addPosToTail(pos());
                tailCounter = 0;
            }
        }

    }



}
