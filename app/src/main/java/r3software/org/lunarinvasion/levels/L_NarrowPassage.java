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
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Narrow Passage level
 */
public class L_NarrowPassage implements Level{

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
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Narrow Passage"

        //2x2 angled platforms
        /*world.platforms.add(new Platform_Angled_2X2(4, 27, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(16, 27, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_2X2(8, 23, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(12, 23, Triangle.TRIANGLE_FACING.NW));

        world.platforms.add(new Platform_Angled_2X2(8, 17, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(12, 17, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_2X2(4, 13, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(16, 13, Triangle.TRIANGLE_FACING.SE));
*/
        //6x6 angled

        world.platforms.add(new Platform_Angled_6X6(3, 37, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_6X6(17.5f, 37, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_6X6(3, 3, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_6X6(17.5f, 3, Triangle.TRIANGLE_FACING.NW));


        // UB 12x2
        world.platforms.add(new Platform_12X2(4, 20, false, 10, 90));
        world.platforms.add(new Platform_12X2(16, 20, false, 10, 90));

        // UB 4x2
        world.platforms.add(new Platform_4X2(8, 20, false, 10, 90));
        world.platforms.add(new Platform_4X2(12, 20, false, 10, 90));

        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 25, true, 10, 0));
        world.platforms.add(new Platform_2X2(10, 15, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(3, 34));
        world.powerUps.add(new WeaponPU(17, 34));
        world.powerUps.add(new ShieldPU(1.5f, 20));
        world.powerUps.add(new ShieldPU(18.5f, 20));
        world.powerUps.add(new WeaponPU(3, 6));
        world.powerUps.add(new WeaponPU(17, 6));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(18, 30));
        d1p.add(new Vector2(2, 30));

        world.drones.add(new Drone(2, 30, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(2, 10));
        d2p.add(new Vector2(18, 10));

        world.drones.add(new Drone(18, 10, d2p, false));


        Assets.randomSong();

    }
}
