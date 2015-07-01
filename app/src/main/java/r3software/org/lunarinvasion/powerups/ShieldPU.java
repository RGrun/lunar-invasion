package r3software.org.lunarinvasion.powerups;

/**
 * Created by Jeff on 6/6/2015.
 *
 * This is the class for the shield power up
 */
public class ShieldPU extends PowerUp {

    public static final float SHIELD_PU_HEIGHT = 1f;
    public static final float SHIELD_PU_WIDTH = 1f;

    public ShieldPU(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public ShieldPU(float x, float y) {
        this(x, y, SHIELD_PU_WIDTH, SHIELD_PU_HEIGHT);
    }

}
