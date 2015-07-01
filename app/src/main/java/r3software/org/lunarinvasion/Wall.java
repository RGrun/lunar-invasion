package r3software.org.lunarinvasion;

import r3software.org.lunarinvasion.platforms.Platform;

/**
 * Created by Jeff on 2/12/2015.
 *
 * This class is for the static walls to the left and right of the game screen
 *
 */
public class Wall extends Platform {

    public static final float WALL_WIDTH = 1;
    public static final float WALL_HEIGHT = 38;



    public Wall(float x, float y) {
        super(x, y, WALL_WIDTH, WALL_HEIGHT, false, 1, PLATFORM_TYPE.WALL, 0);
    }



}
