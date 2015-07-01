package r3software.org.lunarinvasion.engine.framework;

import android.util.Log;

public class FPSCounter {

	long startTime = System.nanoTime();
	int frames = 0;
	
	public void logFrame() {
		frames++;
		if(System.nanoTime() - startTime >= 1000000000) {
			Log.d("FPScounter", "fps: " + frames);
			frames = 0;
			startTime = System.nanoTime();
		}
	}
}
