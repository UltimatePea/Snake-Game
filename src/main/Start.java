package main;

import javax.swing.JFrame;

public class Start {
	/**
	 * Games configuration should be initialized through passed in params
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SnakeGameController snakeGameController = new SnakeGameController();
		
		
		JFrame frame = new JFrame("Smart Snake");
		frame.setLocation(100, 100);
		frame.setSize(400, 400);
		frame.add(snakeGameController.getGameView());
		frame.setVisible(true);
		snakeGameController.startGame();
	}

}
