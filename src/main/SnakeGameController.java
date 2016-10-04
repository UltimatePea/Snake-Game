package main;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.hamcrest.core.IsInstanceOf;

import ui.SnakeGameView;
import model.Box;
import model.Foods;
import model.Grid;
import model.IPathNavigatorDelegate;
import model.ISnakeNavigator;
import model.KeyboardSnakeNavigator;
import model.Path;
import model.PathGeneratorBFS;
import model.PathSnakeNavigator;
import model.RandomSnakeNavigator;
import model.Snake;
import model.Grid.WallStyle;

public class SnakeGameController {
	
	public Grid getGrid(){
		if(this.grid == null){
			this.grid = new Grid(20,20, false);
//			this.grid.addWalls(WallStyle.MAZE);
			this.grid.addWalls(WallStyle.BORDER);
		}
		return this.grid;
	}
	/**
	 * This method allocates and returns a one-dimensional list of boxes (shallow copy)
	 * @return
	 */
	public ArrayList<Box> getAllBoxes(){
		ArrayList<Box> availableBoxes = new ArrayList<Box>();
		for (int i = 0; i < getGrid().getWidth(); i++) {
			for (int j = 0; j < getGrid().getHeight() ; j++) {
				availableBoxes.add(getGrid().get(i, j));
			}
		}
		return availableBoxes;
	}
	
	private SnakeGameView gameView;
	private Snake snake;
	private Grid grid;
	private Foods  foods;
	
	public Foods getFoods() {
		return foods;
	}

	public SnakeGameView getGameView() {
		return gameView;
	}

	public SnakeGameController(){
		this.gameView = new SnakeGameView(this);
		this.foods = new Foods(this);
		this.foods.addRandom();
	}

	public Snake getSnake() {
		// TODO Auto-generated method stub
		if(this.snake == null){
			Grid grid = getGrid();
			LinkedList<Box> boxes = new LinkedList<Box>();
			boxes.addFirst(grid.get(0, 0));
			boxes.addLast(grid.get(1, 0));
			boxes.addLast(grid.get(2, 0));
		
			this.snake = new Snake(boxes);
			
			return this.snake ;
		}
		return this.snake;
	}
	PathGeneratorBFS pathGeneratorBFS;
	private void setupNavigators(){
		/* When navigators get created, Path gets calculated
		 * Therefore, it is imperative that the grid is set before
		 * snake navigator (path navigators) gets set.
		 * As this is, getGrid() is invoked before setSnakeNavigator
		 */
		
		this.snake.setSnakeNavigator(new KeyboardSnakeNavigator(snake, getGameView()));
//		pathGeneratorBFS = new PathGeneratorBFS(this);
//		this.snake.setSnakeNavigator(new PathSnakeNavigator(pathGeneratorBFS.getPath(), new IPathNavigatorDelegate() {
//			
//			@Override
//			public void resetPath() {
//				// TODO Auto-generated method stub
//				pathGeneratorBFS.updatePath();
//				((PathSnakeNavigator)getSnake().getSnakeNavigator()).setPath(pathGeneratorBFS.getPath());
//			}
//			
//			@Override
//			public ISnakeNavigator defaultNavigator() {
//				// TODO Auto-generated method stub
//				return new RandomSnakeNavigator();
//			}
//		}));
	}
	private int score = 0;
	
	
	
	public void startGame(){
		 new Runnable() {
			public void run() {
				setupNavigators();
				getGameView().setFocusable(true);
				if(getGameView().requestFocusInWindow()){System.out.println("Focus failed");};
				boolean continuing = true;
				while(continuing){
					
					getSnake().moveHead();
					if(foods.getFoods().remove(snake.getHead())){
						getFoods().addRandom();
						score ++;
						if(snake.getSnakeNavigator() instanceof PathSnakeNavigator){
							pathGeneratorBFS.updatePath();
							((PathSnakeNavigator)snake.getSnakeNavigator()).setPath(pathGeneratorBFS.getPath());
						}
					} else {
						getSnake().moveTail();
					}
					
					LinkedList<Box> body = ((LinkedList<Box>)(snake.getBoxes().clone()));
					body.removeFirst();
					if(/*snake.getHead().isBorderPresent(snake.getHead().getDirection(snake.getBoxes().get(1)))
						||*/body.contains(snake.getHead())
							){
						continuing = false;
						System.out.println("Failed, Your score is " + score);
						break;
					} else {
					
						getGameView().repaint();
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.run();
	}
	
	public  Path getDumbPath(){
		return new Path(new LinkedList<Box>(Arrays.asList(getGrid().get(0,0),
				getGrid().get(0,1),getGrid().get(0,2),getGrid().get(0,3),getGrid().get(0,4),getGrid().get(0,5),getGrid().get(0,6),
				getGrid().get(0,7),getGrid().get(0,8),getGrid().get(0,9),getGrid().get(1,9),getGrid().get(1,8),getGrid().get(1,7),
				getGrid().get(1,6),getGrid().get(1,5),getGrid().get(1,4),getGrid().get(1,3),getGrid().get(1,2),getGrid().get(1,1),
				getGrid().get(2,1),getGrid().get(2,2),getGrid().get(2,3),getGrid().get(2,4),getGrid().get(2,5),getGrid().get(2,6),getGrid().get(2,7),getGrid().get(2,8),getGrid().get(2,9),getGrid().get(3,9),getGrid().get(3,8),getGrid().get(3,7),getGrid().get(3,6),getGrid().get(3,5),getGrid().get(3,4),getGrid().get(3,3),getGrid().get(3,2),getGrid().get(3,1),getGrid().get(4,1),getGrid().get(4,2),getGrid().get(4,3),getGrid().get(4,4),getGrid().get(4,5),getGrid().get(4,6),getGrid().get(4,7),getGrid().get(4,8),getGrid().get(4,9),getGrid().get(5,9),getGrid().get(5,8),getGrid().get(5,7),getGrid().get(5,6),getGrid().get(5,5),getGrid().get(5,4),getGrid().get(5,3),getGrid().get(5,2),getGrid().get(5,1),getGrid().get(6,1),getGrid().get(6,2),getGrid().get(6,3),getGrid().get(6,4),getGrid().get(6,5),getGrid().get(6,6),getGrid().get(6,7),getGrid().get(6,8),getGrid().get(6,9),getGrid().get(7,9),getGrid().get(7,8),getGrid().get(7,7),getGrid().get(7,6),getGrid().get(7,5),getGrid().get(7,4),getGrid().get(7,3),getGrid().get(7,2),getGrid().get(7,1),getGrid().get(8,1),getGrid().get(8,2),getGrid().get(8,3),getGrid().get(8,4),getGrid().get(8,5),getGrid().get(8,6),getGrid().get(8,7),getGrid().get(8,8),getGrid().get(8,9),getGrid().get(9,9),getGrid().get(9,8),getGrid().get(9,7),getGrid().get(9,6),getGrid().get(9,5),getGrid().get(9,4),getGrid().get(9,3),getGrid().get(9,2),getGrid().get(9,1)
				,
				getGrid().get(9, 0),getGrid().get(8, 0),getGrid().get(7, 0),getGrid().get(6, 0),getGrid().
				get(5, 0),getGrid().get(4, 0),getGrid().get(3, 0),getGrid().get(2, 0),getGrid().get(1, 0),
				getGrid().get(0, 0))));
	}
}
