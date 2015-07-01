package r3software.org.lunarinvasion.powerups;

import r3software.org.lunarinvasion.engine.framework.GameObject;

/**
 * Created by Jeff on 6/6/2015.
 *
 * This is an abstract base class for power ups
 */
public abstract class PowerUp extends GameObject {

    public PowerUp(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
