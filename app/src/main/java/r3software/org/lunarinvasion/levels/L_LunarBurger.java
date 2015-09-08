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
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 9/3/15.
 *
 * Lunar Burger level
 */
public class L_LunarBurger implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(10, 2,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(10, 38,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Lunar Burger"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(1, 29, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(1, 27, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(19.5f, 13, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(19.5f, 11, Triangle.TRIANGLE_FACING.SW));

        //4x4 angled platforms
        world.platforms.add(new Platform_Angled_4X4(2, 38, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(18, 38, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_4X4(2, 2, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_4X4(18, 2, Triangle.TRIANGLE_FACING.NW));


        // UB 12x2
        world.platforms.add(new Platform_12X2(10, 33, false, 10, 0));
        world.platforms.add(new Platform_12X2(10, 7, false, 10, 0));

        // UB 4x2
        world.platforms.add(new Platform_4X2(6, 20, false, 10, 0));
        world.platforms.add(new Platform_4X2(14, 20, false, 10, 0));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(9, 20, true, 10, 0));
        world.platforms.add(new Platform_2X2(11, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(17, 26));
        world.powerUps.add(new HealthPU(18, 20));
        world.powerUps.add(new ShieldPU(2, 20));
        world.powerUps.add(new WeaponPU(2, 13));


        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(6, 25));
        d1p.add(new Vector2(6, 29));

        world.drones.add(new Drone(6, 29, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(10, 25));
        d2p.add(new Vector2(10, 29));

        world.drones.add(new Drone(10, 29, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(14, 25));
        d3p.add(new Vector2(14, 29));

        world.drones.add(new Drone(14, 29, d3p, true));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(3, 23));
        d4p.add(new Vector2(17, 23));

        world.drones.add(new Drone(17, 23, d4p, false));

        List<Vector2> d5p = new ArrayList<>();
        d5p.add(new Vector2(17, 17));
        d5p.add(new Vector2(3, 17));

        world.drones.add(new Drone(3, 17, d5p, true));

        List<Vector2> d6p = new ArrayList<>();
        d6p.add(new Vector2(6, 15));
        d6p.add(new Vector2(6, 11));

        world.drones.add(new Drone(6, 11, d6p, false));

        List<Vector2> d7p = new ArrayList<>();
        d7p.add(new Vector2(10, 15));
        d7p.add(new Vector2(10, 11));

        world.drones.add(new Drone(10, 11, d7p, true));

        List<Vector2> d8p = new ArrayList<>();
        d8p.add(new Vector2(14, 15));
        d8p.add(new Vector2(14, 11));

        world.drones.add(new Drone(14, 11, d8p, false));



        Assets.randomSong();

    }
}
