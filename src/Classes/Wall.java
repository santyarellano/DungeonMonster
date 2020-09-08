package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends GameObj {
	// Instance variables
	private BufferedImage sprite;

	// ----------------------------- CONSTRUCTOR ---------------------------------
	public Wall(int xPos, int yPos, int width, int height, boolean isVisible, String id) {
		super(xPos, yPos, width, height, isVisible, id);

		File fSprite = new File("res/Images/Pisos/wall.png");
		try {
			sprite = ImageIO.read(fSprite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ----------------------------- CONSTRUCTOR ---------------------------------

	// --------------------------- METHODS ------------------------------
	public void paint(Graphics g) {
		g.drawImage(sprite, xPos, yPos, width, height, null);
	}

	public BufferedImage getImage() {
		return sprite;
	}

	public void setImage(BufferedImage image) {
		this.sprite = image;
	}
	// --------------------------- METHODS ------------------------------

}
