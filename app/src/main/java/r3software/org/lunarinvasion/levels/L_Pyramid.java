package r3software.org.lunarinvasion.levels;

import java.util.ArrayList;
import java.util.List;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.Drone;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.engine.math.Triangle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.platforms.Platform_2X2;
import r3software.org.lunarinvasion.platforms.Platform_4X2;
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_8X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/25/15.
 *
 * Pyramid level
 */
public class L_Pyramid implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(15, 7,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(15, 33,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Pyramid"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(14, 39, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(16f, 39, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(1, 34, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 32, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(15, 26, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(5f, 21, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(5, 19, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19.5f, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 19, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(15f, 15, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(1f, 8, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1f, 6, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(14f, 1, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(16f, 1, Triangle.TRIANGLE_FACING.NE));

        // UB 6x2
        world.platforms.add(new Platform_6X2(7, 38, false, 10, 0));
        world.platforms.add(new Platform_6X2(3, 20, false, 10, 90));
        world.platforms.add(new Platform_6X2(5, 2, false, 10, 0));

        // UB 4x2
        world.platforms.add(new Platform_4X2(7, 29, false, 10, 90));
        world.platforms.add(new Platform_4X2(3, 27, false, 10, 90));

        world.platforms.add(new Platform_4X2(11, 20, false, 10, 0));

        world.platforms.add(new Platform_4X2(3, 13, false, 10, 90));
        world.platforms.add(new Platform_4X2(7, 11, false, 10, 90));


        // UB 2x2
        world.platforms.add(new Platform_2X2(11, 27, false, 10, 0));
        world.platforms.add(new Platform_2X2(11, 13, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(3, 24, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 23, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 17, true, 10, 0));
        world.platforms.add(new Platform_2X2(3, 16, true, 10, 0));

        //power ups
        world.powerUps.add(new HealthPU(4, 9));
        world.powerUps.add(new WeaponPU(7, 15));
        world.powerUps.add(new ShieldPU(16, 20));
        world.powerUps.add(new WeaponPU(7, 25));
        world.powerUps.add(new HealthPU(4, 31));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(18, 38));
        d1p.add(new Vector2(18, 24));

        world.drones.add(new Drone(18, 24, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(18, 2));
        d2p.add(new Vector2(18, 16));

        world.drones.add(new Drone(18, 16, d2p, false));


        Assets.randomSong();

    }

}
