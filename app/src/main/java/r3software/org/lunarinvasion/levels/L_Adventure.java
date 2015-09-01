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
 * Adventure level
 */
public class L_Adventure implements Level {

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

        // "Adventure"

        // UB 6x2
        world.platforms.add(new Platform_6X2(5.5f, 30, false, 10, 0));
        world.platforms.add(new Platform_6X2(14.5f, 30, false, 10, 0));
        world.platforms.add(new Platform_6X2(5.5f, 20, false, 10, 0));

        world.platforms.add(new Platform_6X2(14.5f, 20, false, 10, 0));
        world.platforms.add(new Platform_6X2(5.5f, 10, false, 10, 0));
        world.platforms.add(new Platform_6X2(14.5f, 10, false, 10, 0));


        //breakable 2x2 platforms
        world.platforms.add(new Platform_2X2(5, 25, true, 10, 0));
        world.platforms.add(new Platform_2X2(15, 25, true, 10, 0));

        world.platforms.add(new Platform_2X2(5, 15, true, 10, 0));
        world.platforms.add(new Platform_2X2(15, 15, true, 10, 0));

        //power ups
        world.powerUps.add(new HealthPU(1.5f, 20));
        world.powerUps.add(new HealthPU(18.5f, 20));
        world.powerUps.add(new WeaponPU(5, 37));
        world.powerUps.add(new WeaponPU(15, 5));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(15, 33));
        d1p.add(new Vector2(10, 33));

        world.drones.add(new Drone(10, 33, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(10, 33));
        d2p.add(new Vector2(5, 33));

        world.drones.add(new Drone(5, 33, d2p, true));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(5, 27.5f));
        d3p.add(new Vector2(10, 27.5f));

        world.drones.add(new Drone(10, 27.5f, d3p, true));

        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(10, 27.5f));
        d4p.add(new Vector2(15, 27.5f));

        world.drones.add(new Drone(15, 27.5f, d4p, true));

        List<Vector2> d5p = new ArrayList<>();
        d5p.add(new Vector2(15, 22.5f));
        d5p.add(new Vector2(10, 22.5f));

        world.drones.add(new Drone(10, 22.5f, d5p, true));

        List<Vector2> d6p = new ArrayList<>();
        d6p.add(new Vector2(10, 22.5f));
        d6p.add(new Vector2(5, 22.5f));

        world.drones.add(new Drone(5, 22.5f, d6p, true));

        List<Vector2> d7p = new ArrayList<>();
        d7p.add(new Vector2(5, 17.5f));
        d7p.add(new Vector2(10, 17.5f));

        world.drones.add(new Drone(10, 17.5f, d7p, false));

        List<Vector2> d8p = new ArrayList<>();
        d8p.add(new Vector2(10, 17.5f));
        d8p.add(new Vector2(15, 17.5f));

        world.drones.add(new Drone(15, 17.5f, d8p, false));

        List<Vector2> d9p = new ArrayList<>();
        d9p.add(new Vector2(15, 12.5f));
        d9p.add(new Vector2(10, 12.5f));

        world.drones.add(new Drone(10, 12.5f, d9p, false));

        List<Vector2> d10p = new ArrayList<>();
        d10p.add(new Vector2(10, 12.5f));
        d10p.add(new Vector2(5, 12.5f));

        world.drones.add(new Drone(5, 12.5f, d10p, false));

        List<Vector2> d11p = new ArrayList<>();
        d11p.add(new Vector2(5, 7));
        d11p.add(new Vector2(10, 7));

        world.drones.add(new Drone(10, 7, d11p, false
        ));

        List<Vector2> d12p = new ArrayList<>();
        d12p.add(new Vector2(10, 7));
        d12p.add(new Vector2(15, 7));

        world.drones.add(new Drone(15, 7, d12p, false));


        Assets.randomSong();

    }
}
