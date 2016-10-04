package model;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Path is used to navigate the snake, 
 * may contain a loop to form the looping behaviour of the snake
 * @author chenzb
 *
 */
public class Path {

	private LinkedList<Box> boxes;

	public LinkedList<Box> getBoxes() {
		return boxes;
	}

	public Path(LinkedList<Box> boxes){
		this.boxes = boxes;
	}
	
	public String toString(){
		return this.boxes.toString();
	}
}
