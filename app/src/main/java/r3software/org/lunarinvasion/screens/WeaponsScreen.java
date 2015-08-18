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
 * This is the weapons TOC
 */
public class WeaponsScreen extends GLScreen {

    Vector2 touchPoint;
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle back;
    Rectangle toMenu;
    Rectangle soundToggle;

    Rectangle orange;
    Rectangle green;
    Rectangle blue;
    Rectangle purple;
    Rectangle red;

    public WeaponsScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 720, 1280);
        batcher = new SpriteBatcher(glGraphics, 100);

        back = new Rectangle(2, 5, 4, 4);
        toMenu = new Rectangle(9f, 0f, 4, 4);
        soundToggle = new Rectangle(0, 0, 2, 2);
        touchPoint = new Vector2();

        orange = new Rectangle(9, 29, 8, 3);
        green = new Rectangle(9, 25, 8, 3);
        blue = new Rectangle(8, 21, 6, 3);
        purple = new Rectangle(9, 17, 8, 3);
        red = new Rectangle(8, 13, 6, 3);

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

                if(OverlapTester.pointInRectangle(orange, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new OrangePage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(green, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new GreenPage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(blue, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new BluePage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(purple, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new PurplePage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(red, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new RedPage(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(toMenu, touchPoint)) {
                    Assets.playSound(Assets.menuClick);
                    game.setScreen(new MainMenuScreen(game));
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

        batcher.beginBatch(Assets.background_5);
        batcher.drawSprite(360, 640, 720, 1280, Assets.background_5_region);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.menuAtlas);


        batcher.drawSprite(360, 1280 - (4.5f * 32), 14 * 32, 5 * 32, Assets.weapons);

        //shot pictures
        batcher.drawSprite(5.5f * 32, 1280 - (9.5f * 32), 3 * 32, 3 * 32, Assets.orange_shot_menu);
        batcher.drawSprite(5.5f * 32, 1280 - (13.5f * 32), 3 * 32, 3 * 32, Assets.green_shot_menu);
        batcher.drawSprite(5.5f * 32, 1280 - (17.5f * 32), 3 * 32, 3 * 32, Assets.blue_shot_menu);
        batcher.drawSprite(5.5f * 32, 1280 - (21.5f * 32), 3 * 32, 3 * 32, Assets.purple_shot_menu);
        batcher.drawSprite(5.5f * 32, 1280 - (25.5f * 32), 3 * 32, 3 * 32, Assets.red_shot_menu);

        //menu items
        batcher.drawSprite(12 * 32, 1280 - (9.5f * 32), 8 * 32, 3 * 32, Assets.orange);
        batcher.drawSprite(12 * 32, 1280 - (13.5f * 32), 8 * 32, 3 * 32, Assets.green);
        batcher.drawSprite(11 * 32, 1280 - (17.5f * 32), 6 * 32, 3 * 32, Assets.blue);
        batcher.drawSprite(12 * 32, 1280 - (21.5f * 32), 8 * 32, 3 * 32, Assets.purple);
        batcher.drawSprite(11 * 32, 1280 - (25.5f * 32), 6 * 32, 3 * 32, Assets.red);


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
