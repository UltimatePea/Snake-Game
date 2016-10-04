import static org.junit.Assert.*;
import model.Box;
import model.Box.Direction;
import model.Grid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestGrid {

	public TestGrid() {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Grid grid = new Grid(10, 10, true);
		Box topLeft = grid.get(0, 0);
		assertSame( topLeft ,grid.get(0, 9).box(Direction.DOWN));
		assertSame(topLeft, grid.get(9, 0).box(Direction.RIGHT));
	}

}
