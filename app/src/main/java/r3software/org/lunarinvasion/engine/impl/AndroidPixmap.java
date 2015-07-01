package r3software.org.lunarinvasion.engine.impl;

import android.graphics.Bitmap;

import r3software.org.lunarinvasion.engine.framework.Graphics.PixmapFormat;
import r3software.org.lunarinvasion.engine.framework.Pixmap;

public class AndroidPixmap implements Pixmap {

	Bitmap bitmap;
	PixmapFormat format;
	
	public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}
	
	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	@Override
	public PixmapFormat getFormat() {
		return  format;
	}

	@Override
	public void dispose() {
		bitmap.recycle();
	}

}
