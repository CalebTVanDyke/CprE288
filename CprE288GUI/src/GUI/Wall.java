package GUI;

public class Wall {
	
	private int coords1[];
	private int coords2[];
	
	public Wall(int robotX, int robotY, int degrees){
		
		coords1 = new int[2];
		coords2 = new int[2];
		
		int adj = MainFrame.WIDTH - robotX;
		int hyp = (int) Math.round(adj/Math.cos(degrees*Math.PI/180));
		int opp = (int) Math.round(Math.sqrt(Math.pow(hyp, 2)- Math.pow(adj, 2)));
		coords1[0] = adj;
		coords1[1] = opp;
		coords2[0] = robotX;
		coords2[1] = robotY;
		System.out.println();
		System.out.println("x1 : " + coords1[0] + " y1: " + coords1[1]);
		System.out.println("x2 : " + coords2[0] + " y2: " + coords2[1]);
	}

	public int[] getCoords1() {
		return coords1;
	}

	public void setCoords1(int[] coords1) {
		this.coords1 = coords1;
	}

	public int[] getCoords2() {
		return coords2;
	}

	public void setCoords2(int[] coords2) {
		this.coords2 = coords2;
	}
	
}
