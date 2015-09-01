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
 * Created by richard on 9/1/15.
 *
 * Double Helix level
 */
public class L_Double_Helix implements Level {

    @Override
    public void loadLevel(World world) {//add cannons here
        //human cannon
        world.hCannon = new Cannon(3, 6,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                0, World.HUMAN_CANNON, world);
        world.cannons.add(world.hCannon);

        //alien cannon
        world.aCannon = new Cannon(17, 34,
                Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                180f, World.ALIEN_CANNON, world);
        world.cannons.add(world.aCannon);

        // "Double Helix"

        //2x2 angled platforms
        world.platforms.add(new Platform_Angled_2X2(9, 27, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_2X2(15, 27, Triangle.TRIANGLE_FACING.SE));

        world.platforms.add(new Platform_Angled_2X2(5, 13, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_2X2(11, 13, Triangle.TRIANGLE_FACING.NW));

        //4x4 angled

        world.platforms.add(new Platform_Angled_4X4(5, 34, Triangle.TRIANGLE_FACING.SW));
        world.platforms.add(new Platform_Angled_4X4(11, 34, Triangle.TRIANGLE_FACING.SW));


        world.platforms.add(new Platform_Angled_4X4(9, 6, Triangle.TRIANGLE_FACING.NE));
        world.platforms.add(new Platform_Angled_4X4(15, 6, Triangle.TRIANGLE_FACING.NE));

        world.platforms.add(new Platform_Angled_4X4(2, 38, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(18, 2, Triangle.TRIANGLE_FACING.NW));

        world.platforms.add(new Platform_Angled_4X4(8, 22, Triangle.TRIANGLE_FACING.NW));
        world.platforms.add(new Platform_Angled_4X4(8, 18, Triangle.TRIANGLE_FACING.SW));

        world.platforms.add(new Platform_Angled_4X4(12, 18, Triangle.TRIANGLE_FACING.SE));
        world.platforms.add(new Platform_Angled_4X4(12, 22, Triangle.TRIANGLE_FACING.NE));


        //power ups
        world.powerUps.add(new WeaponPU(12, 37));
        world.powerUps.add(new WeaponPU(4, 26));
        world.powerUps.add(new WeaponPU(16, 14));
        world.powerUps.add(new WeaponPU(8, 2));

        //drones
        List<Vector2> d1p = new ArrayList<>();
        d1p.add(new Vector2(17, 30));
        d1p.add(new Vector2(3, 30));

        world.drones.add(new Drone(3, 30, d1p, true));

        List<Vector2> d2p = new ArrayList<>();
        d2p.add(new Vector2(1, 27));
        d2p.add(new Vector2(1, 13));

        world.drones.add(new Drone(1, 13, d2p, false));

        List<Vector2> d3p = new ArrayList<>();
        d3p.add(new Vector2(19, 13));
        d3p.add(new Vector2(19, 27));

        world.drones.add(new Drone(19, 27, d3p, true));


        List<Vector2> d4p = new ArrayList<>();
        d4p.add(new Vector2(3, 10));
        d4p.add(new Vector2(17, 10));

        world.drones.add(new Drone(17, 10, d4p, false));


        Assets.randomSong();

    }
}
