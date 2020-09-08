package Classes;

public class Tools {
	// Instance variables
	
	//----------------------- CONSTRUCTOR --------------------------
	public Tools() {
		
	}
	//----------------------- CONSTRUCTOR --------------------------
	
	//----------------------- METHODS -------------------------
	public boolean place_meeting(double x, double y, GameObj g) {
		if (x > g.getxPos() && x < (g.getxPos() + g.getWidth())) {
			if (y > g.getyPos() && y < (g.getyPos() + g.getHeight())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double min(double x1, double x2) {
		if (x1 < x2) {
			return x1;
		} else if (x2 < x1) {
			return x2;
		} else {
			return 0;
		}
	}
	
	public double max(double x1, double x2) {
		if (x1 > x2) {
			return x1;
		} else if (x2 > x1) {
			return x2;
		} else {
			return 0;
		}
	}
	
	public int sign(double num) {
		if (num > 0) {
			return 1;
		} else if (num < 0) {
			return -1;
		} else {
			return 0;
		}
	}

}
