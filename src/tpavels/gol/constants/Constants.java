package tpavels.gol.constants;

import java.awt.Color;

import tpavels.gol.field.Field;

public interface Constants {
	
	/*************************************************
	 * CORE
	 *************************************************/
	
	/**
	 * Main game loop tick time in milliseconds.
	 */
	public final long TICK = 150L;
	
	
	/*************************************************
	 * FIELD
	 *************************************************/
	
	/**
	 * Number of rows of the game field 
	 */
	public final int ROWS = 150;
	
	/**
	 * Number of columns of the game field 
	 */
	public final int COLS = 250;
	
	/**
	 * Start point of {@link Field}, ({@value}, {@value})
	 */
	public final int START_POINT = 0;
	
	/**
	 * Number of random cells to revive 
	 */
	public static final int NUMBER_RANDOM_CELLS = 1000;
	
	
	/**********************************************
	 * GUI
	 **********************************************/
	
	/**
	 * Dead cell colour on the field
	 */
	public static final Color DEAD_COLOUR = Color.BLACK;
	
	/**
	 * Alive cell colour on the field 
	 */
	public static final Color LIFE_COLOUR = Color.GREEN;

	/**
	 * Main game frame title
	 */
	public static final String FRAME_TITLE = "Game of Life ver 0.3.3";
	
	/**
	 * Main frame height
	 */
	public static final int FRAME_HEIGHT = 677; // ROWS
	
	/**
	 * Main frame width
	 */
	public static final int FRAME_WIDTH = 1016; // COLS
	
	/**
	 * Border width ({@value}) in pixels for all components
	 */
	public static final int BORDER = 5;
	
	/**
	 * Button preferred width
	 */
	public static final int BUTTON_WIDTH = 100;
	
	/**
	 * Button preferred height
	 */
	public static final int BUTTON_HEIGHT = 30;
	
	/**
	 * Cell size
	 */
	public static final int CELL_SIZE = 4;
	
	/**
	 * Image with current generation of cells width
	 */
	public static final int IMAGE_WIDTH = COLS * CELL_SIZE;
	/**
	 * Image with current generation of cells height
	 */
	public static final int IMAGE_HEIGHT = ROWS * CELL_SIZE;
	

	/*****************************************
	 * STATE 
	 *****************************************/
	
	enum GameState{
		RUN,
		PAUSE,
		STEP,
		RANDOM,
	}
	
	enum CellState{
		DEAD,
		LIFE,
		/**
		 * This cell will be dead in the next generation
		 */
		toBeDEAD,
		/**
		 * This cell will born in the next generation
		 */
		toBeLIFE,
	}
	
	
	/*****************************************
	 * DEBUG 
	 *****************************************/
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
