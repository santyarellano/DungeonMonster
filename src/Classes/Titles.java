package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Titles {
	// Instance variables
	private int xPos, yPos, width, height;
	private BufferedImage sprBg, sprWin, sprLoose, sprPlay, sprTitle;
	private int state = 1; // 1 = main menu | 2 = win | 3 = loose

	// --------------------- CONSTRUCTOR -----------------------------
	public Titles(int xPos, int yPos, int width, int height) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		// Get images
		File fSprBg = new File("../Images/Titles/bg.png");
		File fSprTitle = new File("../Images/Titles/MainMenu/DunMons.png");
		File fSprWin = new File("../Images/Titles/MainMenu/win.png");
		File fSprLoose = new File("../Images/Titles/MainMenu/loose.png");
		File fSprPlay = new File("../Images/Titles/MainMenu/play.png");
		try {
			sprBg = ImageIO.read(fSprBg);
			sprWin = ImageIO.read(fSprWin);
			sprLoose = ImageIO.read(fSprLoose);
			sprPlay = ImageIO.read(fSprPlay);
			sprTitle = ImageIO.read(fSprTitle);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// --------------------- CONSTRUCTOR -----------------------------

	// ------------------------ METHODS ----------------------------
	public void paint(Graphics g) {
		g.drawImage(sprBg, xPos, yPos - 20, width, height, null);
		g.drawImage(sprPlay, 75, 820, 800, 120, null);
		switch (state) {
			case 1:
				g.drawImage(sprTitle, 50, 50, 870, 180, null);
				break;
			case 2:
				g.drawImage(sprWin, 50, 50, 870, 180, null);
				break;
			case 3:
				g.drawImage(sprLoose, 50, 50, 870, 180, null);
				break;
		}
	}

	public void setState(int s) {
		this.state = s;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	// ------------------------ METHODS ----------------------------

}
