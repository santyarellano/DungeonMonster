package Classes;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		int scr_size = 1000;
		Window window = new Window(scr_size);
		window.setSize(scr_size, scr_size);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
