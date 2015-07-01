package r3software.org.lunarinvasion;

import r3software.org.lunarinvasion.engine.math.Vector2;

/**
 * Created by Jeff on 4/26/2015.
 */
public class TeleportEffect {

    public Vector2 pos;

    public float existedTime;

    public static float EXISTS_TIME = 0.5f;

    public static float TELEPORT_WIDTH = 3f;
    public static float TELEPORT_HEIGHT = 3f;

    public boolean reverse;

    public TeleportEffect(float x, float y) {
        this.pos = new Vector2(x, y);
        this.existedTime = 0;
        this.reverse = false;
    }

    public TeleportEffect(Vector2 newPos) {
        this.pos = newPos;
        this.existedTime = 0;
        this.reverse = false;
    }

    public TeleportEffect(float x, float y, boolean reverse) {
        this.pos = new Vector2(x, y);
        this.existedTime = 0;
        this.reverse = reverse;
    }

    public TeleportEffect(Vector2 newPos, boolean reverse) {
        this.pos = newPos;
        this.existedTime = 0;
        this.reverse = reverse;
    }

    public void update(float deltaTIme) {
        this.existedTime += deltaTIme;
    }
}
