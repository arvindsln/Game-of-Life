package tpavels.gol.core;

import tpavels.gol.constants.Constants;
import tpavels.gol.field.Field;
import tpavels.gol.field.FieldIter;

public interface Core extends Constants{
	
	/**
	 * Instantiate {@link Field}, {@link FieldIter}, 
	 * Start main loop thread and sets game to pause
	 */
	public void startup();
	
	/**
	 * Starts life generation
	 */
	public void start();
	
	/**
	 * Pauses main loops
	 */
	public void pause();
	
	/**
	 * Step-by-step life generation
	 */
	public void step();
	
	/**
	 * Revives {@link Constants#NUMBER_RANDOM_CELLS} cells on the random location.
	 * @see Field#createRandomLiveCells()
	 */
	public void random();
	
	/**
	 * Revives N number of cells on the random location.
	 * @param n number of random cells to revive
	 * @see Field#createRandomLiveCells(int)
	 */
	public void random(int n);
	
	/**
	 * Clears field, reset generation counter and sets main loop to pause.
	 */
	public void reset();
	
}
