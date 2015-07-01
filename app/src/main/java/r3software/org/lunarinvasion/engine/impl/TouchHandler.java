package r3software.org.lunarinvasion.engine.impl;

import android.view.View.OnTouchListener;

import java.util.List;

import r3software.org.lunarinvasion.engine.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {

	
	public boolean isTouchDown(int pointer);
	
	public int getTouchX(int pointer);
	
	public int getTouchY(int pointer);
	
	public List<TouchEvent> getTouchEvents();
}
