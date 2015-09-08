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
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/30/15.
 *
 * Walled Off Level
 */
public class L_WalledOff implements Level {

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

        // "Walled Off"

        //2x2 angled platforms
        /*world.platforms.add(new Platform_Angled_2X2(10, 39, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(10, 36, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(10, 22, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(6, 20, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(14, 20, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(10, 18, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(10, 4, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(10, 1, Triangle.TRIANGLE_FACING.NW));*/

        //4x4 angled
        world.platforms.add(new Platform_Angled_4X4(18, 38, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_4X4(5, 23, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_4X4(15, 17, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(2, 2, Triangle.TRIANGLE_FACING.NE));

        //6x6 angled
        world.platforms.add(new Platform_Angled_6X6(17, 3, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_6X6(3, 37, Triangle.TRIANGLE_FACING.SE));

        // UB 12x2
        world.platforms.add(new Platform_12X2(10, 29, false, 10, 90));
        world.platforms.add(new Platform_12X2(10, 11, false, 10, 90));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(10, 20, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(5, 36));
        world.powerUps.add(new ShieldPU(7, 26));
        world.powerUps.add(new ShieldPU(13, 14));
        world.powerUps.add(new WeaponPU(15, 4));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(2, 20));
        d1p.add(new Vector2(7, 8));
        d1p.add(new Vector2(2, 20));
        d1p.add(new Vector2(7, 32));

        world.drones.add(new Drone(7, 32, d1p, false));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(18, 20));
        d2p.add(new Vector2(13, 32));
        d2p.add(new Vector2(18, 20));
        d2p.add(new Vector2(13, 8));

        world.drones.add(new Drone(13, 8, d2p, true));


        Assets.randomSong();

    }
}
