package GUI;

import java.util.ArrayList;

public class Scan {
	
	private ArrayList<SpaceObject> objects = new ArrayList<SpaceObject>();
	
	public void addObject(SpaceObject obj){
		objects.add(obj);
	}
	public ArrayList<SpaceObject> getDetectedObjects(){
		return objects;
	}
	
}
