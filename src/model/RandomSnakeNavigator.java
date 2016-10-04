package model;

import java.util.ArrayList;

import model.Box.Direction;

public class RandomSnakeNavigator implements ISnakeNavigator {

	@Override
	public void navigateSnake(Snake snake) {
		// TODO Auto-generated method stub
		System.out.println("Random Navigation!!!");
		ArrayList<Direction> directions = new ArrayList<Box.Direction>();
		for(Direction dir : Direction.values()){
			if(!snake.getBoxes().contains(snake.getHead().box(dir))){
				directions.add(dir);
				
			}
		}
		if(directions.size() > 0)
		snake.setDirection(directions.get((int)(Math.random() * directions.size())));
	}

}
