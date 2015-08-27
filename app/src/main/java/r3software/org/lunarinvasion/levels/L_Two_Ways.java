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
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;

/**
 * Created by richard on 8/27/15.
 *
 * Two Ways level
 */
public class L_Two_Ways implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(3, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(3, 37,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Two Ways"

        //6x6 angled platforms
        world.platforms.add(new Platform_Angled_6X6(15, 36, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_6X6(15f, 4, Triangle.TRIANGLE_FACING.NW));

        //4x4 angled platforms
        world.platforms.add(new Platform_Angled_4X4(13, 20, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_4X4(17, 20, Triangle.TRIANGLE_FACING.SW));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(2, 34, true, 10, 0));
        world.platforms.add(new Platform_2X2(4, 34, true, 10, 0));

        world.platforms.add(new Platform_2X2(2, 6, true, 10, 0));
        world.platforms.add(new Platform_2X2(4, 6, true, 10, 0));

        //power ups
        world.powerUps.add(new WeaponPU(12, 16));
        world.powerUps.add(new WeaponPU(18, 24));
        world.powerUps.add(new ShieldPU(5, 20));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(2, 26));
        d1p.add(new Vector2(9, 26));

        world.drones.add(new Drone(9, 26, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(9, 14));
        d2p.add(new Vector2(2, 14));

        world.drones.add(new Drone(2, 14, d2p, false));


        Assets.randomSong();

    }
}
