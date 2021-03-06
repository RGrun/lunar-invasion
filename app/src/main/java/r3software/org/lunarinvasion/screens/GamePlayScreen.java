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

/**
 * Created by Jeff on 7/2/2015.
 *
 * This is the basics TOC.
 */
public class GamePlayScreen extends GLScreen {

    Vector2 touchPoint;
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle back;
    Rectangle toMenu;
    Rectangle soundToggle;

    Rectangle goals;
    Rectangle movement;
    Rectangle shooting;

    public GamePlayScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 720, 1280);
        batcher = new SpriteBatcher(glGraphics, 100);

        back = new Rectangle(2, 5, 4, 4);
        toMenu = new Rectangle(9f, 0f, 4, 4);
        soundToggle = new Rectangle(0, 0, 2, 2);
        touchPoint = new Vector2();

        goals = new Rectangle(4, 29, 14, 3);
        movement = new Rectangle(4, 25, 14, 3);
        shooting = new Rectangle(4, 21, 14, 3);
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

                if(OverlapTester.pointInRectangle(goals, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GoalsPage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(movement, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new MovementPage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(shooting, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new ShootingPage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(toMenu, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new MainMenuScreen(game, true));
                    return;
                }

                if (OverlapTester.pointInRectangle(back, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new HelpScreen(game));
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

        batcher.beginBatch(Assets.background_4);
        batcher.drawSprite(360, 640, 720, 1280, Assets.background_4_region);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.menuAtlas);

        batcher.drawSprite(360, 1280 - (4.5f * 32), 16 * 32, 5 * 32, Assets.game_play);
        batcher.drawSprite(360, 1280 - (9.5f * 32), 10 * 32, 3 * 32, Assets.goals);
        batcher.drawSprite(360, 1280 - (13.5f * 32), 12 * 32, 3 * 32, Assets.movement);
        batcher.drawSprite(360, 1280 - (17.5f * 32), 12 * 32, 3 * 32, Assets.shooting);

        batcher.drawSprite(128, 224, 128, 128, Assets.left_arrow);
        batcher.drawSprite(360, 2.5f * 32, 128, 128, Assets.down_arrow);

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
