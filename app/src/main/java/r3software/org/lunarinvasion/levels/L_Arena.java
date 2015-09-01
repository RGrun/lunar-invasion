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
 * Created by richard on 9/1/15.
 *
 * Highway level
 */
public class L_Arena implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(4, 5,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(16, 35,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Highway"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(9, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 19, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(11, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 21, Triangle.TRIANGLE_FACING.NE));

        //6x6 angled platforms
        world.platforms.add(new Platform_Angled_6X6(4, 27, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(16, 27, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_6X6(4, 13, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_6X6(16, 13, Triangle.TRIANGLE_FACING.NW));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 27, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 24, true, 10, 0));

        world.platforms.add(new Platform_2X2(3, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(6, 20, true, 10, 0));

        world.platforms.add(new Platform_2X2(14, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(17, 20, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 16, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 13, true, 10, 0));

        //power ups
        world.powerUps.add(new HealthPU(4, 37));
        world.powerUps.add(new ShieldPU(4, 35));
        world.powerUps.add(new HealthPU(4, 33));
        world.powerUps.add(new WeaponPU(10, 35));

        world.powerUps.add(new WeaponPU(5, 25));
        world.powerUps.add(new WeaponPU(15, 15));

        world.powerUps.add(new HealthPU(16, 7));
        world.powerUps.add(new ShieldPU(16, 5));
        world.powerUps.add(new HealthPU(16, 3));
        world.powerUps.add(new WeaponPU(10, 5));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(14, 24));

        world.drones.add(new Drone(14, 24, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(5, 16));

        world.drones.add(new Drone(5, 16, d2p, false));


        Assets.randomSong();

    }
}
