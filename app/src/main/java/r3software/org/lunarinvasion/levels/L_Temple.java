package r3software.org.lunarinvasion.levels;

import java.util.ArrayList;
import java.util.List;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.Drone;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.engine.math.Triangle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.platforms.Platform_16X2;
import r3software.org.lunarinvasion.platforms.Platform_2X2;
import r3software.org.lunarinvasion.platforms.Platform_4X2;
import r3software.org.lunarinvasion.platforms.Platform_8X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/25/15.
 *
 * Temple level
 */
public class L_Temple implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(16, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(4, 37,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                (float) 180, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Factory"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 31, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19f, 31, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(1, 21, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(19f, 21, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(1, 19, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19f, 19, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(1, 9, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(19f, 9, Triangle.TRIANGLE_FACING.SW));

        // angled 4x4

        world.platforms.add(new Platform_Angled_4X4(2, 2, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_4X4(18, 38, Triangle.TRIANGLE_FACING.SW));


        //unbreakable 8x2 platforms
        world.platforms.add(new Platform_8X2(1, 26, false, 10, 90));

        world.platforms.add(new Platform_8X2(19, 26, false, 10, 90));

        world.platforms.add(new Platform_8X2(1, 14, false, 10, 90));

        world.platforms.add(new Platform_8X2(19, 14, false, 10, 90));

        // UB 2x2
        world.platforms.add(new Platform_2X2(7, 29, false, 10, 0));
        world.platforms.add(new Platform_2X2(13, 29, false, 10, 0));

        world.platforms.add(new Platform_2X2(7, 23, false, 10, 0));
        world.platforms.add(new Platform_2X2(13, 23, false, 10, 0));

        world.platforms.add(new Platform_2X2(7, 17, false, 10, 0));
        world.platforms.add(new Platform_2X2(13, 17, false, 10, 0));

        world.platforms.add(new Platform_2X2(7, 11, false, 10, 0));
        world.platforms.add(new Platform_2X2(13, 11, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(28, 34, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(2, 6, true, 10, 0));
        world.platforms.add(new Platform_2X2(18, 34, true, 10, 0));

        //power ups
        world.powerUps.add(new ShieldPU(10, 29));
        world.powerUps.add(new WeaponPU(4, 20));
        world.powerUps.add(new WeaponPU(16, 20));
        world.powerUps.add(new ShieldPU(10, 11));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(4, 26));
        d1p.add(new Vector2(16, 26));

        world.drones.add(new Drone(16, 26, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(16, 14));
        d2p.add(new Vector2(4, 14));

        world.drones.add(new Drone(4, 14, d2p, false));


        Assets.randomSong();

    }

}
