package GUI;

import java.awt.Color;


public class SpaceObject  {
	
	protected int size;
	protected int coords[];
	protected Color color;

	public SpaceObject(int x, int y, int size, Color color) {
				
		coords = new int[2];
		coords[0] = x;
		coords[1] = y;
		this.size = size;
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public int getX(){
		return coords[0];
	}
	public int getY(){
		return coords[1];
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
