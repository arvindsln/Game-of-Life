package tpavels.gol.field;

import tpavels.gol.constants.Constants;

public interface Field extends Iterable<Cell>, Constants{
	
	/**
	 * Set all cells to dead
	 */
	public void clearField();
	
	/**
	 * Create new cells objects on the {@link Field} and set them to dead
	 */
	public void createField();
	
	/**
	 * @return total number of alive cells
	 */
	public int getAliveCells();
	
	/**
	 * Get {@link Cell} from board. Left and right edges of the {@link Field} are stitched together, 
	 * and the top and bottom edges also. 
	 * 
	 * @param x row (start from -1 to {@link Constants#ROWS}, including)
	 * @param y column (start from -1 to {@link Constants#COLS}, including)
	 * @return Cell
	 */
	public Cell getCell(int x, int y);
	
	/**
	 * Set {@link Cell} to dying
	 * @param cell Object to set
	 * @see Cell#setDying()
	 */
	public void setDead(Cell cell);
	
	/**
	 * Set {@link Cell} to born
	 * @param cell Object to set
	 * @see Cell#setBorn()
	 */
	public void setLife(Cell cell);
	
	/**
	 * Updates all changes to {@link Field}. 
	 * Sets all dying cells to dead one,
	 * all born to alive
	 * @return true if at least one cell is changed
	 */
	public boolean updateChanges();

	/**
	 * Create cells at random location.
	 * Number of cells is defined as constant: {@link Constants#NUMBER_RANDOM_CELLS}
	 */
	public void createRandomLiveCells();
	
	/**
	 * Create cells at random location.
	 * @param n number of cell to revive
	 */
	public void createRandomLiveCells(int n);
	
	/**
	 * @return an iterator over the field of type {@link Cell}.
	 */
	public FieldIter<Cell> iterator();
	
	/**
	 * Finds all neighbours around one {@link Cell}
	 * @param cell 
	 * @return number of neighbours around this cell
	 */
	public int getNeighbours(Cell cell);
	
}
