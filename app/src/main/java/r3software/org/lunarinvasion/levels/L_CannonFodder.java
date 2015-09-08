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
 * Created by richard on 9/8/15.
 *
 * Cannon fodder level
 */
public class L_CannonFodder implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(5, 4,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(15, 36,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Cannon Fodder"

        // 6x6 angled platforms
        world.platforms.add(new Platform_Angled_6X6(11, 37, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(3, 29, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_6X6(17, 33, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_6X6(17, 27, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_6X6(7, 23, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_6X6(7, 17, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_6X6(13, 17, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(13, 23, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_6X6(3, 13, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_6X6(3, 7, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_6X6(17, 11, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_6X6(9, 3, Triangle.TRIANGLE_FACING.NW));


        //power ups
        world.powerUps.add(new WeaponPU(3, 25));
        world.powerUps.add(new WeaponPU(17, 15));
        world.powerUps.add(new ShieldPU(1, 33));
        world.powerUps.add(new ShieldPU(19, 7));


        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(10, 12));
        d1p.add(new Vector2(18, 20));
        d1p.add(new Vector2(10, 28));
        d1p.add(new Vector2(2, 20));

        world.drones.add(new Drone(2, 20, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(18, 20));
        d2p.add(new Vector2(10, 28));
        d2p.add(new Vector2(2, 20));
        d2p.add(new Vector2(10, 12));

        world.drones.add(new Drone(10, 12, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(10, 28));
        d3p.add(new Vector2(2, 20));
        d3p.add(new Vector2(10, 12));
        d3p.add(new Vector2(18, 20));

        world.drones.add(new Drone(18, 20, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(2, 20));
        d4p.add(new Vector2(10, 12));
        d4p.add(new Vector2(18, 20));
        d4p.add(new Vector2(10, 28));

        world.drones.add(new Drone(10, 28, d4p, true));


        Assets.randomSong();

    }
}
