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
 * Created by richard on 8/27/15.
 *
 * Honeypot level
 */
public class L_Honeypot implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(5, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(15, 37,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Honeypot"

        //4x4 angled platforms
        world.platforms.add(new Platform_Angled_4X4(6, 27, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(14, 27, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_4X4(6, 13, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_4X4(14, 13, Triangle.TRIANGLE_FACING.NW));

        // UB 4x2
        world.platforms.add(new Platform_4X2(4, 20, false, 10, 90));
        world.platforms.add(new Platform_4X2(16, 20, false, 10, 90));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 21, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 19, true, 10, 0));


        //power ups
        world.powerUps.add(new WeaponPU(7, 14));
        world.powerUps.add(new WeaponPU(13, 14));
        world.powerUps.add(new ShieldPU(6, 20));
        world.powerUps.add(new ShieldPU(14, 20));
        world.powerUps.add(new WeaponPU(7, 25));
        world.powerUps.add(new WeaponPU(13, 25));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(6, 32));
        d1p.add(new Vector2(14, 32));

        world.drones.add(new Drone(14, 32, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(14, 8));
        d2p.add(new Vector2(6, 8));

        world.drones.add(new Drone(6, 8, d2p, false));


        Assets.randomSong();

    }
}
