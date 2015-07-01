package r3software.org.lunarinvasion.engine.framework;

import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
	
	public InputStream readAsset(String fileName) throws IOException;
	
	public InputStream readFile(String filename) throws IOException;
	
	public OutputStream writeFile(String fileName) throws IOException;
	
	public SharedPreferences getPreferences();

}
