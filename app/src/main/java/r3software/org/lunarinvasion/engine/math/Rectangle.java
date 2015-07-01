package r3software.org.lunarinvasion.engine.math;

public class Rectangle {
	
	public final Vector2 bottomLeft, topRight;
    public final Vector2 topLeft, bottomRight; //needed for collision detection
    public Vector2 center;
	public float width, height;
	
	public Rectangle(float x, float y, float width, float height) {
		this.bottomLeft = new Vector2(x, y);
        this.topRight = new Vector2(x + width, y + height);

        this.topLeft = new Vector2(x, y + height);
        this.bottomRight = new Vector2(x + width, y);

        this.center = new Vector2(x + (width / 2), y + (height / 2));

		this.width = width;
		this.height = height;
	}

    public Rectangle(Rectangle other) {
        this.bottomLeft = new Vector2(other.bottomLeft.x, other.bottomLeft.y);
        this.topRight = new Vector2(other.topRight.x, other.topRight.y);

        this.topLeft = new Vector2(other.topLeft.x, other.bottomLeft.y + other.height);
        this.bottomRight = new Vector2(other.bottomRight.x + other.width, other.topRight.y);

        this.center = new Vector2(other.center.x, other.center.y);

        this.width = other.width;
        this.height = other.height;
    }

    public void move(Vector2 newCoords) {
        this.center.set(newCoords);

        this.bottomLeft.set(center.x - (width / 2), center.y - (height /2));
        this.bottomRight.set(center.x + (width /2), center.y - (height / 2));

        this.topLeft.set(center.x - (width / 2), center.y + (height / 2));
        this.topRight.set(center.x + (width / 2), center.y + (height / 2));
    }
	
	public boolean overlapRectangles(Rectangle r1, Rectangle r2) {
		if(r1.bottomLeft.x < r2.bottomLeft.x + r2.width &&
				r1.bottomLeft.x + r1.width > r2.bottomLeft.x &&
				r1.bottomLeft.y < r2.bottomLeft.y + r2.height &&
				r1.bottomLeft.y + r1.height > r2.bottomLeft.y)
			return true;
		else
			return false;
	}

}
