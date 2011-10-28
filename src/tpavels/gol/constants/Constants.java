package tpavels.gol.constants;

import java.awt.Color;

import tpavels.gol.field.Field;

public interface Constants {
	
	/*************************************************
	 * CORE
	 *************************************************/
	
	/**
	 * Main game loop delay time in milliseconds ({@value})
	 */
	public final long TICK = 150L; // 10L
	
	
	/*************************************************
	 * FIELD
	 *************************************************/
	
	
	// ROWS:COLS = 3:5
	
	/**
	 * Number of rows of the game field ({@value})
	 */
	public final int ROWS = 160; // default:150   

	/**
	 * Number of columns of the game field ({@value})
	 */
	public final int COLS = (ROWS/3) * 5; // default:250  
	
	/**
	 * Start point of {@link Field}, ({@value}, {@value})
	 */
	public final int START_POINT = 0;
	
	/**
	 * Number of random cells to revive ({@value})
	 */
	public static final int NUMBER_RANDOM_CELLS = 1000; // 1000
	
	
	/**********************************************
	 * GUI
	 **********************************************/
	
	/**
	 * Main game frame title ({@value})
	 */
	public static final String FRAME_TITLE = "Game of Life ver 0.3.6";
	
	/**
	 * Dead cell colour on the field
	 */
	public static final Color DEAD_COLOUR = Color.BLACK;
	
	/**
	 * Alive cell colour on the field 
	 */
	public static final Color LIFE_COLOUR = Color.GREEN;

	/**
	 * Cell size
	 */
	public static final int CELL_SIZE_INT = 4; // default:4
	public static final double CELL_SIZE_DRAW = 3.74; 
	
	/**
	 * Image with current generation of cells width ({@value})
	 */
	public static final int IMAGE_WIDTH = COLS * CELL_SIZE_INT; // default:1000
	/**
	 * Image with current generation of cells height ({@value})
	 */
	public static final int IMAGE_HEIGHT = ROWS * CELL_SIZE_INT; // default:600
	
	/**
	 * Button preferred width ({@value})
	 */
	public static final int BUTTON_WIDTH = 100;
	
	/**
	 * Button preferred height ({@value})
	 */
	public static final int BUTTON_HEIGHT = 30;
	
	/**
	 * Border width ({@value}) in pixels for all components
	 */
	public static final int BORDER = 3;
	
	/**
	 * Main frame height ({@value})
	 */
	public static final int FRAME_HEIGHT = IMAGE_HEIGHT + BUTTON_HEIGHT + BORDER*2 + 37; // ROWS default:677 
	 															//37 is magic number?
	
	/**
	 * Main frame width ({@value})
	 */
	public static final int FRAME_WIDTH = IMAGE_WIDTH + BORDER*2 + 6; // COLS default:1016
																//6 is magic number?
	
	
	
	

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
