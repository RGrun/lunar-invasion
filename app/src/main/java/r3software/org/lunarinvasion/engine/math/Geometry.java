package r3software.org.lunarinvasion.engine.math;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static r3software.org.lunarinvasion.engine.math.Transformations.PointToLocalSpace;
import static r3software.org.lunarinvasion.engine.math.Vector2.add;
import static r3software.org.lunarinvasion.engine.math.Vector2.div;
import static r3software.org.lunarinvasion.engine.math.Vector2.mul;
import static r3software.org.lunarinvasion.engine.math.Vector2.sub;
import static r3software.org.lunarinvasion.engine.math.Vector2.vec2Distance;
import static r3software.org.lunarinvasion.engine.math.Vector2.vec2DistanceSq;
import static r3software.org.lunarinvasion.engine.math.Vector2.vec2Normalize;

/**
 * Created by Jeff on 1/11/2015.
 */
public class Geometry {

    /**
     * given a plane and a ray this function determines how far along the ray
     * an intersection occurs. Returns negative if the ray is parallel
     */
    public static float DistanceToRayPlaneIntersection(Vector2 RayOrigin,
                                                        Vector2 RayHeading,
                                                        Vector2 PlanePoint, //any point on the plane
                                                        Vector2 PlaneNormal) {

        float d = -PlaneNormal.dot(PlanePoint);
        float numer = PlaneNormal.dot(RayOrigin) + d;
        float denom = PlaneNormal.dot(RayHeading);

        // normal is parallel to vector
        if ((denom < 0.000001f) && (denom > -0.000001f)) {
            return (-1.0f);
        }

        return -(numer / denom);
    }

    //------------------------- WhereIsPoint --------------------------------------
    public static enum span_type {

        plane_backside, plane_front, on_plane;
    }

    public static span_type WhereIsPoint(Vector2 point,
                                         Vector2 PointOnPlane, //any point on the plane
                                         Vector2 PlaneNormal) {
        Vector2 dir = sub(PointOnPlane, point);

        float d = dir.dot(PlaneNormal);

        if (d < -0.000001f) {
            return span_type.plane_front;
        } else if (d > 0.000001f) {
            return span_type.plane_backside;
        }

        return span_type.on_plane;
    }
    public static float pi = 3.14159f;// Math.PI

    /**
     * GetRayCircleIntersect
     */
    public static float GetRayCircleIntersect(Vector2 RayOrigin,
                                               Vector2 RayHeading,
                                               Vector2 CircleOrigin,
                                               float radius) {
        Vector2 ToCircle = sub(CircleOrigin, RayOrigin);
        float length = ToCircle.len();
        float v = ToCircle.dot(RayHeading);
        float d = radius * radius - (length * length - v * v);

        // If there was no intersection, return -1
        if (d < 0.0f) {
            return (-1.0f);
        }

        // Return the distance to the [first] intersecting point
        return (float) (v - Math.sqrt(d));
    }

    /**
     *  DoRayCircleIntersect
     */
    public static boolean DoRayCircleIntersect(Vector2 RayOrigin,
                                               Vector2 RayHeading,
                                               Vector2 CircleOrigin,
                                               float radius) {

        Vector2 ToCircle = sub(CircleOrigin, RayOrigin);
        float length = ToCircle.len();
        float v = ToCircle.dot(RayHeading);
        float d = radius * radius - (length * length - v * v);

        // If there was no intersection, return -1
        return (d < 0.0f);
    }

    /**
     *  Given a point P and a circle of radius R centered at C this function
     *  determines the two points on the circle that intersect with the
     *  tangents from P to the circle. Returns false if P is within the circle.
     *
     *  Thanks to Dave Eberly for this one.
     */
    public static boolean GetTangentPoints(Vector2 C, float R, Vector2 P, Vector2 T1, Vector2 T2) {
        Vector2 PmC = sub(P, C);
        float SqrLen = PmC.lenSq();
        float RSqr = R * R;
        if (SqrLen <= RSqr) {
            // P is inside or on the circle
            return false;
        }

        float InvSqrLen = 1f / SqrLen;
        float Root = (float) Math.sqrt(Math.abs(SqrLen - RSqr));

        T1.x = C.x + R * (R * PmC.x - PmC.y * Root) * InvSqrLen;
        T1.y = C.y + R * (R * PmC.y + PmC.x * Root) * InvSqrLen;
        T2.x = C.x + R * (R * PmC.x + PmC.y * Root) * InvSqrLen;
        T2.y = C.y + R * (R * PmC.y - PmC.x * Root) * InvSqrLen;

        return true;
    }

    /**
     /* given a line segment AB and a point P, this function calculates the
     /*  perpendicular distance between them
     */
    public static float DistToLineSegment(Vector2 A,
                                           Vector2 B,
                                           Vector2 P) {
        //if the angle is obtuse between PA and AB is obtuse then the closest
        //vertex must be A
        float dotA = (P.x - A.x) * (B.x - A.x) + (P.y - A.y) * (B.y - A.y);

        if (dotA <= 0f) {
            return vec2Distance(A, P);
        }

        //if the angle is obtuse between PB and AB is obtuse then the closest
        //vertex must be B
        float dotB = (P.x - B.x) * (A.x - B.x) + (P.y - B.y) * (A.y - B.y);

        if (dotB <= 0f) {
            return vec2Distance(B, P);
        }

        //calculate the point along AB that is the closest to P
        //Vector2 Point = A + ((B - A) * dotA)/(dotA + dotB);
        Vector2 Point = add(A, (div(mul(sub(B, A), dotA), (dotA + dotB))));

        //calculate the distance P-Point
        return vec2Distance(P, Point);
    }

    /**
     *  as above, but avoiding sqrt
     */
    public static float DistToLineSegmentSq(Vector2 A,
                                             Vector2 B,
                                             Vector2 P) {
        //if the angle is obtuse between PA and AB is obtuse then the closest
        //vertex must be A
        float dotA = (P.x - A.x) * (B.x - A.x) + (P.y - A.y) * (B.y - A.y);

        if (dotA <= 0f) {
            return vec2DistanceSq(A, P);
        }

        //if the angle is obtuse between PB and AB is obtuse then the closest
        //vertex must be B
        float dotB = (P.x - B.x) * (A.x - B.x) + (P.y - B.y) * (A.y - B.y);

        if (dotB <= 0f) {
            return vec2DistanceSq(B, P);
        }

        //calculate the point along AB that is the closest to P
        //Vector2 Point = A + ((B - A) * dotA)/(dotA + dotB);
        Vector2 Point = add(A, (div(mul(sub(B, A), dotA), (dotA + dotB))));

        //calculate the distance P-Point
        return vec2DistanceSq(P, Point);
    }

    /**
     *	Given 2 lines in 2D space AB, CD this returns true if an
     *	intersection occurs.
     */
    public static boolean LineIntersection2D(Vector2 A,
                                             Vector2 B,
                                             Vector2 C,
                                             Vector2 D) {
        float rTop = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
        float sTop = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);

        float Bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);

        if (Bot == 0)//parallel
        {
            return false;
        }

        float invBot = 1.0f / Bot;
        float r = rTop * invBot;
        float s = sTop * invBot;

        if ((r > 0) && (r < 1) && (s > 0) && (s < 1)) {
            //lines intersect
            return true;
        }

        //lines do not intersect
        return false;
    }

    /**
     *  Given 2 lines in 2D space AB, CD this returns true if an
     *  intersection occurs and sets dist to the distance the intersection
     *  occurs along AB
     */
    public static boolean LineIntersection2D(Vector2 A,
                                             Vector2 B,
                                             Vector2 C,
                                             Vector2 D,
                                             FloatRef dist) // float &dist
    {

        float rTop = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
        float sTop = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);

        float Bot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);


        if (Bot == 0f)//parallel
        {
            if (isEqual(rTop, 0f) && isEqual(sTop, 0f)) {
                return true;
            }
            return false;
        }

        float r = rTop / Bot;
        float s = sTop / Bot;

        if ((r > 0f) && (r < 1f) && (s > 0f) && (s < 1f)) {
            dist.set(vec2Distance(A, B) * r);

            return true;
        } else {
            dist.set(0.0f);

            return false;
        }
    }

    /**
     *  Given 2 lines in 2D space AB, CD this returns true if an
     *  intersection occurs and sets dist to the distance the intersection
     *  occurs along AB. Also sets the 2d vector point to the point of
     *  intersection
     */
    public static boolean LineIntersection2D(Vector2 A,
                                             Vector2 B,
                                             Vector2 C,
                                             Vector2 D,
                                             FloatRef dist,
                                             Vector2 point) {

        float rTop = (A.y - C.y) * (D.x - C.x) - (A.x - C.x) * (D.y - C.y);
        float rBot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);

        float sTop = (A.y - C.y) * (B.x - A.x) - (A.x - C.x) * (B.y - A.y);
        float sBot = (B.x - A.x) * (D.y - C.y) - (B.y - A.y) * (D.x - C.x);

        if ((rBot == 0f) || (sBot == 0f)) {
            //lines are parallel
            return false;
        }

        float r = rTop / rBot;
        float s = sTop / sBot;

        if ((r > 0f) && (r < 1f) && (s > 0f) && (s < 1f)) {
            dist.set(vec2Distance(A, B) * r);

            point.set(add(A, mul(r, sub(B, A))));

            return true;
        } else {
            dist.set(0.0f);

            return false;
        }
    }

    /**
     *  tests two polygons for intersection. *Does not check for enclosure*
     */
    public static boolean ObjectIntersection2D(ArrayList<Vector2> object1,
                                               ArrayList<Vector2> object2) {
        //test each line segment of object1 against each segment of object2
        for (int r = 0; r < object1.size() - 1; ++r) {
            for (int t = 0; t < object2.size() - 1; ++t) {
                if (LineIntersection2D(object2.get(t),
                        object2.get(t + 1),
                        object1.get(r),
                        object1.get(r + 1))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *  tests a line segment against a polygon for intersection
     *  *Does not check for enclosure*
     */
    public static boolean SegmentObjectIntersection2D(final Vector2 A,
                                                      final Vector2 B,
                                                      final ArrayList<Vector2> object) {
        //test AB against each segment of object
        for (int r = 0; r < object.size() - 1; ++r) {
            if (LineIntersection2D(A, B, object.get(r), object.get(r + 1))) {
                return true;
            }
        }

        return false;
    }

    /**
     *  Returns true if the two circles overlap
     */
    public static boolean TwoCirclesOverlapped(float x1, float y1, float r1,
                                               float x2, float y2, float r2) {
        float DistBetweenCenters = (float) Math.sqrt((x1 - x2) * (x1 - x2)
                + (y1 - y2) * (y1 - y2));

        if ((DistBetweenCenters < (r1 + r2)) || (DistBetweenCenters < Math.abs(r1 - r2))) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if the two circles overlap
     */
    public static boolean TwoCirclesOverlapped(Vector2 c1, float r1,
                                               Vector2 c2, float r2) {
        float DistBetweenCenters = (float) Math.sqrt((c1.x - c2.x) * (c1.x - c2.x)
                + (c1.y - c2.y) * (c1.y - c2.y));

        if ((DistBetweenCenters < (r1 + r2)) || (DistBetweenCenters < Math.abs(r1 - r2))) {
            return true;
        }

        return false;
    }

    /**
     *  returns true if one circle encloses the other
     */
    public static boolean TwoCirclesEnclosed(float x1, float y1, float r1,
                                             float x2, float y2, float r2) {
        float DistBetweenCenters = (float) Math.sqrt((x1 - x2) * (x1 - x2)
                + (y1 - y2) * (y1 - y2));

        if (DistBetweenCenters < Math.abs(r1 - r2)) {
            return true;
        }

        return false;
    }

    /**
     * Given two circles this function calculates the intersection points
     *  of any overlap.
     *
     *  returns false if no overlap found
     *
     * see http://astronomy.swin.edu.au/~pbourke/geometry/2circle/
     */
    public static boolean TwoCirclesIntersectionPoints(float x1, float y1, float r1,
                                                       float x2, float y2, float r2,
                                                       FloatRef p3X, FloatRef p3Y,
                                                       FloatRef p4X, FloatRef p4Y) {
        //first check to see if they overlap
        if (!TwoCirclesOverlapped(x1, y1, r1, x2, y2, r2)) {
            return false;
        }

        //calculate the distance between the circle centers
        float d = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

        //Now calculate the distance from the center of each circle to the center
        //of the line which connects the intersection points.
        float a = (r1 - r2 + (d * d)) / (2f * d);
        float b = (r2 - r1 + (d * d)) / (2f * d);


        //MAYBE A TEST FOR EXACT OVERLAP?

        //calculate the point P2 which is the center of the line which
        //connects the intersection points
        float p2X, p2Y;

        p2X = x1 + a * (x2 - x1) / d;
        p2Y = y1 + a * (y2 - y1) / d;

        //calculate first point
        float h1 = (float) Math.sqrt((r1 * r1) - (a * a));

        p3X.set(p2X - h1 * (y2 - y1) / d);
        p3Y.set(p2Y + h1 * (x2 - x1) / d);


        //calculate second point
        float h2 = (float) Math.sqrt((r2 * r2) - (a * a));

        p4X.set(p2X + h2 * (y2 - y1) / d);
        p4Y.set(p2Y - h2 * (x2 - x1) / d);

        return true;

    }

    /**
     *  Tests to see if two circles overlap and if so calculates the area
     *  defined by the union
     *
     * see http://mathforum.org/library/drmath/view/54785.html
     */
    public static float TwoCirclesIntersectionArea(float x1, float y1, float r1,
                                                    float x2, float y2, float r2) {
        //first calculate the intersection points
        float iX1 = 0.0f, iY1 = 0.0f, iX2 = 0.0f, iY2 = 0.0f;

        if (!TwoCirclesIntersectionPoints(x1, y1, r1, x2, y2, r2,
                new FloatRef(iX1), new FloatRef(iY1), new FloatRef(iX2), new FloatRef(iY2))) {
            return 0.0f; //no overlap
        }

        //calculate the distance between the circle centers
        float d = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

        //find the angles given that A and B are the two circle centers
        //and C and D are the intersection points
        float CBD = (float) (2 * Math.acos((r2 * r2 + d * d - r1 * r1) / (r2 * d * 2)));

        float CAD = (float) (2 * Math.acos((r1 * r1 + d * d - r2 * r2) / (r1 * d * 2)));


        //Then we find the segment of each of the circles cut off by the
        //chord CD, by taking the area of the sector of the circle BCD and
        //subtracting the area of triangle BCD. Similarly we find the area
        //of the sector ACD and subtract the area of triangle ACD.

        float area = (float) (0.5f * CBD * r2 * r2 - 0.5f * r2 * r2 * Math.sin(CBD)
                + 0.5f * CAD * r1 * r1 - 0.5f * r1 * r1 * Math.sin(CAD));

        return area;
    }

    /**
     *  given the radius, calculates the area of a circle
     */
    public static float CircleArea(float radius) {
        return pi * radius * radius;
    }

    /**
     *  returns true if the point p is within the radius of the given circle
     */
    public static boolean PointInCircle(Vector2 Pos,
                                        float radius,
                                        Vector2 p) {
        float DistFromCenterSquared = (sub(p, Pos)).lenSq();

        if (DistFromCenterSquared < (radius * radius)) {
            return true;
        }

        return false;
    }

    /**
     * returns true if the line segment AB intersects with a circle at
     *  position P with radius radius
     */
    public static boolean LineSegmentCircleIntersection(Vector2 A,
                                                        Vector2 B,
                                                        Vector2 P,
                                                        float radius) {
        //first determine the distance from the center of the circle to
        //the line segment (working in distance squared space)
        float DistToLineSq = DistToLineSegmentSq(A, B, P);

        if (DistToLineSq < radius * radius) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  given a line segment AB and a circle position and radius, this function
     *  determines if there is an intersection and stores the position of the
     *  closest intersection in the reference IntersectionPoint
     *
     *  returns false if no intersection point is found
     */
    public static boolean GetLineSegmentCircleClosestIntersectionPoint(Vector2 A,
                                                                       Vector2 B,
                                                                       Vector2 pos,
                                                                       float radius,
                                                                       Vector2 IntersectionPoint) {
        Vector2 toBNorm = vec2Normalize(sub(B, A));

        //move the circle into the local space defined by the vector B-A with origin
        //at A
        Vector2 LocalPos = PointToLocalSpace(pos, toBNorm, toBNorm.perp(), A);

        boolean ipFound = false;

        //if the local position + the radius is negative then the circle lays behind
        //point A so there is no intersection possible. If the local x pos minus the
        //radius is greater than length A-B then the circle cannot intersect the
        //line segment
        if ((LocalPos.x + radius >= 0)
                && ((LocalPos.x - radius) * (LocalPos.x - radius) <= vec2DistanceSq(B, A))) {
            //if the distance from the x axis to the object's position is less
            //than its radius then there is a potential intersection.
            if (Math.abs(LocalPos.y) < radius) {
                //now to do a line/circle intersection test. The center of the
                //circle is represented by A, B. The intersection points are
                //given by the formulae x = A +/-sqrt(r^2-B^2), y=0. We only
                //need to look at the smallest positive value of x.
                float a = LocalPos.x;
                float b = LocalPos.y;

                float ip = (float) (a - Math.sqrt(radius * radius - b * b));

                if (ip <= 0f) {
                    ip = (float)(a + Math.sqrt(radius * radius - b * b));
                }

                ipFound = true;

                IntersectionPoint.set(add(A, mul(toBNorm, ip)));
            }
        }

        return ipFound;
    }

    //compares two real numbers. Returns true if they are equal
    static public boolean isEqual(float a, float b) {
        if (Math.abs(a - b) < 1E-12) {
            return true;
        }

        return false;
    }

    public static class FloatRef extends AtomicReference<Float> {

        public FloatRef(float ref) {
            super(ref);
        }

        public float toFloat() {
            return this.get();
        }
    }
}