package model;

public interface ISnakeNavigator {
	/**
	 * Moves targeted Snake a step forward, 
	 * i.e. adds a box in the start of the boxesRef.
	 * Notice this method DOES NOT REMOVE the tail.
	 * The removal should be completed by the caller of this method.
	 * @param snake
	 */
	public void navigateSnake(Snake snake);
}
