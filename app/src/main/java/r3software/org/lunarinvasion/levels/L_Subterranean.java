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
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 9/8/15.
 *
 * Subterranean level
 */
public class L_Subterranean implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(7, 4,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(7, 36,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Subterranean"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 37, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 35, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(1, 29, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 27, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19, 19, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(1, 13, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 11, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(1, 5, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 3, Triangle.TRIANGLE_FACING.SE));

        //4x4 angled platforms
        world.platforms.add(new Platform_Angled_4X4(18, 38, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_4X4(18, 2, Triangle.TRIANGLE_FACING.NW));

        // UB 6x2
        world.platforms.add(new Platform_6X2(7, 32, false, 10, 0));
        world.platforms.add(new Platform_6X2(7, 20, false, 10, 0));
        world.platforms.add(new Platform_6X2(7, 8, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(2, 22, true, 10, 0));
        world.platforms.add(new Platform_2X2(2, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(2, 18, true, 10, 0));

        world.platforms.add(new Platform_2X2(14, 22, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 18, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(11.5f, 20));
        world.powerUps.add(new WeaponPU(16.5f, 20));


        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(18, 29));
        d1p.add(new Vector2(4, 29));

        world.drones.add(new Drone(4, 29, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(4, 26));
        d2p.add(new Vector2(18, 26));

        world.drones.add(new Drone(18, 26, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(4, 14));
        d3p.add(new Vector2(18, 14));

        world.drones.add(new Drone(18, 14, d3p, true));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(18, 11));
        d4p.add(new Vector2(4, 11));

        world.drones.add(new Drone(4, 11, d4p, false));


        Assets.randomSong();

    }
}
