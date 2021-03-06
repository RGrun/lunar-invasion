package r3software.org.lunarinvasion.engine.impl;

import android.media.SoundPool;

import r3software.org.lunarinvasion.engine.framework.Sound;

public class AndroidSound implements Sound {
	
	int soundId;
	SoundPool soundPool;
	
	public AndroidSound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float volume) {
		soundPool.play(soundId, volume, volume, 0, 0, 1);

	}

	public void stop() {
        soundPool.stop(soundId);
    }

	@Override
	public void dispose() {
		soundPool.unload(soundId);

	}

}
