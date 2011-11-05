package tpavels.gol.field;

import java.util.Set;

import tpavels.gol.constants.Constants;

public interface Field extends Iterable<Cell>, Constants{
	
	/**
	 * Set all cells to dead
	 */
	public void clearField();
	
	/**
	 * @return total number of alive cells
	 */
	public int getNumberOfAliveCells();
	
	/**
	 * Create cells at random location.
	 * Number of cells is defined as constant: {@link Constants#NUMBER_RANDOM_CELLS}
	 */
	public void createRandomLifeCells();
	
	/**
	 * Create cells at random location.
	 * @param n number of cell to revive
	 */
	public void createRandomLifeCells(int n);
	
	/**
	 * @return an iterator over the field of type {@link Cell}.
	 */
	public FieldIter<Cell> iterator();
	
	/**
	 * @return Set of all alive Cells on the field
	 */
	public Set<Cell> getAliveCells();

	/**
	 * There are two possibilities when next generation couldn't be generated:
	 * </br> 1) the field is empty
	 * </br> 2) new generation is the same as previous, so it's the end
	 * @return true if the next generation is successfully generated. 
	 */
	public boolean nextGeneration();
	
	
	/**
	 * Adds alive cell to field, doesn't check if this cell is already alive
	 * @param row 
	 * @param column
	 */
	public void addAliveCell(int row, int column);

	/**
	 * Removes alive cell from field, doesn't check if this cell is already dead
	 * @param row 
	 * @param column
	 */
	public void removeAliveCell(int row, int column);
	
	/**
	 * @return number of current generation
	 */
	public int getGeneration();
	
	/**
	 * @param row
	 * @param column
	 * @return cell object from the field
	 */
	public Cell getCell(int row, int column);
	
}
