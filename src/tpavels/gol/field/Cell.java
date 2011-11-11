package tpavels.gol.field;

import java.util.Set;

import tpavels.gol.constants.Constants;


public interface Cell extends Constants {

	/**
	 * @return {@link Cell} column number (start from 0)
	 */
	public int getRow();

	/**
	 * @return {@link Cell} row number (start from 0)
	 */
	public int getColumn();

	/**
	 * toBeDEAD means, that cell was alive and will be dead after field overall update,
	 * the same is with toBeLIFE state
	 * @return cell state
	 * @see Field#updateChanges()
	 */
	public CellState getState();

	/**
	 * Set {@link Cell} to dead
	 */
	public void setDead();

	/**
	 * Set {@link Cell} to alive
	 */
	public void setLife();

	/**
	 * Set {@link Cell} to alive for the next generation,
	 * cell will be alive after the {@link Field} update.
	 * @see Field#updateChanges()
	 */
	public void setBorn();

	/**
	 * Set {@link Cell} to dead for the next generation,
	 * cell will be dead after the {@link Field} update.
	 * @see Field#updateChanges()
	 */
	public void setDying();

	/**
	 * @return true if {@link Cell} is alive
	 */
	public boolean isAlive();

	/**
	 * @return true if {@link Cell} is dead
	 */
	public boolean isDead();

	/**
	 * @return true if {@link Cell} was alive in the last generation,
	 *  but will be dead in the next
	 */
	public boolean isDying();

	/**
	 * @return true if {@link Cell} was dead in the last generation,
	 *  but will be revived in the next
	 */
	public boolean isBorn();

	/**
	 * @return set of all neighbour cells
	 */
	public Set<Cell> neighbourCells();

	/**
	 * Adds new alive neighbour near cell
	 */
	public void addNeighbour();

	/**
	 * Removes one alive neighbour near this cell
	 */
	public void removeNeighbour();

	/**
	 * @return number of alive neighbours
	 */
	public int getNeighbour();

	/**
	 * Sets alive neighbour counter to zero (0)
	 */
	public void resetNeighbourCounter();

}