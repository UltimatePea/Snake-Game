package model;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Box.Direction;

public class KeyboardSnakeNavigator implements ISnakeNavigator{
	private Snake snake;
	private Direction currentDirection;

	public KeyboardSnakeNavigator(Snake snake, Component comp){
		this.snake = snake;
		this.currentDirection = snake.getCurrentDirection();
		
		
		comp.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
//					currentDirection = Direction.UP;// == currentDirection.getOpposite()? currentDirection : Direction.UP;
					snake.setDirection(Direction.UP);
					break;

				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
//					currentDirection = Direction.LEFT;// == currentDirection.getOpposite()? currentDirection : Direction.LEFT;
					snake.setDirection(Direction.LEFT);
					break;
					
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
//					currentDirection = Direction.RIGHT;// == currentDirection.getOpposite()? currentDirection : Direction.RIGHT;
					snake.setDirection(Direction.RIGHT);
					break;
					
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
//					currentDirection = Direction.DOWN;// == currentDirection.getOpposite()? currentDirection : Direction.DOWN;
					snake.setDirection(Direction.DOWN);
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public void navigateSnake(Snake snake) {
		// TODO Auto-generated method stub
//		snake.setDirection(currentDirection);//what if the direction changes twice
	}
}
