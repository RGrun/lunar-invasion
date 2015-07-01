package r3software.org.lunarinvasion.platforms;

import r3software.org.lunarinvasion.engine.math.Triangle;

/**
 * Created by Jeff on 3/25/2015.
 */
public class Platform_Angled_2X2 extends Platform {

    public Triangle triBounds;

    public static final float PLATFORM_HEIGHT_ANGLED_2X2 = 2f;
    public static final float PLATFORM_WIDTH_ANGLED_2X2 = 2f;

    public Platform_Angled_2X2(float x, float y,
                               Triangle.TRIANGLE_FACING facing) {
        this(x, y, 100, PLATFORM_TYPE.TYPE_ANGLED_2X2, facing);
    }

    public Platform_Angled_2X2(float x, float y,
                               float health, PLATFORM_TYPE type,
                               Triangle.TRIANGLE_FACING facing) {

        super(x, y, PLATFORM_WIDTH_ANGLED_2X2,
                PLATFORM_HEIGHT_ANGLED_2X2,
                false, health, type,
                0);

        if(facing == Triangle.TRIANGLE_FACING.NE) {
            triBounds = new Triangle(bounds.bottomLeft, PLATFORM_WIDTH_ANGLED_2X2,
                    PLATFORM_HEIGHT_ANGLED_2X2,
                    Triangle.TRIANGLE_FACING.NE);

        } else if(facing == Triangle.TRIANGLE_FACING.SE) {
            triBounds = new Triangle(bounds.topLeft, PLATFORM_WIDTH_ANGLED_2X2,
                    PLATFORM_HEIGHT_ANGLED_2X2,
                    Triangle.TRIANGLE_FACING.SE);

        } else if (facing == Triangle.TRIANGLE_FACING.SW) {
            triBounds = new Triangle(bounds.topRight, PLATFORM_WIDTH_ANGLED_2X2,
                    PLATFORM_HEIGHT_ANGLED_2X2,
                    Triangle.TRIANGLE_FACING.SW);

        } else if (facing == Triangle.TRIANGLE_FACING.NW) {
            triBounds = new Triangle(bounds.bottomRight, PLATFORM_WIDTH_ANGLED_2X2,
                    PLATFORM_HEIGHT_ANGLED_2X2,
                    Triangle.TRIANGLE_FACING.NW);
        }


    }
}
