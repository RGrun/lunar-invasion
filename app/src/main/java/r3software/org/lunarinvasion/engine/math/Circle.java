package r3software.org.lunarinvasion.engine.math;

public class Circle {
	
	public final Vector2 center = new Vector2();
	public float radius;
	
	public Circle(float x, float y, float radius) {
		this.center.set(x, y);
		this.radius = radius;
	}

	public Circle(Circle other) {
		this.center.set(other.center.x, other.center.y);
		this.radius = other.radius;
	}
	
	public boolean overlapCircles(Circle c1, Circle c2) {
		float distance = c1.center.distSquared(c2.center);
		float radiusSum = c1.radius + c2.radius;
		return distance <= radiusSum * radiusSum;
	}
	
	

}
