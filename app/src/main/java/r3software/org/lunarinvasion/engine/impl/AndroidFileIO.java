package r3software.org.lunarinvasion.engine.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.opengl.GLES10;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import r3software.org.lunarinvasion.engine.framework.FileIO;

public class AndroidFileIO implements FileIO {
	Context context;
	AssetManager assets;
	String externalStroagePath;

    public static final String TAG = "lunarinvasion";
	
	public AndroidFileIO(Context context) {
		this.context = context;
		this.assets = context.getAssets();
		
		this.externalStroagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

        int[] maxSize = new int[1];
        GLES10.glGetIntegerv(GLES10.GL_MAX_TEXTURE_SIZE, maxSize, 0);

        Log.d(TAG, "Max texture size: " + maxSize[0]);

    }

	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}

	@Override
	public InputStream readFile(String filename) throws IOException {
		return new FileInputStream(externalStroagePath + filename);
	}

	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStroagePath + fileName);
	}

	@Override
	public SharedPreferences getPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

}
