package model;

import java.util.ArrayList;
import java.util.LinkedList;

import main.SnakeGameController;

public class PathGeneratorBFS implements IPathGenerator {

	private SnakeGameController game;
	private Path path;

	public PathGeneratorBFS(SnakeGameController controller){
		this.game = controller;
		this.path = new Path(new LinkedList<Box>());
		this.updatePath();
	}
	private Path proposedPath;
	
	ArrayList<Box> visited;
	@Override
	public void updatePath() {
		
		visited = new ArrayList<Box>();
		visited.addAll(game.getSnake().getBoxes());
		path = new Path(new LinkedList<Box>());
		proposedPath = new Path(new LinkedList<Box>());
		bfsSearch(game.getSnake().getHead());
	}
	
	private boolean bfsSearch(Box startingBox){
		visited.add(startingBox);
		proposedPath.getBoxes().addLast(startingBox);
		ArrayList<Box> boxes = startingBox.getConnectedBoxes();
		boxes.removeAll(visited);
		if(boxes.size() == 0){
			 if(proposePath(proposedPath)){
				 proposedPath.getBoxes().removeLast();
					visited.remove(startingBox);
					return true;
			};
		} else {
			for(Box box : boxes){
				if (bfsSearch(box)){
					proposedPath.getBoxes().removeLast();
					visited.remove(startingBox);
					return true;
				}
			}
		}
		proposedPath.getBoxes().removeLast();
		visited.remove(startingBox);
		return false;
	}
	//returning true if food has been found
	private boolean proposePath(Path newPath){
		 Box food = game.getFoods().getFoods().get(0);//warning: assuming ONE food
		 if(newPath.getBoxes().contains(food) && !path.getBoxes().contains(food)){//newpath has old not
			 setNewPath(newPath);
			 return true;
		 } else if (!newPath.getBoxes().contains(food) && path.getBoxes().contains(food)) {//old has new not
			return false;//rejects
		 } else if(!newPath.getBoxes().contains(food) && !path.getBoxes().contains(food)){//both not
			if(newPath.getBoxes().size() > path.getBoxes().size()){
				setNewPath(newPath);
				return false;
			}
			return false;
		 } else if(newPath.getBoxes().contains(food) && path.getBoxes().contains(food)){//both have
			 if(newPath.getBoxes().indexOf(food) <  path.getBoxes().indexOf(food)){
				 setNewPath(newPath);
				 return true;
			}
			 return false;
		 }
		 System.out.println("[BUG] Control should not reach here. ");
		 return false;
	}
	
	private void setNewPath(Path newPath){
		@SuppressWarnings("unchecked")
		LinkedList<Box> clone = (LinkedList<Box>) newPath.getBoxes().clone();
		path = new Path(clone);
	}

	public Path getPath() {
		return this.path;
	}
}
