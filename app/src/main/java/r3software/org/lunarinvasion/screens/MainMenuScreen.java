package r3software.org.lunarinvasion.screens;

import android.util.Log;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import r3software.org.lunarinvasion.Assets;
import r3software.org.lunarinvasion.Settings;
import r3software.org.lunarinvasion.engine.framework.Camera2D;
import r3software.org.lunarinvasion.engine.framework.FPSCounter;
import r3software.org.lunarinvasion.engine.framework.Game;
import r3software.org.lunarinvasion.engine.framework.Input;
import r3software.org.lunarinvasion.engine.framework.SpriteBatcher;
import r3software.org.lunarinvasion.engine.impl.GLScreen;
import r3software.org.lunarinvasion.engine.math.OverlapTester;
import r3software.org.lunarinvasion.engine.math.Rectangle;
import r3software.org.lunarinvasion.engine.math.Vector2;

/**
 * Created by Jeff on 6/30/2015.
 *
 * This is the main menu for the game.
 */
public class MainMenuScreen extends GLScreen {

    public static final String TAG = "lunarinvasion";

    Vector2 touchPoint;
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle play;
    Rectangle help;
    Rectangle credits;
    Rectangle soundToggle;
    FPSCounter counter;

    public MainMenuScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 720, 1280);
        batcher = new SpriteBatcher(glGraphics, 100);

        play = new Rectangle(128, 512, 384, 128);
        help = new Rectangle(160, 704, 320, 128);
        credits = new Rectangle(160, 864, 320, 128);
        soundToggle = new Rectangle(32, 1280 - 32, 64, 64);

        touchPoint = new Vector2();

        counter = new FPSCounter();
    }

    @Override
    public void update(float deltaTime) {


        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();

        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            touchPoint.set(event.x, event.y);

            if(event.type == Input.TouchEvent.TOUCH_UP) {

                if(OverlapTester.pointInRectangle(play, touchPoint)) {
                    //Assets.playSound(Assets.clickSound);
                    //TODO: Change to level select screen once I have the select
                    Log.d(TAG, "Play Hit!");
                    game.setScreen(new GameScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(help, touchPoint)) {
                    //Assets.playSound(Assets.clickSound);
                    Log.d(TAG, "Help Hit!");
                    //game.setScreen(new HelpScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(credits, touchPoint)) {
                   // Assets.playSound(Assets.clickSound);
                    Log.d(TAG, "Credits Hit!");
                    //game.setScreen(new CreditsScreen(game));
                    return;
                }

                if(OverlapTester.pointInRectangle(soundToggle, touchPoint)) {
                   // Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled) {
                       // Assets.music.play();
                    } else {
                       // Assets.music.pause();
                    }
                }



            }


        }

        counter.logFrame();

    }

    @Override
    public void present(float deltaTime) {

        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(Assets.menuBackground);
        batcher.drawSprite(360, 640, 720, 1280, Assets.menuBackgroundRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.menuAtlas);

        //batcher.drawSprite(160, 480 - 10 - 71, 274, 142, Assets.logo);
        batcher.drawSprite(360, 672, 384, 128, Assets.play);
        batcher.drawSprite(360, 512, 320, 128, Assets.help_small);
        batcher.drawSprite(360, 352, 320, 128, Assets.credits_small);
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
