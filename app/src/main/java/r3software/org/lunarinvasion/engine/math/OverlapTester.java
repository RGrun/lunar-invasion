package r3software.org.lunarinvasion.engine.math;

public class OverlapTester {

    public enum REC_SIDE {
        SIDE_LEFT,
        SIDE_RIGHT,
        SIDE_TOP,
        SIDE_BOTTOM
    }
	
	public static boolean overlapCircles(Circle c1, Circle c2) {
		float distance = c1.center.distSquared(c2.center);
		float radiusSum = c1.radius + c2.radius;
		return distance <= radiusSum * radiusSum;
	}


	public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
		if(r1.bottomLeft.x < r2.bottomLeft.x + r2.width &&
				r1.bottomLeft.x + r1.width > r2.bottomLeft.x &&
				r1.bottomLeft.y < r2.bottomLeft.y + r2.height &&
				r1.bottomLeft.y + r1.height > r2.bottomLeft.y)
			return true;
		else
			return false;
	}
	
	public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
		float closestX = c.center.x;
		float closestY = c.center.y;
		
		if(c.center.x < r.bottomLeft.x) {
			closestX = r.bottomLeft.x;
	
		} else if(c.center.x > r.bottomLeft.x + r.width) {
			closestX = r.bottomLeft.x + r.width;
			
		}
		
		if(c.center.y < r.bottomLeft.y) {
			closestY = r.bottomLeft.y;
		} else if (c.center.y > r.bottomLeft.y + r.height) {
			closestY = r.bottomLeft.y + r.height;
		}

		return c.center.distSquared(closestX, closestY) < (c.radius * c.radius);
	}
	
	public static boolean pointInCircle(Circle c, Vector2 p) {
		return c.center.distSquared(p) < c.radius * c.radius;
	}
	
	public static boolean pointInCircle(Circle c, float x, float y) {
		return c.center.distSquared(x, y) < c.radius * c.radius;
	}
	
	public static boolean pointInRectangle(Rectangle r, Vector2 p) {
		return r.bottomLeft.x <= p.x && r.bottomLeft.x + r.width >= p.x
				&& r.bottomLeft.y <= p.y && r.bottomLeft.y + r.height>= p.y;
	}
	
	public static boolean pointInRectangle(Rectangle r, float x, float y) {
		return r.bottomLeft.x <= x && r.bottomLeft.x + r.width >= x
				&& r.bottomLeft.y <= y && r.bottomLeft.y + r.height>= y;
	}

  /*  public static boolean projHitsTriangle(Platform ptfm, Projectile proj,
                                        int indexOfPlatform,
                                        List<Platform> platforms,
                                        List<ShotBounce> shotBounces,
                                        World world) {

        Triangle tri;

        switch(ptfm.type) {
            case TYPE_ANGLED_2X2:
                tri = ((Platform_Angled_2X2)ptfm).triBounds;
                break;
            case TYPE_ANGLED_4X4:
                tri = ((Platform_Angled_4X4)ptfm).triBounds;
                break;
            case TYPE_ANGLED_6X6:
                tri = ((Platform_Angled_6X6)ptfm).triBounds;
                break;
            default:
                tri = ((Platform_Angled_2X2)ptfm).triBounds;
        }

        //corner of triangle hit?
        if(pointInCircle(proj.boundingCircle, tri.A) ||
                pointInCircle(proj.boundingCircle, tri.B) ||
                pointInCircle(proj.boundingCircle, tri.C) && !ptfm.lastHit) {

            //proj.position = proj.lastNonCollidingPosition;

            float angle = (float) Math.atan2(ptfm.pos().y - proj.pos().y,
                    ptfm.pos().x - proj.pos().x);


            //atan2 returns in radians
            angle *= Vector2.TO_DEGREES;

            if (angle < 0) {
                angle = 360 - (-angle);
            }

            float radians = angle * Vector2.TO_RADIANS;

            float speedFromLen = proj.velocity().len();

            Vector2 exitVelocity =
                    new Vector2(FloatMath.cos(radians) * speedFromLen,
                            FloatMath.sin(radians) * speedFromLen);

            exitVelocity = exitVelocity.getReverse();

            proj.setVelocity(exitVelocity);

            ptfm.setLastHit();

            //unset all other platforms as 'last hit'
            world.leftWallHitLast = false;
            world.rightWallHitLast = false;
            unsetAllOtherPlatformsAsLastHit(indexOfPlatform, platforms);


            Vector2 spotHit = new Vector2(proj.pos());
            shotBounces.add(new ShotBounce(spotHit));

            return true;

            //check against hypot
        } else if(Geometry.LineSegmentCircleIntersection(tri.A, tri.C,
                proj.pos(), proj.boundingCircle.radius) && !ptfm.lastHit) {

           // proj.position = proj.lastNonCollidingPosition;


            Vector2 U = mul(proj.velocity().dot(tri.hypotFacing), tri.hypotFacing);
            Vector2 W = sub(proj.velocity(), U);

            Vector2 newVelocity = sub(W, U);

            proj.velocity().set(newVelocity).add(tri.hypotFacing).mul(1.0f);

            ptfm.setLastHit();

            //unset all other platforms as 'last hit'
            world.leftWallHitLast = false;
            world.rightWallHitLast = false;
            unsetAllOtherPlatformsAsLastHit(indexOfPlatform, platforms);

            Vector2 spotHit = new Vector2(proj.pos());
            shotBounces.add(new ShotBounce(spotHit));

            return true;

            //check against bottom
        } else if(Geometry.LineSegmentCircleIntersection(tri.B, tri.C,
                proj.pos(), proj.boundingCircle.radius) && !ptfm.lastHit) {

          //  proj.position = proj.lastNonCollidingPosition;

            proj.reflectY();

            ptfm.setLastHit();

            //unset all other platforms as 'last hit'
            world.leftWallHitLast = false;
            world.rightWallHitLast = false;
            unsetAllOtherPlatformsAsLastHit(indexOfPlatform, platforms);

            Vector2 spotHit = new Vector2(proj.pos());
            shotBounces.add(new ShotBounce(spotHit));

            return true;
            //check against side
        } else if(Geometry.LineSegmentCircleIntersection(tri.B, tri.A,
                proj.pos(), proj.boundingCircle.radius) && !ptfm.lastHit) {

           // proj.position = proj.lastNonCollidingPosition;

            proj.reflectX();

            ptfm.setLastHit();

            //unset all other platforms as 'last hit'
            world.leftWallHitLast = false;
            world.rightWallHitLast = false;
            unsetAllOtherPlatformsAsLastHit(indexOfPlatform, platforms);

            Vector2 spotHit = new Vector2(proj.pos());
            shotBounces.add(new ShotBounce(spotHit));

            return true;
        }

        return false;

    }
*/

 /*   private static void unsetAllOtherPlatformsAsLastHit(int ptfmIndex,
                                                        List<Platform> platforms) {

        for(int i = 0; i < platforms.size(); i++) {
            //ignore index given in parameter
            if(i == ptfmIndex) continue;

            Platform ptfm = platforms.get(i);
            ptfm.unsetLastHit();
        }

    } */


}
