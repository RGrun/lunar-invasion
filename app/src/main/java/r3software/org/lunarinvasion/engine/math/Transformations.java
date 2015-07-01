package r3software.org.lunarinvasion.engine.math;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static r3software.org.lunarinvasion.engine.math.Vector2.TO_DEGREES;
import static r3software.org.lunarinvasion.engine.math.Vector2.add;
import static r3software.org.lunarinvasion.engine.math.Vector2.mul;

public class Transformations {
    //--------------------------- WorldTransform -----------------------------
//
//  given a List of 2D vectors, a position, orientation and scale,
//  this function transforms the 2D vectors into the object's world space
//------------------------------------------------------------------------
    public static List<Vector2> WorldTransform(List<Vector2> points,
                                                Vector2 pos,
                                                Vector2 forward,
                                                Vector2 side,
                                                Vector2 scale) {
        //copy the original vertices into the buffer about to be transformed
        List<Vector2> TranVector2s = clone(points);

        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();

        //scale
        if ((scale.x != 1.0) || (scale.y != 1.0)) {
            matTransform.scale(scale.x, scale.y);
        }

        //rotate
        matTransform.rotate(forward, side);

        //and translate
        matTransform.translate(pos.x, pos.y);

        //now transform the object's vertices
        matTransform.transformVector2s(TranVector2s);

        return TranVector2s;
    }

    /**
     *  given a List of 2D vectors, a position and  orientation
     *  this function transforms the 2D vectors into the object's world space
     */
    public static List<Vector2> WorldTransform(List<Vector2> points,
                                                Vector2 pos,
                                                Vector2 forward,
                                                Vector2 side) {
        //copy the original vertices into the buffer about to be transformed
        List<Vector2> TranVector2Ds = clone(points);
        for(Vector2 v: points) {
            TranVector2Ds.add(v);
        }

        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();

        //rotate
        matTransform.rotate(forward, side);

        //and translate
        matTransform.translate(pos.x, pos.y);

        //now transform the object's vertices
        matTransform.transformVector2s(TranVector2Ds);

        return TranVector2Ds;
    }

    //--------------------- PointToWorldSpace --------------------------------
//
//  Transforms a point from the agent's local space into world space
//------------------------------------------------------------------------
    public static Vector2 PointToWorldSpace(Vector2 point,
                                             Vector2 AgentHeading,
                                             Vector2 AgentSide,
                                             Vector2 AgentPosition) {
        //make a copy of the point
        Vector2 TransPoint = new Vector2(point);

        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();

        //rotate
        matTransform.rotate(AgentHeading, AgentSide);

        //and translate
        matTransform.translate(AgentPosition.x, AgentPosition.y);

        //now transform the vertices
        matTransform.transformVector2s(TransPoint);

        return TransPoint;
    }

    //--------------------- VectorToWorldSpace --------------------------------
//
//  Transforms a vector from the agent's local space into world space
//------------------------------------------------------------------------
    public static Vector2 VectorToWorldSpace(Vector2 vec,
                                              Vector2 AgentHeading,
                                              Vector2 AgentSide) {
        //make a copy of the point
        Vector2 TransVec = new Vector2(vec);

        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();

        //rotate
        matTransform.rotate(AgentHeading, AgentSide);

        //now transform the vertices
        matTransform.transformVector2s(TransVec);

        return TransVec;
    }

    //--------------------- PointToLocalSpace --------------------------------
//
//------------------------------------------------------------------------
    public static Vector2 PointToLocalSpace(Vector2 point,
                                             Vector2 AgentHeading,
                                             Vector2 AgentSide,
                                             Vector2 AgentPosition) {

        //make a copy of the point
        Vector2 TransPoint = new Vector2(point);

        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();

        float Tx = -AgentPosition.dot(AgentHeading);
        float Ty = -AgentPosition.dot(AgentSide);

        //create the transformation matrix
        matTransform._11(AgentHeading.x);   matTransform._12(AgentSide.x);
        matTransform._21(AgentHeading.y);   matTransform._22(AgentSide.y);
        matTransform._31(Tx);               matTransform._32(Ty);

        //now transform the vertices
        matTransform.transformVector2s(TransPoint);

        return TransPoint;
    }

    //--------------------- VectorToLocalSpace --------------------------------
//
//------------------------------------------------------------------------
    public static Vector2 VectorToLocalSpace(Vector2 vec,
                                              Vector2 AgentHeading,
                                              Vector2 AgentSide) {

        //make a copy of the point
        Vector2 TransPoint = new Vector2(vec);

        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();

        //create the transformation matrix
        matTransform._11(AgentHeading.x);   matTransform._12(AgentSide.x);
        matTransform._21(AgentHeading.y);   matTransform._22(AgentSide.y);

        //now transform the vertices
        matTransform.transformVector2s(TransPoint);

        return TransPoint;
    }

    //-------------------------- Vec2DRotateAroundOrigin --------------------------
//
//  rotates a vector ang rads around the origin
//-----------------------------------------------------------------------------
    public static void vec2RotateAroundOrigin(Vector2 v, float ang) {
        //create a transformation matrix
        C2DMatrix mat = new C2DMatrix();

        //rotate
        mat.rotate(ang);

        //now transform the object's vertices
        mat.transformVector2s(v);
    }

    //------------------------ CreateWhiskers ------------------------------------
//
//  given an origin, a facing direction, a 'field of view' describing the
//  limit of the outer whiskers, a whisker length and the number of whiskers
//  this method returns a vector containing the end positions of a series
//  of whiskers radiating away from the origin and with equal distance between
//  them. (like the spokes of a wheel clipped to a specific segment size)
//----------------------------------------------------------------------------
    public static List<Vector2> createWhiskers(int NumWhiskers,
                                                float WhiskerLength,
                                                float fov,
                                                Vector2 facing,
                                                Vector2 origin) {
        //this is the magnitude of the angle separating each whisker
        float SectorSize = fov / (float) (NumWhiskers - 1);

        List<Vector2> whiskers = new ArrayList<Vector2>(NumWhiskers);
        Vector2 temp;
        float angle = -fov * 0.5f;

        for (int w = 0; w < NumWhiskers; ++w) {
            //create the whisker extending outwards at this angle
            temp = facing;
            vec2RotateAroundOrigin(temp, angle);
            whiskers.add(add(origin, mul(WhiskerLength, temp)));

            angle += SectorSize;
        }

        return whiskers;
    }


    //clone vector list
    public static List<Vector2> clone(List<Vector2> list) {
        try {
            List<Vector2> c = list.getClass().newInstance();
            for (Vector2 t : list) {
                Vector2 copy = (Vector2) t.getClass().getDeclaredConstructor(t.getClass()).newInstance(t);
                c.add(copy);
            }
            return c;
        } catch (Exception e) {
            throw new RuntimeException("List cloning unsupported", e);
        }
    }


    public static float findAngleBetweenTwoVectors(Vector2 baseVec, Vector2 newVec) {

        //tweak more? replace with atan2?

        //first, make copies of the vectors
        Vector2 baseCopy = new Vector2(baseVec);
        Vector2 newCopy = new Vector2(newVec);

        //next, ensure they're normalized
        baseCopy.nor();
        newCopy.nor();

        //the arc-cosine is the angle between the two vectors
        //this will be used to rotate oldVec in another method
        //this could also be used as the "cannonAngle" value (does not work)

        float angle = (float) Math.acos(baseCopy.dot(newCopy)) * TO_DEGREES;

        Log.d("transformations", "Angle: " + angle);

        if(angle < 0) {
            angle += 360;
        }

        //FUCK WITH THIS SOME MORE
        return angle - 65;

        //return (float) Math.acos(newCopy.dot(baseCopy)) * TO_DEGREES;

    }
}