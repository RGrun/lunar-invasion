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
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Ricochet Level
 */
public class L_Ricochet implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(4, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(4, 37,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Ricochet"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(9, 27, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 25, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11,25, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 27, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(4, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(4, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(6, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(6, 21, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(14, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(14, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(16, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(16, 21, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(9, 15, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 13, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11, 13, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 15, Triangle.TRIANGLE_FACING.NE));

        // 4x2 angled
        world.platforms.add(new Platform_Angled_4X4(16, 36, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_4X4(16, 4, Triangle.TRIANGLE_FACING.NW));

        // UB 4x4
        world.platforms.add(new Platform_4X4(4, 32, false, 10, 0));
        world.platforms.add(new Platform_4X4(4, 8, false, 10, 90));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new HealthPU(1, 35));
        world.powerUps.add(new WeaponPU(1, 20));
        world.powerUps.add(new WeaponPU(19, 20));
        world.powerUps.add(new HealthPU(1, 5));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(18, 32));
        d1p.add(new Vector2(8, 32));

        world.drones.add(new Drone(8, 32, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(2, 28));
        d2p.add(new Vector2(8, 22));

        world.drones.add(new Drone(8, 22, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(18, 28));
        d3p.add(new Vector2(12, 22));

        world.drones.add(new Drone(12, 22, d3p, true));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(2, 12));
        d4p.add(new Vector2(8, 18));

        world.drones.add(new Drone(8, 18, d4p, false));

        List<Vector2> d5p = new ArrayList<>();
        d5p.add(new Vector2(18, 12));
        d5p.add(new Vector2(12, 18));

        world.drones.add(new Drone(12, 18, d5p, true));

        List<Vector2> d6p = new ArrayList<>();
        d6p.add(new Vector2(18, 8));
        d6p.add(new Vector2(8, 8));

        world.drones.add(new Drone(8, 8, d6p, false));


        Assets.randomSong();

    }


}
