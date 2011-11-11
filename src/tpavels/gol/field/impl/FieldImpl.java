package tpavels.gol.field.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import tpavels.gol.field.Cell;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;


public class FieldImpl implements Field {

	private static final int CAN_HOLD_CELLS = (COLS) * (ROWS);
	private Cell[][] field = null;
	private int generation = 0;
	private Set<Cell> lifecells = null;

	/**
	 * Populate field with new dead cells
	 * @see #createField()
	 */
	public FieldImpl() {
		this.createField();
		lifecells = new HashSet<Cell>();
	}

	@Override
	public void clearField() {
		for(int row = 0; row < ROWS; row++){
			for(int column = 0; column < COLS; column++){
				field[row][column].setDead();
				field[row][column].resetNeighbourCounter();
			}
		}
		lifecells.clear();
		generation = 0;
	}

	@Override
	public void createRandomLifeCells() {
		createRandomLifeCells(NUMBER_RANDOM_CELLS);
	}

	@Override
	public void createRandomLifeCells(int cellsToCreate) {

		if (isCellLimit(cellsToCreate)) {
			return;
		}
		if (cellsToCreate < 0) {
			cellsToCreate = NUMBER_RANDOM_CELLS;
		}
		Random randomXGenerator = new Random();
		Random randomYGenerator = new Random();
		int randomRow, randomColumn;
		Cell cell = null;
		for (int idx = 1; idx <= cellsToCreate; idx++){
			do {
				randomRow = randomXGenerator.nextInt(ROWS);
				randomColumn = randomYGenerator.nextInt(COLS);
				cell = field[randomRow][randomColumn];
			} while (cell.isAlive());
			addCell(cell);
		}

	}

	@Override
	public Set<Cell> getAliveCells() {
		return lifecells;
	}

	@Override
	public int getNumberOfAliveCells() {
		return lifecells.size();
	}

	@Override
	public FieldIter<Cell> iterator() {
		return new FieldIterImpl<Cell>(this);
	}

	@Override
	public void addAliveCell(int row, int column) {
		Cell cell = getCell(row, column);
		addCell(cell);
	}

	@Override
	public void removeAliveCell(int row, int column) {
		Cell cell = getCell(row, column);
		removeCell(cell);
	}

	@Override
	public int getGeneration() {
		return generation;
	}

	@Override
	public boolean nextGeneration() {
		/*
		 * 1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
		 * 2. Any live cell with two or three live neighbours lives on to the next generation.
		 * 3. Any live cell with more than three live neighbours dies, as if by overcrowding.
		 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
		 */

		int neighbours = 0;
		FieldIter<Cell> fieldIter = this.iterator();
		if(fieldIter.isEmpty()) {
			return false;
		}

		while(fieldIter.hasNext()){
			Cell cell = fieldIter.next();
			neighbours = cell.getNeighbour();
			if (cell.isDead()) {
				if (neighbours == 3) {
					setLife(cell);
				}
			} else {
				if (neighbours < 2) {
					setDead(cell);
				} else if (neighbours  > 3) {
					setDead(cell);
				}
			}
		}
		boolean isNewGeneration = updateChanges();
		if (isNewGeneration){
			generation ++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get {@link Cell} from board. Left and right edges of the {@link Field} are stitched together,
	 * and the top and bottom edges also.
	 *
	 * @param row field row
	 * @param column field column
	 * @return Cell
	 */
	public Cell getCell(int row, int column) {
		return field[row][column];
	}

	/**
	 * Set {@link Cell} to dying
	 * @param cell Object to set
	 * @see Cell#setDying()
	 */
	public void setDead(Cell cell) {
		field[cell.getRow()][cell.getColumn()].setDying();
	}

	/**
	 * Set {@link Cell} to born
	 * @param cell Object to set
	 * @see Cell#setBorn()
	 */
	public void setLife(Cell cell) {
		field[cell.getRow()][cell.getColumn()].setBorn();
	}

	@Override
	public String toString() {
		StringBuffer sbuf = new StringBuffer();
		for(int row = 0; row < ROWS; row++){
			for(int column = 0; column < COLS; column++){
				switch(this.field[row][column].getState()){
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

	/**
	 * Makes a cell alive and updates its neighbours, incrementing neighbour counter by 1
	 * @param cell to make alive again
	 * @see #addNeighbours(Cell)
	 */
	private void addCell(Cell cell) {
		cell.setLife();
		addNeighbours(cell);
		lifecells.add(cell);
	}

	/**
	 * Kills cells and updates its neighbours, decrementing neighbour counter by 1
	 * @param cell
	 * @see #removeNeighbours(Cell)
	 */
	private void removeCell(Cell cell) {
		cell.setDead();
		removeNeighbours(cell);
		lifecells.remove(cell);
	}

	/**
	 * When a cell is reborn all its neighbours cell neighbour counter need to be incremented by 1
	 * @param cell that was born in the neighborhood
	 */
	private void addNeighbours(Cell cell) {

		Iterator<Cell> neighbours = cell.neighbourCells().iterator();
		while (neighbours.hasNext()) {
			Cell cellNeighbour = neighbours.next();
			cellNeighbour = getCell(cellNeighbour.getRow(), cellNeighbour.getColumn());
			cellNeighbour.addNeighbour();
		}

	}


	/**
	 * When a cell is dead all its neighbours cell neighbour counter need to be decremented by 1
	 * @param cell
	 */
	private void removeNeighbours(Cell cell) {
		Iterator<Cell> iterator = cell.neighbourCells().iterator();
		while(iterator.hasNext()){
			Cell next = iterator.next();
			next = getCell(next.getRow(), next.getColumn());
			next.removeNeighbour();
		}
	}

	/**
	 * Creates new cells objects on the field and set them to dead
	 */
	private void createField() {
		field = new Cell[ROWS][COLS];
		for(int row = 0; row < ROWS; row++){
			for(int column = 0; column < COLS; column++){
				field[row][column] = new CellImpl(row, column);
			}
		}
	}

	/**
	 * RandomLifeCells helper, checks limits
	 * @param cellsToCreate number of random cells to create
	 * @return true is there is no place to revive that number of cells
	 */
	private boolean isCellLimit(int cellsToCreate) {
		int canHold = CAN_HOLD_CELLS - getNumberOfAliveCells();
		// no more then field can hold
		if (cellsToCreate > canHold) {
			System.err.println("Field cannot revive more cells. There are "
					+ getNumberOfAliveCells() + " alive cell; want to create "
					+ cellsToCreate + " more cells, but field can hold "+ canHold );
			setAlltoLife();
			return true;
		}
		return false;
	}


	/**
	 * RandomLifeCells helper, revive all remaining cells on the field
	 */
	private void setAlltoLife() {
		for(int row = 0; row < ROWS; row++){
			for(int column = 0; column < COLS; column++){
				if (field[row][column].isDead()){
					addCell(field[row][column]);
				}
			}
		}
	}

	/**
	 * Updates all changes to the field.
	 * Sets all dying cells to dead one,
	 * all born to alive
	 * @return true if at least one cell is changed
	 */
	private boolean updateChanges() {
		boolean updated = false;
		for(int row = 0; row < ROWS; row++){
			for(int column = 0; column < COLS; column++){
				Cell currentCell = field[row][column];
				switch(currentCell.getState()){
				case toBeDEAD:
					removeCell(currentCell);
					updated = true;
					break;
				case toBeLIFE:
					addCell(currentCell);
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

}
