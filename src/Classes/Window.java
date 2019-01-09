package Classes;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame {
	// Main variables
	MainCanvas canvas;
	Color bgColor;
	
	// Constructors
	public Window(int scr_size) {
		super();
		this.setLayout(new BorderLayout());
		canvas = new MainCanvas(scr_size);
		this.add(canvas, BorderLayout.CENTER);
	}

	//--------------------- METHODS ---------------------------
	//--------------------- METHODS ---------------------------
}
