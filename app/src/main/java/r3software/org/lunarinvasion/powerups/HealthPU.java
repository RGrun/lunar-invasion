package r3software.org.lunarinvasion.powerups;

/**
 * Created by Jeff on 6/11/2015.
 *
 * This is the class for the health power up.
 */
public class HealthPU extends PowerUp {

    public static final float HEALTH_PU_HEIGHT = 1f;
    public static final float HEALTH_PU_WIDTH = 1f;

    public HealthPU(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public HealthPU(float x, float y) {
        this(x, y, HEALTH_PU_HEIGHT, HEALTH_PU_WIDTH);
    }

}
