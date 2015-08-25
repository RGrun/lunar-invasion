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
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/25/15.
 *
 * Chaos Theroy Level
 */
public class L_ChaosTheroy implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 7,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 33,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                (float) 180, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Chaos Theroy"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(2, 24, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(2, 22, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(4, 24, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(4, 22, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(2, 18, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(2, 16, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(4, 18, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(4, 16, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(16, 24, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(16, 22, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(18, 24, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(18, 22, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(16, 18, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(16, 16, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(18, 18, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(18, 16, Triangle.TRIANGLE_FACING.SE));


        //unbreakable 8x2 platforms
        world.platforms.add(new Platform_8X2(10, 26, false, 10, 0));
        world.platforms.add(new Platform_8X2(10, 14, false, 10, 0));

        //UB 4x2 pl
        world.platforms.add(new Platform_4X2(10, 20, false, 10, 0));

        // 16x2 pl
        world.platforms.add(new Platform_16X2(10, 40, false, 10, 0));
        world.platforms.add(new Platform_16X2(10, 0, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(8, 37, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 37, true, 10, 0));
        world.platforms.add(new Platform_2X2(12, 37, true, 10, 0));

        world.platforms.add(new Platform_2X2(8, 3, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 3, true, 10, 0));
        world.platforms.add(new Platform_2X2(12, 3, true, 10, 0));

        world.platforms.add(new Platform_2X2(7, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(10, 17));
        world.powerUps.add(new WeaponPU(10, 23));
        world.powerUps.add(new ShieldPU(3, 20));
        world.powerUps.add(new ShieldPU(17, 20));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(3, 37));
        d1p.add(new Vector2(3, 26));

        world.drones.add(new Drone(3, 26, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(17, 26));
        d2p.add(new Vector2(17, 37));

        world.drones.add(new Drone(17, 37, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(3, 13));
        d3p.add(new Vector2(3, 3));

        world.drones.add(new Drone(3, 3, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(17, 3));
        d4p.add(new Vector2(17, 13));

        world.drones.add(new Drone(17, 13, d4p, false));


        Assets.randomSong();

    }
}
