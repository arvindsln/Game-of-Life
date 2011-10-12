package tpavels.gol.field.impl;

import java.util.Random;

import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;


public class FieldImpl implements Field {

	private int cells = 0; // life cells on the field
	private Cell[][] field = null;

	/**
	 * Populate field with new dead cells
	 * @see #createField()
	 */
	public FieldImpl() {
		this.createField();
	}

	@Override
	public void clearField() {
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				field[i][j].setDead();
			}
		}
		cells = 0;
	}

	@Override
	public void createField() {
		field = new Cell[ROWS][COLS];
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				field[i][j] = new CellImpl(i, j);
			}
		}
	}

	@Override
	public void createRandomLiveCells() {
		this.createRandomLiveCells(NUMBER_RANDOM_CELLS);
	}

	@Override
	public void createRandomLiveCells(int cellsToCreate) {

		if (isCellLimit(cellsToCreate)) return;

		if (cellsToCreate <= 0) cellsToCreate = NUMBER_RANDOM_CELLS;
		Random randomXGenerator = new Random();
		Random randomYGenerator = new Random();
//		int max = Math.min(ROWS,COLS);
		int randomX, randomY;
		Cell cell = null;
		for (int idx = 1; idx <= cellsToCreate; idx++){
			do {
				randomX = randomXGenerator.nextInt(ROWS);
				randomY = randomYGenerator.nextInt(COLS);
				cell = field[randomX][randomY];
			} while (cell.isAlive());
			cells++;
			field[randomX][randomY].setLife();
		}
	}

	@Override
	public Cell getCell(int x, int y) {
		boolean noBordersAround  = (x > START_POINT && x < ROWS-1) 
				&& (y > START_POINT && y < COLS-1);
		if (noBordersAround){
			return field[x][y];
		}
		if (x < START_POINT) x = ROWS - 1;
		if (y < START_POINT) y = COLS - 1;

		if (x >= ROWS) x = START_POINT;
		if (y >= COLS) y = START_POINT;

		return field[x][y];
	}

	public int getAliveCells() {
		return cells;
	}

	public int getNeighbours(Cell cell) {
		int x = cell.getX();
		int y = cell.getY();
		int n = 0;
		if (isNeighbour(this.getCell(x-1, y))) n++;
		if (isNeighbour(this.getCell(x-1, y+1))) n++;
		if (isNeighbour(this.getCell(x, y+1))) n++;
		if (isNeighbour(this.getCell(x+1, y+1))) n++;
		if (isNeighbour(this.getCell(x+1, y))) n++;
		if (isNeighbour(this.getCell(x+1, y-1))) n++;
		if (isNeighbour(this.getCell(x, y-1))) n++;
		if (isNeighbour(this.getCell(x-1, y-1))) n++;
		return n;
	}

	@Override
	public FieldIter<Cell> iterator() {
		return new FieldIterImpl<Cell>(this);
	}

	@Override
	public void setDead(Cell cell) {
		field[cell.getX()][cell.getY()].setDying();
	}

	@Override
	public void setLife(Cell cell) {
		field[cell.getX()][cell.getY()].setBorn();
	}

	@Override
	public String toString() {
		StringBuffer sbuf = new StringBuffer();
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				switch(this.field[i][j].getState()){
				case toBeDEAD:
					sbuf.append(toBeDEAD_CELL_DRAW);
					break;
				case toBeLIFE:
					sbuf.append(toBeLIFE_CELL_DRAW);
					break;
				case DEAD:
					sbuf.append(DEAD_CELL_DRAW);
					break;
				case LIFE:
					sbuf.append(LIFE_CELL_DRAW);
					break;
				}
			}
			sbuf.append("\n");
		}
		return sbuf.toString();
	}
	
	@Override
	public boolean updateChanges() {
		boolean updated = false;
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				switch(field[i][j].getState()){
				case toBeDEAD:
					field[i][j].setDead();
					cells--;
					updated = true;
					break;
				case toBeLIFE:
					field[i][j].setLife();
					cells++;
					updated = true;
					break;
				case DEAD:
					// do nothing
					break;
				case LIFE:
					// do nothing
					break;
				}
			}
		}
		return updated;
	}

	/**
	 * No more then ~50% of field can be revived
	 * @param cellsToCreate number of random cells to create
	 * @return true is there is no place to revive that number of cells
	 */
	private boolean isCellLimit(int cellsToCreate) {
		int canHoldCells = (COLS-1) * (ROWS-1);
		// no more then field can hold
		if (cellsToCreate >= canHoldCells) {
			System.err.println("Field cannot revive more cells. There are " 
					+ cellsToCreate + ", but field " );
			return true;
		}
		// no more then 50% of field
		float cellPercent = ((float)cells / canHoldCells);
		if (cellPercent > 0.5) return true;

		return false;
	}

	/**
	 * @param cell to check
	 * @return true if the cell was alive in the last generation
	 */
	private boolean isNeighbour(Cell cell) {
		return cell.isAlive() || cell.isDying();
	}
	
}
