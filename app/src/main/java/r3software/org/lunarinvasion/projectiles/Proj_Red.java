package r3software.org.lunarinvasion.projectiles;

import java.util.List;

/**
 * Created by Jeff on 5/17/2015.
 *
 * This projectile starts off faster than the regular shot, but only lasts half as long.
 * If the projectile hits a platform of some kind, its life timer is reset.
 *
 * This projectile does three damage if the enemy is hit.
 */

public class Proj_Red extends Projectile {

    public float lifeTimer;

    //reference to projectiles List enables self-removal in update method
    List<Projectile> projectiles;

    public static final float LIFE_TIME = 0.75f;


    public Proj_Red(float x, float y, float width, float height,
                     float mass, float maxSpeed, float maxForce,
                     float radius, List<Projectile> projectiles) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.RED;
        this.lifeTimer = 0f;

        this.projectiles = projectiles;

    }


    @Override
    public void update(float deltaTime) {
        //handled below
    }


    public void update(float deltaTime, int index) {

        if(fizzleState == FIZZLE_STATE.FIZZLING) {
            this.fizzleTime += deltaTime;

           if(fizzleTime > 5) {
               projectiles.remove(index);
           }

        } else {
            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
            bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
            boundingCircle.center.set(position);

            this.existedTime += deltaTime;
            this.lifeTimer += deltaTime;


            if(lifeTimer >= LIFE_TIME) {
                this.toggleFizzleState();
            }

            this.tailCounter++;

            if(tailCounter >= 5) {
                addPosToTail(pos());
                tailCounter = 0;
            }
        }

    }

    public void speedUp() {

        //increasing projectile speed proved too buggy with the collision code
        this.velocity.mul(1.0f);
        this.lifeTimer = 0f;
    }
}
