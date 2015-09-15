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
 * Factory Level
 */
public class L_Factory implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(17, 9,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(3, 31,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                (float) 180, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Factory"

        //2x2 angled platforms

        world.platforms.add(new Platform_Angled_2X2(3, 26, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(3, 24, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(5, 24, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(5, 26, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(15, 16, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(15, 14, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(17, 14, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(17, 16, Triangle.TRIANGLE_FACING.NE));

        // angled 4x4
        world.platforms.add(new Platform_Angled_4X4(7, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(13, 21, Triangle.TRIANGLE_FACING.NW));


        //unbreakable 8x2 platforms
        world.platforms.add(new Platform_8X2(19f, 35, false, 10, 90));

        world.platforms.add(new Platform_8X2(19f, 23, false, 10, 90));

        world.platforms.add(new Platform_8X2(0, 17, false, 10, 90));

        world.platforms.add(new Platform_8X2(0, 5, false, 10, 90));

        //UB 4x2 pl
        world.platforms.add(new Platform_4X2(0, 31, false, 10, 90));
        world.platforms.add(new Platform_4X2(19, 9, false, 10, 90));

        // 16x2 pl
        world.platforms.add(new Platform_16X2(10, 40, false, 10, 0));
        world.platforms.add(new Platform_16X2(10, 0, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(14, 29, true, 10, 0));
        world.platforms.add(new Platform_2X2(18, 29, true, 10, 0));

        world.platforms.add(new Platform_2X2(2, 11, true, 10, 0));
        world.platforms.add(new Platform_2X2(6, 11, true, 10, 0));

        //power ups
        world.powerUps.add(new ShieldPU(14, 25));
        world.powerUps.add(new WeaponPU(2, 23));
        world.powerUps.add(new WeaponPU(18, 17));
        world.powerUps.add(new ShieldPU(6, 15));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(2, 37));
        d1p.add(new Vector2(15, 37));

        world.drones.add(new Drone(15, 37, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(15, 34));
        d2p.add(new Vector2(2, 34));

        world.drones.add(new Drone(2, 34, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(5, 6));
        d3p.add(new Vector2(18, 6));

        world.drones.add(new Drone(18, 6, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(18, 3));
        d4p.add(new Vector2(5, 3));

        world.drones.add(new Drone(5, 3, d4p, false));


        Assets.randomSong();

    }
}
