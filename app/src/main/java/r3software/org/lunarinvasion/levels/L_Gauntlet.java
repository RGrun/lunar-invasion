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
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Gauntlet Level
 */
public class L_Gauntlet implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 4,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 36,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Gauntlet"

        //6x6 angled platforms
        world.platforms.add(new Platform_Angled_6X6(3, 37, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(17, 3, Triangle.TRIANGLE_FACING.NW));

        //4x4 angled platforms
        world.platforms.add(new Platform_Angled_4X4(17, 37, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_4X4(3, 3, Triangle.TRIANGLE_FACING.NE));


        // UB 8x2
        world.platforms.add(new Platform_8X2(4, 26, false, 10, 90));
        world.platforms.add(new Platform_8X2(10, 29, false, 10, 90));
        world.platforms.add(new Platform_8X2(16, 26, false, 10, 90));

        world.platforms.add(new Platform_8X2(4, 14, false, 10, 90));
        world.platforms.add(new Platform_8X2(10, 11, false, 10, 90));
        world.platforms.add(new Platform_8X2(16, 14, false, 10, 90));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(17, 33, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 23, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 17, true, 10, 0));

        world.platforms.add(new Platform_2X2(3, 7, true, 10, 0));


        //power ups
        world.powerUps.add(new WeaponPU(4, 31));
        world.powerUps.add(new WeaponPU(4, 20));
        world.powerUps.add(new WeaponPU(16, 20));
        world.powerUps.add(new WeaponPU(16, 9));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(7, 10));
        d1p.add(new Vector2(7, 30));

        world.drones.add(new Drone(7, 30, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(13, 30));
        d2p.add(new Vector2(13, 10));

        world.drones.add(new Drone(13, 10, d2p, false));


        Assets.randomSong();

    }
}
