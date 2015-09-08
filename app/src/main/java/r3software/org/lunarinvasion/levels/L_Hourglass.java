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
 * Created by richard on 8/25/15.
 *
 * Hourglass level
 */
public class L_Hourglass implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 9,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 31,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                (float) 180, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Hourglass"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 39, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(19.25f, 39, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(1, 21, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19.25f, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(1, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(19.25f, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(9, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(11f, 21, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(9, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11f, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(1, 1, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(19.25f, 1, Triangle.TRIANGLE_FACING.NW));

        //unbreakable 2x2 platforms
        world.platforms.add(new Platform_2X2(5, 35, false, 10, 0));
        world.platforms.add(new Platform_2X2(15, 35, false, 10, 0));
        world.platforms.add(new Platform_2X2(7, 27, false, 10, 0));
        world.platforms.add(new Platform_2X2(13, 27, false, 10, 0));

        world.platforms.add(new Platform_2X2(5, 5, false, 10, 0));
        world.platforms.add(new Platform_2X2(15, 5, false, 10, 0));
        world.platforms.add(new Platform_2X2(7, 13, false, 10, 0));
        world.platforms.add(new Platform_2X2(13, 13, false, 10, 0));

        //UB 4x2 pl
        world.platforms.add(new Platform_4X2(10, 35, false, 10, 0));
        world.platforms.add(new Platform_4X2(10, 5, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(7, 35, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 35, true, 10, 0));
        world.platforms.add(new Platform_2X2(9, 27, true, 10, 0));
        world.platforms.add(new Platform_2X2(11, 27, true, 10, 0));

        world.platforms.add(new Platform_2X2(7, 5, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 5, true, 10, 0));
        world.platforms.add(new Platform_2X2(9, 13, true, 10, 0));
        world.platforms.add(new Platform_2X2(11, 13, true, 10, 0));

        //power ups
        world.powerUps.add(new ShieldPU(2, 2));
        world.powerUps.add(new HealthPU(10, 2));
        world.powerUps.add(new ShieldPU(18, 2));
        world.powerUps.add(new WeaponPU(10, 16));

        world.powerUps.add(new ShieldPU(2, 38));
        world.powerUps.add(new HealthPU(10, 38));
        world.powerUps.add(new ShieldPU(18, 38));
        world.powerUps.add(new WeaponPU(10, 24));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(4, 32));
        d1p.add(new Vector2(4, 8));

        world.drones.add(new Drone(4, 8, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(16, 8));
        d2p.add(new Vector2(16, 32));

        world.drones.add(new Drone(16, 32, d2p, false));

        Assets.randomSong();

    }
}
