package r3software.org.lunarinvasion.engine.math;

import android.util.FloatMath;

public class Vector2 {

    //compass directions
    public enum Cardinals {
        NORTH,
        NORTHEAST,
        EAST,
        SOUTHEAST,
        SOUTH,
        SOUTHWEST,
        WEST,
        NORTHWEST

    }


	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
    public static final int CLOCKWISE = 1;
    public static final int COUNTERCLOCKWISE = -1;
	public float x, y;
	
	public Vector2() {
		
	}
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public Vector2 cpy() {
		return new Vector2(x, y);
	}
	
	public Vector2 set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2 set(Vector2 other) {
		this.x = other.x;
		this.y = other.y;
		return this;
	}

    public Vector2 zero() {
        this.x = 0;
        this.y = 0;
        return this;
    }
	
	public Vector2 add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2 add(Vector2 other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}

    public Vector2 div (float other) {
        this.x /= other;
        this.y /= other;
        return this;
    }
	
	public Vector2 sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vector2 sub(Vector2 other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2 mul(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	
	public float len() {
		return FloatMath.sqrt(x * x + y * y);
	}

    //returns the squared length of the vector (thereby avoiding the sqrt)
    public float lenSq() {
        return (x * x + y * y);
    }
	
	public Vector2 nor() {
		float len = len();
		if(len != 0) {
			this.x /= len;
			this.y /= len;
		}
		return this;
	}
	
	public float angle() {
		float angle = (float) Math.atan2(y, x) * TO_DEGREES;
		if(angle < 0) {
			angle += 360;
		}
		return angle;
	}
	
	public Vector2 rotate(float angle) {
		float rad = angle * TO_RADIANS;
		float cos = FloatMath.cos(rad);
		float sin = FloatMath.sin(rad);
		
		float newX = this.x * cos - this.y * sin;
		float newY = this.x * sin + this.y * cos;
		
		this.x = newX;
		this.y = newY;
		
		return this;
	}
	
	public float dist(Vector2 other) {
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return FloatMath.sqrt(distX * distX + distY * distY);
	}
	
	public float dist(float x, float y) {
		float distX = this.x - x;
		float distY = this.y - y;
		return FloatMath.sqrt(distX * distX + distY * distY);
	}
	
	public float distSquared(Vector2 other) {
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return distX * distX + distY * distY;
	}
	
	public float distSquared(float x, float y) {
		float distX = this.x - x;
		float distY = this.y - y;
		return distX * distX + distY * distY;
	}

    //returns true if both x and y are zero
    public boolean isZero() {
        return (x * x + y * y) < Float.MIN_VALUE;
    }

    //returns dot product
    public float dot(Vector2 v2) {
        return x * v2.x + y * v2.y;
    }

    /**
     /* returns positive if v2 is clockwise of this vector,
     /* negative if anticlockwise (assuming the Y axis is pointing down,
     /* X axis to right like a Window app)
     */
    public int sign(Vector2 v2) {
        if (y * v2.x > x * v2.y) { //may need to reverse because opengl's y points up
            return COUNTERCLOCKWISE;
        } else {
            return CLOCKWISE;
        }
    }

    /**
     * returns the vector that is perpendicular to this one.
     *
     * Perpendicular: A line is said to be perpendicular to another line if
     * the two lines intersect at a right angle.
     */
    public Vector2 perp() {
        return new Vector2(-y, x);
    }

    /**
     * adjusts x and y so that the length of the vector does not exceed max
     * truncates a vector so that its length does not exceed max
     * @param max
     */
    public Vector2 truncate(float max) {
        if (this.len() > max) {
            this.nor();
            this.mul(max);
        }
        return this;
    }

    /**
     *  given a normalized vector this method reflects the vector it
     *  is operating upon. (like the path of a ball bouncing off a wall)
     * @param norm
     */
    public Vector2 reflect(Vector2 norm) {
       return this.add(norm.getReverse().mul(2 * dot(norm)));
    }

    /**
     * @return the vector that is the reverse of this vector
     */
    public Vector2 getReverse() {
        return new Vector2(-this.x, -this.y);
    }

    //static methods

    public static Vector2 vec2Normalize(Vector2 v) {
        Vector2 vec = new Vector2(v);

        float vector_length = vec.len();

        if (vector_length > Float.MAX_VALUE) { //epsilonDouble?
            vec.x /= vector_length;
            vec.y /= vector_length;
        }

        return vec;
    }

    public static float vec2Distance(Vector2 v1, Vector2 v2) {

        float ySeparation = v2.y - v1.y;
        float xSeparation = v2.x - v1.x;

        return (float) Math.sqrt(ySeparation * ySeparation + xSeparation * xSeparation);
    }

    public static float vec2DistanceSq(Vector2 v1, Vector2 v2) {

        float ySeparation = v2.y - v1.y;
        float xSeparation = v2.x - v1.x;

        return ySeparation * ySeparation + xSeparation * xSeparation;
    }

    public static float vec2Length(Vector2 v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y);
    }

    public static float vec2LengthSq(Vector2 v) {
        return (v.x * v.x + v.y * v.y);
    }

    /**
     * returns true if the point p is not inside the region defined by top_left
     * and bot_rgt
     * @param p
     * @param top_left
     * @param bot_rgt
     * @return
     */
    public static boolean notInsideRegion(Vector2 p,
                                          Vector2 top_left,
                                          Vector2 bot_rgt) {
        return (p.x < top_left.x) || (p.x > bot_rgt.x)
                || (p.y < top_left.y) || (p.y > bot_rgt.y);
    }

    public static boolean insideRegion(Vector2 p,
                                       Vector2 top_left,
                                       Vector2 bot_rgt) {
        return !((p.x < top_left.x) || (p.x > bot_rgt.x)
                || (p.y < top_left.y) || (p.y > bot_rgt.y));
    }

    public static boolean insideRegion(Vector2 p, int left, int top, int right, int bottom) {
        return !((p.x < left) || (p.x > right) || (p.y < top) || (p.y > bottom));
    }

    /**
     * @return true if the target position is in the field of view of the entity
     *         positioned at posFirst facing in facingFirst
     */
    public static boolean isSecondInFOVOfFirst(Vector2 posFirst,
                                               Vector2 facingFirst,
                                               Vector2 posSecond,
                                               float fov) {
        Vector2 toTarget = vec2Normalize(sub(posSecond, posFirst));
        return facingFirst.dot(toTarget) >= Math.cos(fov / 2.0);
    }

    public static  Vector2 mul(Vector2 lhs, float rhs) {
        Vector2 result = new Vector2(lhs);
        result.mul(rhs);
        return result;
    }

    public static  Vector2 mul(float lhs, Vector2 rhs) {
        Vector2 result = new Vector2(rhs);
        result.mul(lhs);
        return result;
    }

    public static  Vector2 sub(Vector2 lhs, Vector2 rhs) {
        Vector2 result = new Vector2(lhs);
        result.x -= rhs.x;
        result.y -= rhs.y;

        return result;
    }



    public static Vector2 div(Vector2 lhs, float rhs) {
        lhs.x /= rhs;
        lhs.y /= rhs;

        return lhs;
    }

    static public Vector2 add(Vector2 lhs, Vector2 rhs) {
        Vector2 result = new Vector2(lhs);
        result.x += rhs.x;
        result.y += rhs.y;

        return result;
    }


}
