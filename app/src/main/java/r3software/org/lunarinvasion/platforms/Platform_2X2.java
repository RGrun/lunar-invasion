package r3software.org.lunarinvasion.platforms;

/**
 * Created by Jeff on 2/22/2015.
 */
public class Platform_2X2 extends Platform {


    public static final float PLATFORM_HEIGHT_2X2 = 2f;
    public static final float PLATFORM_WIDTH_2X2 = 2f;

    public Platform_2X2(float x, float y,
                        boolean breakable, float health,
                        float orientation) {

        super(x, y, PLATFORM_WIDTH_2X2, PLATFORM_HEIGHT_2X2,
                breakable, health, PLATFORM_TYPE.TYPE_2X2, orientation);


    }

}
