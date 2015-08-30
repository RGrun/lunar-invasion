package r3software.org.lunarinvasion.levels;

import java.util.ArrayList;
import java.util.List;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.Drone;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.engine.math.Triangle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.platforms.Platform_12X2;
import r3software.org.lunarinvasion.platforms.Platform_2X2;
import r3software.org.lunarinvasion.platforms.Platform_4X2;
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_8X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Around the Corner Level
 */
public class L_AroundTheCorner implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 10,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 30,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Around The Corner"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 39, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(19, 39, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(1, 29, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19, 29, Triangle.TRIANGLE_FACING.NW));

        world.platforms.add(new Platform_Angled_2X2(1, 25, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19, 25, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(1, 21, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(19, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(1, 19, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19, 19, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(1, 16, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19, 16, Triangle.TRIANGLE_FACING.NW));

        world.platforms.add(new Platform_Angled_2X2(1, 12, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(19, 12, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(1, 1, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19, 1, Triangle.TRIANGLE_FACING.NW));

        // UB 12x2
        world.platforms.add(new Platform_12X2(10, 34, false, 10, 0));
        world.platforms.add(new Platform_12X2(10, 6, false, 10, 0));

        // UB 8x2
        world.platforms.add(new Platform_8X2(4, 27, false, 10, 0));
        world.platforms.add(new Platform_8X2(16, 27, false, 10, 0));

        world.platforms.add(new Platform_8X2(4, 14, false, 10, 0));
        world.platforms.add(new Platform_8X2(16, 14, false, 10, 0));


        // UB 4x2
        world.platforms.add(new Platform_4X2(10, 20, false, 10, 0));
        

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(7, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(10, 36));
        world.powerUps.add(new ShieldPU(2, 24));
        world.powerUps.add(new HealthPU(18, 17));
        world.powerUps.add(new WeaponPU(10, 4));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(3, 38));
        d1p.add(new Vector2(17, 38));

        world.drones.add(new Drone(17, 38, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(4, 23));
        d2p.add(new Vector2(16, 23));
        d2p.add(new Vector2(16, 17));
        d2p.add(new Vector2(4, 17));

        world.drones.add(new Drone(4, 17, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(16, 17));
        d3p.add(new Vector2(4, 17));
        d3p.add(new Vector2(4, 23));
        d3p.add(new Vector2(16, 23));

        world.drones.add(new Drone(16, 23, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(17, 2));
        d4p.add(new Vector2(3, 2));

        world.drones.add(new Drone(3, 2, d4p, true));



        Assets.randomSong();

    }
}
