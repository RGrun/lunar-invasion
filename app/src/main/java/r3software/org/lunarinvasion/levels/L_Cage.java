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
 * Created by richard on 8/27/15.
 *
 * Cage Level
 */
public class L_Cage implements Level {

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

        // "Cage"

        // UB 4x2
        world.platforms.add(new Platform_4X2(10, 31, false, 10, 0));
        world.platforms.add(new Platform_4X2(10, 9, false, 10, 0));


        // UB 2x2
        world.platforms.add(new Platform_2X2(3, 31, false, 10, 0));
        world.platforms.add(new Platform_2X2(17, 31, false, 10, 0));

        world.platforms.add(new Platform_2X2(3, 20, false, 10, 0));
        world.platforms.add(new Platform_2X2(10, 20, false, 10, 0));
        world.platforms.add(new Platform_2X2(17, 20, false, 10, 0));

        world.platforms.add(new Platform_2X2(3, 9, false, 10, 0));
        world.platforms.add(new Platform_2X2(17, 9, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(6, 31, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 31, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 27, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 24, true, 10, 0));

        world.platforms.add(new Platform_2X2(7, 21, true, 10, 0));
        world.platforms.add(new Platform_2X2(13, 21, true, 10, 0));
        world.platforms.add(new Platform_2X2(6, 18, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 18, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 16, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 13, true, 10, 0));

        world.platforms.add(new Platform_2X2(6, 9, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 9, true, 10, 0));

        //power ups
        world.powerUps.add(new ShieldPU(5, 26));
        world.powerUps.add(new WeaponPU(15, 26));
        world.powerUps.add(new ShieldPU(15, 13));
        world.powerUps.add(new WeaponPU(5, 13));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(7, 24));
        d1p.add(new Vector2(7, 28));
        d1p.add(new Vector2(3, 28));
        d1p.add(new Vector2(3, 24));

        world.drones.add(new Drone(3, 24, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(13, 28));
        d2p.add(new Vector2(13, 24));
        d2p.add(new Vector2(17, 24));
        d2p.add(new Vector2(17, 28));

        world.drones.add(new Drone(17, 28, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(3, 15));
        d3p.add(new Vector2(3, 11));
        d3p.add(new Vector2(7, 11));
        d3p.add(new Vector2(7, 15));

        world.drones.add(new Drone(7, 15, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(17, 11));
        d4p.add(new Vector2(17, 15));
        d4p.add(new Vector2(13, 15));
        d4p.add(new Vector2(13, 11));

        world.drones.add(new Drone(13, 11, d4p, false));


        Assets.randomSong();

    }
}
