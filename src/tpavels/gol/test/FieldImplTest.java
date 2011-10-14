package tpavels.gol.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.impl.FieldImpl;

public class FieldImplTest implements Constants {
	
	FieldImpl field;
	
	@Before
	public void setUp() throws Exception {
		this.field = new FieldImpl();
	}
	
	@Test
	public void testCreateField(){
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (!cell.isDead()){
					fail("there is at leas one non dead cell. " +
							"" + cell.getState() + ", x: " + cell.getX() + ", y: " + cell.getY());
				}
			}
		}
	}

	@Test
	public void testClearField() {
		Cell cell1 = field.getCell(START_POINT+2, START_POINT+3);
		field.setLife(cell1);
		
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (!cell.isDead()){
					assertTrue("There is at least one non DEAD cell", cell.getState() != CellState.DEAD);
				}
			}
		}
		
		field.clearField();
		
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (!cell.isDead()){
					fail("there is at leas one non DEAD cell. " +
							"" + cell.getState() + ", x: " + cell.getX() + ", y: " + cell.getY());
				}
			}
		}
	}
	@Test
	public void testRandom() {
		field.clearField();
		field.createRandomLiveCells(5);
		int counter = 0;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (cell.isAlive()){
					counter++;
				}
			}
		}
		assertTrue("There are " + counter + " LIFE cells", counter == 5);
	}
	
	@Test
	public void testUpdateField(){
		field.clearField();
		
		Cell cell1 = field.getCell(ROWS-1, START_POINT+1);
		field.setLife(cell1);
		cell1 = field.getCell(ROWS-1, COLS-1);
		field.setLife(cell1);
		cell1 = field.getCell(START_POINT, COLS-1);
		field.setLife(cell1);
		cell1 = field.getCell(START_POINT+1, COLS-1);
		field.setLife(cell1);
		
		int count = 0;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (cell.isBorn()){
					count++;
				}
			}
		}
		assertTrue("There are " + count + " fresh born cells", count == 4);
		
		field.updateChanges();
		
		count = 0;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (cell.isAlive()){
					count++;
				}
			}
		}
		
		assertTrue("There are " + count + " life cells", count == 4);
	}

	@Test
	public void testgetExactlyThreeNeighbours(){
		field.clearField();
		Cell cell = field.getCell(ROWS-1, COLS-1);
		field.setLife(cell);
		cell = field.getCell(START_POINT, COLS-1);
		field.setLife(cell);
		cell = field.getCell(START_POINT+1, COLS-1);
		field.setLife(cell);
		
		field.updateChanges();
		cell = field.getCell(START_POINT, START_POINT);
		int neighbours = field.getNeighbours(cell);
		assertTrue(neighbours + " Neighbours", neighbours == 3);
	}
	
	@Test
	public void testgetExactlyTwoNeighbours(){
		field.clearField();
		Cell cell = field.getCell(ROWS-1, START_POINT);
		field.setLife(cell);
		cell = field.getCell(ROWS-1, START_POINT+1);
		field.setLife(cell);
		
		field.updateChanges();
		cell = field.getCell(START_POINT, START_POINT);
		int neighbours = field.getNeighbours(cell);
		assertTrue(neighbours + " Neighbours", neighbours == 2);
	}
	
	@Test
	public void testgetMoreThanThreeNeighbours(){
		field.clearField();
		Cell cell = field.getCell(START_POINT, START_POINT);
		field.setLife(cell);
		cell = field.getCell(ROWS-1, START_POINT);
		field.setLife(cell);
		cell = field.getCell(ROWS-2, START_POINT);
		field.setLife(cell);
		cell = field.getCell(START_POINT, COLS-1);
		field.setLife(cell);
		
		field.updateChanges();
		cell = field.getCell(ROWS-1, COLS-1);
		int neighbours = field.getNeighbours(cell);
		assertTrue(neighbours + " Neighbours", neighbours > 3);
	}

}
