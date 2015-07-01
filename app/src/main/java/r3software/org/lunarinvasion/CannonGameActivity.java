package r3software.org.lunarinvasion;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import r3software.org.lunarinvasion.engine.framework.Screen;
import r3software.org.lunarinvasion.engine.impl.GLGame;
import r3software.org.lunarinvasion.screens.GameScreen;
import r3software.org.lunarinvasion.screens.MainMenuScreen;


public class CannonGameActivity extends GLGame {

    boolean firstTimeCreate = true;


    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if(firstTimeCreate) {
            //Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        } else {
            Assets.load(this);
            if(getCurrentScreen() instanceof GameScreen) {
                WorldRenderer.reload();
            }
        }
    }

}
