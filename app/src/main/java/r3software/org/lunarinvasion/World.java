package r3software.org.lunarinvasion;

import android.util.FloatMath;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import r3software.org.lunarinvasion.engine.framework.Camera2D;
import r3software.org.lunarinvasion.engine.framework.Game;
import r3software.org.lunarinvasion.engine.framework.GameObject;
import r3software.org.lunarinvasion.engine.framework.Input;
import r3software.org.lunarinvasion.engine.framework.Screen;
import r3software.org.lunarinvasion.engine.impl.Texture;
import r3software.org.lunarinvasion.engine.math.Circle;
import r3software.org.lunarinvasion.engine.math.Geometry;
import r3software.org.lunarinvasion.engine.math.OverlapTester;
import r3software.org.lunarinvasion.engine.math.Rectangle;
import r3software.org.lunarinvasion.engine.math.Triangle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.levels.Level;
import r3software.org.lunarinvasion.platforms.Platform;
import r3software.org.lunarinvasion.platforms.Platform_2X2;
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.PowerUp;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;
import r3software.org.lunarinvasion.projectiles.Proj_Blue;
import r3software.org.lunarinvasion.projectiles.Proj_Green;
import r3software.org.lunarinvasion.projectiles.Proj_Missile;
import r3software.org.lunarinvasion.projectiles.Proj_Orange;
import r3software.org.lunarinvasion.projectiles.Proj_Red;
import r3software.org.lunarinvasion.projectiles.Projectile;
import r3software.org.lunarinvasion.screens.MainMenuScreen;

import static r3software.org.lunarinvasion.engine.math.OverlapTester.pointInCircle;
import static r3software.org.lunarinvasion.engine.math.Vector2.mul;
import static r3software.org.lunarinvasion.engine.math.Vector2.sub;

/**
 * Created by Jeff on 2/15/2015.
 *
 * This is the main game world class.
 */
public class World {

    public interface WorldListener {

    }

    public static final String TAG = "lunarinvasion";

    //While the world width is 23, the game world is only between x = 0 thru 20
    public static final float WORLD_WIDTH = 23;
    public static final float WORLD_HEIGHT = 40;

    //used to represent wall hits in projectile 'last hit' system
    public static final float LEFT_WALL = -1;
    public static final float RIGHT_WALL = -2;

    public static final float TARGET_DISTANCE_MAX = 4;

    //world states
    public static final int H_CANNON_AIM = 0;
    public static final int H_MOVE = 1;
    public static final int H_SHOOT = 2;
    public static final int A_CANNON_AIM = 3;
    public static final int A_MOVE = 4;
    public static final int A_SHOOT = 5;
    public static final int H_SELECT = 6;
    public static final int A_SELECT = 7;
    public static final int GAME_PAUSED = 8;
    public static final int HUMAN_WIN = 9;
    public static final int ALIEN_WIN = 10;

    //cannon IDs
    public static final int HUMAN_CANNON = 0;
    public static final int ALIEN_CANNON = 1;

    Vector2 humanTouchPoint; //point player touched
    Vector2 alienTouchPoint;

    //various lists
    public final List<Cannon> cannons;
    public final List<Projectile> projectiles;
    public final List<Platform> platforms;

    //drones
    public final List<Drone> drones;

    //power ups
    public final List<PowerUp> powerUps;

    //for the little animation that comes from bouncing shots
    public final List<ShotBounce> shotBounces = new ArrayList<>();

    //for teleport effects
    public final List<TeleportEffect> teleportEffects = new ArrayList<>();

    //shield effects
    public final List<ShieldEffect> shieldEffects = new ArrayList<>();

    public final WorldListener listener;

    public int state;

    //this is so that we can remember the last state
    //we were in when the game was paused
    public int prevState;

    public Screen screen;
    public Game game;

    public Camera2D cam;

    public GameObject humanTarget;
    public GameObject alienTarget;

    //should we render the targets?
    public boolean humanTargetOn;
    public boolean alienTargetOn;

    //bounds for move buttons
    public GameObject humanMoveButton;
    public GameObject alienMoveButton;

    //bounds for weapon buttons
    public GameObject humanWeaponButton;
    public GameObject alienWeaponButton;

    //energy bars
    public GameObject humanEnergyBar;
    public GameObject alienEnergyBar;

    //refs to cannons
    public Cannon hCannon;
    public Cannon aCannon;

    //rectangles for weapon selection
    public GameObject hOrgangeBox = new GameObject(10, 16, 4, 4);
    public GameObject hGreenBox = new GameObject(14, 16, 4, 4);
    public GameObject hMissileBox = new GameObject(18, 16, 4, 4);
    public GameObject hRedBox = new GameObject(10, 12, 4, 4);
    public GameObject hBlueBox = new GameObject(14, 12, 4, 4);

    public GameObject aOrangeBox = new GameObject(10, 24, 4, 4);
    public GameObject aGreenBox = new GameObject(14, 24, 4, 4);
    public GameObject aMissileBox = new GameObject(18, 24, 4, 4);
    public GameObject aRedBox = new GameObject(10, 28, 4, 4);
    public GameObject aBlueBox = new GameObject(14, 28, 4, 4);

    // "gear" pause button
    public GameObject pauseButton = new GameObject(21.655f, 19.95f, 2, 2);

    //other pause menu buttons
    public GameObject resumeButton = new GameObject((WORLD_WIDTH - 3) / 2, (WORLD_HEIGHT / 2) + 1,
            15, 3.5f);
    public GameObject quitButton = new GameObject((WORLD_WIDTH) / 2, (WORLD_HEIGHT / 2) - 3,
            7, 3f);
    public GameObject soundToggleButton = new GameObject((WORLD_WIDTH / 2) + 5, (WORLD_HEIGHT / 2) - 6,
            7, 2.5f);

    // win menu buttons
    public GameObject winQuitButton = new GameObject(((WORLD_WIDTH - 3) / 2) - 2, (WORLD_HEIGHT / 2) - 2,
            8, 3.5f);
    public GameObject winPlayAgainButton = new GameObject(((WORLD_WIDTH - 3) / 2), (WORLD_HEIGHT / 2) - 6,
            20, 2.5f);

    //random
    public Random rand;


    // pointer to current game level
    public Level curLevel;

    // randomized background texture for this level
    public Texture background;

    public World(WorldListener listener, Game game,
                 Screen screen, Camera2D cam,
                 Level gameLevel) {

        this.cam = cam;

        this.rand = new Random();

        humanTouchPoint = new Vector2();
        alienTouchPoint = new Vector2();

        //ref to game system
        this.game = game;

        this.screen = screen;

        this.listener = listener;

        cannons = new ArrayList<>();

        // cannon setup moved to block set-up method

        projectiles = new ArrayList<>();

        powerUps = new ArrayList<>();

        drones = new ArrayList<>();

        //add platforms here
        platforms = new ArrayList<>();

        this.curLevel = gameLevel;

        //set up obstacle blocks and everything else
        curLevel.loadLevel(this);

        Cannon hCannon = cannons.get(HUMAN_CANNON);
        Cannon aCannon = cannons.get(ALIEN_CANNON);

        this.humanTarget = new GameObject(hCannon.pos().x,
                hCannon.pos().y + TARGET_DISTANCE_MAX, 1, 1);
        this.alienTarget = new GameObject(aCannon.pos().x,
                aCannon.pos().y - TARGET_DISTANCE_MAX, 1, 1);

        //button placement
        this.humanMoveButton = new GameObject(21.65f, 9.7f, 2, 3);
        this.alienMoveButton = new GameObject(21.65f, 30, 2, 3);

        this.humanWeaponButton = new GameObject(21.65f, 17.5f, 2, 3);
        this.alienWeaponButton = new GameObject(21.65f, 22.5f, 2, 3);

        //energy bar placement
        this.humanEnergyBar = new GameObject(21.65f, 13.7f, 2, 3);
        this.alienEnergyBar = new GameObject(21.65f, 26, 2, 3);

        this.humanTargetOn = true;
        this.alienTargetOn = true;

        this.background = Assets.randomBackground();

        Log.d(TAG, "Entering H_CANNON_AIM");
        this.prevState = -1;
        this.state = H_CANNON_AIM;

    }


    public void update(float deltaTime) {

        if(state != GAME_PAUSED &&
                state != HUMAN_WIN &&
                state != ALIEN_WIN) {

            checkIfCannonsDead();

            updateCannons(deltaTime);
            updateProjectiles(deltaTime);
            updateShotBounces(deltaTime);
            updateTeleportEffects(deltaTime);
            updateExplodingPlatforms(deltaTime);
            updateShieldEffects(deltaTime);
            updatePowerUps(deltaTime);
            updateDrones(deltaTime);
            checkDroneCollisions();
            checkCannonPowerUpCollisions();

        }


        switch (state) {
            case H_CANNON_AIM:
                updateHCannonAim();
                break;

            case H_MOVE:
                updateHCannonMove();
                break;

            case H_SELECT:
                updateHSelect();
                break;

            case H_SHOOT:
                updateHShoot();
                break;

            case A_CANNON_AIM:
                updateACannonAim();
                break;

            case A_MOVE:
                updateACannonMove();
                break;

            case A_SELECT:
                updateASelect();
                break;

            case A_SHOOT:
                updateAShoot();
                break;

            case GAME_PAUSED:
                updatePaused();
                break;

            case HUMAN_WIN:
                updateHumanWin();
                break;

            case ALIEN_WIN:
                updateAlienWin();
                break;

        }


    }

    private void checkIfCannonsDead() {
        //check human cannon
        if(hCannon.curState == Cannon.CANNON_STATE.DEAD &&
                hCannon.stateTime > 0.5f) {
            this.state = ALIEN_WIN;
        }

        //check alien cannon
        if(aCannon.curState == Cannon.CANNON_STATE.DEAD &&
                aCannon.stateTime > 0.5f) {
            this.state = HUMAN_WIN;
        }
    }

    private void updateHumanWin() {

        if(Assets.currentMusic != Assets.victoryShort) {
            Assets.changeMusic(Assets.victoryShort);
        }

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            Vector2 winTouchPoint = new Vector2(event.x, event.y);

            //translate screen coords to world coords
            winTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            winTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    winTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                resetLevel();
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(winQuitButton.bounds,
                    winTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                game.setScreen(new MainMenuScreen(game));
                Assets.randomSong();
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(winPlayAgainButton.bounds,
                    winTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                resetLevel();
                break;
            }

        }

    }

    private void updateAlienWin() {

        if(Assets.currentMusic != Assets.victoryShort) {
            Assets.changeMusic(Assets.victoryShort);
        }

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            Vector2 winTouchPoint = new Vector2(event.x, event.y);

            //translate screen coords to world coords
            winTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            winTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    winTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                resetLevel();
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(winQuitButton.bounds,
                    winTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                game.setScreen(new MainMenuScreen(game));
                Assets.randomSong();
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(winPlayAgainButton.bounds,
                    winTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                resetLevel();
                break;
            }

        }

    }

    private void resetLevel() {
        cannons.clear();
        projectiles.clear();
        drones.clear();
        platforms.clear();
        shotBounces.clear();
        powerUps.clear();

        curLevel.loadLevel(this);

        this.state = H_CANNON_AIM;
        game.getInput().getTouchEvents().clear();

    }


    private void checkCannonPowerUpCollisions() {
        for(int i = 0; i < powerUps.size(); i++) {
            PowerUp pup = powerUps.get(i);

            //check against human cannon
            if (OverlapTester.overlapCircleRectangle(hCannon.cannonCircle, pup.bounds)) {
                if (pup instanceof ShieldPU) {

                    hCannon.enableShield();
                    powerUps.remove(i);
                    break;


                } else if (pup instanceof WeaponPU) {


                    if (((WeaponPU) pup).curState == WeaponPU.STATE.PRIMARY) {

                        WeaponPU.CONTENTS payload = ((WeaponPU) pup).contents;
                        float test = rand.nextFloat();
                        int amount;

                        if (test <= 0.8f) {
                            amount = 1;
                        } else {
                            amount = 2;
                        }


                        hCannon.addAmmo(payload, amount);
                        ((WeaponPU) pup).pickup(HUMAN_CANNON, amount);
                        break;

                    }

                    if (((WeaponPU) pup).curState == WeaponPU.STATE.SECONDARY) {
                        if (((WeaponPU) pup).stateTime > WeaponPU.MAX_TIME) {
                            powerUps.remove(i);
                        }
                    }

                } else if (pup instanceof HealthPU) {

                    hCannon.heal(10);
                    powerUps.remove(i);
                    break;
                }
                //check against alien cannon
            } else if(OverlapTester.
                    overlapCircleRectangle(aCannon.cannonCircle, pup.bounds)) {
                if(pup instanceof ShieldPU) {

                    aCannon.enableShield();
                    powerUps.remove(i);
                    break ;


                } else if (pup instanceof WeaponPU) {

                    if(((WeaponPU) pup).curState == WeaponPU.STATE.PRIMARY) {

                        WeaponPU.CONTENTS payload = ((WeaponPU) pup).contents;
                        float test = rand.nextFloat();
                        int amount;

                        if(test <= 0.8f) {
                            amount = 1;
                        } else {
                            amount = 2;
                        }

                        aCannon.addAmmo(payload, amount);
                        ((WeaponPU) pup).pickup(ALIEN_CANNON, amount);
                        break;

                    }

                    if(((WeaponPU) pup).curState == WeaponPU.STATE.SECONDARY) {
                        if (((WeaponPU) pup).stateTime > WeaponPU.MAX_TIME) {
                            powerUps.remove(i);
                        }
                    }

                } else if (pup instanceof HealthPU) {
                    aCannon.heal(10);
                    powerUps.remove(i);
                    break;

                }
            }
        }
    }

    private void updateExplodingPlatforms(float deltaTime) {
        for(int i = 0; i < platforms.size(); i++) {
            Platform ptfm = platforms.get(i);

            if(ptfm.curState == Platform.PLATFORM_STATE.EXPLODING) {
                ptfm.update(deltaTime);
            }

            if(ptfm.curState == Platform.PLATFORM_STATE.EXPLODING &&
                    ptfm.stateTime >= 0.5f) {
                platforms.remove(i);
            }
        }

    }

    private void updatePowerUps(float deltaTime) {
        for(int i = 0; i < powerUps.size(); i++) {

            PowerUp pup = powerUps.get(i);

            if(pup instanceof WeaponPU) {
                ((WeaponPU) pup).update(deltaTime);

                if(((WeaponPU) pup).curState == WeaponPU.STATE.SECONDARY)
                    if (((WeaponPU) pup).stateTime > WeaponPU.MAX_TIME) {
                        powerUps.remove(i);
                        i = 0;
                    }
            }
        }
    }

    public void updateDrones(float deltaTime) {
        for(int i = 0; i < drones.size(); i++) {

            Drone dr = drones.get(i);

            dr.update(deltaTime);

            if(dr.curState == Drone.STATE.EXPLODING) {
                if(dr.stateTime > 0.5f) {

                    //check for payload to drop
                    float payload = rand.nextFloat();


                    if(payload <= 0.11f) {
                        powerUps.add(new
                                ShieldPU(dr.pos().x, dr.pos().y));

                        Assets.playSound(Assets.satellite_destory_pu);

                    } else if(payload > 0.11f && payload <= 0.22f) {
                        powerUps.add(new
                                WeaponPU(dr.pos().x, dr.pos().y));
                        Assets.playSound(Assets.satellite_destory_pu);

                    } else if(payload > 0.22f && payload <= 0.33f) {
                        powerUps.add(new
                                HealthPU(dr.pos().x, dr.pos().y));
                        Assets.playSound(Assets.satellite_destory_pu);


                    } else if (payload > 0.33f) {
                        //drop nothing
                        Assets.playSound(Assets.satellite_destroy_no_pu);
                    }

                    drones.remove(i);
                }
            }

        }

    }

    public void updateCannons(float deltaTime) {
        for(int i = 0; i < cannons.size(); i++) {
            cannons.get(i).update(deltaTime);
        }
    }

    public void updateShotBounces(float deltaTime) {
        for(int i = 0; i < shotBounces.size(); i++) {
            shotBounces.get(i).update(deltaTime);
        }

        //remove shotbounces that have been in too long
        for(int i = 0; i < shotBounces.size(); i++) {
            ShotBounce thisBounce = shotBounces.get(i);
            if(thisBounce.existedTime > ShotBounce.EXISTS_TIME) {
                shotBounces.remove(i);
            }
        }
    }

    private void updateTeleportEffects(float deltaTime) {
        for(int i = 0; i < teleportEffects.size(); i++) {
            teleportEffects.get(i).update(deltaTime);
        }

        //remove teleports that have been in too long
        for(int i = 0; i < teleportEffects.size(); i++) {
            TeleportEffect thisEffect = teleportEffects.get(i);
            if(thisEffect.existedTime > TeleportEffect.EXISTS_TIME) {
                teleportEffects.remove(i);
            }
        }
    }

    private void updateShieldEffects(float deltaTime) {
        for(int i = 0; i < shieldEffects.size(); i++) {
            shieldEffects.get(i).update(deltaTime);
        }

        //remove shields that have been in too long
        for(int i = 0; i < shieldEffects.size(); i++) {
            ShieldEffect thisEffect = shieldEffects.get(i);
            if(thisEffect.existedTime > ShieldEffect.EXISTS_TIME) {
                shieldEffects.remove(i);
            }
        }
    }

    private void updateHCannonAim() {

        /*if(playTurnStartSound) {
            Assets.playSound(Assets.beginTurn);
            playTurnStartSound = false;
        }*/

        Cannon cannon = cannons.get(HUMAN_CANNON);  //human cannon is index 0

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            humanTouchPoint.set(event.x, event.y);

            //translate screen coords to world coords
            humanTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            humanTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            if(event.type == Input.TouchEvent.TOUCH_UP) {

                Log.i(TAG, "X: " + humanTouchPoint.x + " Y: " + humanTouchPoint.y);
            }

            //check for move button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                && OverlapTester.pointInRectangle(humanMoveButton.bounds, humanTouchPoint)) {
                game.getInput().getTouchEvents().clear();
                state = H_MOVE;
                break;
            }

            //check for weapon button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(humanWeaponButton.bounds,
                    humanTouchPoint)) {
                game.getInput().getTouchEvents().clear();
                state = H_SELECT;
                break;
            }

            // check for pause button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    humanTouchPoint)) {
                Assets.playSound(Assets.menuSelect);
                game.getInput().getTouchEvents().clear();
                prevState = H_CANNON_AIM;
                state = GAME_PAUSED;
                break;
            }



            //do not allow position to update if the user touches outside
            //the game area
            if(humanTouchPoint.x < WORLD_WIDTH - 3) {
                //check to see if we should show the target again
                if(event.type == Input.TouchEvent.TOUCH_DOWN) {
                    humanTargetOn = true;
                }

                //test distance from cannon to point to see if it's
                //past a max distance. If so, truncate
                Vector2 distFromCannon = sub(humanTouchPoint, cannon.pos());

                if(distFromCannon.len() > TARGET_DISTANCE_MAX) {

                    Vector2 A = new Vector2(cannon.pos());
                    Vector2 C = new Vector2(humanTouchPoint);

                    Vector2 B = calculateOvershootPoint(A, C);


                    humanTarget.setPosition(B);
                    cannon.setTarget(B);

                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        Log.d(TAG, "Entering H_SHOOT");
                        fire(); //fire switches state to shoot
                    }

                }
                if(distFromCannon.len() < TARGET_DISTANCE_MAX) {

                    humanTarget.setPosition(humanTouchPoint); //for debugging
                    cannon.setTarget(humanTouchPoint);

                    if (event.type == Input.TouchEvent.TOUCH_UP) {
                        Log.d(TAG, "Entering H_SHOOT");
                        fire(); //fire switches state to shoot
                    }

                }

            }

        }

    }

    private Vector2 calculateOvershootPoint(Vector2 A, Vector2 C) {

        float maxDistance = TARGET_DISTANCE_MAX;


        float topHalfX = maxDistance * (C.x - A.x);
        float bottomHalfX = (float) Math.sqrt(((A.x - C.x) * (A.x - C.x)) +
                (A.y - C.y) * (A.y - C.y));

        float Bx = A.x + (topHalfX / bottomHalfX);

        float topHalfY = maxDistance * (C.y - A.y);
        float bottomHalfY = (float) Math.sqrt(((A.x - C.x) * (A.x - C.x)) +
                (A.y - C.y) * (A.y - C.y));

        float By = A.y + (topHalfY / bottomHalfY);

        return new Vector2(Bx, By);

    }

    public void updateHCannonMove() {
        Cannon cannon = cannons.get(HUMAN_CANNON);  //human cannon is index 0
        Rectangle bounds = new Rectangle(cannon.bounds);
        Vector2 testPoint = new Vector2();

        boolean invalidMove = false;

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {

            Input.TouchEvent event = touchEvents.get(i);

            if(event.type != Input.TouchEvent.TOUCH_UP) continue;

            testPoint.set(event.x, event.y);

            //translate screen coords to world coords
            testPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            testPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;


            bounds.move(testPoint);

            // check for pause button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    testPoint)) {
                Assets.playSound(Assets.menuSelect);
                game.getInput().getTouchEvents().clear();
                prevState = H_MOVE;
                state = GAME_PAUSED;
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(humanMoveButton.bounds, testPoint)) {
                game.getInput().getTouchEvents().clear();
                state = H_CANNON_AIM;
                return;
            }

            //test new overlap against all platforms
            for(int j = 0; j < platforms.size(); j++) {

                Platform ptfm = platforms.get(j);

                //special check case for angled platforms
                if(ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2 ||
                        ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4 ||
                        ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6) {

                    if(!checkCannonAngledPtfmTeleportSafety(ptfm, cannon, testPoint)) {
                        invalidMove = true;
                    }

                } else if(OverlapTester.overlapRectangles(bounds, ptfm.bounds)) {
                    invalidMove = true;
                }
            }

            //check to see if point is outside game area
            if(testPoint.x > WORLD_WIDTH - 3) {
                invalidMove = true;
            }

            if(!invalidMove && event.type == Input.TouchEvent.TOUCH_UP) {
                cannon.teleport(testPoint, teleportEffects);
                game.getInput().getTouchEvents().clear();
                state = A_CANNON_AIM;
            } else if(invalidMove) {
                Assets.playSound(Assets.cantGoHere);
                game.getInput().getTouchEvents().clear();
                state = H_CANNON_AIM;
            }

            invalidMove = false;
        }

    }

    public void updateHSelect() {
        Cannon cannon = cannons.get(HUMAN_CANNON);  //human cannon is index 0

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            humanTouchPoint.set(event.x, event.y);

            //translate screen coords to world coords
            humanTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            humanTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            // check for pause button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    humanTouchPoint)) {
                Assets.playSound(Assets.menuSelect);
                game.getInput().getTouchEvents().clear();
                prevState = H_SELECT;
                state = GAME_PAUSED;
                break;
            }

            //did the user select a new weapon?
            if(OverlapTester.pointInRectangle(hOrgangeBox.bounds, humanTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.ORANGE)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = H_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(hGreenBox.bounds, humanTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.GREEN)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = H_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(hMissileBox.bounds, humanTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.MISSILE)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = H_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(hRedBox.bounds, humanTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.RED)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = H_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(hBlueBox.bounds, humanTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.BLUE)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = H_CANNON_AIM;
                    }
                }
            }

            //check for weapon button press
            if (event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(humanWeaponButton.bounds,
                    humanTouchPoint)) {
                game.getInput().getTouchEvents().clear();
                state = H_CANNON_AIM;
                break;
            }
        }
    }

    public void updateHShoot() {

        checkCollisions();

        if(projectiles.size() == 0) {

            Log.d(TAG, "Entering A_CANNON_AIM");
            game.getInput().getTouchEvents().clear();

            Cannon cannon = cannons.get(HUMAN_CANNON);


            if(cannon.currentTeleportEnergy < cannon.TELEPORT_DISTANCE_MAX) {
                //give 5% of total energy back each turn
                cannon.currentTeleportEnergy += cannon.FIFTEEN_PERCENT_OF_ENERGY;


                //do not allow > 100%
                if(cannon.currentTeleportEnergy > cannon.TELEPORT_DISTANCE_MAX) {
                    cannon.currentTeleportEnergy = cannon.TELEPORT_DISTANCE_MAX;
                }

                cannon.energyRatio = cannon.currentTeleportEnergy /
                        cannon.TELEPORT_DISTANCE_MAX;
            }

            state = A_CANNON_AIM;
        }

    }

    public void updateACannonAim() {

       /* if(playTurnStartSound) {
            Assets.playSound(Assets.beginTurn);
            playTurnStartSound = false;
        }*/

        Cannon cannon = cannons.get(ALIEN_CANNON); //alien cannon is index 1

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            alienTouchPoint.set(event.x, event.y);

            //translate screen coords to world coords
            alienTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            alienTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            //check for move button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(alienMoveButton.bounds, alienTouchPoint)) {
                game.getInput().getTouchEvents().clear();
                state = A_MOVE;
                break;
            }

            //check for weapon button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(alienWeaponButton.bounds,
                    alienTouchPoint)) {
                game.getInput().getTouchEvents().clear();
                state = A_SELECT;
                break;
            }

            // check for pause button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    alienTouchPoint)) {
                Assets.playSound(Assets.menuSelect);
                game.getInput().getTouchEvents().clear();
                prevState = A_CANNON_AIM;
                state = GAME_PAUSED;
                break;
            }



            //do not allow position to update if the user touches outside
            //the game area
            if(alienTouchPoint.x < WORLD_WIDTH - 3) {

                //check to see if we should show the target again
                if(event.type == Input.TouchEvent.TOUCH_DOWN) {
                    alienTargetOn = true;
                }

                //test distance from cannon to point to see if it's
                //past a max distance. If so, truncate
                Vector2 distFromCannon = sub(alienTouchPoint, cannon.pos());

                if (distFromCannon.len() > TARGET_DISTANCE_MAX) {

                    Vector2 A = new Vector2(cannon.pos());
                    Vector2 C = new Vector2(alienTouchPoint);

                    Vector2 B = calculateOvershootPoint(A, C);


                    alienTarget.setPosition(B);
                    cannon.setTarget(B);

                    if (event.type == Input.TouchEvent.TOUCH_UP) {
                        Log.d(TAG, "Entering A_SHOOT");
                        fire(); //fire switches state to shoot
                    }

                }
                if (distFromCannon.len() < TARGET_DISTANCE_MAX) {

                    alienTarget.setPosition(alienTouchPoint); //for debugging
                    cannon.setTarget(alienTouchPoint);

                    if (event.type == Input.TouchEvent.TOUCH_UP) {
                        Log.d(TAG, "Entering A_SHOOT");
                        fire(); //fire switches state to shoot
                    }

                }

            }


        }

    }

    public void updateACannonMove() {
        Cannon cannon = cannons.get(ALIEN_CANNON);  //alien cannon is index 1
        Rectangle bounds = new Rectangle(cannon.bounds);
        Vector2 testPoint = new Vector2();

        boolean invalidMove = false;

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type != Input.TouchEvent.TOUCH_UP) continue;

            testPoint.set(event.x, event.y);

            //translate screen coords to world coords
            testPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            testPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;


            bounds.move(testPoint);

            // check for pause button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    testPoint)) {
                Assets.playSound(Assets.menuSelect);
                game.getInput().getTouchEvents().clear();
                prevState = A_MOVE;
                state = GAME_PAUSED;
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(alienMoveButton.bounds, testPoint)) {
                game.getInput().getTouchEvents().clear();
                state = A_CANNON_AIM;
                return;
            }

            //test new overlap against all platforms
            for(int j = 0; j < platforms.size(); j++) {

                Platform ptfm = platforms.get(j);

                //special check case for angled platforms
                if(ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2 ||
                        ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4 ||
                        ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6) {

                    if(!checkCannonAngledPtfmTeleportSafety(ptfm, cannon, testPoint)) {
                        invalidMove = true;
                    }

                } else if(OverlapTester.overlapRectangles(bounds, platforms.get(j).bounds)) {
                    invalidMove = true;
                }
            }

            //check to see if point is outside game area
            if(testPoint.x > WORLD_WIDTH -3) {
                invalidMove = true;
            }

            if(!invalidMove && event.type == Input.TouchEvent.TOUCH_UP) {
                cannon.teleport(testPoint, teleportEffects);
                game.getInput().getTouchEvents().clear();
                state = H_CANNON_AIM;
            } else if(invalidMove) {
                Assets.playSound(Assets.cantGoHere);
                game.getInput().getTouchEvents().clear();
                state = A_CANNON_AIM;
            }

            invalidMove = false;
        }

    }

    public void updateASelect() {
        Cannon cannon = cannons.get(ALIEN_CANNON); //alien cannon is index 1

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            alienTouchPoint.set(event.x, event.y);

            //translate screen coords to world coords
            alienTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            alienTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            // check for pause button press
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    alienTouchPoint)) {
                Assets.playSound(Assets.menuSelect);
                game.getInput().getTouchEvents().clear();
                prevState = A_SELECT;
                state = GAME_PAUSED;
                break;
            }

            //did the user select a new weapon?
            if(OverlapTester.pointInRectangle(aOrangeBox.bounds, alienTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.ORANGE)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = A_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(aGreenBox.bounds, alienTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.GREEN)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = A_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(aMissileBox.bounds, alienTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.MISSILE)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = A_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(aRedBox.bounds, alienTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.RED)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = A_CANNON_AIM;
                    }
                }
            }

            if(OverlapTester.pointInRectangle(aBlueBox.bounds, alienTouchPoint)) {
                if(!cannon.setWeapon(Projectile.TYPE.BLUE)) {
                    Assets.playSound(Assets.error2);
                } else {
                    if(event.type == Input.TouchEvent.TOUCH_UP) {
                        state = A_CANNON_AIM;
                    }
                }
            }

            //check for weapon button press
            if (event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(alienWeaponButton.bounds,
                    alienTouchPoint)) {
                game.getInput().getTouchEvents().clear();
                state = A_CANNON_AIM;
                break;
            }
        }
    }


    public void updateAShoot() {

        checkCollisions();

        if(projectiles.size() == 0) {

            Log.d(TAG, "Entering H_CANNON_AIM");
            game.getInput().getTouchEvents().clear();

            Cannon cannon = cannons.get(ALIEN_CANNON);

            if(cannon.currentTeleportEnergy < cannon.TELEPORT_DISTANCE_MAX) {
                //give 5% of total energy back each turn
                cannon.currentTeleportEnergy += cannon.FIFTEEN_PERCENT_OF_ENERGY;


                //do not allow > 100%
                if(cannon.currentTeleportEnergy > cannon.TELEPORT_DISTANCE_MAX) {
                    cannon.currentTeleportEnergy = cannon.TELEPORT_DISTANCE_MAX;
                }
                cannon.energyRatio = cannon.currentTeleportEnergy / cannon.TELEPORT_DISTANCE_MAX;
            }

            state = H_CANNON_AIM;


        }

    }

    private void updatePaused() {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            Vector2 pausedTouchPoint = new Vector2(event.x, event.y);

            //translate screen coords to world coords
            pausedTouchPoint.x =
                    (event.x / (float) cam.glGraphics.getWidth() * WORLD_WIDTH);
            pausedTouchPoint.y =
                    (1 - event.y / (float) cam.glGraphics.getHeight()) * WORLD_HEIGHT;

            // return to previous game state
            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(pauseButton.bounds,
                    pausedTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                state = prevState;
                prevState = -1;
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(resumeButton.bounds,
                    pausedTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                state = prevState;
                prevState = -1;
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(quitButton.bounds,
                    pausedTouchPoint)) {
                Assets.playSound(Assets.menuClose);
                game.getInput().getTouchEvents().clear();
                game.setScreen(new MainMenuScreen(game));
                break;
            }

            if(event.type == Input.TouchEvent.TOUCH_UP
                    && OverlapTester.pointInRectangle(soundToggleButton.bounds,
                    pausedTouchPoint)) {
                game.getInput().getTouchEvents().clear();

                Settings.soundEnabled = !Settings.soundEnabled;
                if(Settings.soundEnabled) {
                    Assets.currentMusic.play();
                } else {
                    Assets.currentMusic.pause();
                }

                break;
            }


        }
    }


    public void checkCollisions() {

        checkCannonCollisions();
        checkWallCollisions();
        checkAngledPlatformCollisions();
        checkPlatformCollisions();
        checkPowerUpCollisions();
        checkDroneCollisions();

    }

    public void fire() {

        final float POWER_SCALE = 4f;

        Cannon hCannon = cannons.get(HUMAN_CANNON);
        Cannon aCannon = cannons.get(ALIEN_CANNON);

        if(state == H_CANNON_AIM) {


            Vector2 hCannonPos = hCannon.pos();
            float cannonAngle = hCannon.cannonAngle;

            Vector2 distFromCannon = sub(humanTarget.pos(), hCannonPos);

            float speedFromLen = distFromCannon.len() * POWER_SCALE;

            Log.d(TAG, "Speed From Len: " + speedFromLen);

            Projectile.TYPE projType = hCannon.curWeapon;

            Projectile newProj;

            switch(projType) {
                case ORANGE:
                    Assets.playSound(Assets.shotSound);
                  newProj = new Proj_Orange( hCannonPos.x, hCannonPos.y,
                          Projectile.PROJECTILE_WIDTH,
                          Projectile.PROJECTILE_HEIGHT,
                          1f, 100f, 100f, 0.5f);
                    break;
                case BLUE:
                    Assets.playSound(Assets.blue_shot);

                    newProj = new Proj_Blue( hCannonPos.x, hCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f, cannons.get(ALIEN_CANNON),
                            this);

                    hCannon.blueAmmo -= 1;
                    if(hCannon.blueAmmo <= 0) {
                        hCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                case GREEN:
                    Assets.playSound(Assets.green_shot);

                    newProj = new Proj_Green( hCannonPos.x, hCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f);

                    hCannon.greenAmmo -= 1;
                    if(hCannon.greenAmmo <= 0) {
                        hCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                case RED:
                    Assets.playSound(Assets.shotSound);

                    //red projectile moves faster than the others do
                    speedFromLen *= 1.3f;

                    newProj = new Proj_Red( hCannonPos.x, hCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 300f, 300f, 0.5f, projectiles);

                    hCannon.redAmmo -= 1;
                    if(hCannon.redAmmo <= 0) {
                        hCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                case MISSILE:
                    Assets.playSound(Assets.purple_shot);

                    newProj = new Proj_Missile( hCannonPos.x, hCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 20f, 0.5f,
                            new Vector2(humanTarget.pos()),
                            new Vector2(aCannon.pos()),
                            projectiles);

                    hCannon.missileAmmo -= 1;
                    if(hCannon.missileAmmo <= 0) {
                        hCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                default:
                    newProj = new Proj_Orange( hCannonPos.x, hCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f);

            }


            float radians = (cannonAngle + 90) * Vector2.TO_RADIANS;

            Vector2 exitVelocity = new Vector2(FloatMath.cos(radians)  * speedFromLen,
                    FloatMath.sin(radians) * speedFromLen );

            newProj.setVelocity(exitVelocity);

            projectiles.add(newProj);


            state = H_SHOOT;
        } else if(state == A_CANNON_AIM) {



            Vector2 aCannonPos = aCannon.pos();
            float cannonAngle = aCannon.cannonAngle;

            Vector2 distFromCannon = sub(alienTarget.pos(), aCannonPos);

            float speedFromLen = distFromCannon.len() * POWER_SCALE;

            Log.d(TAG, "Speed From Len: " + speedFromLen);

            Projectile.TYPE projType = aCannon.curWeapon;

            Projectile newProj;

            switch(projType) {
                case ORANGE:
                    Assets.playSound(Assets.shotSound);

                    newProj = new Proj_Orange( aCannonPos.x, aCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f);
                    break;
                case BLUE:
                    Assets.playSound(Assets.blue_shot);

                    newProj = new Proj_Blue( aCannonPos.x, aCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f, cannons.get(HUMAN_CANNON),
                            this);

                    aCannon.blueAmmo -= 1;
                    if(aCannon.blueAmmo <= 0) {
                        aCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                case GREEN:
                    Assets.playSound(Assets.green_shot);

                    newProj = new Proj_Green( aCannonPos.x, aCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f);

                    aCannon.greenAmmo -= 1;
                    if(aCannon.greenAmmo <= 0) {
                        aCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                case RED:
                    Assets.playSound(Assets.shotSound);

                    //red projectile moves faster than the others do
                    speedFromLen *= 1.3f;

                    newProj = new Proj_Red( aCannonPos.x, aCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f, projectiles);

                    aCannon.redAmmo -= 1;
                    if(aCannon.redAmmo <= 0) {
                        aCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                case MISSILE:
                    Assets.playSound(Assets.purple_shot);

                    newProj = new Proj_Missile( aCannonPos.x, aCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 20f, 0.5f,
                            new Vector2(alienTarget.pos()),
                            new Vector2(hCannon.pos()),
                            projectiles);

                    aCannon.missileAmmo -= 1;
                    if(aCannon.missileAmmo <= 0) {
                        aCannon.setWeapon(Projectile.TYPE.ORANGE);
                    }
                    break;
                default:
                    newProj = new Proj_Orange( aCannonPos.x, aCannonPos.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT,
                            1f, 100f, 100f, 0.5f);

            }

            float radians = (cannonAngle + 90) * Vector2.TO_RADIANS;

            Vector2 exitVelocity = new Vector2(FloatMath.cos(radians)  * speedFromLen,
                    FloatMath.sin(radians) * speedFromLen);

            newProj.setVelocity(exitVelocity);

            projectiles.add(newProj);


            state = A_SHOOT;

        }

    }

    private void updateProjectiles(float deltaTime) {

        for(int i = 0; i < projectiles.size(); i++) {

            Projectile proj = projectiles.get(i);

            //projectile off screen?
            //switch to timer later
            if (proj.position.x > WORLD_WIDTH) {
                projectiles.remove(i);
            } else if (proj.position.x < 0) {
                projectiles.remove(i);
            } else if (proj.position.y > WORLD_HEIGHT) {
                projectiles.remove(i);
            } else if (proj.position.y < 0) {
                projectiles.remove(i);
            }

            proj.update(deltaTime);

        }

        for(int i = 0; i < projectiles.size(); i++) {

            Projectile proj = projectiles.get(i);

            //handle different projectiles differently
            switch(proj.projType) {
                case BLUE:

                    if(((Proj_Blue)proj).curState ==
                            Proj_Blue.BLUE_STATE.EXPLODING) {
                        if(((Proj_Blue) proj).stateTime > 0.3f) {
                            projectiles.remove(i);
                            return;
                        }
                    }

                    break;

                case GREEN:
                    ((Proj_Green)proj).update(deltaTime, projectiles,
                            platforms, i);
                    break;

                case RED:
                    ((Proj_Red)proj).update(deltaTime, i);
                    break;

                case MISSILE:
                    ((Proj_Missile)proj).update(deltaTime, i);

                default:

            }

            //remove projectile if it's existed for 5 seconds and its fizzling
            if(proj.fizzleTime > 0.5f && proj.fizzleState == Projectile.FIZZLE_STATE.FIZZLING) {
                projectiles.remove(i);
            }

        }
    }


    private void checkCannonCollisions() {

        for(int i = 0; i < projectiles.size(); i++) {
            Projectile proj = projectiles.get(i);

            for(int j = 0; j < cannons.size(); j++) {
                Cannon cannon = cannons.get(j);

                //these are so the cannon can't hurt itself right when it shoots
                if(state == H_SHOOT && j == 0) continue;
                if(state == A_SHOOT && j == 1) continue;

                if (OverlapTester.overlapRectangles(proj.bounds, cannon.bounds)) {

                    projectiles.remove(i);

                    //handle hits differently depending on proj type
                    switch(proj.projType) {
                        case BLUE:
                            //blue shots handle damage in thier own class
                            break;

                        case RED:
                            //red shots deal more damage when hitting a target
                            cannon.damage(30);

                            break;

                        default:
                            cannon.damage(10);
                    }

                }

            }
        }

    }

    //reflecting against walls (walls do not actually exist in game world)
    private void checkWallCollisions() {

        for(int i = 0; i < projectiles.size(); i++) {
            Projectile proj = projectiles.get(i);

            if(proj.pos().x - (proj.bounds.width / 2) <= 0 ||
                    proj.pos().x + (proj.bounds.width / 2) >= WORLD_WIDTH - 3) {

                proj.position = proj.lastNonCollidingPosition;

                if(proj.pos().x > (WORLD_WIDTH - 3) / 2 &&
                        proj.idOfLastPlatformHit == RIGHT_WALL) {
                    continue;
                }

                if(proj.pos().x < (WORLD_WIDTH - 3) / 2 &&
                        proj.idOfLastPlatformHit == LEFT_WALL) {
                    continue;
                }


                if(proj.pos().x > (WORLD_WIDTH - 3) / 2) {
                    if(proj.projType != Projectile.TYPE.MISSILE) {
                        proj.setIdOfLastPlatformHit(RIGHT_WALL);
                    }
                } else {
                    if(proj.projType != Projectile.TYPE.MISSILE) {
                        proj.setIdOfLastPlatformHit(LEFT_WALL);
                    }
                }


                proj.reflectX();

                if(proj.projType == Projectile.TYPE.MISSILE ) {
                     ((Proj_Missile) proj).wallsHit++;
                }

                if(proj.projType == Projectile.TYPE.RED) {
                    //red projectiles speed up after they hit
                    //noinspection ConstantConditions
                    ((Proj_Red)proj).speedUp();

                    Assets.playSound(Assets.red_death_short);
                }

                if(proj.projType != Projectile.TYPE.RED) {
                    Assets.playSound(Assets.bump);
                }
                Vector2 spotHit = new Vector2(proj.pos());
                shotBounces.add(new ShotBounce(spotHit));
            }
        }

    }

    private void checkDroneCollisions() {

        for(int i = 0; i < drones.size(); i++) {
            Drone dr = drones.get(i);

            if(dr.curState == Drone.STATE.EXPLODING) continue;

            //check for projectile collisions
            for(int j = 0; j < projectiles.size(); j++) {
                Projectile proj = projectiles.get(j);

                if(OverlapTester.overlapCircles(dr.boundingCircle,
                        proj.boundingCircle)) {
                    dr.explode();
                    if(proj.projType == Projectile.TYPE.BLUE) {
                        checkBlueShotExplosionRadius((Proj_Blue) proj, false);
                        ((Proj_Blue) proj).explode(false);
                    }
                    if(proj.projType != Projectile.TYPE.BLUE) {
                        projectiles.remove(j);
                    }

                }

            }

            //check for cannon collisions
            for(int j = 0; j < cannons.size(); j++) {
                Cannon cannon = cannons.get(j);

                if(OverlapTester.overlapCircles(dr.boundingCircle,
                        cannon.cannonCircle)) {
                    dr.explode();
                    cannon.damage(10);
                }
            }

        }
    }

    private void checkAngledPlatformCollisions() {

        //break statement is for preventing projectiles from getting stuck
        //in a reflection loop on angled platforms.
        outer:
        for (int i = 0; i < platforms.size(); i++) {
            Platform ptfm = platforms.get(i);

            //filter out non-angled platforms
            if (ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2 ||
                    ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4 ||
                    ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6) {


                for (int j = 0; j < projectiles.size(); j++) {

                    Projectile proj = projectiles.get(j);

                    if(proj.idOfLastPlatformHit == ptfm.getId()) {
                        continue outer;
                    }

                    //projectile's velocity is modified in function below
                    //so is id of last hit var
                    if(projHitsTriangle(ptfm, proj, shotBounces)) {

                        if(proj.projType == Projectile.TYPE.RED) {
                            //red projectiles speed up after they hit
                            ((Proj_Red)proj).speedUp();
                            Assets.playSound(Assets.red_death_short);
                        } else {
                            Assets.playSound(Assets.bump);
                        }

                        continue outer;
                    } else {
                        //no platform was hit
                        proj.setLastNonCollidingPosition(proj.pos());
                    }
                }

            }
        }
    }

    private void checkPowerUpCollisions() {

        stopChecking:
        for(int i = 0; i < projectiles.size(); i++) {
            Projectile proj = projectiles.get(i);

            for(int j = 0; j < powerUps.size(); j++) {
                PowerUp pup = powerUps.get(j);

                if(OverlapTester.overlapCircleRectangle(proj.boundingCircle,
                        pup.bounds)) {
                    //power up is hit

                    if(pup instanceof ShieldPU) {

                        if(state == H_SHOOT) {
                            hCannon.enableShield();
                            powerUps.remove(j);
                            projectiles.remove(i);
                            break stopChecking;
                        } else if(state == A_SHOOT) {
                            aCannon.enableShield();
                            powerUps.remove(j);
                            projectiles.remove(i);
                            break stopChecking;
                        }

                    } else if (pup instanceof WeaponPU) {

                        if(((WeaponPU) pup).curState == WeaponPU.STATE.PRIMARY) {

                            WeaponPU.CONTENTS payload = ((WeaponPU) pup).contents;
                            float test = rand.nextFloat();
                            int amount;

                            if(test <= 0.8f) {
                                amount = 1;
                            } else {
                                amount = 2;
                            }

                            if (state == H_SHOOT) {

                                hCannon.addAmmo(payload, amount);
                                ((WeaponPU) pup).pickup(HUMAN_CANNON, amount);

                                projectiles.remove(i);
                                break stopChecking;
                            } else if (state == A_SHOOT) {

                                aCannon.addAmmo(payload, amount);
                                ((WeaponPU) pup).pickup(ALIEN_CANNON, amount);

                                projectiles.remove(i);
                                break stopChecking;
                            }
                        }

                        if(((WeaponPU) pup).curState == WeaponPU.STATE.SECONDARY) {
                            if (((WeaponPU) pup).stateTime > WeaponPU.MAX_TIME) {
                                powerUps.remove(j);
                            }
                        }

                    } else if (pup instanceof HealthPU) {
                        Assets.playSound(Assets.powerup);
                        if(state == H_SHOOT) {
                            hCannon.heal(10);
                            powerUps.remove(j);
                            projectiles.remove(i);
                            break stopChecking;
                        } else if(state == A_SHOOT) {
                            aCannon.heal(10);
                            powerUps.remove(j);
                            projectiles.remove(i);
                            break stopChecking;
                        }
                    }


                }

            }
        }
    }

    public void checkPowerUpCollisionsCannons() {
        for(int i = 0; i < cannons.size(); i++) {
            Cannon cannon = cannons.get(i);

            for(int j = 0; j < powerUps.size(); j++) {
                PowerUp pup = powerUps.get(j);

                if(OverlapTester.overlapCircleRectangle(cannon.cannonCircle,
                        pup.bounds)) {
                    //power up is hit

                    if(pup instanceof ShieldPU) {

                        if(state == H_MOVE || state == H_CANNON_AIM) {
                            cannon.enableShield();
                            powerUps.remove(j);
                        } else if(state == A_MOVE || state == A_CANNON_AIM) {
                            cannon.enableShield();
                            powerUps.remove(j);
                        }

                    } else if (pup instanceof WeaponPU) {


                        if(((WeaponPU) pup).curState == WeaponPU.STATE.PRIMARY) {

                            WeaponPU.CONTENTS payload = ((WeaponPU) pup).contents;
                            int amount = rand.nextInt(1) + 1;

                            if (state == H_SHOOT) {

                                hCannon.addAmmo(payload, amount);
                                ((WeaponPU) pup).pickup(HUMAN_CANNON, amount);

                                projectiles.remove(i);
                            } else if (state == A_SHOOT) {

                                aCannon.addAmmo(payload, amount);
                                ((WeaponPU) pup).pickup(ALIEN_CANNON, amount);

                                projectiles.remove(i);
                            }
                        }

                        if(((WeaponPU) pup).curState == WeaponPU.STATE.SECONDARY) {
                            if (((WeaponPU) pup).stateTime > WeaponPU.MAX_TIME) {
                                powerUps.remove(j);
                            }
                        }
                    } else if (pup instanceof HealthPU) {

                        if(state == H_SHOOT) {
                            hCannon.heal(10);
                            powerUps.remove(j);
                            projectiles.remove(i);
                        } else if(state == A_SHOOT) {
                            aCannon.heal(10);
                            powerUps.remove(j);
                            projectiles.remove(i);
                        }
                    }


                }

            }
        }
    }

    public void checkBlueShotExplosionRadius(Proj_Blue proj, boolean shipExplosion) {
        //check against all destroyable objects

        /*if (proj.curState == Proj_Blue.BLUE_STATE.EXPLODING || !shipExplosion) {
            return;
        }*/

        for(int i = 0; i < platforms.size(); i++) {
            Platform ptfm = platforms.get(i);
            if(ptfm.breakable) {
                if(OverlapTester.overlapCircleRectangle(proj.explosionCheck, ptfm.bounds)) {
                    if(ptfm.curState != Platform.PLATFORM_STATE.EXPLODING) {
                        ptfm.explode();
                    }
                }
            }
        }

        //check against all drones
        drones:
        for (int i = 0; i < drones.size(); i++) {
            Drone dr = drones.get(i);

            if (OverlapTester.overlapCircles(proj.explosionCheck, dr.boundingCircle)) {
                dr.explode();
            }
        }

        if (state == H_SHOOT && proj.curState != Proj_Blue.BLUE_STATE.EXPLODING) {
            if (OverlapTester.overlapCircleRectangle(proj.explosionCheck, aCannon.bounds)) {
                aCannon.damage(10);
            }
        }

        if (state == A_SHOOT && proj.curState != Proj_Blue.BLUE_STATE.EXPLODING) {
            if (OverlapTester.overlapCircleRectangle(proj.explosionCheck, hCannon.bounds)) {
                proj.explode(true);
                hCannon.damage(10);
            }
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    private void checkPlatformCollisions() {

        stopChecking:
        for(int i = 0; i < projectiles.size(); i++) {
            Projectile proj = projectiles.get(i);


            for(int j = 0; j < platforms.size(); j++) {
                Platform ptfm = platforms.get(j);

                //ignore angled platforms, or the 'last hit' platform
                if(ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2 ||
                        ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4 ||
                        ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6) {
                    continue;
                }

                if (OverlapTester.overlapCircleRectangle(proj.boundingCircle,
                        ptfm.bounds)) {

                    if(proj.projType != Projectile.TYPE.RED &&
                            proj.projType != Projectile.TYPE.BLUE) {
                        Assets.playSound(Assets.bump);
                    } else if (proj.projType != Projectile.TYPE.BLUE) {
                        Assets.playSound(Assets.red_death_short);
                    }

                    if(ptfm.breakable) {

                        if(proj.projType == Projectile.TYPE.BLUE) {
                            checkBlueShotExplosionRadius((Proj_Blue) proj, false);
                            if(((Proj_Blue) proj).curState !=
                                    Proj_Blue.BLUE_STATE.EXPLODING) {
                                ((Proj_Blue) proj).explode(false);
                            }
                        }

                        if(projectiles.size() == 0) {
                            break stopChecking;
                        }

                        if(proj.projType != Projectile.TYPE.BLUE) {
                            projectiles.remove(i);
                        }

                        //platform is hit
                        ptfm.health -= 10;

                        if(ptfm.health <= 0 &&
                                ptfm.curState != Platform.PLATFORM_STATE.EXPLODING) {
                            // platform is dead
                            // explode!
                            ptfm.explode();
                            if(ptfm.curState != Platform.PLATFORM_STATE.EXPLODING) {

                            }
                            //check for payload to drop
                            float payload = rand.nextFloat();

                            if(payload <= 0.10f) {
                                powerUps.add(new
                                        ShieldPU(ptfm.pos().x, ptfm.pos().y));
                            } else if(payload > 0.10f && payload <= 0.20f) {
                                powerUps.add(new
                                        WeaponPU(ptfm.pos().x, ptfm.pos().y));
                            } else if(payload > 0.20f && payload <= 0.30f) {
                                powerUps.add(new
                                        HealthPU(ptfm.pos().x, ptfm.pos().y));

                            } else if (payload > 0.30f) {
                                //drop nothing
                            }


                        }


                        proj.setIdOfLastPlatformHit(-1);

                        Vector2 spotHit = new Vector2(proj.pos());
                        shotBounces.add(new ShotBounce(spotHit));
                    } else {

                        //this check is so projectile wont get stuck in a reflection loop
                        if(proj.idOfLastPlatformHit != ptfm.getId()) {

                            Rectangle bounds = ptfm.bounds;

                            Circle projCircle = proj.boundingCircle;

                            if(projCircle.center.x >= bounds.bottomLeft.x &&
                                    projCircle.center.x <= bounds.topRight.x) {
                               //top/bottom hit

                                proj.position = proj.lastNonCollidingPosition;
                                proj.reflectY();

                                if(proj.projType == Projectile.TYPE.MISSILE ) {
                                    ((Proj_Missile) proj).wallsHit++;
                                }

                                if(proj.projType == Projectile.TYPE.RED) {
                                    //red projectiles speed up after they hit
                                    ((Proj_Red)proj).speedUp();
                                }

                                //mark platform as "last hit"
                                if(proj.projType != Projectile.TYPE.MISSILE) {
                                    proj.setIdOfLastPlatformHit(ptfm.getId());
                                }

                                Vector2 spotHit = new Vector2(proj.pos());
                                shotBounces.add(new ShotBounce(spotHit));


                            } else if (projCircle.center.y >= bounds.bottomLeft.y &&
                                    projCircle.center.y <= bounds.topRight.y) {
                                //left/right hit

                                proj.position = proj.lastNonCollidingPosition;
                                proj.reflectX();

                                if(proj.projType == Projectile.TYPE.MISSILE ) {
                                    ((Proj_Missile) proj).wallsHit++;
                                }

                                if(proj.projType == Projectile.TYPE.RED) {
                                    //red projectiles speed up after they hit
                                    ((Proj_Red)proj).speedUp();
                                }

                                //mark platform as "last hit"
                                if(proj.projType != Projectile.TYPE.MISSILE) {
                                    proj.setIdOfLastPlatformHit(ptfm.getId());
                                }

                                Vector2 spotHit = new Vector2(proj.pos());
                                shotBounces.add(new ShotBounce(spotHit));

                            } else {


                                proj.position = proj.lastNonCollidingPosition;

                                //corner hit
                                Log.d(TAG, "!!!!CORNER HIT!!!!");
                                Log.d(TAG, "!!!!CORNER HIT!!!!");
                                Log.d(TAG, "!!!!CORNER HIT!!!!");
                                Log.d(TAG, "!!!!CORNER HIT!!!!");

                                float angle = (float) Math.atan2(ptfm.pos().y - proj.pos().y,
                                        ptfm.pos().x - proj.pos().x);


                                //atan2 returns in radians
                                angle *= Vector2.TO_DEGREES;

                                if(angle < 0) {
                                    angle = 360 - (-angle);
                                }

                                float radians = angle * Vector2.TO_RADIANS;

                                float speedFromLen = proj.velocity().len();

                                Vector2 exitVelocity =
                                        new Vector2(FloatMath.cos(radians) * speedFromLen,
                                                FloatMath.sin(radians) * speedFromLen );

                                exitVelocity = exitVelocity.getReverse();

                                proj.setVelocity(exitVelocity);

                                if(proj.projType == Projectile.TYPE.MISSILE ) {
                                    ((Proj_Missile) proj).wallsHit++;
                                }

                                if(proj.projType == Projectile.TYPE.RED) {
                                    //red projectiles speed up after they hit
                                    ((Proj_Red)proj).speedUp();
                                }

                                //mark platform as "last hit"
                                if(proj.projType != Projectile.TYPE.MISSILE) {
                                    proj.setIdOfLastPlatformHit(ptfm.getId());
                                }



                                Vector2 spotHit = new Vector2(proj.pos());
                                shotBounces.add(new ShotBounce(spotHit));

                            }


                            break stopChecking;


                        }

                    }

                } else {
                    proj.setLastNonCollidingPosition(proj.pos());
                }

            }
        }

    }

    public static boolean projHitsTriangle(Platform ptfm, Projectile proj,
                                           List<ShotBounce> shotBounces) {

        Triangle tri;

        switch(ptfm.type) {
            case TYPE_ANGLED_2X2:
                tri = ((Platform_Angled_2X2)ptfm).triBounds;
                break;
            case TYPE_ANGLED_4X4:
                tri = ((Platform_Angled_4X4)ptfm).triBounds;
                break;
            case TYPE_ANGLED_6X6:
                tri = ((Platform_Angled_6X6)ptfm).triBounds;
                break;
            default:
                tri = ((Platform_Angled_2X2)ptfm).triBounds;
        }

        if(proj.idOfLastPlatformHit == ptfm.getId()) return false;

        //corner of triangle hit?
        if(pointInCircle(proj.boundingCircle, tri.A) ||
                pointInCircle(proj.boundingCircle, tri.B) ||
                pointInCircle(proj.boundingCircle, tri.C)) {

            proj.position = proj.lastNonCollidingPosition;

            float angle = (float) Math.atan2(ptfm.pos().y - proj.pos().y,
                    ptfm.pos().x - proj.pos().x);


            //atan2 returns in radians
            angle *= Vector2.TO_DEGREES;

            if (angle < 0) {
                angle = 360 - (-angle);
            }

            float radians = angle * Vector2.TO_RADIANS;

            float speedFromLen = proj.velocity().len();

            Vector2 exitVelocity =
                    new Vector2(FloatMath.cos(radians) * speedFromLen,
                            FloatMath.sin(radians) * speedFromLen);

            exitVelocity = exitVelocity.getReverse();

            proj.setVelocity(exitVelocity);

            if(proj.projType == Projectile.TYPE.MISSILE ) {
                ((Proj_Missile) proj).wallsHit++;
            }

            if(proj.projType != Projectile.TYPE.MISSILE) {
                proj.setIdOfLastPlatformHit(ptfm.getId());
            }

            Vector2 spotHit = new Vector2(proj.pos());

            if(shotBounces != null) {
                shotBounces.add(new ShotBounce(spotHit));
            }

            return true;

            //check against hypot
        } else if(Geometry.LineSegmentCircleIntersection(tri.A, tri.C,
                proj.pos(), proj.boundingCircle.radius)) {

            proj.position = proj.lastNonCollidingPosition;


            Vector2 U = mul(proj.velocity().dot(tri.hypotFacing), tri.hypotFacing);
            Vector2 W = sub(proj.velocity(), U);

            Vector2 newVelocity = sub(W, U);

            proj.velocity().set(newVelocity).add(tri.hypotFacing).mul(1.0f);

            if(proj.projType == Projectile.TYPE.MISSILE ) {
                ((Proj_Missile) proj).wallsHit++;
            }

            if(proj.projType != Projectile.TYPE.MISSILE) {
                proj.setIdOfLastPlatformHit(ptfm.getId());
            }

            Vector2 spotHit = new Vector2(proj.pos());

            if(shotBounces != null) {
                shotBounces.add(new ShotBounce(spotHit));
            }

            return true;

            //check against bottom
        } else if(Geometry.LineSegmentCircleIntersection(tri.B, tri.C,
                proj.pos(), proj.boundingCircle.radius)) {


            proj.position = proj.lastNonCollidingPosition;

            if(tri.triFacing == Triangle.TRIANGLE_FACING.NE) {
                proj.reflectY();
            } else if(tri.triFacing == Triangle.TRIANGLE_FACING.NW) {
                proj.reflectX();
            } else if(tri.triFacing == Triangle.TRIANGLE_FACING.SE) {
                proj.reflectX();
            } else if(tri.triFacing == Triangle.TRIANGLE_FACING.SW) {
                proj.reflectY();
            }

            if(proj.projType == Projectile.TYPE.MISSILE ) {
                ((Proj_Missile) proj).wallsHit++;
            }


            if(proj.projType != Projectile.TYPE.MISSILE) {
                proj.setIdOfLastPlatformHit(ptfm.getId());
            }

            Vector2 spotHit = new Vector2(proj.pos());
            if(shotBounces != null) {
                shotBounces.add(new ShotBounce(spotHit));
            }

            return true;
            //check against side
        } else if(Geometry.LineSegmentCircleIntersection(tri.B, tri.A,
                proj.pos(), proj.boundingCircle.radius)) {


            proj.position = proj.lastNonCollidingPosition;

            if(tri.triFacing == Triangle.TRIANGLE_FACING.NE) {
                proj.reflectX();
            } else if(tri.triFacing == Triangle.TRIANGLE_FACING.NW) {
                proj.reflectY();
            } else if(tri.triFacing == Triangle.TRIANGLE_FACING.SE) {
                proj.reflectY();
            } else if(tri.triFacing == Triangle.TRIANGLE_FACING.SW) {
                proj.reflectX();
            }

            if(proj.projType == Projectile.TYPE.MISSILE ) {
                ((Proj_Missile) proj).wallsHit++;
            }

            if(proj.projType != Projectile.TYPE.MISSILE) {
                proj.setIdOfLastPlatformHit(ptfm.getId());
            }

            Vector2 spotHit = new Vector2(proj.pos());
            if(shotBounces != null) {
                shotBounces.add(new ShotBounce(spotHit));
            }

            return true;
        }

        return false;

    }

    public boolean checkCannonAngledPtfmTeleportSafety(Platform ptfm, Cannon cannon,
                                                       Vector2 testPoint) {
        Triangle tri;

        Circle testCircle = new Circle(cannon.cannonCircle);

        testCircle.center.set(testPoint);

        switch(ptfm.type) {
            case TYPE_ANGLED_2X2:
                tri = ((Platform_Angled_2X2)ptfm).triBounds;
                break;
            case TYPE_ANGLED_4X4:
                tri = ((Platform_Angled_4X4)ptfm).triBounds;
                break;
            case TYPE_ANGLED_6X6:
                tri = ((Platform_Angled_6X6)ptfm).triBounds;
                break;
            default:
                tri = ((Platform_Angled_2X2)ptfm).triBounds;
        }

        //corner of triangle hit?
        if(pointInCircle(testCircle, tri.A) ||
                pointInCircle(testCircle, tri.B) ||
                pointInCircle(testCircle, tri.C)) {

            return false;
            //check against hypot
        } else if(Geometry.LineSegmentCircleIntersection(tri.A, tri.C,
                testCircle.center, testCircle.radius)) {

            return false;
            //check against bottom
        } else if(Geometry.LineSegmentCircleIntersection(tri.B, tri.C,
                testCircle.center, testCircle.radius)) {


            return false;
            //check against side
        } else if(Geometry.LineSegmentCircleIntersection(tri.B, tri.A,
                testCircle.center, testCircle.radius)) {

            return false;
        } else if (Triangle.pointInTriangle(testCircle.center, tri.A, tri.B, tri.C)) {
            return false;
        }

        return true;
    }

    public boolean safeToTeleport(Vector2 pos, Cannon cannon) {
        Rectangle bounds = new Rectangle(cannon.bounds);
        Circle testCircle = new Circle(cannon.cannonCircle);
        bounds.move(pos);
        testCircle.center.set(pos);

        //test new overlap against all platforms

        for(int j = 0; j < platforms.size(); j++) {

            Platform ptfm = platforms.get(j);

            if(ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_2X2 ||
                    ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_4X4 ||
                    ptfm.type == Platform.PLATFORM_TYPE.TYPE_ANGLED_6X6) {

                if(!checkCannonAngledPtfmTeleportSafety(ptfm, cannon, pos)) {
                    return false;
                }

            } else if(OverlapTester.overlapRectangles(bounds, ptfm.bounds)) {
                return false;
            }

        }

        return true;
    }

}
