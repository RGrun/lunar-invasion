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
 * Entropy Level
 */
public class L_Entropy implements Level {

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

        // "Entropy"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 37, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 35, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19, 37, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19, 35, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(1, 5, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 3, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19, 5, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19f, 3, Triangle.TRIANGLE_FACING.SW));


        // UB 6x2
        world.platforms.add(new Platform_6X2(10, 0, false, 10, 0));
        world.platforms.add(new Platform_6X2(10, 40, false, 10, 0));


        // UB 2x2
        world.platforms.add(new Platform_2X2(6, 28, false, 10, 0));
        world.platforms.add(new Platform_2X2(14, 28, false, 10, 0));

        world.platforms.add(new Platform_2X2(10, 20, false, 10, 0));

        world.platforms.add(new Platform_2X2(6, 12, false, 10, 0));
        world.platforms.add(new Platform_2X2(14, 12, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 28, true, 10, 0));

        world.platforms.add(new Platform_2X2(6, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 20, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 12, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(2, 28));
        world.powerUps.add(new WeaponPU(18, 12));


        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(2, 31));
        d1p.add(new Vector2(18, 31));

        world.drones.add(new Drone(18, 31, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(2, 25));
        d2p.add(new Vector2(18, 25));

        world.drones.add(new Drone(18, 25, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(18, 23));
        d3p.add(new Vector2(2, 23));

        world.drones.add(new Drone(2, 23, d3p, true));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(18, 17));
        d4p.add(new Vector2(2, 17));

        world.drones.add(new Drone(2, 17, d4p, false));

        List<Vector2> d5p = new ArrayList<>();
        d5p.add(new Vector2(2, 15));
        d5p.add(new Vector2(18, 15));

        world.drones.add(new Drone(18, 15, d5p, true));

        List<Vector2> d6p = new ArrayList<>();
        d6p.add(new Vector2(2, 9));
        d6p.add(new Vector2(18, 9));

        world.drones.add(new Drone(18, 9, d6p, false));


        Assets.randomSong();

    }
}
