package Classes;

import java.awt.Graphics;
import java.util.LinkedList;

public class Walls {
	// Instance variables
	private LinkedList<Wall> walls;
	private int number;
	
	//---------------------- CONSTRUCTOR ------------------------
	public Walls() {
		walls = new LinkedList<Wall>();
		number = 0;
	}
	//---------------------- CONSTRUCTOR ------------------------
	
	//---------------------- METHODS -----------------------
	public void addWall(int xPos, int yPos, int size) {
		String wallId = "wall" + number;
		Wall wall = new Wall(xPos, yPos, size, size, true, wallId);
		walls.push(wall);
	}
	
	public void paint(Graphics g) {
		Wall wall;
		for (int i = 0; i < walls.size(); i++) {
			wall = walls.get(i);
			wall.paint(g);
		}
	}
	
	public int getLength() {
		return walls.size();
	}
	
	public Wall getWall(int i) {
		return walls.get(i);
	}
	
	public void deleteWall() {
		walls.pop();
	}
	//---------------------- METHODS -----------------------
}
