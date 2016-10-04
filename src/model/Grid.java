package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import model.Box.Direction;

public class Grid {
	private ArrayList<ArrayList<Box>> boxes = new ArrayList<ArrayList<Box>>();

	
	private int width, height;
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Grid(int width, int height, boolean withBorders){
		for (int i = 0; i < width; i++) {
			ArrayList<Box> column = new ArrayList<Box>();
			for (int j = 0; j < height; j++) {
				column.add(new Box(i, j, this, withBorders));
			}
			boxes.add(column);
		}
		this.width = width;
		this.height = height;
		
	}
	
	public Box get(int x, int y){
		return boxes.get(Math.floorMod(x, width)).get(Math.floorMod(y, height));
	}
	
	public enum WallStyle{
		BORDER, MAZE, RANDOM
	}
	
	
	ArrayList<Box> visited ;
	private void generateMazeWithCenterNode(Box box){
		
		visited.add(box);
		LinkedList<Box.Direction> directions =  new LinkedList<Box.Direction>(Arrays.asList(Direction.values()));
		if(box.getX() == 0) directions.remove(Direction.LEFT);
		if(box.getY() == 0) directions.remove(Direction.UP);
		if(box.getX() == getWidth() - 1) directions.remove(Direction.RIGHT);
		if(box.getY() == getHeight() - 1) directions.remove(Direction.DOWN);
		Collections.shuffle(directions, new Random(System.nanoTime()));
		for (Direction direction : directions){
			if(!visited.contains(box.box(direction))){
				box.removeBorder(direction);
				generateMazeWithCenterNode(box.box(direction));
			}
		}
		
		
		
	}
	
	public void addWalls(WallStyle style){
		switch (style) {
		case MAZE:
			for (int i = 0; i < width ; i++) {
				for (int j = 0; j < height ; j++) {
					get(i,j).addBorder(Direction.DOWN);
					get(i,j).addBorder(Direction.RIGHT);
				}
				
			}
			for(int i = 0; i < width; i ++){
				get(i, 0).removeBorder(Direction.UP);
				get(0, i).removeBorder(Direction.LEFT);
			}
			visited = new ArrayList<Box>();
			generateMazeWithCenterNode(get(getWidth() / 2, getHeight() / 2));//safe to pass in null 
			break;
		case BORDER:
			for (int i = 0; i < width; i++) {
				get(i, 0).addBorder(Direction.UP);
				get(0, i).addBorder(Direction.LEFT);
			}
			
			break;

		default:
			break;
		}
	}

}
