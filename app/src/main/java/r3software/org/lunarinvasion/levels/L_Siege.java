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
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Siege level
 */
public class L_Siege implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(7, 4,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(13, 36,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Siege"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(9, 28, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 26, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11, 26, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 28, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(9, 14, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 12, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11, 12, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 14, Triangle.TRIANGLE_FACING.NE));

        // 6x6 angled
        world.platforms.add(new Platform_Angled_6X6(3, 37, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(17, 3, Triangle.TRIANGLE_FACING.NW));

        // UB 8x2
        world.platforms.add(new Platform_6X2(17, 32, false, 10, 90));
        world.platforms.add(new Platform_6X2(3, 8, false, 10, 90));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(7, 30, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 30, true, 10, 0));

        world.platforms.add(new Platform_2X2(4, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(16, 20, true, 10, 0));

        world.platforms.add(new Platform_2X2(7, 10, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 10, true, 10, 0));

        //power ups
        world.powerUps.add(new ShieldPU(17, 38));
        world.powerUps.add(new WeaponPU(3, 27));
        world.powerUps.add(new WeaponPU(17, 27));
        world.powerUps.add(new WeaponPU(3, 14));
        world.powerUps.add(new WeaponPU(17, 14));
        world.powerUps.add(new ShieldPU(3, 2));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(8, 36));
        d1p.add(new Vector2(2, 30));

        world.drones.add(new Drone(2, 30, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(13, 23));
        d2p.add(new Vector2(13, 17));
        d2p.add(new Vector2(7, 17));
        d2p.add(new Vector2(7, 23));

        world.drones.add(new Drone(7, 23, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(7, 17));
        d3p.add(new Vector2(7, 23));
        d3p.add(new Vector2(13, 23));
        d3p.add(new Vector2(13, 17));

        world.drones.add(new Drone(13, 17, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(12, 4));
        d4p.add(new Vector2(18, 10));

        world.drones.add(new Drone(18, 10, d4p, true));


        Assets.randomSong();

    }
}
