package r3software.org.lunarinvasion.powerups;

import java.util.Random;

import r3software.org.lunarinvasion.World;

/**
 * Created by Jeff on 6/6/2015.
 *
 * This class encapsulates the weapon power ups. Weapons are given based
 * on a probability.
 */
public class WeaponPU extends PowerUp {

    private Random rand;

    public int whichPlayerHitPowerup;
    public int howManyWereGiven;

    public enum STATE {
        PRIMARY,
        SECONDARY
    }

    public STATE curState;


    public enum CONTENTS {
        GREEN,
        BLUE,
        RED,
        MISSILE
    }

    //this powerup's payload
    public CONTENTS contents;

    public float stateTime;

    public static final float WEAPON_PU_HEIGHT = 1f;
    public static final float WEAPON_PU_WIDTH = 1f;

    public static final float MAX_TIME = 1f;

    public WeaponPU(float x, float y) {
        this(x, y, WEAPON_PU_WIDTH, WEAPON_PU_HEIGHT);
    }

    public WeaponPU(float x, float y, float width, float height) {
        super(x, y, width, height);

        this.rand = new Random();

        float payload = rand.nextFloat();

        if(payload <= 0.3f) {
            this.contents = CONTENTS.BLUE;
        } else if(payload > 0.3f && payload <= 0.6f) {
            this.contents = CONTENTS.MISSILE;
        } else if(payload > 0.6f && payload <= 0.8f) {
            this.contents = CONTENTS.GREEN;
        } else if(payload > 0.8f) {
            this.contents = CONTENTS.RED;
        }

        this.curState = STATE.PRIMARY;
        this.stateTime = 0;

        this.whichPlayerHitPowerup = -1;
        this.howManyWereGiven = -1;

    }


    public void update(float deltaTime) {
        this.stateTime += deltaTime;
    }


    public void pickup(int whichPlayer, int howMany) {
        if(this.curState == STATE.SECONDARY) return;

        if(whichPlayer == World.HUMAN_CANNON) {
            this.whichPlayerHitPowerup = World.HUMAN_CANNON;
        } else if(whichPlayer == World.ALIEN_CANNON) {
            this.whichPlayerHitPowerup = World.ALIEN_CANNON;
        }
        this.howManyWereGiven = howMany;

        this.curState = STATE.SECONDARY;
        this.stateTime = 0f;

    }


}
