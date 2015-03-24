/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcode.games.uEngine2.GFX.sprites;

import dcode.games.uEngine2.StData;

import java.awt.*;

/**
 * This class, if enabled in setup, allows you to use single (basic) sprite more
 * than once by wrapping it and shifting it's coordinates or altering them
 * compleetly
 *
 * @author dusakus
 */
public class SpriteWrapper extends Sprite {
	public boolean shiftOrReplace = true; // true for shift, false for replace;
	public int spriteId;
	public Sprite localSprite;

	public boolean enabled = false;

	public void setSprite(Sprite s) {
		localSprite = s;
		spriteId = -1;
		enabled = true;
	}

	public void clear() {
		spriteId = -1;
		localSprite = new nullSprite();
		enabled = false;
	}

	@Override
	public int getX() {
		if (shiftOrReplace) {
			return getSprite().x + x;
		}
		return x;
	}

	@Override
	public int getY() {
		if (shiftOrReplace) {
			return getSprite().y + y;
		}
		return y;
	}

	@Override
	public int getZ() {
		if (shiftOrReplace) {
			return getSprite().z + z;
		}
		return z;
	}

	private Sprite getSprite() {
		if (spriteId >= 0) {
			return StData.currentGC.currentSC.sprites[spriteId];
		}
		return localSprite;
	}

	public void setSprite(int s) {
		spriteId = s;
		enabled = true;
	}

	//return texture
	@Override
	public String getTextureKey() {
		return getSprite().textureKey;
	}

	// if textureKey == null, return custom texture
	@Override
	public Image getCustomTexture() {
		return getSprite().getCustomTexture();
	}

	// if doCustomRender, perform drawing manually
	@Override
	public boolean doCustomRender() {
		return getSprite().doCustomRender();
	}

	@Override
	public void customRender(Graphics2D G) {
		getSprite().customRender(G);
	}

}
