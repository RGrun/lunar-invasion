package r3software.org.lunarinvasion.platforms;

/**
 * Created by Jeff on 3/5/2015.
 */
public class Platform_18X2 extends Platform {
    public static final float PLATFORM_HEIGHT_18X2 = 2f;
    public static final float PLATFORM_WIDTH_18X2 = 18f;

    public Platform_18X2(float x, float y,
                        boolean breakable, float health,
                        float orientation) {

        super(x, y, PLATFORM_WIDTH_18X2, PLATFORM_HEIGHT_18X2,
                breakable, health, PLATFORM_TYPE.TYPE_18X2,
                orientation);


    }
}
