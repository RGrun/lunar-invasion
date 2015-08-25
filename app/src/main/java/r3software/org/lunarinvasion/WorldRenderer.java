package r3software.org.lunarinvasion;

import javax.microedition.khronos.opengles.GL10;

import r3software.org.lunarinvasion.engine.framework.Animation;
import r3software.org.lunarinvasion.engine.framework.Camera2D;
import r3software.org.lunarinvasion.engine.framework.GameObject;
import r3software.org.lunarinvasion.engine.framework.SpriteBatcher;
import r3software.org.lunarinvasion.engine.framework.TextureRegion;
import r3software.org.lunarinvasion.engine.impl.GLGame;
import r3software.org.lunarinvasion.engine.impl.GLGraphics;
import r3software.org.lunarinvasion.engine.impl.Texture;
import r3software.org.lunarinvasion.engine.impl.Vertices;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.platforms.Platform;
import r3software.org.lunarinvasion.platforms.Platform_10X2;
import r3software.org.lunarinvasion.platforms.Platform_12X2;
import r3software.org.lunarinvasion.platforms.Platform_14X2;
import r3software.org.lunarinvasion.platforms.Platform_16X2;
import r3software.org.lunarinvasion.platforms.Platform_18X2;
import r3software.org.lunarinvasion.platforms.Platform_1X1;
import r3software.org.lunarinvasion.platforms.Platform_2X2;
import r3software.org.lunarinvasion.platforms.Platform_4X2;
import r3software.org.lunarinvasion.platforms.Platform_4X4;
import r3software.org.lunarinvasion.platforms.Platform_6X2;
import r3software.org.lunarinvasion.platforms.Platform_8X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_2X2;
import r3software.org.lunarinvasion.platforms.Platform_Angled_4X4;
import r3software.org.lunarinvasion.platforms.Platform_Angled_6X6;
import r3software.org.lunarinvasion.powerups.HealthPU;
import r3software.org.lunarinvasion.powerups.PowerUp;
import r3software.org.lunarinvasion.powerups.ShieldPU;
import r3software.org.lunarinvasion.powerups.WeaponPU;
import r3software.org.lunarinvasion.projectiles.Proj_Blue;
import r3software.org.lunarinvasion.projectiles.Projectile;

/**
 * Created by Jeff on 1/29/2015.
 *
 * This class renders the game world.
 */
public class WorldRenderer {

    public static final float FRUSTUM_WIDTH = 23;
    public static final float FRUSTUM_HEIGHT = 40;
    GLGraphics glGraphics;
    World world;
    Camera2D cam;

    //must be scaled and rendered outside the batcher
    Vertices teleportCircleVerts;
    TextureRegion teleportCircleRegion;
    static Texture circle;

    static Texture atlas;


    Vertices energyBarVerts;
    TextureRegion energyBarRegion;
    TextureRegion energyBarFrame;

    SpriteBatcher batcher;

    @SuppressWarnings("AccessStaticViaInstance")
    public WorldRenderer(GLGraphics glGraphics, SpriteBatcher batcher,
                         World world) {
        this.glGraphics = glGraphics;
        this.world = world;
        this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.batcher = batcher;

        this.circle = new Texture((GLGame)world.game, "Distance_Sprite_pwr2.png");
        this.atlas = new Texture((GLGame)world.game, "Sprite_Atlas_D.png");

        this.teleportCircleRegion = new TextureRegion(circle, 0, 0,
                1024, 1024);

        this.energyBarRegion = new TextureRegion(atlas, 22 * 32, 6 * 32, 2 * 32, 3 * 32);
        this.energyBarFrame = new  TextureRegion(atlas, 22 * 32, 6 * 32, 2 * 32, 3 * 32);

        this.energyBarVerts = new Vertices(glGraphics, 4, 6, false, true);
        this.energyBarVerts.setVertices(new float[]{
                -1, -1.5f, energyBarRegion.u1, energyBarRegion.v2,
                1, -1.5f, energyBarRegion.u2, energyBarRegion.v2,
                1, 1.5f, energyBarRegion.u2, energyBarRegion.v1,
                -1, 1.5f, energyBarRegion.u1, energyBarRegion.v1
        }, 0, 16);
        this.energyBarVerts.setIndices(new short[]{
                0, 1, 2, 2, 3, 0
        }, 0, 6);


        this.teleportCircleVerts = new Vertices(glGraphics, 4, 6, false, true);
        this.teleportCircleVerts.setVertices(new float[]{
                -5, -5, teleportCircleRegion.u1, teleportCircleRegion.v2,
                5, -5, teleportCircleRegion.u2, teleportCircleRegion.v2,
                5, 5, teleportCircleRegion.u2, teleportCircleRegion.v1,
                -5, 5, teleportCircleRegion.u1, teleportCircleRegion.v1
        }, 0, 16);
        this.teleportCircleVerts.setIndices(new short[]{
                0, 1, 2, 2, 3, 0
        }, 0, 6);

        //play music

        if(Settings.soundEnabled) {
            Assets.currentMusic.play();
        }

    }



    public void render() {

        cam.setViewportAndMatrices();
        renderBackground();
        renderObjects();

        //UI HERE
        renderButtons();
        renderEnergyBars();
        renderWeaponOverlay();

    }



    private void renderBackground() {
        Texture background = world.background;

        batcher.beginBatch(background);
        batcher.drawSprite(cam.position.x, cam.position.y, //uses camera's center as background center
                FRUSTUM_WIDTH, FRUSTUM_HEIGHT,
                Assets.background_3_UI_region);
        batcher.endBatch();
    }

    private void renderWeaponOverlay() {

        GL10 gl = glGraphics.getGL();

        if(world.state == World.H_SELECT) {

            Cannon hCannon = world.cannons.get(World.HUMAN_CANNON);

            gl.glEnable(GL10.GL_BLEND); //enable blending
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA); //blending equations

            int greenAmmo = hCannon.greenAmmo;
            int missileAmmo = hCannon.missileAmmo;
            int redAmmo = hCannon.redAmmo;
            int blueAmmo = hCannon.blueAmmo;

            batcher.beginBatch(Assets.atlas);

            TextureRegion keyFrame = Assets.humanMenu;

            batcher.drawSprite(14, 14, 12, 8, keyFrame);

            Assets.blackFont.drawText(batcher, greenAmmo + "", 15f, 15f, "world");
            Assets.blackFont.drawText(batcher, missileAmmo + "", 19f, 15f, "world");
            Assets.blackFont.drawText(batcher, redAmmo + "", 11f, 11f, "world");
            Assets.blackFont.drawText(batcher, blueAmmo + "", 15f, 11f, "world");

            batcher.endBatch();


        } else if(world.state == World.A_SELECT) {

            Cannon aCannon = world.cannons.get(World.ALIEN_CANNON);

            gl.glEnable(GL10.GL_BLEND); //enable blending
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA); //blending equations

            int greenAmmo = aCannon.greenAmmo;
            int missileAmmo = aCannon.missileAmmo;
            int redAmmo = aCannon.redAmmo;
            int blueAmmo = aCannon.blueAmmo;

            batcher.beginBatch(Assets.atlas);

            TextureRegion keyFrame = Assets.alienMenu;

            batcher.drawSprite(14, 26, 12, -8, keyFrame);

            Assets.blackFont.drawText(batcher, missileAmmo + "", 19f, 25f, true, "world");
            Assets.blackFont.drawText(batcher, greenAmmo + "", 15f, 25f, true, "world");
            Assets.blackFont.drawText(batcher, blueAmmo + "", 15f, 29f, true, "world");
            Assets.blackFont.drawText(batcher, redAmmo + "", 11f, 29f, true, "world");

            batcher.endBatch();

        }

    }

    private void renderEnergyBars() {

        GL10 gl = glGraphics.getGL();

        //bind texture and verts
        atlas.bind();
        energyBarVerts.bind();

        //grab human cannon
        Cannon hCannon = world.cannons.get(World.HUMAN_CANNON);

        gl.glLoadIdentity();
        gl.glTranslatef(world.humanEnergyBar.pos().x,
                world.humanEnergyBar.pos().y, 0);
        gl.glScalef(1, hCannon.energyRatio, 1);
        energyBarVerts.draw(GL10.GL_TRIANGLES, 0, 6);
        gl.glLoadIdentity();

        //grab alien cannon
        Cannon aCannon = world.cannons.get(World.ALIEN_CANNON);

        gl.glLoadIdentity();
        gl.glTranslatef(world.alienEnergyBar.pos().x,
                world.alienEnergyBar.pos().y, 0);
        gl.glScalef(1, aCannon.energyRatio, 1);
        energyBarVerts.draw(GL10.GL_TRIANGLES, 0, 6);
        gl.glLoadIdentity();

        //unbind and clean up
        energyBarVerts.unbind();


    }

    private void renderButtons() {

        GL10 gl = glGraphics.getGL();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        batcher.beginBatch(Assets.atlas);
        //draw move buttons in their various states
        //human move button
        if(world.state == World.H_CANNON_AIM || world.state == World.H_SELECT) {
            batcher.drawSprite(world.humanMoveButton.pos().x, world.humanMoveButton.pos().y,
                    2, 3, Assets.enabledButton);
        } else if(world.state == World.H_MOVE) {
            batcher.drawSprite(world.humanMoveButton.pos().x, world.humanMoveButton.pos().y,
                    2, 3, Assets.pressedButton);
        } else {
            batcher.drawSprite(world.humanMoveButton.pos().x, world.humanMoveButton.pos().y,
                    2, 3, Assets.disabledButton);
        }

        //alien move button
        if(world.state == World.A_CANNON_AIM || world.state == World.A_SELECT) {
            batcher.drawSprite(world.alienMoveButton.pos().x, world.alienMoveButton.pos().y,
                    -2, -3, Assets.enabledButton);
        } else if(world.state == World.A_MOVE) {
            batcher.drawSprite(world.alienMoveButton.pos().x, world.alienMoveButton.pos().y,
                    -2, -3, Assets.pressedButton);
        } else {
            batcher.drawSprite(world.alienMoveButton.pos().x, world.alienMoveButton.pos().y,
                    -2, -3, Assets.disabledButton);
        }

        //pause menu button

        batcher.drawSprite(world.pauseButton.pos().x, world.pauseButton.pos().y,
                2, 2, Assets.gearButton);

        batcher.endBatch();

    }


    private void renderObjects() {
        GL10 gl = glGraphics.getGL();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        //these are outside the batcher because I need to scale them around the origin
        //before I move them
        renderTeleportCircle();


        //render things from the first sprite sheet
        batcher.beginBatch(Assets.atlas);

        renderPowerUps();
        renderPlatforms();
        renderDrones();
        renderProjectiles();
        renderFizzlingProjectiles();
        renderProjectileTails();
        renderShotBounces();
        renderCannons();
        renderTargets();
        renderTeleportEffects();
        renderShieldEffects();
        renderBlueShotExplosion();
        batcher.endBatch();


        gl.glDisable(GL10.GL_BLEND);

    }

    private void renderFizzlingProjectiles() {
        for(Projectile proj : world.projectiles) {

            //only render fizzling projectiles
            if(proj.fizzleState != Projectile.FIZZLE_STATE.FIZZLING) continue;

            TextureRegion keyFrame = null;

            switch(proj.projType) {
                case ORANGE:
                    keyFrame = Assets.orangeExplosion.getKeyFrame(proj.fizzleTime,
                            Animation.ANIMATION_NONLOOPING);
                    break;
                case BLUE:
                    keyFrame = Assets.blueExplosion.getKeyFrame(proj.fizzleTime,
                            Animation.ANIMATION_NONLOOPING);
                    break;
                case GREEN:
                    keyFrame = Assets.greenExplosion.getKeyFrame(proj.fizzleTime,
                            Animation.ANIMATION_NONLOOPING);
                    break;
                case RED:
                    keyFrame = Assets.redExplosion.getKeyFrame(proj.fizzleTime,
                            Animation.ANIMATION_NONLOOPING);
                    break;
                case MISSILE:
                    keyFrame = Assets.purpleExplosion.getKeyFrame(proj.fizzleTime,
                            Animation.ANIMATION_NONLOOPING);
                    break;

                default:
                    keyFrame = Assets.orangeExplosion.getKeyFrame(proj.fizzleTime,
                            Animation.ANIMATION_NONLOOPING);
            }

            //for safety with pointer below
            if(keyFrame == null) {
                keyFrame = Assets.orangeExplosion.getKeyFrame(proj.fizzleTime,
                        Animation.ANIMATION_NONLOOPING);
            }

            batcher.drawSprite(proj.position.x, proj.position.y,
                    Projectile.PROJECTILE_WIDTH, Projectile.PROJECTILE_HEIGHT, keyFrame);

        }
    }

    private void renderBlueShotExplosion() {

        for(Projectile proj : world.projectiles) {

            if(proj.projType == Projectile.TYPE.BLUE) {

                if(((Proj_Blue)proj).curState == Proj_Blue.BLUE_STATE.EXPLODING) {

                    TextureRegion keyFrame =
                            Assets.blueShotExplosion.getKeyFrame(((Proj_Blue) proj).stateTime,
                            Animation.ANIMATION_NONLOOPING);


                    batcher.drawSprite(((Proj_Blue) proj).position.x,
                            ((Proj_Blue) proj).position.y,
                            5, 5, keyFrame);


                }
            }
        }
    }

    private void renderProjectileTails() {
        for(Projectile proj : world.projectiles) {

            if(proj.fizzleState == Projectile.FIZZLE_STATE.FIZZLING) continue;

            Vector2 tail1 = proj.tail1;
            Vector2 tail2 = proj.tail2;
            Vector2 tail3 = proj.tail3;
            Vector2 tail4 = proj.tail4;
            Vector2 tail5 = proj.tail5;

            TextureRegion tailSprite = null;

            switch(proj.projType) {
                case BLUE:
                    if(((Proj_Blue)proj).curState !=
                            Proj_Blue.BLUE_STATE.EXPLODING) {
                        tailSprite = Assets.blueShot;
                    } else {
                        tailSprite = null;
                    }
                break;
                case GREEN:
                    tailSprite = Assets.greenShot;
                    break;
                case RED:
                    tailSprite = Assets.redShot;
                    break;
                case ORANGE:
                    tailSprite = Assets.orangeShot;
                    break;
                case MISSILE:
                    tailSprite = Assets.missile;
                    break;
            }

            if(tailSprite != null) {
                batcher.drawSprite(tail1.x, tail1.y, 0.8f, 0.8f, tailSprite);
                batcher.drawSprite(tail2.x, tail2.y, 0.6f, 0.6f, tailSprite);
                batcher.drawSprite(tail3.x, tail3.y, 0.4f, 0.4f, tailSprite);
                batcher.drawSprite(tail4.x, tail4.y, 0.2f, 0.2f, tailSprite);
                batcher.drawSprite(tail5.x, tail5.y, 0.1f, 0.1f, tailSprite);
            }
        }
    }

    private void renderDrones() {
        for(int i = 0; i < world.drones.size(); i++) {
            Drone dr = world.drones.get(i);

            TextureRegion keyFrame;

            if(dr.curState == Drone.STATE.NORMAL) {

                if(dr.isAlienDrone) {
                    keyFrame = Assets.alienDrone;
                } else {
                    keyFrame = Assets.humanDrone;
                }

                batcher.drawSprite(dr.pos().x, dr.pos().y,
                        Drone.DRONE_WIDTH,
                        Drone.DRONE_HEIGHT,
                        dr.rotationAngle,
                        keyFrame);
            } else if(dr.curState == Drone.STATE.EXPLODING) {
                keyFrame = Assets.shipExplosion.getKeyFrame(dr.stateTime,
                        Animation.ANIMATION_NONLOOPING);

                batcher.drawSprite(dr.pos().x, dr.pos().y,
                        3, 3,
                        keyFrame);
            }

        }
    }

    private void renderPowerUps() {
        for(int i = 0; i < world.powerUps.size(); i++) {
            PowerUp pup = world.powerUps.get(i);

            TextureRegion keyFrame;

            if(pup instanceof ShieldPU) {

                keyFrame = Assets.armorPU;

                batcher.drawSprite(pup.pos().x, pup.pos().y,
                        ((ShieldPU) pup).bounds.width,
                        ((ShieldPU) pup).bounds.height, keyFrame);

            } else if(pup instanceof WeaponPU) {

                if(((WeaponPU) pup).curState == WeaponPU.STATE.SECONDARY) {

                    float SCALE = 1f;

                    if(((WeaponPU) pup).whichPlayerHitPowerup == World.ALIEN_CANNON)
                        SCALE *= -1;

                    switch(((WeaponPU) pup).contents) {
                        case BLUE:

                            keyFrame = Assets.blueShot;

                            batcher.drawSprite(pup.pos().x, pup.pos().y,
                                    SCALE, SCALE, keyFrame);

                            break;

                        case GREEN:
                            keyFrame = Assets.greenShot;

                            batcher.drawSprite(pup.pos().x, pup.pos().y,
                                    SCALE, SCALE, keyFrame);

                            break;

                        case RED:
                            keyFrame = Assets.redShot;

                            batcher.drawSprite(pup.pos().x, pup.pos().y,
                                    SCALE, SCALE, keyFrame);
                            break;

                        case MISSILE:
                            keyFrame = Assets.missile;

                            batcher.drawSprite(pup.pos().x, pup.pos().y,
                                    SCALE, SCALE, keyFrame);
                            break;
                    }

                    if(((WeaponPU) pup).whichPlayerHitPowerup == World.HUMAN_CANNON) {
                        Assets.font.drawText(batcher,
                                "+" + ((WeaponPU) pup).howManyWereGiven,
                                pup.pos().x + 0.65f, pup.pos().y - 0.65f, "World");
                    } else {

                        Assets.font.drawText(batcher,
                                ((WeaponPU) pup).howManyWereGiven + "+",
                                pup.pos().x - 0.85f, pup.pos().y + 0.85f, true, "World");
                    }


                } else {

                    keyFrame = Assets.weaponPU;

                    batcher.drawSprite(pup.pos().x, pup.pos().y,
                            ((WeaponPU) pup).bounds.width,
                            ((WeaponPU) pup).bounds.height, keyFrame);
                }
            } else if(pup instanceof HealthPU) {
                keyFrame = Assets.healthPU;

                batcher.drawSprite(pup.pos().x, pup.pos().y,
                        ((HealthPU) pup).bounds.width,
                        ((HealthPU) pup).bounds.height, keyFrame);
            }
        }
    }

    private void renderTeleportCircle() {
        GL10 gl = glGraphics.getGL();

        float MULTIPLIER = 2.86f; //necessary for unknown reason. Some scaling issue.

        //scale and draw teleport circle around ship when in move mode
        switch(world.state) {
            case World.H_MOVE:
                //bind texture and verts
                circle.bind();
                teleportCircleVerts.bind();

                //grab human cannon
                Cannon hCannon = world.cannons.get(World.HUMAN_CANNON);

                gl.glLoadIdentity();
                gl.glTranslatef(hCannon.pos().x, hCannon.pos().y, 0);
                gl.glScalef(hCannon.energyRatio * MULTIPLIER,
                        hCannon.energyRatio * MULTIPLIER, 1);
                teleportCircleVerts.draw(GL10.GL_TRIANGLES, 0, 6);
                gl.glLoadIdentity();

                //unbind and clean up
                teleportCircleVerts.unbind();

                break;

            case World.A_MOVE:

                //bind texture and verts
                circle.bind();
                teleportCircleVerts.bind();

                //grab human cannon
                Cannon aCannon = world.cannons.get(World.ALIEN_CANNON);

                gl.glLoadIdentity();
                gl.glTranslatef(aCannon.pos().x, aCannon.pos().y, 0);
                gl.glScalef(aCannon.energyRatio * MULTIPLIER,
                        aCannon.energyRatio * MULTIPLIER, 1);
                teleportCircleVerts.draw(GL10.GL_TRIANGLES, 0, 6);
                gl.glLoadIdentity();

                //unbind and clean up
                teleportCircleVerts.unbind();

                break;

            default:
                //draw nothing
        }

    }

    private void renderShieldEffects() {
        int len = world.shieldEffects.size();

        for(int i = 0; i < len; i++) {
            ShieldEffect thisShield = world.shieldEffects.get(i);

            TextureRegion keyFrame;


            keyFrame = Assets.shield.getKeyFrame(thisShield.existedTime,
                    Animation.ANIMATION_NONLOOPING);

            batcher.drawSprite(thisShield.pos.x, thisShield.pos.y,
                    ShieldEffect.SHIELD_WIDTH,
                    ShieldEffect.SHIELD_HEIGHT, keyFrame);
        }
    }

    private void renderTeleportEffects() {
        int len = world.teleportEffects.size();

        for(int i = 0; i < len; i++) {
            TeleportEffect thisTeleport = world.teleportEffects.get(i);

            TextureRegion keyFrame;

            if(thisTeleport.reverse) {
                 keyFrame = Assets.reverseTeleport.getKeyFrame(thisTeleport.existedTime,
                        Animation.ANIMATION_NONLOOPING);
            } else {
                 keyFrame = Assets.teleport.getKeyFrame(thisTeleport.existedTime,
                        Animation.ANIMATION_NONLOOPING);
            }


            batcher.drawSprite(thisTeleport.pos.x, thisTeleport.pos.y,
                    TeleportEffect.TELEPORT_WIDTH,
                    TeleportEffect.TELEPORT_HEIGHT, keyFrame);
        }
    }


    //for debugging and testing
    private void renderTargets() {

        if((world.state == World.H_CANNON_AIM || world.state == World.H_SHOOT)
                && world.humanTargetOn) {
            //render human target
            GameObject humanTarget = world.humanTarget;

            TextureRegion humanKeyFrame = Assets.humanTargetRegion;

            batcher.drawSprite(humanTarget.position.x, humanTarget.position.y,
                    1, 1, humanKeyFrame);

        } else if ((world.state == World.A_CANNON_AIM || world.state == World.A_SHOOT)
                && world.alienTargetOn) {
            //render alien target
            GameObject alienTarget = world.alienTarget;

            TextureRegion alienKeyFrame = Assets.alienTargetRegion;

            batcher.drawSprite(alienTarget.position.x, alienTarget.position.y,
                    1, 1, alienKeyFrame);
        }

    }

    private void renderShotBounces() {

        int len = world.shotBounces.size();

        for(int i = 0; i < len; i++) {
            ShotBounce thisBounce = world.shotBounces.get(i);

            TextureRegion keyFrame = Assets.shotBounce.getKeyFrame(thisBounce.existedTime,
                    Animation.ANIMATION_NONLOOPING);

            batcher.drawSprite(thisBounce.pos.x, thisBounce.pos.y, ShotBounce.BOUNCE_WIDTH,
                    ShotBounce.BOUNCE_HEIGHT, keyFrame);
        }


    }


    @SuppressWarnings("AccessStaticViaInstance")
    private void renderProjectiles() {

        int len = world.projectiles.size();
        for(int i = 0; i < len; i++) {
            Projectile proj = world.projectiles.get(i);

            // only render non-fizzling projectiles
            if(proj.fizzleState == Projectile.FIZZLE_STATE.FIZZLING) continue;

            TextureRegion keyFrame = null;

            switch(proj.projType) {
                case ORANGE:
                    keyFrame = Assets.orangeShot;
                    break;
                case BLUE:
                    if(((Proj_Blue)proj).curState == Proj_Blue.BLUE_STATE.NORMAL) {

                        keyFrame = Assets.blueShot;

                    } else {
                        //defer explosion rendering to second sprite sheet
                        continue;
                    }

                    break;
                case GREEN:
                    keyFrame = Assets.greenShot;
                    break;
                case RED:
                    keyFrame = Assets.redShot;
                    break;
                case MISSILE:
                    keyFrame = Assets.missile;
                    break;

                default:
                    keyFrame = Assets.orangeShot;
            }

            //for safety with pointer below
            if(keyFrame == null) {
                keyFrame = Assets.orangeShot;
            }

            //need special case for blue shot's explosion
            if(proj.projType == Projectile.TYPE.BLUE) {

                if(((Proj_Blue)proj).curState == Proj_Blue.BLUE_STATE.NORMAL) {
                    batcher.drawSprite(proj.position.x, proj.position.y,
                            Projectile.PROJECTILE_WIDTH,
                            Projectile.PROJECTILE_HEIGHT, keyFrame);

                } else {
                    continue;
                }
            //need special case for missile
            } else if(proj.projType == Projectile.TYPE.MISSILE) {
                batcher.drawSprite(proj.position.x, proj.position.y,
                        Projectile.PROJECTILE_WIDTH,
                        Projectile.PROJECTILE_HEIGHT, keyFrame);
            } else {
                batcher.drawSprite(proj.position.x, proj.position.y,
                        Projectile.PROJECTILE_WIDTH, Projectile.PROJECTILE_HEIGHT, keyFrame);
            }
        }

    }

    private void renderCannons() {
        int len = world.cannons.size();
        for(int i = 0; i < len; i++) {
            Cannon cannon = world.cannons.get(i);

            if(cannon.curState == Cannon.CANNON_STATE.ALIVE) {
                TextureRegion keyFrame;

                if(i == 0) keyFrame = Assets.humanShip; //human ship at pos 0
                else if(i == 1) keyFrame = Assets.alienShip; //alien ship at pos 1
                else keyFrame = Assets.humanShip; //default for safety

                batcher.drawSprite(cannon.position.x, cannon.position.y,
                        Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                        cannon.cannonAngle, keyFrame);

            } else if (cannon.curState == Cannon.CANNON_STATE.DEAD) {
                TextureRegion keyFrame;

                keyFrame = Assets.shipExplosion.getKeyFrame(cannon.stateTime, Animation.ANIMATION_LOOPING);

                batcher.drawSprite(cannon.position.x, cannon.position.y,
                        Cannon.CANNON_WIDTH, Cannon.CANNON_HEIGHT,
                        cannon.cannonAngle, keyFrame);
            }

        }
    }


    private void renderPlatforms() {

        int len = world.platforms.size();
        for(int i = 0; i < len; i++) {
            Platform platform = world.platforms.get(i);

            switch(platform.type) {
                case TYPE_1X1:
                    render_1x1(platform);
                    break;
                case TYPE_2X2:
                    render_2x2(platform);
                    break;
                case TYPE_4X4:
                    render_4x4(platform);
                    break;
                case TYPE_4X2:
                    render_4x2(platform);
                    break;
                case TYPE_6X2:
                    render_6x2(platform);
                    break;
                case TYPE_8X2:
                    render_8x2(platform);
                    break;
                case TYPE_10X2:
                    render_10x2(platform);
                    break;
                case TYPE_12X2:
                    render_12x2(platform);
                    break;
                case TYPE_14X2:
                    render_14x2(platform);
                    break;
                case TYPE_16X2:
                    render_16x2(platform);
                    break;
                case TYPE_18X2:
                    render_18x2(platform);
                    break;
                case TYPE_20X2:
                    //render_20x2(platform);
                    break;
                case TYPE_ANGLED_2X2:
                    render_angled_2x2((Platform_Angled_2X2)platform);
                    break;
                case TYPE_ANGLED_4X4:
                    render_angled_4x4((Platform_Angled_4X4)platform);
                    break;
                case TYPE_ANGLED_6X6:
                    render_angled_6x6((Platform_Angled_6X6)platform);
                    break;

            }

        }

    }

    public void render_angled_2x2(Platform_Angled_2X2 ptfm) {
        TextureRegion keyFrame;

        keyFrame = Assets.angled_2x2;

        batcher.drawSprite(ptfm.position.x, ptfm.position.y,
                Platform_Angled_2X2.PLATFORM_WIDTH_ANGLED_2X2,
                Platform_Angled_2X2.PLATFORM_HEIGHT_ANGLED_2X2,
                ptfm.triBounds.rotationAngle, keyFrame);
    }

    public void render_angled_4x4(Platform_Angled_4X4 ptfm) {
        TextureRegion keyFrame;

        keyFrame = Assets.angled_4x4;

        batcher.drawSprite(ptfm.position.x, ptfm.position.y,
                Platform_Angled_4X4.PLATFORM_WIDTH_ANGLED_4X4,
                Platform_Angled_4X4.PLATFORM_HEIGHT_ANGLED_4X4,
                ptfm.triBounds.rotationAngle, keyFrame);
    }

    public void render_angled_6x6(Platform_Angled_6X6 ptfm) {
        TextureRegion keyFrame;

        keyFrame = Assets.angled_6x6;

        batcher.drawSprite(ptfm.position.x, ptfm.position.y,
                Platform_Angled_6X6.PLATFORM_WIDTH_ANGLED_6X6,
                Platform_Angled_6X6.PLATFORM_HEIGHT_ANGLED_6X6,
                ptfm.triBounds.rotationAngle, keyFrame);
    }


    public void render_1x1(Platform platform) {
        TextureRegion keyFrame;

        if(platform.breakable) {
            keyFrame = Assets.platform_1x1_breakable;
        } else {
            keyFrame = Assets.platform_1x1_static;
        }

        if(platform.breakable &&
                platform.curState == Platform.PLATFORM_STATE.EXPLODING) {
            keyFrame = Assets.shipExplosion.getKeyFrame(platform.stateTime,
                    Animation.ANIMATION_NONLOOPING);
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_1X1.PLATFORM_WIDTH_1X1,
                    Platform_1X1.PLATFORM_HEIGHT_1X1, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_1X1.PLATFORM_WIDTH_1X1,
                    Platform_1X1.PLATFORM_HEIGHT_1X1, keyFrame);
        }



    }

    public void render_2x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.breakable) {
            keyFrame = Assets.platform_2x2_breakable;
        } else {
            keyFrame = Assets.platform_2x2_static;
        }

        if(platform.breakable &&
                platform.curState == Platform.PLATFORM_STATE.EXPLODING) {
            keyFrame = Assets.shipExplosion.getKeyFrame(platform.stateTime,
                    Animation.ANIMATION_NONLOOPING);
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_2X2.PLATFORM_WIDTH_2X2,
                    Platform_2X2.PLATFORM_HEIGHT_2X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_2X2.PLATFORM_WIDTH_2X2,
                    Platform_2X2.PLATFORM_HEIGHT_2X2, keyFrame);
        }

    }

    public void render_4x4(Platform platform) {
        TextureRegion keyFrame;

        if(platform.breakable) {
            keyFrame = Assets.platform_4x4_breakable;
        } else {
            keyFrame = Assets.platform_4x4_static;
        }

        if(platform.breakable &&
                platform.curState == Platform.PLATFORM_STATE.EXPLODING) {
            keyFrame = Assets.shipExplosion.getKeyFrame(platform.stateTime,
                    Animation.ANIMATION_NONLOOPING);
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_4X4.PLATFORM_WIDTH_4X4,
                    Platform_4X4.PLATFORM_HEIGHT_4X4, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_4X4.PLATFORM_WIDTH_4X4,
                    Platform_4X4.PLATFORM_HEIGHT_4X4, keyFrame);
        }


    }

    public void render_4x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_4x2_static_h;
        } else {
            keyFrame = Assets.platform_4x2_static_h;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_4X2.PLATFORM_WIDTH_4X2,
                    Platform_4X2.PLATFORM_HEIGHT_4X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_4X2.PLATFORM_WIDTH_4X2,
                    Platform_4X2.PLATFORM_HEIGHT_4X2, keyFrame);
        }


    }

    public void render_6x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_6x2_static_h;
        } else {
            keyFrame = Assets.platform_6x2_static_h;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_6X2.PLATFORM_WIDTH_6X2,
                    Platform_6X2.PLATFORM_HEIGHT_6X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_6X2.PLATFORM_WIDTH_6X2,
                    Platform_6X2.PLATFORM_HEIGHT_6X2, keyFrame);
        }


    }

    public void render_8x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_8x2_static_h;
        } else {
            keyFrame = Assets.platform_8x2_static_v;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_8X2.PLATFORM_WIDTH_8X2,
                    Platform_8X2.PLATFORM_HEIGHT_8X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_8X2.PLATFORM_WIDTH_8X2,
                    Platform_8X2.PLATFORM_HEIGHT_8X2, keyFrame);
        }


    }

    public void render_10x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_10x2_static_h;
        } else {
            keyFrame = Assets.platform_10x2_static_v;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_10X2.PLATFORM_WIDTH_10X2,
                    Platform_10X2.PLATFORM_HEIGHT_10X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_10X2.PLATFORM_WIDTH_10X2,
                    Platform_10X2.PLATFORM_HEIGHT_10X2, keyFrame);
        }

    }

    public void render_12x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_12x2_static_h;
        } else {
            keyFrame = Assets.platform_12x2_static_v;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_12X2.PLATFORM_WIDTH_12X2,
                    Platform_12X2.PLATFORM_HEIGHT_12X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_12X2.PLATFORM_WIDTH_12X2,
                    Platform_12X2.PLATFORM_HEIGHT_12X2, keyFrame);
        }

    }

    public void render_14x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_14x2_static_h;
        } else {
            keyFrame = Assets.platform_14x2_static_v;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_14X2.PLATFORM_WIDTH_14X2,
                    Platform_14X2.PLATFORM_HEIGHT_14X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_14X2.PLATFORM_WIDTH_14X2,
                    Platform_14X2.PLATFORM_HEIGHT_14X2, keyFrame);
        }


    }

    public void render_16x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_16x2_static_v;
        } else {
            keyFrame = Assets.platform_16x2_static_h;
        }

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_16X2.PLATFORM_WIDTH_16X2,
                    Platform_16X2.PLATFORM_HEIGHT_16X2, 90, keyFrame);
        } else {
            batcher.drawSprite(platform.position.x, platform.position.y,
                    Platform_16X2.PLATFORM_WIDTH_16X2,
                    Platform_16X2.PLATFORM_HEIGHT_16X2, keyFrame);
        }
    }

    public void render_18x2(Platform platform) {
        TextureRegion keyFrame;

        if(platform.orientation == Platform.TYPE_VERTICAL) {
            keyFrame = Assets.platform_18x2_static_h;
        } else {
            //keyFrame = Assets.platform_18x2_static_v;
            keyFrame = Assets.platform_18x2_static_h;
        }
        batcher.drawSprite(platform.position.x, platform.position.y,
                Platform_18X2.PLATFORM_WIDTH_18X2,
                Platform_18X2.PLATFORM_HEIGHT_18X2, keyFrame);

    }

    public static void reload() {
        circle.reload();
        atlas.reload();
    }

}
