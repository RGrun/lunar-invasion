package r3software.org.lunarinvasion.platforms;

/**
 * Created by Jeff on 3/5/2015.
 */
public class Platform_8X2 extends Platform {
    public static final float PLATFORM_HEIGHT_8X2 = 2f;
    public static final float PLATFORM_WIDTH_8X2 = 8f;

    public Platform_8X2(float x, float y,
                        boolean breakable, float health,
                        float orientation) {

        super(x, y, PLATFORM_WIDTH_8X2, PLATFORM_HEIGHT_8X2,
                breakable, health, PLATFORM_TYPE.TYPE_8X2,
                orientation);


    }
}
