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
 * Schema Level
 */
public class L_Schema implements Level {
    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(4, 4,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(16, 36,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Schema"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(9, 39, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11, 39, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19, 37, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19, 35, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(19, 37, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19, 35, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(5, 31, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(5, 29, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(7, 29, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(7, 31, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(13, 31, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(13, 29, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(15, 29, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(15, 31, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(9, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 21, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(5, 11, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(5, 9, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(7, 9, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(7, 11, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(1, 11, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 9, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(1, 5, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 3, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(9, 1, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(11, 1, Triangle.TRIANGLE_FACING.NE));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(1, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(3, 20, true, 10, 0));

        world.platforms.add(new Platform_2X2(17, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(19, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(2, 30));
        world.powerUps.add(new WeaponPU(18, 10));


        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(10, 36));
        d1p.add(new Vector2(10, 25));

        world.drones.add(new Drone(10, 25, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(10, 4));
        d2p.add(new Vector2(10, 16));

        world.drones.add(new Drone(10, 16, d2p, false));


        Assets.randomSong();

    }

}
