package ui;

import java.awt.Graphics;

import model.Box;
import model.Box.Direction;
import model.Grid;

public class GridPainter {
	private Grid grid;
	
	public GridPainter(Grid grid){
		this.grid = grid;
	}
	public static final int BOX_WIDTH = 30, BOX_HEIGHT = 30;
	public int atXForBox(Box box){
		return BOX_WIDTH * box.getX();
	}
	public int atYForBox(Box box){
		return BOX_HEIGHT * box.getY();
	}
	public void paintGrid(Graphics g){

		for (int i = 0; i < grid.getWidth(); i++) {
			for (int j = 0; j < grid.getHeight() ; j++) {
				BoxPainter painter = new BoxPainter(grid.get(i, j));
				painter.paintBox(i * BOX_WIDTH, j * BOX_HEIGHT, g);
			}
		}
	}
	
	
	
	
	
	private class BoxPainter {
		private Box box;

		public BoxPainter(Box box){
			this.box = box;
		}
		public void paintBox(int atX, int atY, Graphics g){
			int arcLen = (int) (Math.sqrt(5) * BOX_WIDTH / 4);
			int smallAngle = (int) Math.toDegrees(Math.atan(1 / 2));
			
			int arcAngle = 90 - 2 * smallAngle;
//			g.drawArc(atX + BOX_WIDTH / 4, atY + BOX_HEIGHT / 4, arcLen, arcLen, -90 + smallAngle, arcAngle);
//			g.drawArc(atX + BOX_WIDTH - BOX_WIDTH / 4, atY + BOX_HEIGHT / 4, arcLen, arcLen, -180 + smallAngle, arcAngle);
//			g.drawArc(atX + BOX_WIDTH / 4, atY + BOX_HEIGHT - BOX_HEIGHT / 4, arcLen, arcLen, smallAngle, arcAngle);
//			g.drawArc(atX + BOX_WIDTH - BOX_WIDTH / 4, atY + BOX_HEIGHT - BOX_HEIGHT / 4, arcLen, arcLen, 90 + smallAngle, arcAngle);
			
			g.drawArc(atX, atY , arcLen, arcLen, -90 + smallAngle, arcAngle);
			g.drawArc(atX + BOX_WIDTH / 2, atY , arcLen, arcLen, -180 + smallAngle, arcAngle);
			g.drawArc(atX , atY + BOX_HEIGHT / 2, arcLen, arcLen, smallAngle, arcAngle);
			g.drawArc(atX + BOX_WIDTH / 2, atY + BOX_HEIGHT / 2, arcLen, arcLen, 90 + smallAngle, arcAngle);
			
			
			if(box.isBorderPresent(Direction.UP)){
				g.drawLine(atX, atY, atX + BOX_WIDTH, atY);
			}
			if(box.isBorderPresent(Direction.RIGHT)){
				g.drawLine(atX + BOX_WIDTH, atY, atX + BOX_WIDTH, atY + BOX_HEIGHT);
			}
			if(box.isBorderPresent(Direction.LEFT)){
				g.drawLine(atX, atY, atX, atY + BOX_HEIGHT);
			}
			if(box.isBorderPresent(Direction.DOWN)){
				g.drawLine(atX, atY + BOX_HEIGHT, atX + BOX_WIDTH, atY + BOX_HEIGHT);
			}
		}
	}
}
