package r3software.org.lunarinvasion.screens;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Cannon;
import r3software.org.lunarinvasion.Settings;
import r3software.org.lunarinvasion.World;
import r3software.org.lunarinvasion.WorldRenderer;
import r3software.org.lunarinvasion.engine.framework.Camera2D;
import r3software.org.lunarinvasion.engine.framework.FPSCounter;
import r3software.org.lunarinvasion.engine.framework.Game;
import r3software.org.lunarinvasion.engine.framework.SpriteBatcher;
import r3software.org.lunarinvasion.engine.impl.GLScreen;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.levels.Level;
import r3software.org.lunarinvasion.projectiles.Projectile;

import static r3software.org.lunarinvasion.engine.framework.Input.TouchEvent;

/**
 * Created by Jeff on 2/15/2015.
 *
 * This is the main screen of the game.
 * It's the main controller that contains all the game's primary elements.
 */

@SuppressWarnings("PointlessArithmeticExpression")
public class GameScreen extends GLScreen {

    //game states
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    static final int GAME_LEVEL_END = 3;
    static final int GAME_OVER = 4;

    int state;
    Camera2D guiCam; //game camera
    Vector2 touchPoint; //point player touched
    SpriteBatcher batcher;    //for sprite rendering
    SpriteBatcher UIbatcher;
    World world; //Game world
    WorldRenderer renderer;   //renders game sprites, not UI sprites
    FPSCounter counter;

    public GameScreen(Game game, Level levelToLoad) {
        super(game);
        state = GAME_READY;

        guiCam = new Camera2D(glGraphics, 720, 1280); //takes glGraphics, fruW, fruH
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000);
        UIbatcher = new SpriteBatcher(glGraphics, 1000);

        world = new World(game, this, guiCam, levelToLoad);
        renderer = new WorldRenderer(glGraphics, batcher, world);
        counter = new FPSCounter();
    }

    @Override
    public void update(float deltaTime) {
        // if(deltaTime > 0.1f)
        //   deltaTime = 0.1f;

        switch(state) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(deltaTime);
                break;
            case GAME_PAUSED:
                updatePaused();
                break;
           /* case GAME_LEVEL_END:
                updateLevelEnd();
                break;
            case GAME_OVER:
                updateGameOver();
                break;*/
        }
    }

    private void updateReady() {

        state = GAME_RUNNING;

    }

    private void updateRunning(float deltaTime) {

        world.update(deltaTime); //game updated here

        counter.logFrame();


    }

    private void updatePaused() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type != TouchEvent.TOUCH_UP)
                continue;

            touchPoint.set(event.x, event.y);
            guiCam.touchToWorld(touchPoint);

        }
    }

    //NOT IMPLEMENTED

/*    private void updateLevelEnd() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type != TouchEvent.TOUCH_UP)
                continue;
            world = new World(worldListener, game, this, guiCam);
            renderer = new WorldRenderer(glGraphics, batcher, world);
            state = GAME_READY;
        }
    }

    private void updateGameOver() {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type != TouchEvent.TOUCH_UP)
                continue;
            game.setScreen(new MainMenuScreen(game));
        }
    }*/

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT); //clear out screen buffer
        gl.glEnable(GL10.GL_TEXTURE_2D); //allow texture rendering

        renderer.render(); //this renders the game

        guiCam.setViewportAndMatrices(); //set up GUI
        gl.glEnable(GL10.GL_BLEND); //enable blending
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA); //blending equations
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        UIbatcher.beginBatch(Assets.atlas); //get ready to draw sprites
        switch(state) { //these draw the GUI elements
            case GAME_READY:
                presentReady();
                break;
            case GAME_RUNNING:
                presentRunning();
                break;
            case GAME_PAUSED:
                presentPaused();
                break;
            case GAME_LEVEL_END:
                presentLevelEnd();
                break;
            case GAME_OVER:
                presentGameOver();
                break;
        }
        UIbatcher.endBatch(); //draw sprites

        //draw pause menu if game is paused
        if(world.state == World.GAME_PAUSED) {
            if (Settings.soundEnabled) {
                batcher.beginBatch(Assets.gameMenuSoundOn);

                batcher.drawSprite(720 / 2, 1280 / 2,
                        20 * 32, 20 * 32,
                        Assets.gameMenuSoundOnRegion);

                batcher.endBatch();

            } else {
                batcher.beginBatch(Assets.gameMenuSoundOff);

                batcher.drawSprite(720 / 2, 1280 / 2,
                        20 * 32, 20 * 32,
                        Assets.gameMenuSoundOffRegion);

                batcher.endBatch();
            }
        }

        //draw human win menu if humans win
        if(world.state == World.HUMAN_WIN) {
            batcher.beginBatch(Assets.earth_victory_menu);

            batcher.drawSprite(720 / 2, 1280 / 2,
                    20 * 32, 20 * 32,
                    Assets.earth_victory_menu_region);

            batcher.endBatch();
        }

        //draw alien win menu is aliens win
        if(world.state == World.ALIEN_WIN) {
            batcher.beginBatch(Assets.alien_victory_menu);

            batcher.drawSprite(720 / 2, 1280 / 2,
                    20 * 32, 20 * 32,
                    Assets.alien_victory_menu_region);

            batcher.endBatch();
        }

        //draw interstitial screens
        if(world.state == World.HUMAN_TURN_START) {
            batcher.beginBatch(Assets.humanTurnConfirm);

            batcher.drawSprite(720 / 2, 1280 / 2,
                    20 * 32, 15 * 32,
                    Assets.humanTurnConfirmRegion);

            batcher.endBatch();
        }

        if(world.state == World.ALIEN_TURN_START) {
            batcher.beginBatch(Assets.alienTurnConfirm);

            batcher.drawSprite(720 / 2, 1280 / 2,
                    -(20 * 32), -(15 * 32),
                    Assets.alienTurnConfirmRegion);

            batcher.endBatch();
        }


        gl.glDisable(GL10.GL_BLEND); //disable blending

        counter.logFrame();
    }

    private void presentReady() {
        //REMOVE WHEN WE HAVE REAL MENUS
        /*String textToDraw = "Tap to start!";
        Assets.font.drawText(UIbatcher, textToDraw, 720 / 2 - 140, 1280 / 2 + 100);*/
       /* batcher.drawSprite(160, 240, 192, 32, Assets.ready);*/
    }

    private void presentRunning() {

        GL10 gl = glGraphics.getGL();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        Cannon hCannon = world.cannons.get(World.HUMAN_CANNON);
        Cannon aCannon = world.cannons.get(World.ALIEN_CANNON);

        //draw power bars
        //draw human power bar as text
        float humanEnergy = hCannon.energyRatio;
        int humanEnergyInt = Math.round(humanEnergy * 100);
        String humanEnergyString = humanEnergyInt + "%";

        //for proper position as string gets shorter
        float xValue100 =  720 - (96 / 2) - 20;
        float xValue10 =  720 - (96 / 2) - 10;
        float xValue1 =  720 - (96 / 2);

        float xValue;

        if(humanEnergyInt >= 100) {
            xValue = xValue100;
        } else if (humanEnergyInt >= 10) {
            xValue = xValue10;
        } else  {
            xValue = xValue1;
        }

        Assets.font.drawText(UIbatcher, humanEnergyString,
                xValue,
                (64 * 6) - 10);


        //draw alien power bar as text
        float alienEnergy = aCannon.energyRatio;
        int alienEnergyInt = Math.round(alienEnergy * 100);
        String alienEnergyString =  reverseString(alienEnergyInt + "%");

        float xValuea;

        if(alienEnergyInt >= 100) {
            xValuea = xValue100;
        } else if (alienEnergyInt >= 10) {
            xValuea = xValue10;
        } else {
            xValuea = xValue1;
        }


        Assets.font.drawText(UIbatcher, alienEnergyString,
                xValuea,
                1280 - (64 * 6), true);



        //draw weapon ammo below weapon button
        int hCurrentAmmo = hCannon.getCurrentAmmo();

        if(hCannon.curWeapon == Projectile.TYPE.ORANGE) {
            Assets.font.drawText(UIbatcher, "inf",
                    720 - (96 / 2) - 10,
                    (1280 / 2) - 140);
        } else  {
            Assets.font.drawText(UIbatcher, "" + hCurrentAmmo,
                    720 - (96 / 2) + 5,
                    (1280 / 2) - 140);
        }

        int aCurrentAmmo = aCannon.getCurrentAmmo();

        if(aCannon.curWeapon == Projectile.TYPE.ORANGE) {
            Assets.font.drawText(UIbatcher, reverseString("inf"),
                    720 - (96 / 2) - 10,
                    (1280 / 2) + 130, true);
        } else  {
            Assets.font.drawText(UIbatcher, reverseString("" + aCurrentAmmo),
                    (720 - (96 / 2) + 5),
                    ((1280 / 2) + 130
                    ), true);
        }

        //blacken game world
        if(world.state == World.GAME_PAUSED ||
                world.state == World.HUMAN_WIN ||
                world.state == World.ALIEN_WIN ||
                world.state == World.HUMAN_TURN_START ||
                world.state == World.ALIEN_TURN_START) {


            //draw black square over game world
            batcher.beginBatch(Assets.blackOverlay);

            batcher.drawSprite(720 / 2, 1280 / 2,
                    720, 1280,
                    Assets.blackOverlayRegion);

            batcher.endBatch();

        }


    }

    private void presentPaused() {
        Assets.font.drawText(UIbatcher, " ", 50, 50);
      /*  batcher.drawSprite(160, 240, 192, 96, Assets.pauseMenu);
        Assets.font.drawText(batcher, scoreString, 16, 480-20);*/
    }

    private void presentLevelEnd() {
        Assets.font.drawText(UIbatcher, " ", 50, 50);
       /* String topText = "the princess is ...";
        String bottomText = "in another castle!";
        float topWidth = Assets.font.glyphWidth * topText.length();
        float bottomWidth = Assets.font.glyphWidth * bottomText.length();
        Assets.font.drawText(batcher, topText, 160 - topWidth / 2, 480 - 40);
        Assets.font.drawText(batcher, bottomText, 160 - bottomWidth / 2, 40);*/
    }

    private void presentGameOver() {
        Assets.font.drawText(UIbatcher, " ", 50, 50);
      /*  batcher.drawSprite(160, 240, 160, 96, Assets.gameOver);
        float scoreWidth = Assets.font.glyphWidth * scoreString.length();
        Assets.font.drawText(batcher, scoreString, 160 - scoreWidth / 2, 480-20);*/
    }


    @Override
    public void pause() {
        if(state == GAME_RUNNING)
            state = GAME_PAUSED;
    }

    @Override
    public void resume() {
        if(state == GAME_PAUSED)
            state = GAME_RUNNING;
    }

    @Override
    public void dispose() {
    }

    public static String reverseString(String source) {
        int i, len = source.length();
        StringBuilder dest = new StringBuilder(len);

        for (i = (len - 1); i >= 0; i--){
            dest.append(source.charAt(i));
        }

        return dest.toString();
    }
}
