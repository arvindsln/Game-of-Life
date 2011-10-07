package tpavels.gol.constants;

import tpavels.gol.field.Field;

public interface Constants {
	
	/**
	 * Number of rows of game field
	 */
	public final int ROWS = 21;
	/**
	 * Number of columns of game field, it is equals to {@link #ROWS}
	 */
	public final int COLS = ROWS;
	
	/**
	 * Start point of {@link Field}, i.e. ROW = COLUMN (START_POINT, START_POINT)
	 */
	public final int START_POINT = 0;
	
	/**
	 * Number of random cells to revive at default
	 */
	public static final int NUMBER_RANDOM_CELLS = 50;
	
	/**
	 * Main game loop tick time in milliseconds.
	 */
	public final long TICK = 600L;
	
	/**
	 * String representation of LIFE cell
	 */
	public static final String LIFE_CELL_DRAW = "* ";
	/**
	 * String representation of DEAD cell
	 */
	public static final String DEAD_CELL_DRAW = "  ";
	
	// for debug purposes
	public static final String toBeDEAD_CELL_DRAW = "- ";
	public static final String toBeLIFE_CELL_DRAW = "+ ";
	
	/**
	 * Main game loop states
	 */
	enum GameState{
		RUN,
		PAUSE,
	}
	
	/**
	 * Cell states, default is DEAD
	 */
	enum CellState{
		DEAD,
		LIFE,
		toBeDEAD,
		toBeLIFE,
	}
	
	enum PrintLevel{
		FULL,
		FIELD,
		EMPTY,
	}

}
