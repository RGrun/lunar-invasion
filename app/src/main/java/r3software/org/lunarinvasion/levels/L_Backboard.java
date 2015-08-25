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
import r3software.org.lunarinvasion.platforms.Platform_8X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/25/15.
 *
 * Backboard Level
 */
public class L_Backboard implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 6,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 34,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                (float) 180, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Backboard"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 26, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 24, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(1, 21, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 19, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(1, 16, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 14, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19.5f, 26, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 24, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 21, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 19, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 16, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 14, Triangle.TRIANGLE_FACING.SW));

        //unbreakable 8x2 platforms
        world.platforms.add(new Platform_8X2(5, 38, false, 10, 0));
        world.platforms.add(new Platform_8X2(15, 38, false, 10, 0));
        world.platforms.add(new Platform_8X2(5, 2, false, 10, 0));
        world.platforms.add(new Platform_8X2(15, 2, false, 10, 0));

        //UB 4x2 pl
        world.platforms.add(new Platform_4X2(6, 30, false, 10, 0));
        world.platforms.add(new Platform_4X2(14, 30, false, 10, 0));

        world.platforms.add(new Platform_4X2(6, 10, false, 10, 0));
        world.platforms.add(new Platform_4X2(14, 10, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 30, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 28, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 26, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 24, true, 10, 0));

        world.platforms.add(new Platform_2X2(10, 16, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 14, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 12, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 10, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(10, 20));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(6, 27));
        d1p.add(new Vector2(6, 13));

        world.drones.add(new Drone(6, 13, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(14, 13));
        d2p.add(new Vector2(14, 27));

        world.drones.add(new Drone(14, 27, d2p, false));

        Assets.randomSong();

    }
}
