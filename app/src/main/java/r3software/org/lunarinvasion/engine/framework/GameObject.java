package r3software.org.lunarinvasion.engine.framework;

import r3software.org.lunarinvasion.engine.math.Rectangle;
import r3software.org.lunarinvasion.engine.math.Vector2;


//for static game objects
public class GameObject {
	public Vector2 position;
	public Rectangle bounds;
    public boolean tagged; //for variable use
	
	public GameObject(float x, float y, float width, float height) {
		this.position = new Vector2(x,y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
        tagged = false;
    }

    public GameObject(GameObject other, float width, float height) {
        this.position = new Vector2(other.pos().x, other.pos().y);
        this.bounds = new Rectangle(other.pos().x - width / 2,
                other.pos().y - height / 2, width, height);
        tagged = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 pos() { return position; }

    public void setPosition(Vector2 newPos) {
        position = newPos;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(float width, float height) {
        this.bounds = new Rectangle(this.position.x - width /2, this.position.y - height / 2,
                width, height);
    }

    public void toggleTag() {
        tagged = !tagged;
    }

    public boolean isTagged() {
        return tagged;
    }


}
