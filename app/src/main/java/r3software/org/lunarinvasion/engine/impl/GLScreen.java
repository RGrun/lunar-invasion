package r3software.org.lunarinvasion.engine.impl;

import r3software.org.lunarinvasion.engine.framework.Game;
import r3software.org.lunarinvasion.engine.framework.Screen;


public abstract class GLScreen extends Screen {
	
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;

	public GLScreen(Game game) {
		super(game);
		glGame = (GLGame) game;
		glGraphics = glGame.getGLGraphics();
	}

}
