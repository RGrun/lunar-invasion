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
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 9/8/15.
 *
 * Maneuver level
 */
public class L_Maneuver implements Level {
    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(3, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(17, 37,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Maneuver"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(4, 30, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(4, 28, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(6, 28, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(6, 30, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(14, 30, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(14, 28, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(16, 28, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(16, 30, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(4, 12, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(4, 10, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(6, 10, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(6, 12, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(14, 12, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(14, 10, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(16, 10, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(16, 12, Triangle.TRIANGLE_FACING.NE));

        // UB 6x2
        world.platforms.add(new Platform_6X2(5, 20, false, 10, 0));
        world.platforms.add(new Platform_6X2(15, 20, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(2, 27));
        world.powerUps.add(new ShieldPU(5, 25));
        world.powerUps.add(new WeaponPU(18, 13));
        world.powerUps.add(new ShieldPU(15, 15));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(3, 37));
        d1p.add(new Vector2(15, 23));

        world.drones.add(new Drone(15, 23, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(17, 3));
        d2p.add(new Vector2(5, 17));

        world.drones.add(new Drone(5, 17, d2p, false));


        Assets.randomSong();

    }
}
