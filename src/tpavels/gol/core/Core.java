package tpavels.gol.core;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Field;

public interface Core extends Constants{
	
	/**
	 * Instantiate and
	 * starts main loop thread and sets game to pause
	 */
	public void startup();
	
	/**
	 * Starts life generation
	 */
	public void start();
	
	/**
	 * Pauses main loop
	 */
	public void pause();
	
	/**
	 * Step-by-step life generation
	 */
	public void step();
	
	/**
	 * Revives {@link Constants#NUMBER_RANDOM_CELLS} cells on the random location.
	 * @see Field#createRandomLifeCells()
	 */
	public void random();
	
	/**
	 * Revives N number of cells on the random location.
	 * @param n number of random cells to revive
	 * @see Field#createRandomLifeCells(int)
	 */
	public void random(int n);
	
	/**
	 * Clears field, reset generation and all other counters and sets main loop to pause.
	 */
	public void reset();
	
}
