package r3software.org.lunarinvasion.engine.framework;

import r3software.org.lunarinvasion.engine.impl.Texture;

public class Font {

	public final Texture texture;
	public final int glyphWidth;
	public final int glyphHeight;
	public final TextureRegion[] glyphs = new TextureRegion[96];
	
	public Font(Texture texture, int offsetX, int offsetY,
                int glyphsPerRow, int glyphWidth, int glyphHeight) {
		this.texture = texture;
		this.glyphWidth = glyphWidth;
		this.glyphHeight = glyphHeight;
		int x = offsetX;
		int y = offsetY;
		for(int i = 0; i < 96; i++) {
			glyphs[i] = new TextureRegion(texture, x, y, glyphWidth, glyphHeight);
			x += glyphWidth;
			if(x == offsetX + glyphsPerRow * glyphWidth) {
				x = offsetX;
				y += glyphHeight;
			}
		}
	}
	
	public void drawText(SpriteBatcher batcher, String text, float x, float y) {
		
		int len = text.length();
		for(int i = 0; i < len; i++) {
			int c = text.charAt(i) - ' ';
			if(c < 0 || c > glyphs.length - 1)
				continue;
			
			TextureRegion glyph = glyphs[c];
			batcher.drawSprite(x, y, glyphWidth, glyphHeight, glyph);
			x += glyphWidth;
		}
	}

    public void drawText(SpriteBatcher batcher, String text, float x, float y,
                         String inWorld) {

        float worldWidth = 0.5f;
        float worldHeight = 0.5f;

        int len = text.length();
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, worldWidth, worldHeight, glyph);
            x += worldWidth;
        }
    }

    public void drawText(SpriteBatcher batcher, String text,
                         float x, float y,
                         boolean upsideDown) {


        int len = text.length();
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, -glyphWidth, -glyphHeight, glyph);
            x += glyphWidth;
        }
    }

    public void drawText(SpriteBatcher batcher, String text,
                         float x, float y,
                         boolean upsideDown, String inWorld) {

        float worldWidth = 0.5f;
        float worldHeight = 0.5f;


        int len = text.length();
        for(int i = 0; i < len; i++) {
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;

            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, -worldWidth, -worldHeight, glyph);
            x += worldWidth;
        }
    }




}
