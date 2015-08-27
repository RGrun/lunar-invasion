package r3software.org.lunarinvasion.levels;

import java.util.ArrayList;
import java.util.List;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.Drone;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.engine.math.Triangle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.platforms.Platform_12X2;
import r3software.org.lunarinvasion.platforms.Platform_2X2;
import r3software.org.lunarinvasion.platforms.Platform_4X2;
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/27/15.
 *
 * Symmetry level
 */
public class L_Symmetry implements Level {
    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(2, 20,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(18, 20,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Symmetry"

        //4x4 angled platforms
        world.platforms.add(new Platform_Angled_4X4(7, 37, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(13, 37, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_4X4(7, 3, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_4X4(13, 3, Triangle.TRIANGLE_FACING.NW));


        // UB 12x2
        world.platforms.add(new Platform_12X2(5, 20, false, 10, 90));
        world.platforms.add(new Platform_12X2(15, 20, false, 10, 90));


        // UB 2x2
        world.platforms.add(new Platform_2X2(10, 30, false, 10, 0));
        world.platforms.add(new Platform_2X2(10, 20, false, 10, 0));
        world.platforms.add(new Platform_2X2(10, 10, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(5, 31, true, 10, 0));
        world.platforms.add(new Platform_2X2(15, 31, true, 10, 0));

        world.platforms.add(new Platform_2X2(5, 9, true, 10, 0));
        world.platforms.add(new Platform_2X2(15, 9, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(7, 25));
        world.powerUps.add(new ShieldPU(13, 25));
        world.powerUps.add(new WeaponPU(13, 15));
        world.powerUps.add(new ShieldPU(7, 15));


        Assets.randomSong();

    }

}
