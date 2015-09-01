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
 * Created by richard on 9/1/15.
 *
 * Highway level
 */
public class L_Highway implements Level {
    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(4, 3,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(17, 38,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Highway"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(11, 30, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(9, 28, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_2X2(11, 12, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_2X2(9, 10, Triangle.TRIANGLE_FACING.SW));


        // UB 6x2
        world.platforms.add(new Platform_6X2(4, 33, false, 10, 90));
        world.platforms.add(new Platform_6X2(16, 27, false, 10, 90));
        world.platforms.add(new Platform_6X2(4, 13, false, 10, 90));
        world.platforms.add(new Platform_6X2(16, 7, false, 10, 90));

        // UB 2x2
        world.platforms.add(new Platform_2X2(4, 21, false, 10, 0));
        world.platforms.add(new Platform_2X2(16, 19, false, 10, 0));


        //power ups
        world.powerUps.add(new HealthPU(4, 37));
        world.powerUps.add(new WeaponPU(16, 31));
        world.powerUps.add(new ShieldPU(10, 20));
        world.powerUps.add(new WeaponPU(4, 9));
        world.powerUps.add(new HealthPU(16, 3));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(4, 23));
        d1p.add(new Vector2(4, 29));

        world.drones.add(new Drone(4, 29, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(13, 22));
        d2p.add(new Vector2(7, 22));

        world.drones.add(new Drone(7, 22, d2p, true));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(7, 18));
        d3p.add(new Vector2(13, 18));

        world.drones.add(new Drone(13, 18, d3p, false));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(16, 17));
        d4p.add(new Vector2(16, 11));

        world.drones.add(new Drone(16, 11, d4p, false));


        Assets.randomSong();

    }

}
