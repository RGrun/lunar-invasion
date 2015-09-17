package r3software.org.lunarinvasion.engine.framework;

import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("unused")
public interface FileIO {
	
	 InputStream readAsset(String fileName) throws IOException;
	
	 InputStream readFile(String filename) throws IOException;
	
	 OutputStream writeFile(String fileName) throws IOException;
	
	 SharedPreferences getPreferences();

}
