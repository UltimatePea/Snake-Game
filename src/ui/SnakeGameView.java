package ui;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;

import main.SnakeGameController;

public class SnakeGameView extends JPanel {
	private SnakeGameController controller;
	
	public SnakeGameView(SnakeGameController controller){
		super();
		this.controller = controller;
		setSize(200, 200);
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		new GridPainter(controller.getGrid()).paintGrid(g);
		new SnakePainter(controller.getSnake()).paintSnake(g);
		new FoodPainter(controller.getFoods().getFoods()).paintFoods(g);
	}
}
