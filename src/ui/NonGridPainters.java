package ui;

import model.Box;
/**
 * Abstract class for painting something onto the grid.
 * Usually for painters painting <code>Collection<model.Box></code>.
 * <code>paintBox(box)</code> should be invoked prior to the painting of the specific box,
 * in order to get <code>width, height, atX, atY</code> property updated.
 * 
 * Subclass code routine is that
 * 
 * <code><br>
 * class A extends NonGridPainters{<br>
 * 		private Collection<model.Box> boxes;<br>
 * 		public A(Collection<model.Box> boxes){<br>
 * 			this.boxes = boxes;<br>
 * 		}<br>
 * 		public void paintAitems(Graphics g){<br>
 * 			for(model.Box box : boxes){<br>
 * 				paintAitem(box, g);<br>
 * 			}<br>
 * 		}<br>
 * 		private void paintAitem(model.Box box, Graphics g){<br>
 * 			<i>super.paintBox(box)</i>;<br>
 * 			g....<br>
 * 			//custom drawing codes using width, height, atX, atY fields<br>
 * 		}<br>
 * }<br>
 * </code>
 * @author chenzb
 *
 */
public abstract class NonGridPainters {

	
	int width = GridPainter.BOX_WIDTH;
	int height = GridPainter.BOX_HEIGHT;
	int atX ;
	int atY ;
	
	protected void paintBox(Box box){
		atX = box.getX() * width;
		atY = box.getY() * height;
	}
}
