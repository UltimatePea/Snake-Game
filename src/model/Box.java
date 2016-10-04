package model;

import java.util.ArrayList;

public class Box {
	
	private int x, y;
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private Grid inGrid;
	private boolean borderRight, borderDown;
	
	public Box(int x, int y, Grid inGrid, boolean withBorders) {
		super();
		this.x = x;
		this.y = y;
		this.inGrid = inGrid;
		this.borderRight = withBorders;
		this.borderDown = withBorders;
	}
	
	public enum Direction{ 
		UP, DOWN, LEFT, RIGHT;
		
		private Direction opposite;
		static {
			UP.opposite = DOWN;
			LEFT.opposite = RIGHT;
			RIGHT.opposite = LEFT;
			DOWN.opposite = UP;
		}
		public Direction getOpposite(){
			return opposite;
		}
	}
	
	
	public Box box(Direction direction){
		return inGrid.get(x + (direction == Direction.RIGHT ? 1 : 0 )+ (direction == Direction.LEFT ? -1 : 0), 
				y + (direction == Direction.UP? -1 : 0) + (direction == Direction.DOWN ? 1 : 0));
	}
	
	/**
	 * gets the direction of the box
	 *  in the perspective of the receiver
	 * @param anotherBox
	 * @return
	 */
	public Direction getDirection(Box anotherBox){
		Direction result = null;
		for(Direction direction : Direction.values()){
			if (anotherBox.equals(box(direction))){
				result =  direction;
			}
		}
		if(result == null){
			try {
				throw new Exception("Unable to Determine Direction for " + this.toString() + anotherBox.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//borders
	public void addBorder(Direction direction){
		switch (direction) {
		case UP:
			this.box(Direction.UP).addBorder(Direction.DOWN);
			break;
		case LEFT:
			this.box(Direction.LEFT).addBorder(Direction.RIGHT);
			break;
		case RIGHT:
			borderRight = true;
			break;
		case DOWN:
			borderDown = true;
			break;
		default:
			break;
		}
	}
	
	public void removeBorder(Direction direction){
		switch (direction) {
		case UP:
			this.box(Direction.UP).removeBorder(Direction.DOWN);
			break;
		case LEFT:
			this.box(Direction.LEFT).removeBorder(Direction.RIGHT);
			break;
		case RIGHT:
			borderRight = false;
			break;
		case DOWN:
			borderDown = false;
			break;
		default:
			break;
		}
	}
	
	public boolean isBorderPresent(Direction direction){
		switch (direction) {
		case UP:
			return this.box(Direction.UP).isBorderPresent(Direction.DOWN);	
		case LEFT:
			return this.box(Direction.LEFT).isBorderPresent(Direction.RIGHT);
		case RIGHT:
			return borderRight;
		case DOWN:
			return borderDown;
		default:
			return false;
		}
	}
	
	public ArrayList<Box> getConnectedBoxes(){
		ArrayList<Box> box = new ArrayList<Box>();
		for (Direction direction : Direction.values()) {
			if (!isBorderPresent(direction)) {
				box.add(box(direction));
			}
		}
		return box; 
	}
	
	public String toString(){
		return "Box[" + x + "," + y + "]";
	}
}

