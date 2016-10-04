package model;

import java.util.LinkedList;

import model.Box.Direction;

public class Snake {
	private LinkedList<Box> boxesRef;
	private ISnakeNavigator snakeNavigator;
	private Direction currentDirection;
	
	public void setSnakeNavigator(ISnakeNavigator snakeNavigator) {
		this.snakeNavigator = snakeNavigator;
	}

	public ISnakeNavigator getSnakeNavigator() {
		return snakeNavigator;
	}
	
	public LinkedList<Box> getBoxes(){
		return boxesRef;
	}

	public Snake(LinkedList<Box> boxesRef) {
		super();
		this.boxesRef = boxesRef;
		Snake snake = this;
		snake.currentDirection = snake.getBoxes().get(1).getDirection(snake.getBoxes().get(0));
	}
	
	private void addHeadNodeWithDirection(Direction direction){
		getBoxes().addFirst(getBoxes().getFirst().box(direction));
		
	}
	
	public void moveHead(){
		snakeNavigator.navigateSnake(this);
		addHeadNodeWithDirection(currentDirection);
	}
	
	public Direction getCurrentDirection(){
		return currentDirection;
	}
	public void setDirection(Direction direction){
		if(direction != this.currentDirection.getOpposite()){
			this.currentDirection = direction;
		}
	}
	
	public void moveTail(){
		boxesRef.removeLast();
	}

	public Box getHead() {
		// TODO Auto-generated method stub
		return getBoxes().getFirst();
	}
	
	public Box getTail(){
		return getBoxes().getLast();
	}
	
}
