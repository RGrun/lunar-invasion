package r3software.org.lunarinvasion;

import r3software.org.lunarinvasion.engine.math.Vector2;

/**
 * Created by Jeff on 5/3/2015.
 */
public class ShieldEffect {
    public Vector2 pos;

    public float existedTime;

    public static float EXISTS_TIME = 0.5f;

    public static float SHIELD_WIDTH = 3f;
    public static float SHIELD_HEIGHT = 3f;

    public ShieldEffect(float x, float y) {
        this.pos = new Vector2(x, y);
        this.existedTime = 0;
    }

    public ShieldEffect(Vector2 newPos) {
        this.pos = newPos;
        this.existedTime = 0;
    }

    public void update(float deltaTIme) {
        this.existedTime += deltaTIme;
    }
}
