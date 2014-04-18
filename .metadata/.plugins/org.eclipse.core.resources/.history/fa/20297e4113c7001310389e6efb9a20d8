package GUI;

public class Robot extends SpaceObject {

	private int degrees;
	
	public Robot(int x, int y, int size) {
		super(x, y, size);
		degrees = 0;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}
	public void move(int distance){
		coords[1] -= distance;
	}
	public void rotate(int rotation){
		degrees += rotation;
		degrees %= 360;
	}
}
