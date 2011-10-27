package tpavels.gol.test;

import junit.framework.TestCase;
import tpavels.gol.constants.Constants;
import tpavels.gol.field.impl.CellImpl;

public class CellImplTest extends TestCase {

	public void testCellImpl() {
		CellImpl cell = new CellImpl(1, 2);
		assertTrue(cell.getRow() == 1);
		assertTrue(cell.getColumn() == 2);
		assertTrue(cell.getNeighbour() == 0);
		assertTrue(cell.getState() == Constants.CellState.DEAD);
	}
	
	public void testAddNeighbour() {
		CellImpl cell = new CellImpl(0,0);
		assertTrue(cell.getNeighbour() == 0);
		cell.addNeighbour();
		cell.addNeighbour();
		assertTrue(cell.getNeighbour() == 2);
		cell.addNeighbour();
		assertTrue(cell.getNeighbour() == 3);
		
	}
	
	public void testRemoveNeighbour() {
		CellImpl cell = new CellImpl(0,0);
		assertTrue(cell.getNeighbour() == 0);
		cell.addNeighbour();
		cell.addNeighbour();
		assertTrue(cell.getNeighbour() == 2);
		cell.removeNeighbour();
		assertTrue(cell.getNeighbour() == 1);
		cell.removeNeighbour();
		assertTrue(cell.getNeighbour() == 0);
	}
	
	public void testResetNeighbourCounter() {
		CellImpl cell = new CellImpl(0,0);
		assertTrue(cell.getNeighbour() == 0);
		cell.addNeighbour();
		cell.addNeighbour();
		cell.addNeighbour();
		assertTrue(cell.getNeighbour() == 3);
		cell.resetNeighbourCounter();
		assertTrue(cell.getNeighbour() == 0);
	}


	public void testGetRow() {
		CellImpl cell = new CellImpl(2,4);
		assertTrue(cell.getRow() == 2);
	}
	
	public void testGetColumn() {
		CellImpl cell = new CellImpl(2,4);
		assertTrue(cell.getColumn() == 4);
	}
	
	public void testSetCorrectCoordinates() {
		CellImpl cell = null;
		
		cell = new CellImpl(0,-1);
		assertTrue(cell.getRow() == 0 && cell.getColumn() == Constants.COLS-1);
		cell = new CellImpl(-1,-1);
		assertTrue(cell.getRow() == Constants.ROWS-1 && cell.getColumn() == Constants.COLS-1 );
		cell = new CellImpl(-1,-0);
		assertTrue(cell.getRow() == Constants.ROWS-1 && cell.getColumn() == 0);
		
		cell = new CellImpl(-1,Constants.COLS-1);
		assertTrue(cell.getRow() == Constants.ROWS-1 && cell.getColumn() == Constants.COLS-1);
		cell = new CellImpl(-1,Constants.COLS);
		assertTrue(cell.getRow() == Constants.ROWS-1 && cell.getColumn() == 0);
		cell = new CellImpl(0,Constants.COLS);
		assertTrue(cell.getRow() == 0 && cell.getColumn() == 0);
		
		cell = new CellImpl(Constants.ROWS-1,Constants.COLS);
		assertTrue(cell.getRow() == Constants.ROWS-1 && cell.getColumn() == 0);
		cell = new CellImpl(Constants.ROWS,Constants.COLS);
		assertTrue(cell.getRow() == 0 && cell.getColumn() == 0);
		cell = new CellImpl(Constants.ROWS,Constants.COLS-1);
		assertTrue(cell.getRow() == 0 && cell.getColumn() == Constants.COLS-1);
		
		cell = new CellImpl(Constants.ROWS,0);
		assertTrue(cell.getRow() == 0 && cell.getColumn() == 0);
		cell = new CellImpl(Constants.ROWS,-1);
		assertTrue(cell.getRow() == 0 && cell.getColumn() == Constants.COLS-1);
		cell = new CellImpl(Constants.ROWS-1,-1);
		assertTrue(cell.getRow() == Constants.ROWS-1 && cell.getColumn() == Constants.COLS-1);
	}

	public void testState() {
		CellImpl cell = new CellImpl(0,0);
		assertTrue(cell.getState() == Constants.CellState.DEAD);
		cell.setLife();
		assertTrue(cell.getState() == Constants.CellState.LIFE);
		assertTrue(cell.isAlive());
		cell.setBorn();
		assertTrue(cell.getState() == Constants.CellState.toBeLIFE);
		assertTrue(cell.isBorn());
		cell.setDying();
		assertTrue(cell.getState() == Constants.CellState.toBeDEAD);
		assertTrue(cell.isDying());
		cell.setDead();
		assertTrue(cell.getState() == Constants.CellState.DEAD);
		assertTrue(cell.isDead());
	}

}
