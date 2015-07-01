package r3software.org.lunarinvasion;

import r3software.org.lunarinvasion.engine.math.Vector2;

/**
 * Created by Jeff on 3/29/2015.
 */
public class ShotBounce {

    public Vector2 pos;

    public float existedTime;

    public static float EXISTS_TIME = 0.5f;

    public static float BOUNCE_WIDTH = 1f;
    public static float BOUNCE_HEIGHT = 1f;

    public ShotBounce(float x, float y) {
        this.pos = new Vector2(x, y);
        this.existedTime = 0;
    }

    public ShotBounce(Vector2 newPos) {
        this.pos = newPos;
        this.existedTime = 0;
    }

    public void update(float deltaTIme) {
        this.existedTime += deltaTIme;
    }

}
