package tpavels.gol.test;

import junit.framework.TestCase;
import tpavels.gol.constants.Constants;
import tpavels.gol.field.Cell;
import tpavels.gol.field.impl.FieldImpl;

public class FieldImplTest extends TestCase implements Constants {

	FieldImpl field;

	public void setUp() throws Exception {
		this.field = new FieldImpl();
	}

	public void testCreateField(){
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (!cell.isDead()){
					fail("There is at leas one non dead cell. " +
							"" + cell.getState() + ", x: " + cell.getRow() + ", y: " + cell.getColumn());
				}
			}
		}
	}

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
					fail("There is at least one non DEAD cell. " +
							"" + cell.getState() + ", x: " + cell.getRow() + ", y: " + cell.getColumn());
				}
			}
		}
	}

	public void testRandom() {
		field.clearField();
		field.createRandomLifeCells(5);
		int counter = 0;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				Cell cell = field.getCell(i, j);
				if (cell.isAlive()){
					counter++;
				}
			}
		}
		assertTrue("There are " + counter + " LIFE cells! Have to be only 5!", counter == 5);
	}

	public void testAliveCells() {
		field.clearField();
		field.createRandomLifeCells(4);
		assertTrue("There have to be only 4 alive cells!", field.getNumberOfAliveCells() == 4);
	}

	public void testNextGenerationOneCellAllisDead() {
		field.clearField();
		field.createRandomLifeCells(1);
		assertTrue("There have to be only one cell!", field.getNumberOfAliveCells() == 1);
		field.nextGeneration();
		assertTrue("Not all cells are dead!", field.getNumberOfAliveCells() == 0);
	}

	public void testNextGenerationThreeCellNewBorn() {
		field.clearField();
		field.addAliveCell(1, 1);
		field.addAliveCell(1, 2);
		field.addAliveCell(2, 1);
		assertTrue("There have to be only three cells!", field.getNumberOfAliveCells() == 3);
		field.nextGeneration();
		assertTrue("New cell is not born! There have to be only 4 cells on the filed!",
				field.getNumberOfAliveCells() == 4);
		assertTrue("This cell is not newbown!", field.getCell(2, 2).isAlive());
	}

	public void testNextGenerationOneDiesFourBorn() {
		field.clearField();
		field.addAliveCell(1, 1);
		field.addAliveCell(1, 3);
		field.addAliveCell(2, 2);
		field.addAliveCell(3, 1);
		field.addAliveCell(3, 3);
		assertTrue("There must be only five cells!", field.getNumberOfAliveCells() == 5);
		field.nextGeneration();
		assertTrue("All previous cell have to be dead, four must be bourn", field.getNumberOfAliveCells() == 4);
		assertTrue("One - has to be alive", field.getCell(1, 2).isAlive());
		assertTrue("Two - has to be alive", field.getCell(2, 3).isAlive());
		assertTrue("Three - has to be alive", field.getCell(3, 2).isAlive());
		assertTrue("Four - has to be alive", field.getCell(2, 1).isAlive());
	}



}
