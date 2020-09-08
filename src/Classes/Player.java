package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Character {
	// Instance variables
	private static final int sprite_spd = 13;
	private int frameCnt, spr_pos;
	private BufferedImage sprIdle, sprStp1, sprStp3, sprite;

	// ------------------------------ CONSTRUCTOR
	// -----------------------------------
	public Player(int xPos, int yPos, int width, int height, int health, int jmpPow, boolean isAlive, boolean isVisible,
			String id) {
		super(xPos, yPos, width, height, health, sprite_spd, jmpPow, isAlive, isVisible, id);
		frameCnt = 0;
		spr_pos = 0;

		// Get images
		File fSprIdle = new File("res/Images/Hero/Hero.png");
		File fSprStp1 = new File("res/Images/Hero/Hero_STPONE.png");
		File fSprStp3 = new File("res/Images/Hero/Hero_STP3.png");
		try {
			sprIdle = ImageIO.read(fSprIdle);
			sprStp1 = ImageIO.read(fSprStp1);
			sprStp3 = ImageIO.read(fSprStp3);
			sprite = sprIdle;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ------------------------------ CONSTRUCTOR
	// -----------------------------------

	// ------------------------------ METHODS -----------------------------------
	public void paint(Graphics g) {
		g.drawImage(sprite, xPos, yPos, width, height, null);
	}

	public void run() {
		if (hsp != 0) {
			if (hsp > 0 && width > 0) {
				width *= -1;
				xPos -= width;
			} else if (hsp < 0 && width < 0) {
				width *= -1;
				xPos -= width;
			}
			frameCnt++;
			if (!jumping) {
				if (frameCnt > sprite_spd) {
					frameCnt = 0;
					spr_pos++;
					if (spr_pos > 1) {
						spr_pos = 0;
					}
					switch (spr_pos) {
						case 0:
							sprite = sprStp1;
							break;
						case 1:
							sprite = sprStp3;
							break;
					}
				}
			} else {
				sprite = sprStp1;
			}
		} else {
			if (!jumping) {
				sprite = sprIdle;
			} else {
				sprite = sprStp1;
			}
		}
		move();
	}

	public BufferedImage getImage() {
		return sprite;
	}

	public void setImage(BufferedImage image) {
		this.sprite = image;
	}
	// ------------------------------ METHODS -----------------------------------
}
