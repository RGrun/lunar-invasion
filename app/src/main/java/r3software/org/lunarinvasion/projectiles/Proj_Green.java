package r3software.org.lunarinvasion.projectiles;

import java.util.List;

import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.engine.math.OverlapTester;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.platforms.Platform;

/**
 * Created by Jeff on 5/17/2015.
 *
 * This shot breaks into three individual shots after a short
 * time period. Each green shot after the break
 * is identical to an orange shot.
 */

//TODO make new detection method for green shot explosions
//TODO if radius detects platform, don't explode yet
public class Proj_Green extends Projectile {

    public enum GREEN_STATE {
        PRE_BREAK,
        POST_BREAK
    }
    public GREEN_STATE curState;

    public float stateTime;

    public static final float BREAK_TIME = 1f;

    //constructor used at firing
    public Proj_Green(float x, float y, float width, float height,
                     float mass, float maxSpeed, float maxForce,
                     float radius) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.GREEN;
        this.curState = GREEN_STATE.PRE_BREAK;
        this.stateTime = 0f;


    }

    //constructros used during break
    public Proj_Green(float x, float y, float width, float height,
                      float mass, float maxSpeed, float maxForce,
                      float radius, Vector2 newVel) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.GREEN;
        this.curState = GREEN_STATE.POST_BREAK;
        this.stateTime = 0;

        this.velocity.set(newVel);

    }


    @Override
    public void update(float deltaTime) {
        //nothing
    }

    public boolean update(float deltaTime,
                       List<Projectile> projectiles,
                       int index)  {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
        boundingCircle.center.set(position);

        this.existedTime += deltaTime;
        this.stateTime += deltaTime;

        if(curState == GREEN_STATE.PRE_BREAK &&
                existedTime >= BREAK_TIME) {
            //break into three
            separate(projectiles);
            projectiles.remove(index);

            return true;
        }

        this.tailCounter++;

        if(tailCounter >= 5) {
            addPosToTail(pos());
            tailCounter = 0;
        }


        return false;

    }


    private void separate(List<Projectile> projectiles) {

        Vector2 curVel = this.velocity;

        float BREAK_OFFSET = 25;

        Vector2 leftVel = new Vector2(curVel).rotate(-BREAK_OFFSET);
        Vector2 rightVel = new Vector2(curVel).rotate(BREAK_OFFSET);

        //add proj to left
        projectiles.add(new Proj_Green(pos().x, pos().y, bounds.width,
                bounds.height, mass, maxSpeed, maxForce, boundingCircle.radius,
                 leftVel));

        //add canter proj
        projectiles.add(new Proj_Green(pos().x, pos().y, bounds.width,
                bounds.height, mass, maxSpeed, maxForce, boundingCircle.radius,
                curVel));

        //add proj to right
        projectiles.add(new Proj_Green(pos().x, pos().y, bounds.width,
                bounds.height, mass, maxSpeed, maxForce, boundingCircle.radius,
                 rightVel));

    }

    public static void checkGreenProjectiles(List<Projectile> projectiles,
                                             List<Platform> platforms) {
        outer:
        for(int i = 0; i < projectiles.size(); i++)  {
            Projectile proj = projectiles.get(i);

            for(int j = 0; j < platforms.size(); j++) {
                Platform ptfm = platforms.get(j);

                if(ptfm.type != Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2 &&
                        ptfm.type != Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4 &&
                        ptfm.type != Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6) {

                    if(OverlapTester.overlapCircleRectangle(proj.boundingCircle,
                            ptfm.bounds)) {
                        //green ball is inside platform. Delete it.
                        projectiles.remove(i);
                        i = 0;
                        continue outer;
                    }

                } else {
                    if(World.projHitsTriangle(ptfm, proj, null)) {
                        //green ball is inside platform. Delete it.
                        projectiles.remove(i);
                        i = 0;
                        continue outer;
                    }
                }

            }

        }
    }

}
