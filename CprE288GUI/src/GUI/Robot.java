package GUI;

import java.awt.Color;

public class Robot extends SpaceObject {

	private int degrees;
	
	public Robot(int x, int y, int size) {
		super(x, y, size, Color.BLUE);
		degrees = 0;
	}

	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}
	public void move(int distance){
		long y = -Math.round(Math.cos(degrees * Math.PI / 180)*distance);
		long x = Math.round(Math.sin(degrees * Math.PI / 180)*distance);
		System.out.println("d=" + degrees);
		System.out.println("y=" + y);
		System.out.println("x=" + x);
		coords[0] += x;
		coords[1] += y;
	}
	public void rotate(int rotation){
		degrees += rotation;
		degrees %= 360;
	}
}
