package r3software.org.lunarinvasion.projectiles;

import java.util.List;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.engine.math.Circle;
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

public class Proj_Green extends Projectile {

    public enum GREEN_STATE {
        PRE_BREAK,
        POST_BREAK
    }
    public GREEN_STATE curState;

    public float stateTime;

    public static final float BREAK_TIME = 1f;

    public Circle breakSafetyRadius;
    private boolean safeToBreak;

    //constructor used at firing
    public Proj_Green(float x, float y, float width, float height,
                     float mass, float maxSpeed, float maxForce,
                     float radius) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.GREEN;
        this.curState = GREEN_STATE.PRE_BREAK;
        this.breakSafetyRadius = new Circle(x, y, 1f);
        this.safeToBreak = false;
        this.stateTime = 0f;


    }

    //constructor used during break
    public Proj_Green(float x, float y, float width, float height,
                      float mass, float maxSpeed, float maxForce,
                      float radius, Vector2 newVel, float curTime,
                      Circle oldBreakRadius, float idLastPlatformHit) {
        super(x, y, width, height, mass, maxSpeed, maxForce, radius);

        this.projType = TYPE.GREEN;
        this.curState = GREEN_STATE.POST_BREAK;
        this.existedTime = curTime;
        this.stateTime = 0f;
        this.breakSafetyRadius = oldBreakRadius;

        this.idOfLastPlatformHit = idLastPlatformHit;

        this.velocity.set(newVel);

    }


    @Override
    public void update(float deltaTime) {
        //nothing
    }

    public boolean update(float deltaTime,
                       List<Projectile> projectiles,
                          List<Platform> platforms,
                            int index)  {

        if(fizzleState == FIZZLE_STATE.FIZZLING) {
            this.fizzleTime += deltaTime;


            return false;
        } else {

            position.add(velocity.x * deltaTime, velocity.y * deltaTime);
            bounds.bottomLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
            boundingCircle.center.set(position);
            breakSafetyRadius.center.set(position);

            this.existedTime += deltaTime;
            this.stateTime += deltaTime;

            if(existedTime > 5) {
                toggleFizzleState();
            }

            //check against all platforms every update step
            //to see if it's safe to break
            for (Platform ptfm : platforms) {
                if ((ptfm.type != Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2) &&
                        (ptfm.type != Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4) &&
                        (ptfm.type != Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6)) {

                    safeToBreak = !OverlapTester.overlapCircleRectangle(breakSafetyRadius,
                            ptfm.bounds);

                } else {
                    safeToBreak = !OverlapTester.checkAngledPlatformCollisions(ptfm, this);
                }
            }


            // check to see if it's time to break
            // if we're too close to a platform, delay break
            if (curState == GREEN_STATE.PRE_BREAK &&
                    existedTime >= BREAK_TIME &&
                    safeToBreak) {
                //break into three
                separate(projectiles);
                projectiles.remove(index);

                return true;
            }

            this.tailCounter++;

            if (tailCounter >= 5) {
                addPosToTail(pos());
                tailCounter = 0;
            }


            return false;
        }

    }


    private void separate(List<Projectile> projectiles) {

        Vector2 curVel = this.velocity;

        float BREAK_OFFSET = 25;

        Vector2 leftVel = new Vector2(curVel).rotate(-BREAK_OFFSET);
        Vector2 rightVel = new Vector2(curVel).rotate(BREAK_OFFSET);

        //add proj to left
        projectiles.add(new Proj_Green(pos().x, pos().y, bounds.width,
                bounds.height, mass, maxSpeed, maxForce, boundingCircle.radius,
                 leftVel, existedTime, breakSafetyRadius, idOfLastPlatformHit));

        //add canter proj
        projectiles.add(new Proj_Green(pos().x, pos().y, bounds.width,
                bounds.height, mass, maxSpeed, maxForce, boundingCircle.radius,
                curVel, existedTime, breakSafetyRadius, idOfLastPlatformHit));

        //add proj to right
        projectiles.add(new Proj_Green(pos().x, pos().y, bounds.width,
                bounds.height, mass, maxSpeed, maxForce, boundingCircle.radius,
                 rightVel, existedTime, breakSafetyRadius, idOfLastPlatformHit));

        Assets.playSound(Assets.green_activate);

    }

}
