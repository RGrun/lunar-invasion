package r3software.org.lunarinvasion.screens;

import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Settings;
import r3software.org.lunarinvasion.engine.framework.Camera2D;
import r3software.org.lunarinvasion.engine.framework.Game;
import r3software.org.lunarinvasion.engine.framework.Input;
import r3software.org.lunarinvasion.engine.framework.SpriteBatcher;
import r3software.org.lunarinvasion.engine.impl.GLScreen;
import r3software.org.lunarinvasion.engine.math.OverlapTester;
import r3software.org.lunarinvasion.engine.math.Rectangle;
import r3software.org.lunarinvasion.engine.math.Vector2;
import r3software.org.lunarinvasion.levels.L_Maneuver;
import r3software.org.lunarinvasion.levels.L_NarrowPassage;
import r3software.org.lunarinvasion.levels.L_Pyramid;
import r3software.org.lunarinvasion.levels.L_Ricochet;
import r3software.org.lunarinvasion.levels.L_Rodeo;
import r3software.org.lunarinvasion.levels.L_Schema;

/**
 * Created by richard on 9/15/15.
 *
 * Fourth level select screen
 */
public class LevelSelect4 extends GLScreen {


    Vector2 touchPoint;
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle back;
    Rectangle toMenu;
    Rectangle nextScreen;
    Rectangle soundToggle;

    Rectangle level1;
    Rectangle level2;
    Rectangle level3;
    Rectangle level4;
    Rectangle level5;
    Rectangle level6;

    public LevelSelect4(Game game) {

        super(game);
        guiCam = new Camera2D(glGraphics, 720, 1280);
        batcher = new SpriteBatcher(glGraphics, 100);

        back = new Rectangle(2, 2, 4, 4);
        toMenu = new Rectangle(9f, 0f, 4, 4);
        nextScreen = new Rectangle(17, 2, 4, 4);
        soundToggle = new Rectangle(0, 0, 2, 2);

        level1 = new Rectangle(2, 28, 8, 8);
        level2 = new Rectangle(13, 28, 8, 8);
        level3 = new Rectangle(2, 18, 8, 8);
        level4 = new Rectangle(13, 18, 8, 8);
        level5 = new Rectangle(2, 8, 8, 8);
        level6 = new Rectangle(13, 8, 8, 8);


        touchPoint = new Vector2();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            touchPoint.set(event.x, event.y);

            touchPoint.x =
                    (event.x / (float) guiCam.glGraphics.getWidth() * Settings.WORLD_WIDTH);
            touchPoint.y =
                    (1 - event.y / (float) guiCam.glGraphics.getHeight()) * Settings.WORLD_HEIGHT;

            if(event.type == Input.TouchEvent.TOUCH_UP && Settings.logging) {

                Log.i(MainMenuScreen.TAG, "X: " + touchPoint.x + " Y: " + touchPoint.y);
            }

            if(event.type == Input.TouchEvent.TOUCH_UP) {


                if (OverlapTester.pointInRectangle(back, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new LevelSelect3(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(toMenu, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new MainMenuScreen(game, true));
                    return;
                }

                if(OverlapTester.pointInRectangle(nextScreen, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new LevelSelect5(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(level1, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GameScreen(game, new L_Maneuver()));
                    return;
                }

                if(OverlapTester.pointInRectangle(level2, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GameScreen(game, new L_NarrowPassage()));
                    return;
                }

                if(OverlapTester.pointInRectangle(level3, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GameScreen(game, new L_Pyramid()));
                    return;
                }

                if(OverlapTester.pointInRectangle(level4, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GameScreen(game, new L_Ricochet()));
                    return;
                }

                if(OverlapTester.pointInRectangle(level5, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GameScreen(game, new L_Rodeo()));
                    return;
                }

                if(OverlapTester.pointInRectangle(level6, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GameScreen(game, new L_Schema()));
                    return;
                }

                if(OverlapTester.pointInRectangle(soundToggle, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled) {
                        if(Settings.soundEnabled) {
                            Assets.changeMusic(Assets.menuMusic);
                        }
                        Assets.currentMusic.play();
                    } else {
                        Assets.currentMusic.pause();
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(Assets.select4);
        batcher.drawSprite(360, 640, 720, 1280, Assets.select4Region);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.menuAtlas);

        batcher.drawSprite(128, 4 * 32, 128, 128, Assets.left_arrow);
        batcher.drawSprite(360, 2.5f * 32, 128, 128, Assets.down_arrow);
        batcher.drawSprite(18 * 32, 4 * 32, 128, 128, Assets.right_arrow);

        batcher.drawSprite(32, 32, 64, 64, (Settings.soundEnabled ?
                Assets.soundOn : Assets.soundOff));

        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
