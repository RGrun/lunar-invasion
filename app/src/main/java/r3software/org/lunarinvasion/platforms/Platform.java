package r3software.org.lunarinvasion.platforms;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.engine.framework.GameObject;

/**
 * Created by Jeff on 1/29/2015.
 */
public abstract class Platform extends GameObject {


    public static float platformIDCounter = 0;

    public float platformId;

    public boolean breakable;

    public enum PLATFORM_TYPE {
        WALL,
        TYPE_1X1,
        TYPE_2X2,
        TYPE_4X4,
        TYPE_4X2,
        TYPE_6X2,
        TYPE_8X2,
        TYPE_10X2,
        TYPE_12X2,
        TYPE_14X2,
        TYPE_16X2,
        TYPE_18X2,
        TYPE_20X2,
        TYPE_ANGLED_2X2,
        TYPE_ANGLED_4X4,
        TYPE_ANGLED_6X6
    }

    public enum PLATFORM_STATE {
        NORMAL,
        EXPLODING
    }

    public PLATFORM_STATE curState;


    public static final float PLATFORM_HEIGHT_2X1 = 2f;
    public static final float PLATFORM_WIDTH_2X1 = 4f;

    //for rotating sprites in atlas
    public static final float TYPE_HORIZONTAL = 0;
    public static final float TYPE_VERTICAL = 90; //misleading, its the other way around

    public PLATFORM_TYPE type;

    public float orientation;

    public float health;

    public float stateTime;


    public Platform(float x, float y, float width, float height,
                    boolean breakable, float health, PLATFORM_TYPE type,
                    float orientation) {

        super(x, y, width, height);

        if(orientation == TYPE_VERTICAL) {
            swapWH();
        }

        this.type = type;

        this.orientation = orientation;

        this.breakable = breakable;

        if(this.breakable) {
            this.health = health;
        } else {
            this.health = 100f;
        }


        this.platformId = platformIDCounter;

        platformIDCounter++;

        this.curState = PLATFORM_STATE.NORMAL;
        this.stateTime = 0f;

    }

    public void update(float deltaTime) {
        this.stateTime += deltaTime;
    }

    protected void swapWH() {

        setBounds(this.bounds.height, this .bounds.width);

    }

    public float getId() {

        return this.platformId;
    }

    public void explode() {
        this.curState = PLATFORM_STATE.EXPLODING;
        this.stateTime = 0f;
        Assets.playSound(Assets.blockDestroy);
    }

}
