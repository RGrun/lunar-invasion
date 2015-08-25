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
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/25/15.
 *
 * Rodeo Level
 */
public class L_Rodeo implements Level {

    @Override
    public void loadLevel(World world) {
        //add cannons here
        //human cannon
        world.hCannon = new Cannon(3, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(17, 37,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                (float) 180, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Rodeo"

        //6x6 angled
        world.platforms.add(new Platform_Angled_6X6(16, 4, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_6X6(4, 36, Triangle.TRIANGLE_FACING.SE));

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(9, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(9, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(11, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(11, 21, Triangle.TRIANGLE_FACING.NE));

        //6x2 platforms
        world.platforms.add(new Platform_6X2(6, 7, false, 100, 0));
        world.platforms.add(new Platform_6X2(14, 33, false, 100, 0));

        //breakable 2c2 platforms
        world.platforms.add(new Platform_2X2(6, 24, true, 10, 0));
        world. platforms.add(new Platform_2X2(6, 16, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 16, true, 10, 0));
        world.platforms.add(new Platform_2X2(14, 24, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(9, 3));
        world.powerUps.add(new WeaponPU(9, 37));
        world.powerUps.add(new ShieldPU(6, 20));
        world.powerUps.add(new WeaponPU(14, 20));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(3, 27));
        d1p.add(new Vector2(3, 13));
        d1p.add(new Vector2(17, 13));
        d1p.add(new Vector2(17, 27));

        world.drones.add(new Drone(3, 27, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(3, 13));
        d2p.add(new Vector2(17, 13));
        d2p.add(new Vector2(17, 27));
        d2p.add(new Vector2(3, 27));

        world.drones.add(new Drone(3, 13, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(17, 13));
        d3p.add(new Vector2(17, 27));
        d3p.add(new Vector2(3, 27));
        d3p.add(new Vector2(3, 13));

        world.drones.add(new Drone(17, 13, d3p, true));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(17, 27));
        d4p.add(new Vector2(3, 27));
        d4p.add(new Vector2(3, 13));
        d4p.add(new Vector2(17, 13));

        world.drones.add(new Drone(17, 27, d4p, false));

        Assets.randomSong();
    }
}
