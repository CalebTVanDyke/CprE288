package GUI;

import java.util.ArrayList;

public class Scan {
	
	private static int SCAN_COUNT = 1;
	private int count;
	
	public Scan(){
		count = SCAN_COUNT;
		SCAN_COUNT++;
	}
	
	private ArrayList<SpaceObject> objects = new ArrayList<SpaceObject>();
	
	public void addObject(SpaceObject obj){
		objects.add(obj);
	}
	public ArrayList<SpaceObject> getDetectedObjects(){
		return objects;
	}
	@Override
	public String toString() {
		return "Scan " + count;
	}
}
