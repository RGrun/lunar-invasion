package r3software.org.lunarinvasion.engine.math;

import static r3software.org.lunarinvasion.engine.math.Vector2.sub;

/**
 * Created by Jeff on 2/28/2015.
 */
public class Triangle {

    public enum TRIANGLE_FACING {
        NE,
        SE,
        SW,
        NW
    }

    //vertexes
    public Vector2 A, B, C;

    public Vector2 rightAngle;

    //center means the center of the hypotenuse
    public Vector2 center;

    public float base, height;

    //sides
    public Vector2 AB, BC, hypot;

    //perpendicular from hypot
    public Vector2 hypotFacing;

    //for sprites
    public float rotationAngle;

    public TRIANGLE_FACING triFacing;

    public Triangle(Vector2 rightangle, float base, float height,
                    TRIANGLE_FACING facing) {

        if(facing == Triangle.TRIANGLE_FACING.NE) {
            this.rightAngle = rightangle;
            this.base = base;
            this.height = height;

            this.A = new Vector2(rightAngle.x, rightangle.y + height);
            this.B = rightAngle;
            this.C = new Vector2(rightangle.x + base, rightangle.y);

            this.rotationAngle = 0;

        } else if(facing == Triangle.TRIANGLE_FACING.SE) {
            this.rightAngle = rightangle;
            this.base = base;
            this.height = height;

            this.A = new Vector2(rightangle.x + base, rightangle.y);
            this.B = rightAngle;
            this.C = new Vector2(rightangle.x, rightangle.y - height);

            this.rotationAngle = 270;

        } else if (facing == Triangle.TRIANGLE_FACING.SW) {
            this.rightAngle = rightangle;
            this.base = base;
            this.height = height;

            this.A = new Vector2(rightangle.x, rightangle.y - height);
            this.B = rightAngle;
            this.C = new Vector2(rightangle.x - base, rightangle.y);

            this.rotationAngle = 180;


        } else if (facing == Triangle.TRIANGLE_FACING.NW) {
            this.rightAngle = rightangle;
            this.base = base;
            this.height = height;

            this.A = new Vector2(rightangle.x - base, rightangle.y);
            this.B = rightAngle;
            this.C = new Vector2(rightangle.x, rightangle.y + height);

            this.rotationAngle = 90;


        }


        this.AB = sub(B, A);
        this.BC = sub(C, B);
        this.hypot = sub(C, A);

        this.center = new Vector2(hypot.x / 2, hypot.y / 2);

        this.hypotFacing = hypot.perp().nor();

        this.triFacing = facing;

    }

    // these are for testing whether a cannon's center (or any point) is inside one of the triangles
    public static float sign (Vector2 p1, Vector2 p2, Vector2 p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    public static boolean pointInTriangle (Vector2 pt, Vector2 v1, Vector2 v2, Vector2 v3)
    {
        boolean b1, b2, b3;

        b1 = sign(pt, v1, v2) < 0.0f;
        b2 = sign(pt, v2, v3) < 0.0f;
        b3 = sign(pt, v3, v1) < 0.0f;

        return ((b1 == b2) && (b2 == b3));
    }

}
