package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Poison extends GameObj {
	// Instance variables
	private final double grv = 1.2;
	private int frameCnt, framePos, frameChange;
	private boolean exists;
	private double hsp, vsp;
	private BufferedImage sprIdle, sprStp1, sprStp2, sprStp3, sprStp4, sprStp5, sprStp6, sprStp7, sprStp8, sprStp9,
			sprStp10, sprStp11, sprStp12, sprStp13, sprStp14, sprStp15, sprStp16, sprStp17, sprite;

	// ----------------------------- CONSTRUCTOR ---------------------------
	public Poison(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height, false, "Poison");
		exists = false;
		hsp = 0;
		vsp = 0;
		frameCnt = 0;
		framePos = 0;
		frameChange = 5;
		// Get images
		File fSprIdle = new File("/FX/poison/idle.png");
		File fSprStp1 = new File("/FX/poison/1.png");
		File fSprStp2 = new File("/FX/poison/2.png");
		File fSprStp3 = new File("/FX/poison/3.png");
		File fSprStp4 = new File("/FX/poison/4.png");
		File fSprStp5 = new File("/FX/poison/5.png");
		File fSprStp6 = new File("/FX/poison/6.png");
		File fSprStp7 = new File("/FX/poison/7.png");
		File fSprStp8 = new File("/FX/poison/8.png");
		File fSprStp9 = new File("/FX/poison/9.png");
		File fSprStp10 = new File("/FX/poison/10.png");
		File fSprStp11 = new File("/FX/poison/11.png");
		File fSprStp12 = new File("/FX/poison/12.png");
		File fSprStp13 = new File("/FX/poison/13.png");
		File fSprStp14 = new File("/FX/poison/14.png");
		File fSprStp15 = new File("/FX/poison/15.png");
		File fSprStp16 = new File("/FX/poison/16.png");
		File fSprStp17 = new File("/FX/poison/17.png");
		try {
			sprIdle = ImageIO.read(fSprIdle);
			sprStp1 = ImageIO.read(fSprStp1);
			sprStp2 = ImageIO.read(fSprStp2);
			sprStp3 = ImageIO.read(fSprStp3);
			sprStp4 = ImageIO.read(fSprStp4);
			sprStp5 = ImageIO.read(fSprStp5);
			sprStp6 = ImageIO.read(fSprStp6);
			sprStp7 = ImageIO.read(fSprStp7);
			sprStp8 = ImageIO.read(fSprStp8);
			sprStp9 = ImageIO.read(fSprStp9);
			sprStp10 = ImageIO.read(fSprStp10);
			sprStp11 = ImageIO.read(fSprStp11);
			sprStp12 = ImageIO.read(fSprStp12);
			sprStp13 = ImageIO.read(fSprStp13);
			sprStp14 = ImageIO.read(fSprStp14);
			sprStp15 = ImageIO.read(fSprStp15);
			sprStp16 = ImageIO.read(fSprStp16);
			sprStp17 = ImageIO.read(fSprStp17);
			sprite = sprIdle;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ----------------------------- CONSTRUCTOR ---------------------------

	// -------------------------- METHODS -------------------------------
	public void update() {
		if (exists) {
			move();
		} else if (isVisible) {
			frameCnt++;
			if (frameCnt == frameChange) {
				frameCnt = 0;
				framePos++;
			}
			switch (framePos) {
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
				case 4:
					sprite = sprStp5;
					break;
				case 5:
					sprite = sprStp6;
					break;
				case 6:
					sprite = sprStp7;
					break;
				case 7:
					sprite = sprStp8;
					break;
				case 8:
					sprite = sprStp9;
					break;
				case 9:
					sprite = sprStp10;
					break;
				case 10:
					sprite = sprStp11;
					break;
				case 11:
					sprite = sprStp12;
					break;
				case 12:
					sprite = sprStp13;
					break;
				case 13:
					sprite = sprStp14;
					break;
				case 14:
					sprite = sprStp15;
					break;
				case 15:
					sprite = sprStp16;
					break;
				case 16:
					sprite = sprStp17;
					break;
				case 17:
					sprite = sprIdle;
					exists = true;
					width /= 2;
					width /= 2;
					height /= 2;
					height /= 2;
					xPos += width;
					yPos += height;
					break;
			}
		}
	}

	public void move() {
		vsp += grv;
		xPos += hsp;
		yPos += vsp;
	}

	public void create(int xPos, int yPos, int hsp, int vsp, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.hsp = hsp;
		this.vsp = vsp;
		this.width = width;
		this.height = height;
		framePos = 0;
		isVisible = true;
	}

	public void paint(Graphics g) {
		g.drawImage(sprite, xPos, yPos, width, height, null);
	}

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public double getHsp() {
		return hsp;
	}

	public void setHsp(double hsp) {
		this.hsp = hsp;
	}

	public double getVsp() {
		return vsp;
	}

	public void setVsp(double vsp) {
		this.vsp = vsp;
	}
	// -------------------------- METHODS -------------------------------

}
