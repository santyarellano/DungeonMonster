package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Character {
	// Instance variables
	private static final int sprite_spd = 10;
	private int frameCnt, spr_pos;
	private BufferedImage sprIdle, sprStp1, sprStp2, sprStp3, sprStp4, sprAir, sprite;
	private boolean shooting;

	// ----------------------------- CONSTRUCTOR ----------------------------
	public Enemy(int xPos, int yPos, int width, int height, int health) {
		super(xPos, yPos, width, height, health, sprite_spd, 0, true, true, "Enemy");
		frameCnt = 0;
		spr_pos = 0;
		hsp = -1;
		shooting = false;
		// Get images
		File fSprIdle = new File("../Images/Monster/monsterIdle.png");
		File fSprStp1 = new File("../Images/Monster/monsterW1.png");
		File fSprStp2 = new File("../Images/Monster/monsterW2.png");
		File fSprStp3 = new File("../Images/Monster/monsterW3.png");
		File fSprStp4 = new File("../Images/Monster/monsterW4.png");
		File fSprAir = new File("../Images/Monster/monsterAir.png");
		try {
			sprIdle = ImageIO.read(fSprIdle);
			sprStp1 = ImageIO.read(fSprStp1);
			sprStp2 = ImageIO.read(fSprStp2);
			sprStp3 = ImageIO.read(fSprStp3);
			sprStp4 = ImageIO.read(fSprStp4);
			sprAir = ImageIO.read(fSprAir);
			sprite = sprIdle;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ----------------------------- CONSTRUCTOR ----------------------------

	// ---------------------------- METHODS --------------------------
	public void run() {
		if (hsp != 0) {
			frameCnt++;
			if (inFloor) {
				if (frameCnt > sprite_spd) {
					frameCnt = 0;
					spr_pos++;
					if (spr_pos > 3) {
						spr_pos = 0;
					}
					switch (spr_pos) {
						case 0:
							sprite = sprStp1;
							break;
						case 1:
							sprite = sprStp2;
							break;
						case 2:
							sprite = sprStp3;
							break;
						case 3:
							sprite = sprStp4;
							break;
					}
				}
			} else {
				sprite = sprAir;
			}
		} else {
			if (inFloor) {
				sprite = sprIdle;
			} else {
				sprite = sprAir;
			}
		}
		move();
	}

	public void paint(Graphics g) {
		g.drawImage(sprite, xPos, yPos, width, height, null);
	}

	public BufferedImage getImage() {
		return sprite;
	}

	public void setImage(BufferedImage image) {
		this.sprite = image;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	// ---------------------------- METHODS --------------------------

}
