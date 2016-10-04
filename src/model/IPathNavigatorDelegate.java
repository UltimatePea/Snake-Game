package model;

public interface IPathNavigatorDelegate {
	
	/**
	 * Invoked when the snake is unable to find path for the snake
	 * i.e. the node that the snake head is on is at the end of the 
	 * given list
	 */
	public void resetPath();
	
	/**
	 * After calling reset Path, if the path still cannot be used,
	 * The Path navigator delegates the navigation to the navigator returned by this method.
	 * @return
	 */
	public ISnakeNavigator defaultNavigator();
}
