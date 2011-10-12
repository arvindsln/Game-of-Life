package tpavels.gol.constants;

import tpavels.gol.field.Field;

public interface Constants {
	
	/**
	 * Number of rows of game field
	 */
	public final int ROWS = 50;
	/**
	 * Number of columns of game field, it is equals to {@link #ROWS}
	 */
	public final int COLS = 160;
	
	/**
	 * Start point of {@link Field}, i.e. ROW = COLUMN (START_POINT, START_POINT)
	 */
	public final int START_POINT = 0;
	
	/**
	 * Number of random cells to revive at default
	 */
	public static final int NUMBER_RANDOM_CELLS = 100;
	
	/**
	 * Main game loop tick time in milliseconds.
	 */
	public final long TICK = 10L;
	
	/**
	 * GUI
	 */
	public static final int CELL_SIZE_COL = 5;
	public static final int CELL_SIZE_ROW = 5;
	public static final int BORDER = 5;
	public static final String FRAME_TITLE = "Game of Life ver 0.2";
	
	enum GameState{
		RUN,
		PAUSE,
	}
	
	enum CellState{
		DEAD,
		LIFE,
		toBeDEAD,
		toBeLIFE,
	}
	
	
	//for debug purposes
	enum PrintLevel{
		FULL,
		FIELD,
		EMPTY,
	}
	public static final String LIFE_CELL_DRAW = "* ";
	public static final String DEAD_CELL_DRAW = "  ";
	public static final String toBeDEAD_CELL_DRAW = "- ";
	public static final String toBeLIFE_CELL_DRAW = "+ ";
}
