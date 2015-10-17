package r3software.org.lunarinvasion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import r3software.org.lunarinvasion.engine.framework.Screen;
import r3software.org.lunarinvasion.engine.impl.GLGame;
import r3software.org.lunarinvasion.screens.GameScreen;
import r3software.org.lunarinvasion.screens.MainMenuScreen;


public class CannonGameActivity extends GLGame {

    boolean firstTimeCreate = true;


    // this is needed because the game runs on a separate background thread.
    // when other parts of the game want to do something on the UI thread,
    // (like open a web browser) this ref is used to send a message to the UI thread.
    public Handler responseHandler;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        // this Handler belongs to the UI Thread's Looper
        // because it's created in the UI Thread.
        responseHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                String urlToView = msg.getData().getString("browserUri");

                // implicit intent to start browser app
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToView));
                startActivity(browserIntent);
                return true;
            }
        });

    }




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

    @Override
    public void onPause() {
        super.onPause();
        if(Settings.soundEnabled) {
            Assets.currentMusic.pause();
        }

    }

}
