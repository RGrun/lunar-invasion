package r3software.org.lunarinvasion.platforms;

/**
 * Created by Jeff on 2/22/2015.
 */
public class Platform_4X4 extends Platform {


    public static final float PLATFORM_HEIGHT_4X4 = 4f;
    public static final float PLATFORM_WIDTH_4X4 = 4f;

    public Platform_4X4(float x, float y,
                        boolean breakable, float health,
                        float orientation) {

        super(x, y, PLATFORM_WIDTH_4X4, PLATFORM_HEIGHT_4X4,
                breakable, health, PLATFORM_TYPE.TYPE_4X4, orientation);


    }

}
