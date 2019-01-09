package Classes;

import java.awt.Graphics;

public class GameObj {
	// Instance variables
	protected int xPos, yPos, width, height;
	protected boolean isVisible;
	protected String id;
	
	//-------------------- Constructor -------------------------
	public GameObj(int xPos, int yPos, int width, int height, boolean isVisible, String id) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.isVisible = isVisible;
		this.id = id;
	}
	//-------------------- Constructor -------------------------
	
	//-------------------------- METHODS ---------------------------
	public void paint(Graphics g) {
		
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
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	//-------------------------- METHODS ---------------------------

}
