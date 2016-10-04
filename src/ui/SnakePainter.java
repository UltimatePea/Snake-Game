package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import model.Box;
import model.Snake;

public class SnakePainter extends NonGridPainters{
	private Snake snake;

	public SnakePainter(Snake snake) {
		this.snake = snake;
	}
	
	
	
	public void paintSnake(Graphics g){
		double total = snake.getBoxes().size();
		int i = 0;
		//copy to prevent concurrent exception (removing tails and painting happens on different threads) 
		for(Box box : (LinkedList<Box>)snake.getBoxes().clone()){
			i ++;
			paintSingleSnake(box, g, i / total );
		}
		
	}
	
	public void paintSingleSnake(Box box, Graphics g, double numIn){
		super.paintBox(box);
		int alpha = (int) ((1-numIn) * 255);
		
		g.setColor(new Color(0, 0, 0, alpha > 80? alpha : 50));
//		if(box == snake.getTail()) g.setColor(Color.WHITE);
		
		g.fillOval(atX + width / 4, atY + height / 4, width / 2, height / 2);
		
	}
}
