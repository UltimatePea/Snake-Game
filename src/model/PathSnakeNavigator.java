package model;

import java.util.LinkedList;

import model.Box.Direction;

public class PathSnakeNavigator implements ISnakeNavigator {

	
	private Path path;
	private IPathNavigatorDelegate delegate;

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public PathSnakeNavigator(Path path, IPathNavigatorDelegate delegate){
		this.path = path;
		this.delegate = delegate;
	}
	
	@Override
	public void navigateSnake(Snake snake) {
		// TODO Auto-generated method stub
		LinkedList<Box> boxes = path.getBoxes();
		int index;
		index = boxes.indexOf(snake.getHead());
		if( index != -1 && index != boxes.size() - 1){
			navigateSnakeWithPathIndex(snake, index);
		} else {
			this.delegate.resetPath();
			index = boxes.indexOf(snake.getHead());
			if( index != -1 && index != boxes.size() - 1){
				navigateSnakeWithPathIndex(snake, index);
			} else {
				this.delegate.defaultNavigator().navigateSnake(snake);
			}
		}
	}
	
	private void navigateSnakeWithPathIndex(Snake snake, int index){

		LinkedList<Box> boxes = path.getBoxes();
		Box box = boxes.get(index);
		Box next = boxes.get(index + 1);//garanteed not OOUTOFBOUNDS by caller
		Direction direction = box.getDirection(next);
		snake.setDirection(direction);

	}


}
