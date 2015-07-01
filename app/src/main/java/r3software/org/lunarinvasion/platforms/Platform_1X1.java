package r3software.org.lunarinvasion.platforms;

/**
 * Created by Jeff on 2/22/2015.
 */
public class Platform_1X1 extends Platform {

    public static final float PLATFORM_HEIGHT_1X1 = 1f;
    public static final float PLATFORM_WIDTH_1X1 = 1f;

    public Platform_1X1(float x, float y,
                        boolean breakable, float health,
                        float orientation) {

        super(x, y, PLATFORM_WIDTH_1X1, PLATFORM_HEIGHT_1X1,
                breakable, health, PLATFORM_TYPE.TYPE_1X1,
                orientation);


    }

}
