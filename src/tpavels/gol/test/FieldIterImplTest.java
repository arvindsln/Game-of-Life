package tpavels.gol.test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import tpavels.gol.field.Cell;
import tpavels.gol.field.FieldIter;
import tpavels.gol.field.impl.FieldImpl;

public class FieldIterImplTest extends TestCase {
	
	FieldImpl field;
	
	public void setUp() throws Exception {
		this.field = new FieldImpl();
	}

	public void tesEmptyIterator() {
		FieldIter<Cell> iterator = field.iterator();
		assertTrue("Not all cells are dead", iterator.isEmpty());
	}
	
	public void testSetLife() {
		field.clearField();
		assertTrue(field.getCell(0, 2).isDead());
		
		FieldIter<Cell> iterator = field.iterator();
		iterator.hasNext();
		iterator.next(); // 0 0
		iterator.hasNext();
		iterator.next(); // 0 1
		iterator.hasNext();
		iterator.next(); // 0 2
	    iterator.setLife();
	    assertTrue("Cell is not born", field.getCell(0, 2).isBorn());
	
	}
	
	public void testSetDead() {
		field.clearField();
		assertTrue(field.getCell(0, 3).isDead());
		
		FieldIter<Cell> iterator = field.iterator();
		iterator.hasNext();
		iterator.next(); // 0 0
		iterator.hasNext();
		iterator.next(); // 0 1
		iterator.hasNext();
		iterator.next(); // 0 2
		iterator.hasNext();
		iterator.next(); // 0 3
	    iterator.setDead();
	    assertTrue("Cell is not dying", field.getCell(0, 3).isDying());
	
	}
	

	public void testIterateOverAllDeadCells() {
		field.clearField();
		final int totalNumberOfCells = field.COLS * field.ROWS;
		final Set<Cell> counter = new HashSet<Cell>(totalNumberOfCells);
		FieldIter<Cell> iterator = field.iterator();
		while(iterator.hasNext()){
			Cell cell = iterator.next();
			assertTrue("Cell is not dead", cell.isDead());
			counter.add(cell);
			
		}
		assertTrue("Iterated over "+counter.size()+" of dead cells,but there are only "+totalNumberOfCells+"in the field!",
				counter.size() == totalNumberOfCells);
	}

}
