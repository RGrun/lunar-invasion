package r3software.org.lunarinvasion.platforms;

import r3software.org.lunarinvasion.engine.math.Triangle;

/**
 * Created by Jeff on 4/2/2015.
 */
public class Platform_Angled_4X4 extends Platform {

    public Triangle triBounds;

    public static final float PLATFORM_HEIGHT_ANGLED_4X4 = 4f;
    public static final float PLATFORM_WIDTH_ANGLED_4X4 = 4f;

    public Platform_Angled_4X4(float x, float y,
                               float health, PLATFORM_TYPE type,
                               Triangle.TRIANGLE_FACING facing) {

        super(x, y, PLATFORM_WIDTH_ANGLED_4X4,
                PLATFORM_HEIGHT_ANGLED_4X4,
                false, health, type,
                0);

        if(facing == Triangle.TRIANGLE_FACING.NE) {
            triBounds = new Triangle(bounds.bottomLeft, PLATFORM_WIDTH_ANGLED_4X4,
                    PLATFORM_HEIGHT_ANGLED_4X4,
                    Triangle.TRIANGLE_FACING.NE);

        } else if(facing == Triangle.TRIANGLE_FACING.SE) {
            triBounds = new Triangle(bounds.topLeft, PLATFORM_WIDTH_ANGLED_4X4,
                    PLATFORM_HEIGHT_ANGLED_4X4,
                    Triangle.TRIANGLE_FACING.SE);

        } else if (facing == Triangle.TRIANGLE_FACING.SW) {
            triBounds = new Triangle(bounds.topRight, PLATFORM_WIDTH_ANGLED_4X4,
                    PLATFORM_HEIGHT_ANGLED_4X4,
                    Triangle.TRIANGLE_FACING.SW);

        } else if (facing == Triangle.TRIANGLE_FACING.NW) {
            triBounds = new Triangle(bounds.bottomRight, PLATFORM_WIDTH_ANGLED_4X4,
                    PLATFORM_HEIGHT_ANGLED_4X4,
                    Triangle.TRIANGLE_FACING.NW);
        }


    }
}
