package r3software.org.lunarinvasion.levels;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.platforms.Platform_2X2;

/**
 * Created by richard on 9/1/15.
 *
 * Dig level
 */
public class L_Dig implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 5,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 35,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Dig"


        //breakable 2x2 platforms

        for(float x = 3; x <= 17; x += 2) {

            for(float y = 29; y >= 11; y -= 2) {
                world.platforms.add(new Platform_2X2(x, y, true, 10, 0));

            }
        }

        // players start with more blue ammo on this level
        world.hCannon.blueAmmo = 4;
        world.aCannon.blueAmmo = 4;

        Assets.randomSong();

    }
}
