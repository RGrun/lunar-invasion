package r3software.org.lunarinvasion.engine.framework;

import r3software.org.lunarinvasion.engine.framework.Graphics.PixmapFormat;

public interface Pixmap {

	
	public int getWidth();
	
	public int getHeight();
	
	public PixmapFormat getFormat();
	
	public void dispose();
}
