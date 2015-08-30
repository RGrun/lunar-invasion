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
import r3software.org.lunarinvasion.platforms.Platform_4X4;
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Isolation level
 */
public class L_Isolation implements Level {

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

        // "Isolation"

        //6x6 angled
        world.platforms.add(new Platform_Angled_6X6(3, 37, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(17, 37, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_6X6(3, 24, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_6X6(17, 24, Triangle.TRIANGLE_FACING.NW));

        world.platforms.add(new Platform_Angled_6X6(3, 16, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(17, 16, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_6X6(3, 3, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_6X6(17, 3, Triangle.TRIANGLE_FACING.NW));

        // UB 4x4
        world.platforms.add(new Platform_4X4(10, 13, false, 10, 0));
        world.platforms.add(new Platform_4X4(10, 27, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(8, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(12, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new HealthPU(4, 31));
        world.powerUps.add(new WeaponPU(16, 31));

        world.powerUps.add(new WeaponPU(4, 9));
        world.powerUps.add(new HealthPU(16, 9));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(6, 31));
        d1p.add(new Vector2(14, 31));
        d1p.add(new Vector2(14, 23));
        d1p.add(new Vector2(6, 23));

        world.drones.add(new Drone(6, 23, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(14, 23));
        d2p.add(new Vector2(6, 23));
        d2p.add(new Vector2(6, 31));
        d2p.add(new Vector2(14, 31));

        world.drones.add(new Drone(14, 31, d2p, true));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(14, 17));
        d3p.add(new Vector2(14, 9));
        d3p.add(new Vector2(6, 9));
        d3p.add(new Vector2(6, 17));

        world.drones.add(new Drone(6, 17, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(6, 9));
        d4p.add(new Vector2(6, 17));
        d4p.add(new Vector2(14, 17));
        d4p.add(new Vector2(14, 9));

        world.drones.add(new Drone(14, 9, d4p, false));


        Assets.randomSong();

    }
}
